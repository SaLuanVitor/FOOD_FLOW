package com.example.springboot.config;

import com.example.springboot.models.Funcionarios;
import com.example.springboot.models.Perfil;
import com.example.springboot.repositories.FuncRepository;
import com.example.springboot.repositories.PerfRepository; // Importe o repositório de Perfil

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializer {

    @Autowired
    private FuncRepository funcionariosRepository;

    @Autowired
    private PerfRepository perfilRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            String adminNome = "adminFlow";
            String adminSenha = "floow123";
            String perfilDescricao = "Administrador";

            Perfil perfilAdmin;
            if (perfilRepository.findByDescricao(perfilDescricao).isEmpty()) {

                perfilAdmin = new Perfil();
                perfilAdmin.setDescricao(perfilDescricao);
                perfilAdmin = perfilRepository.save(perfilAdmin);

                System.out.println("Perfil " + perfilDescricao + " criado com sucesso!");
            } else {
                perfilAdmin = perfilRepository.findByDescricao(perfilDescricao).get();

                System.out.println("Perfil " + perfilDescricao + " já existe. Usando perfil existente.");
            }

            if (funcionariosRepository.findByNome(adminNome).isEmpty()) {
                Funcionarios admin = new Funcionarios();
                admin.setNome(adminNome);
                admin.setSenha(adminSenha);
                admin.setFuncao("Administrador");
                admin.setEmail("admin@floow.com");
                admin.setPerfil(perfilAdmin);
                funcionariosRepository.save(admin);

                System.out.println("Funcionário " + adminNome + " criado com sucesso!");
            } else {
                System.out.println("Funcionário " + adminNome + " já existe. Nenhuma ação necessária.");
            }
        };
    }
}
