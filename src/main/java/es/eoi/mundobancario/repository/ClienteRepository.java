package es.eoi.mundobancario.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
	//Listado de todos los clientes
	public List <Cliente> findAll();
	
	
	//Busca un cliente por ID
	public Cliente findById(int id);
	
	//Busca todas las cuentas de un cliente a partir de su ID
	public Optional <Cliente> findAllCuentasById(int id);
	
	
	public Cliente findByUsuario(String usuario);
	
	
	
	

}
