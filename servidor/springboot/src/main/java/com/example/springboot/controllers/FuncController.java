package com.example.springboot.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.springboot.dto.LoginDto;
import com.example.springboot.models.Funcionarios;
import com.example.springboot.models.Perfil;
import com.example.springboot.repositories.FuncRepository;
import com.example.springboot.repositories.PerfRepository;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class FuncController {

    @Autowired
    FuncRepository funcRepository;
    @Autowired
    PerfRepository perfilRepository;
    private Funcionarios funcionarioLogado;

    @GetMapping("/funcionarios") // rota
    public String getAllFuncionariosPage(final Model model) {
        List<Funcionarios> funcionarios = funcRepository.findAll();
        model.addAttribute("funcionarios", funcionarios);
        return "funcionarios/funcionarios"; // html
    }

    @GetMapping("/funcionarios/adicionar")
    public String addNewFuncionario(Model model) {
        Funcionarios funcionario = new Funcionarios();
        List<Perfil> perfis = perfilRepository.findAll();
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("perfis", perfis);
        return "funcionarios/adicionar";
    }

    @PostMapping("/funcionarios/salvar")
    public String saveFuncionario(@ModelAttribute("funcionario") Funcionarios funcionario) {
        funcRepository.save(funcionario);
        return "redirect:/funcionarios";
    }

    @GetMapping("/funcionarios/atualizar/{id}")
    public String updateFuncionario(@PathVariable(value = "id") Long id, Model model) {
        Funcionarios funcionario = funcRepository.findById(id).get();
        List<Perfil> perfis = perfilRepository.findAll();
        model.addAttribute("perfis", perfis);
        model.addAttribute("funcionario", funcionario);
        return "funcionarios/atualizar";
    }

    @GetMapping("/funcionarios/excluir/{id}")
    public String deleteFuncionarioById(@PathVariable(value = "id") Long id) {
        funcRepository.deleteById(id);
        return "redirect:/funcionarios";

    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDto loginDto, HttpSession session) {
        String nome = loginDto.nome();
        String senha = loginDto.senha();

        // Verificar se o nome de usuário e a senha foram fornecidos
        if (nome == null || senha == null || nome.isEmpty() || senha.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nome de usuário e senha devem ser fornecidos.");
        }

        // Buscar o funcionário pelo nome de usuário (nome) no banco de dados
        Optional<Funcionarios> funcionarioOpt = funcRepository.findByNome(nome);

        // Verificar se o funcionário foi encontrado
        if (funcionarioOpt.isPresent()) {
            Funcionarios funcionario = funcionarioOpt.get();
            // Verificar se a senha corresponde
            if (senha.equals(funcionario.getSenha())) {
                // Login bem-sucedido
                funcionarioLogado = funcionario;
                session.setAttribute("logado", nome);
                session.setAttribute("perfil", funcionario.getPerfil()); // Pode ser útil se precisar do objeto Perfil
                session.setAttribute("idPerfil", funcionario.getPerfil().getIdPerfil()); // Atribuindo o idPerfil

                // Verificar o perfil do usuário
                Perfil perfil = funcionario.getPerfil(); // Supondo que exista um método getPerfil() na sua classe
                                                         // Funcionarios
                return ResponseEntity.status(HttpStatus.OK).body(Map.of(
                        "mensagem", "Login bem-sucedido!",
                        "perfil", perfil, // Incluindo o perfil no retorno
                        "funcao", funcionario.getFuncao()));
            } else {
                // Senha incorreta
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Senha incorreta!");
            }
        } else {
            // Funcionário não encontrado
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
    }

    public Funcionarios getFuncionarioLogado() {
        return funcionarioLogado;
    }
}
