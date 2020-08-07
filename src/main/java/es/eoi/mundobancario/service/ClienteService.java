package es.eoi.mundobancario.service;

import java.io.FileNotFoundException;
import java.util.List;

import es.eoi.mundobancario.dto.ClienteCreateDto;
import es.eoi.mundobancario.dto.DtoEntity;


public interface ClienteService {
	
	public List <DtoEntity> findAll();
	
	public DtoEntity findClienteById(int id);
	
	public DtoEntity login(String usuario, String pass);
	
	public boolean isPasswordCorrect(String usuario, String pass);
	
	public DtoEntity findCuentasByClienteId(int id);
	
	public boolean updateClienteEmail(String email, int id);
	
	public boolean addCliente(ClienteCreateDto clienteDto);
	
	
	
	
	//Reports
	
	public DtoEntity findCuentasAndMovsByClienteId(int id);
	
	public void generarClientesPdf(int id) throws FileNotFoundException;

}
