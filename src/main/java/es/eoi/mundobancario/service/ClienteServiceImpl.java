package es.eoi.mundobancario.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.eoi.mundobancario.dto.ClienteBasicDto;
import es.eoi.mundobancario.dto.ClienteCreateDto;
import es.eoi.mundobancario.dto.ClienteCuentasDto;
import es.eoi.mundobancario.dto.ClienteDto;
import es.eoi.mundobancario.dto.ClienteUpdateEmailDto;
import es.eoi.mundobancario.dto.DtoEntity;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.repository.ClienteRepository;
import es.eoi.utils.DtoUtils;


@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository repository;
	
	@Autowired
	DtoUtils dtoUtils;

	public List<DtoEntity> findAll() {

		List<Cliente> clientes = repository.findAll();
		List<DtoEntity> clientesDto = new ArrayList();
		
		for (Cliente cliente : clientes) {
		
			DtoEntity clienteDto= dtoUtils.convertToDto(cliente, new ClienteBasicDto());
			clientesDto.add(clienteDto);
		}

		return clientesDto;
	}

	public DtoEntity findClienteById(int id) {
		return dtoUtils.convertToDto(repository.findById(id), new ClienteBasicDto());
		
	}
	
	public DtoEntity findCuentasByClienteId(int id) {
		return dtoUtils.convertToDto(repository.findById(id), new ClienteCuentasDto());

	}
	
	public void updateClienteEmail(String email, int id) {
		Cliente cliente = repository.findById(id);
		cliente.setEmail(email);
		repository.save(cliente);
	}
	
	public void addCliente(ClienteCreateDto clienteDto) {
		Cliente cliente = (Cliente) dtoUtils.convertToEntity(new Cliente(), clienteDto);
		repository.save(cliente);
	}
	

	
	


}
