package com.projetojpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojpa.entity.Usuario;
import com.projetojpa.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	
	@Autowired
	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscaUsuarioControlId(@PathVariable Long id){
		Usuario usuario = usuarioService.buscaUsuarioId(id);
		if(usuario != null) {
			return ResponseEntity.ok(usuario);
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
	@GetMapping("/")
	public ResponseEntity<List<Usuario>> buscaTodosUsuarioControl(){
		List<Usuario> Usuario = usuarioService.buscaTodoUsuario();
		return ResponseEntity.ok(Usuario);
	}
	
	@PostMapping("/")
	public ResponseEntity<Usuario> salvaUsuarioControl(@RequestBody @Valid Usuario usuario){
		Usuario salvaUsuario = usuarioService.salvaUsuario(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(salvaUsuario);
	}
	
	@PutMapping("/(id)")
	public ResponseEntity<Usuario> alterarUsuarioControl(@PathVariable Long id, @RequestBody @Valid Usuario usuario){
		Usuario altUsuario = usuarioService.alterarUsuario(id, usuario);
		if(altUsuario !=null) {
			return ResponseEntity.ok(usuario);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/(id)")
	public ResponseEntity<Usuario> apagar(@PathVariable Long id){
		boolean apagar = usuarioService.apagarUsuario(id);
		if(apagar) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.notFound().build();
		}
	}
}
