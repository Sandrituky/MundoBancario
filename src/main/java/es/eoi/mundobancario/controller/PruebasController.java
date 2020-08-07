package es.eoi.mundobancario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.DtoEntity;
import es.eoi.mundobancario.entity.TipoMovimiento;
import es.eoi.mundobancario.service.MovimientoService;
import es.eoi.mundobancario.service.TipoMovimientoService;

@RestController
public class PruebasController {
	
	
	@Autowired
	TipoMovimientoService serviceTipoMov;
	
	@Autowired
	MovimientoService serviceMov;
	
	
	
	@GetMapping("tiposmov")	
	public ResponseEntity <List <DtoEntity> > findAll() {
		return ResponseEntity.ok(serviceTipoMov.findAll());
	}
	
	
	@GetMapping("movimientos")	
	public ResponseEntity <List <DtoEntity> > findAllMovs() {
		return ResponseEntity.ok(serviceMov.findAll());
	}
	

}

