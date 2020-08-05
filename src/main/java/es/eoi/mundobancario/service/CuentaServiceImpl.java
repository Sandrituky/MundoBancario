package es.eoi.mundobancario.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.DtoEntity;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.repository.CuentaRepository;
import es.eoi.utils.DtoUtils;

@Service
public class CuentaServiceImpl implements CuentaService{
	
	@Autowired
	CuentaRepository repository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	DtoUtils dtoUtils;

	public List<DtoEntity> findAll() {

		List<Cuenta> cuentas = repository.findAll();
		List<DtoEntity> cuentasDto = new ArrayList();
		
		for (Cuenta cuenta : cuentas) {
		
			DtoEntity cuentaDto= dtoUtils.convertToDto(cuenta, new CuentaDto());
			cuentasDto.add(cuentaDto);
		}

		return cuentasDto;
	}
	
	public List<DtoEntity> findCuentasSaldoNeg() {

		List<Cuenta> cuentas = repository.findBySaldoLessThan(0);
		List<DtoEntity> cuentasDto = new ArrayList();
		
		for (Cuenta cuenta : cuentas) {
		
			DtoEntity cuentaDto= dtoUtils.convertToDto(cuenta, new CuentaDto());
			cuentasDto.add(cuentaDto);
		}

		return cuentasDto;
	}
	
	

	public DtoEntity findCuentaById(int id) {
		return dtoUtils.convertToDto(repository.findById(id), new CuentaDto());
		
	}

}
