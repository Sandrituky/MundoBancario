package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.dto.CuentaCreateDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.DtoEntity;

public interface CuentaService {
	
	public List<DtoEntity> findAll();
	
	public List<DtoEntity> findCuentasSaldoNeg();
	
	public DtoEntity findCuentaById(int id);
	
	public void updateCuentaAlias(String alias, int id);
	
	public void addCuenta(CuentaCreateDto cuentaDto);
	
	
	
	
	
	public void addSaldo(int id, double importe);

	
	public boolean substractSaldo(int id, double importe);
}
