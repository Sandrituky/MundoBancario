package es.eoi.mundobancario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.DtoEntity;
import es.eoi.mundobancario.service.CuentaService;
import es.eoi.mundobancario.service.MovimientoService;

@RestController
public class CuentasController {
	
	@Autowired
	CuentaService cuentaService;
	
	@Autowired
	MovimientoService movService;
	
	//Devuelve un listado con todas las cuentas (Toda la información y datos del cliente).
	@GetMapping("cuentas")	
	public ResponseEntity <List <DtoEntity> > findAllCuentas() {
		return ResponseEntity.ok(cuentaService.findAll());
	}
	
	
	
	//Devuelve un listado de las cuentas con saldo negativo (Toda la información y datos del cliente).
	@GetMapping("cuentas/deudoras")	
	public ResponseEntity <List <DtoEntity> > findCuentasSaldoNeg() {
		return ResponseEntity.ok(cuentaService.findCuentasSaldoNeg());
	}
	
	
	

	//Devuelve la cuenta solicitada (Toda la información y datos del cliente).
	@GetMapping("cuentas/{id}")	
	public ResponseEntity<DtoEntity> findClienteById(@PathVariable Integer id) {
		return ResponseEntity.ok(cuentaService.findCuentaById(id));
	}	
	
	
	//Devuelve los movimientos de la cuenta solicitada. (incluirá el tipo de movimiento)
	@GetMapping("cuentas/{id}/movimientos")	
	public ResponseEntity<List<DtoEntity>> findMovimientosByCuentaId(@PathVariable Integer id) {
		return ResponseEntity.ok(movService.findAllMovsByCuentaID(id));
	}	
	
	

}
