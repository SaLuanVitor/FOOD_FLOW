package com.example.springboot.config;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.example.springboot.models.Funcionarios;
import com.example.springboot.repositories.FuncRepository;

// @Service
// public class CustomUserDetailsService implements FuncRepository {

// @Autowired
// private FuncRepository funcionarioRepository;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

// @Autowired
// private FuncRepository funcionarioRepository;

// @Override
// public UserDetails loadUserByUsername(String email) throws
// UsernameNotFoundException {
// Funcionarios funcionario = funcionarioRepository.findByEmail(email)
// .orElseThrow(() -> new UsernameNotFoundException("Funcionário não
// encontrado"));

// // Mapeia o perfil para o Spring Security
// GrantedAuthority authority = new SimpleGrantedAuthority(
// "ROLE_" + funcionario.getPerfil().getDescricao().toUpperCase());

// return User.builder()
// .username(funcionario.getEmail())
// .password(funcionario.getSenha())
// .authorities(authority)
// .build();
// }
// }
// }
