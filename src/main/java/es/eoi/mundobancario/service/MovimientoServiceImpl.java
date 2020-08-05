package es.eoi.mundobancario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.dto.ClienteBasicDto;
import es.eoi.mundobancario.dto.DtoEntity;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.dto.TipoMovimientoDto;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.repository.MovimientoRepository;
import es.eoi.mundobancario.repository.TipoMovimientoRepository;
import es.eoi.utils.DtoUtils;

@Service
public class MovimientoServiceImpl implements MovimientoService {
	
	@Autowired
	MovimientoRepository repository;

	@Autowired
	DtoUtils dtoUtils;
	
	//Usado para pruebas
	public List<DtoEntity> findAll() {

		List<Movimiento> movimientos = repository.findAll();
		List<DtoEntity> movimientosDto = new ArrayList();
		
		for (Movimiento movimiento : movimientos) {
		
			DtoEntity movimientoDto= dtoUtils.convertToDto(movimiento, new MovimientoDto());
			movimientosDto.add(movimientoDto);
		}

		return movimientosDto;
	}
	
	public List<DtoEntity> findAllMovsByCuentaID(int id) {
		
		List<Movimiento> movimientos = repository.findByCuentaId(id);
		List<DtoEntity> movimientosDto = new ArrayList();
		
		for (Movimiento movimiento : movimientos) {
		
			DtoEntity movimientoDto= dtoUtils.convertToDto(movimiento, new MovimientoDto());
			movimientosDto.add(movimientoDto);
		}

		return movimientosDto;
	}
	
	
	


}
