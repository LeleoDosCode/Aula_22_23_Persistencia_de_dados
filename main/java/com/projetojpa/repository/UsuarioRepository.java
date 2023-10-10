package com.projetojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetojpa.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
