package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.dto.DtoEntity;

public interface CuentaService {
	
	public List<DtoEntity> findAll();
	
	public List<DtoEntity> findCuentasSaldoNeg();
	
	public DtoEntity findCuentaById(int id);

}
