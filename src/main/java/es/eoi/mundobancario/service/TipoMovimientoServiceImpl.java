package es.eoi.mundobancario.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.dto.TipoMovimientoDto;
import es.eoi.mundobancario.dto.ClienteBasicDto;
import es.eoi.mundobancario.dto.DtoEntity;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.TipoMovimiento;
import es.eoi.mundobancario.repository.TipoMovimientoRepository;
import es.eoi.utils.DtoUtils;

@Service
public class TipoMovimientoServiceImpl implements TipoMovimientoService {

	@Autowired
	TipoMovimientoRepository repository;
	
	@Autowired
	DtoUtils dtoUtils;
	
	
	public List<DtoEntity> findAllDto() {

		List<TipoMovimiento> tiposMovimiento = repository.findAll();
		List<DtoEntity> tiposMovimientoDto = new ArrayList();
		
		for (TipoMovimiento tipoMovimiento : tiposMovimiento) {
		
			DtoEntity tipoMovimientoDto= dtoUtils.convertToDto(tipoMovimiento, new TipoMovimientoDto());
			tiposMovimientoDto.add(tipoMovimientoDto);
		}

		return tiposMovimientoDto;
		
	}
	
	public List<TipoMovimiento> findAll() {

		List<TipoMovimiento> tiposMovimiento = repository.findAll();

		return tiposMovimiento;
		
	}
	
	
}
