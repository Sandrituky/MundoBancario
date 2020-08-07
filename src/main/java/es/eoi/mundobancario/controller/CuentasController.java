package es.eoi.mundobancario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.eoi.mundobancario.dto.ClienteBasicDto;
import es.eoi.mundobancario.dto.ClienteCreateDto;
import es.eoi.mundobancario.dto.CuentaBasicDto;
import es.eoi.mundobancario.dto.CuentaCreateDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.DtoEntity;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoCreateDto;
import es.eoi.mundobancario.dto.PrestamoCuentaClienteDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.service.AmortizacionService;
import es.eoi.mundobancario.service.CuentaService;
import es.eoi.mundobancario.service.MovimientoService;
import es.eoi.mundobancario.service.PrestamoService;

@RestController
public class CuentasController {

	@Autowired
	CuentaService cuentaService;

	@Autowired
	MovimientoService movService;

	@Autowired
	PrestamoService prestamoService;
	
	@Autowired
	AmortizacionService amortizacionService;

	// Devuelve un listado con todas las cuentas (Toda la información y datos del
	// cliente).
	@GetMapping("cuentas")
	public ResponseEntity<List<DtoEntity>> findAllCuentas() {
		return ResponseEntity.ok(cuentaService.findAll());
	}

	// Devuelve un listado de las cuentas con saldo negativo (Toda la información y
	// datos del cliente).
	@GetMapping("cuentas/deudoras")
	public ResponseEntity<List<DtoEntity>> findCuentasSaldoNeg() {
		return ResponseEntity.ok(cuentaService.findCuentasSaldoNeg());
	}

	// Devuelve la cuenta solicitada (Toda la información y datos del cliente).
	@GetMapping("cuentas/{id}")
	public ResponseEntity<DtoEntity> findClienteById(@PathVariable Integer id) {
		return ResponseEntity.ok(cuentaService.findCuentaById(id));
	}

	// Modifica el campo alias de la cuenta solicitada
	@PutMapping("cuentas/{id}")
	public ResponseEntity<String> updateCuentaAlias(@PathVariable Integer id, @RequestBody CuentaBasicDto cuentaDto) {

		try {
			cuentaService.updateCuentaAlias(cuentaDto.getAlias(), id);
			return new ResponseEntity<String>(HttpStatus.ACCEPTED);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.NOT_MODIFIED);
		}

	}

	// Añadimos una nueva cuenta
	@PostMapping("cuentas")
	public ResponseEntity<String> addCuenta(@RequestBody CuentaCreateDto cuentaDto) {
		try {
			cuentaService.addCuenta(cuentaDto);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}

	// Devuelve los movimientos de la cuenta solicitada. (incluirá el tipo de movimiento)
	@GetMapping("cuentas/{id}/movimientos")
	public ResponseEntity<List<DtoEntity>> findMovimientosByCuentaId(@PathVariable Integer id) {
		
		return ResponseEntity.ok(movService.findAllMovsByCuentaID(id));
	}

	// Devuelve los préstamos de la cuenta. (incluirán las amortizaciones planificadas)
	@GetMapping("cuentas/{id}/prestamos")
	public ResponseEntity<List<DtoEntity>> findPrestamosByCuenta(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(prestamoService.findPrestamosByCuenta(id));
		} catch (Exception e) {
			return new ResponseEntity<List<DtoEntity>>(HttpStatus.NOT_FOUND);
		}
	}

	
	// Devuelve los préstamos vivos de la cuenta. (incluirán las amortizaciones planificadas)
	@GetMapping("cuentas/{id}/prestamosVivos")
	public ResponseEntity<List<DtoEntity>> findPrestamosVivosByCuenta(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(prestamoService.findPrestamosVivosByCuenta(id));
		} catch (Exception e) {
			return new ResponseEntity<List<DtoEntity>>(HttpStatus.NOT_FOUND);
		}
	}

	
	// Devuelve los préstamos amortizados de la cuenta. (incluirán las amortizaciones planificadas)
	@GetMapping("cuentas/{id}/prestamosAmortizados")
	public ResponseEntity<List<DtoEntity>> findPrestamosPagadosByCuenta(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(prestamoService.findPrestamosAmortizadosByCuenta(id));
		} catch (Exception e) {
			return new ResponseEntity<List<DtoEntity>>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	// Crearemos un nuevo prestamo
	@PostMapping("cuentas/{id}/prestamos")
	public ResponseEntity<String> addPrestamo(@PathVariable Integer id, @RequestBody PrestamoCreateDto prestamoDto) {
		try {
			CuentaDto cuenta = (CuentaDto) cuentaService.findCuentaById(id);
			
				 if(prestamoService.addPrestamo(cuenta, prestamoDto)) {
				
				cuentaService.addSaldo(id, prestamoDto.getImporte());
				movService.addMovimientoPrestamo(id, prestamoDto);
				return new ResponseEntity<String>(HttpStatus.CREATED);
			}else {
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}

		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}
	
	
	// Crearemos un nuevo ingreso
	@PostMapping("cuentas/{id}/ingresos")
	public ResponseEntity<String> addIngreso(@PathVariable Integer id, @RequestBody MovimientoDto movimientoDto) {
		try {
			CuentaDto cuenta = (CuentaDto) cuentaService.findCuentaById(id);
			movService.hacerIngreso(id, movimientoDto);
			cuentaService.addSaldo(id, movimientoDto.getImporte());
			return new ResponseEntity<String>(HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}
	
	
	// Crearemos un nuevo ingreso
	@PostMapping("cuentas/{id}/pagos")
	public ResponseEntity<String> addPago(@PathVariable Integer id, @RequestBody MovimientoDto movimientoDto) {
		try {
			CuentaDto cuenta = (CuentaDto) cuentaService.findCuentaById(id);
			if(cuentaService.substractSaldo(id, movimientoDto.getImporte())) {
				movService.hacerPago(id, movimientoDto);
				return new ResponseEntity<String>(HttpStatus.CREATED);
			}else {
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}
	
	
	//Funcionalidad encargada de ejecutar las amortizaciones en caso de cuya fecha coincida con la del sistema
	@PostMapping("cuentas/ejecutarAmortizacionesDiarias")
	public ResponseEntity<String> ejecutarAmortizacionesDiarias() {
		try {
			List<Prestamo> prestamosVivos = prestamoService.findAllPrestamosVivosEnt();
			amortizacionService.ejecutarAmortizacionesDiarias(prestamosVivos);
			return new ResponseEntity<String>(HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

	}
	
	
	
	
	
	

}
