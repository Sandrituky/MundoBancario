package es.eoi.mundobancario.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eoi.mundobancario.dto.ClienteBasicDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.DtoEntity;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoCreateDto;
import es.eoi.mundobancario.dto.TipoMovimientoDto;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TipoMovimiento;
import es.eoi.mundobancario.enums.TipoMov;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.repository.CuentaRepository;
import es.eoi.mundobancario.repository.MovimientoRepository;
import es.eoi.mundobancario.repository.TipoMovimientoRepository;
import es.eoi.mundobancario.utils.DtoUtils;

@Service
public class MovimientoServiceImpl implements MovimientoService {
	
	@Autowired
	MovimientoRepository movRepo;
	
	@Autowired
	CuentaRepository cuentaRepo;

	@Autowired
	DtoUtils dtoUtils;
	
	//Usado para pruebas
	public List<DtoEntity> findAll() {

		List<Movimiento> movimientos = movRepo.findAll();
		List<DtoEntity> movimientosDto = new ArrayList();
		
		for (Movimiento movimiento : movimientos) {
		
			DtoEntity movimientoDto= dtoUtils.convertToDto(movimiento, new MovimientoDto());
			movimientosDto.add(movimientoDto);
		}

		return movimientosDto;
	}
	
	//____________
	
	public List<DtoEntity> findAllMovsByCuentaID(int id) {
		
		List<Movimiento> movimientos = movRepo.findByCuentaNumCuenta(id);
		List<DtoEntity> movimientosDto = new ArrayList();
		
		for (Movimiento movimiento : movimientos) {
		
			DtoEntity movimientoDto= dtoUtils.convertToDto(movimiento, new MovimientoDto());
			movimientosDto.add(movimientoDto);
		}

		return movimientosDto;
	}
	

	
	@Transactional(rollbackFor=Exception.class)
	public void addMovimientoPrestamo(int id, PrestamoCreateDto prestamo) {
		
	Movimiento movimiento = new Movimiento();
	movimiento.setDescripcion(prestamo.getDescripcion());
	movimiento.setFecha(LocalDate.now());
	movimiento.setImporte(prestamo.getImporte());
	
	TipoMovimiento tipo = new TipoMovimiento();
	tipo.setTipo(TipoMov.PRESTAMO);
	tipo.setId(2);
	movimiento.setTipoMov(tipo);
	
	Cuenta cuenta = cuentaRepo.findById(id);
	movimiento.setCuenta(cuenta);
	movRepo.save(movimiento);
	
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void hacerIngreso(int id, MovimientoDto movimientoDto) {
		Movimiento movimiento= (Movimiento) dtoUtils.convertToEntity(new Movimiento(), movimientoDto);
		
		movimiento.setDescripcion(movimiento.getDescripcion());
		movimiento.setFecha(LocalDate.now());
		movimiento.setImporte(movimiento.getImporte());
		
		TipoMovimiento tipo = new TipoMovimiento();
		tipo.setTipo(TipoMov.INGRESO);
		tipo.setId(1);
		movimiento.setTipoMov(tipo);
		
		Cuenta cuenta = cuentaRepo.findById(id);
		movimiento.setCuenta(cuenta);
		movRepo.save(movimiento);
		
	}
	
	@Transactional(rollbackFor=Exception.class)
	public void hacerPago(int id, MovimientoDto movimientoDto) {
		Movimiento movimiento= (Movimiento) dtoUtils.convertToEntity(new Movimiento(), movimientoDto);
		
		movimiento.setDescripcion(movimiento.getDescripcion());
		movimiento.setFecha(LocalDate.now());
		movimiento.setImporte(movimiento.getImporte());
		
		TipoMovimiento tipo = new TipoMovimiento();
		tipo.setTipo(TipoMov.PAGO);
		tipo.setId(3);
		movimiento.setTipoMov(tipo);
		
		Cuenta cuenta = cuentaRepo.findById(id);
		movimiento.setCuenta(cuenta);
		movRepo.save(movimiento);
		
	}
	
	
	
	


}
