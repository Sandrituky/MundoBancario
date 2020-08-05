package es.eoi.mundobancario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteBasicDto;
import es.eoi.mundobancario.dto.ClienteCreateDto;
import es.eoi.mundobancario.dto.ClienteLoginDto;
import es.eoi.mundobancario.dto.ClienteUpdateEmailDto;
import es.eoi.mundobancario.dto.DtoEntity;
import es.eoi.mundobancario.service.ClienteService;

@RestController
public class ClientesController {
	
	@Autowired
	ClienteService service;
	
	
	//Devuelve un listado con todos los clientes (información básica sin la contraseña).
	@GetMapping("clientes")	
	public ResponseEntity<List<DtoEntity>>findAllClientes() {
		try {
			return ResponseEntity.ok(service.findAll());
		}catch (Exception e) {
			return new ResponseEntity<List<DtoEntity>>(HttpStatus.NOT_FOUND);
		}
	}

	//Devuelve el cliente solicitado (información básica sin la contraseña).
	@GetMapping("clientes/{id}")	
	public ResponseEntity<DtoEntity> findClienteById(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(service.findClienteById(id));
		}catch (Exception e) {
			return new ResponseEntity<DtoEntity>(HttpStatus.NOT_FOUND);
		}
	}	
	
	
	

	
	
	
	//Devuelve las cuentas del cliente solicitado (información básica sin la contraseña).
	@GetMapping("clientes/{id}/cuentas")	
	public ResponseEntity<DtoEntity> findCuentasByClienteId(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(service.findCuentasByClienteId(id));
		} catch (Exception e) {
			return new ResponseEntity<DtoEntity>(HttpStatus.NOT_FOUND);
		}
	}	
	
	

	
	//Modifica el campo email del cliente solicitado
	@PutMapping("clientes/{id}")
	public ResponseEntity<String> updateClienteEmail(@PathVariable Integer id, @RequestBody ClienteUpdateEmailDto clienteDto) {
		service.updateClienteEmail(clienteDto.getEmail(), id);
		return new ResponseEntity<String>(HttpStatus.ACCEPTED);

	}
	
	//Añadimos un nuevo cliente
	@PostMapping("clientes")
	public ResponseEntity<String> addCliente(@RequestBody ClienteCreateDto clienteDto) {
			try {
				service.addCliente(clienteDto);
				return new ResponseEntity<String>(HttpStatus.CREATED);
			} catch (Exception e) {
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}
			
	  }

	
	
	
	
}
