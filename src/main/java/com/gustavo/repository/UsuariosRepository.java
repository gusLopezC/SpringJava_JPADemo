package com.gustavo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
