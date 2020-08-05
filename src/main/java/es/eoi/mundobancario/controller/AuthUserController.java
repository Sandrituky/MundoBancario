package es.eoi.mundobancario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteBasicDto;
import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.ClienteLoginDto;
import es.eoi.mundobancario.dto.DtoEntity;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.repository.ClienteRepository;
import es.eoi.utils.DtoUtils;


@RestController
public class AuthUserController { //GlobalControllerAdvice
	
	@Autowired
	ClienteRepository repository;
	
	@Autowired
	DtoUtils dtoUtils;


	
	@PostMapping("clientes/login")
	public ResponseEntity<Cliente> login (@RequestBody ClienteLoginDto datosLogin){ //devuelve el usuario logeado
		Cliente cliente = null;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {		    
			try {
				cliente = repository.findByUsuario(authentication.getName());    
			}
			catch (Exception e) {
				System.out.println(e); 
			}
		}
    
		return ResponseEntity.ok(cliente);
	}
}