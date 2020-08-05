package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.dto.DtoEntity;
import es.eoi.mundobancario.entity.Movimiento;

public interface MovimientoService {

	

	List<DtoEntity> findAll();
	
	List<DtoEntity> findAllMovsByCuentaID(int id);
	
}
