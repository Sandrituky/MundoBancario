package es.eoi.mundobancario.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteCuentasMovDto;
import es.eoi.mundobancario.dto.DtoEntity;
import es.eoi.mundobancario.service.ClienteService;
import es.eoi.mundobancario.service.PrestamoService;



@RestController
public class ReportsController {
	
	
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	PrestamoService prestamoService;
	
	
	//Devuelve los datos del cliente junto al listado de las cuentas de las que dispone y sus movs
	@GetMapping("reports/clientes/{id}")	
	public ResponseEntity<DtoEntity> findCuentasByClienteId(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(clienteService.findCuentasAndMovsByClienteId(id));
		} catch (Exception e) {
			return new ResponseEntity<DtoEntity>(HttpStatus.NOT_FOUND);
		}
	}	
	
	
	@PostMapping("reports/clientes/{id}")
	public ResponseEntity<String> generarClientePdfById(@PathVariable Integer id) {
		try {
			clienteService.generarClientesPdf(id);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
		}
	}
	
	//Devuelve los datos del cliente junto a la información del préstamo indicado y las amortizaciones planificadas.
	@GetMapping("reports/prestamos/{id}")	
	public ResponseEntity<DtoEntity> findPrestamosByPrestamoId(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(prestamoService.findClienteAndAmortizacionesByPrestamo(id));
		} catch (Exception e) {
			return new ResponseEntity<DtoEntity>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	//Devuelve un listado de los préstamos que no se han pagado todavía junto a la cuenta y al cliente correspondiente
	@GetMapping("reports/prestamosVivos")	
	public ResponseEntity<List<DtoEntity>> findAllPrestamosVivos() {
		try {
			return ResponseEntity.ok(prestamoService.findAllPrestamosVivos());
		} catch (Exception e) {
			return new ResponseEntity<List<DtoEntity>>(HttpStatus.NOT_FOUND);
		}
	}	
	
	//Devuelve un listado de los préstamos que se han pagado totalmente junto a la cuenta y al cliente correspondiente
	@GetMapping("reports/prestamosAmortizados")	
	public ResponseEntity<List<DtoEntity>> findAllPrestamosAmortizados() {
		try {
			return ResponseEntity.ok(prestamoService.findAllPrestamosAmortizados());
		} catch (Exception e) {
			return new ResponseEntity<List<DtoEntity>>(HttpStatus.NOT_FOUND);
		}
	}	
	
	
	
	


}
