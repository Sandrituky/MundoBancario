package es.eoi.mundobancario.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eoi.mundobancario.dto.ClienteCreateDto;
import es.eoi.mundobancario.dto.CuentaCreateDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.DtoEntity;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.TipoMovimiento;
import es.eoi.mundobancario.enums.TipoMov;
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
	
	
	
	@Transactional(rollbackFor=Exception.class)
	public void updateCuentaAlias(String alias, int id) {
		Cuenta cuenta = repository.findById(id);
			cuenta.setAlias(alias);
			repository.save(cuenta);
	}
	
	
	@Transactional(rollbackFor=Exception.class)
	public void addCuenta(CuentaCreateDto cuentaDto) {
			Cuenta cuenta = (Cuenta) dtoUtils.convertToEntity(new Cuenta(), cuentaDto);
			repository.save(cuenta);
	}
	
	
	
	
	
	@Transactional(rollbackFor=Exception.class)
	public void addSaldo(int id, double importe) {
		Cuenta cuenta = repository.findById(id);
		double saldo = cuenta.getSaldo()+importe;
		cuenta.setSaldo(saldo);
		repository.save(cuenta);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public boolean substractSaldo(int id, double importe) {
		
		Cuenta cuenta = repository.findById(id);
		boolean resul = false;
		
		if(cuenta.getSaldo()>=importe) {
			double saldo = cuenta.getSaldo()-importe;
			cuenta.setSaldo(saldo);
			repository.save(cuenta);
			resul = true;
		}
		return resul;
		
	}
	
	
	
	
	
	
	
		
	
	

}
