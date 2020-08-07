package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.DtoEntity;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.PrestamoCreateDto;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;

public interface MovimientoService {

	

	List<DtoEntity> findAll();
	
	List<DtoEntity> findAllMovsByCuentaID(int id);
	
	public void addMovimientoPrestamo(int id, PrestamoCreateDto prestamo);
	
	public void hacerIngreso(int id, MovimientoDto movimientoDto);
	
	public void hacerPago(int id, MovimientoDto movimientoDto);
	
}
