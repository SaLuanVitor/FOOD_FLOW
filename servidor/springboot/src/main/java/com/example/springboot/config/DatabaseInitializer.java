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
            String adminPerfilDescricao = "Administrador";
            String garcomPerfilDescricao = "Garçom";
            String cozinhaPerfilDescricao = "Cozinha";

            // Criar o perfil Administrador
            Perfil perfilAdmin = criarPerfilSeNaoExistir(adminPerfilDescricao);

            // Criar os perfis Garçom e Cozinha
            criarPerfilSeNaoExistir(garcomPerfilDescricao);
            criarPerfilSeNaoExistir(cozinhaPerfilDescricao);

            // Criar o funcionário Admin
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

    private Perfil criarPerfilSeNaoExistir(String descricao) {
        return perfilRepository.findByDescricao(descricao).orElseGet(() -> {
            Perfil perfil = new Perfil();
            perfil.setDescricao(descricao);
            perfilRepository.save(perfil);
            System.out.println("Perfil " + descricao + " criado com sucesso!");
            return perfil;
        });
    }
}
