package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.dto.ClienteCreateDto;
import es.eoi.mundobancario.dto.ClienteUpdateEmailDto;
import es.eoi.mundobancario.dto.DtoEntity;


public interface ClienteService {
	
	public List <DtoEntity> findAll();
	
	public DtoEntity findClienteById(int id);
	
	public DtoEntity findCuentasByClienteId(int id);
	
	public void updateClienteEmail(String email, int id);
	
	public void addCliente(ClienteCreateDto clienteDto);

}
