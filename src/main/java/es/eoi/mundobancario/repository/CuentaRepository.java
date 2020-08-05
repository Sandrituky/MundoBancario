package es.eoi.mundobancario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import es.eoi.mundobancario.entity.Cuenta;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {

	
	//Listado de todas las cuentas
	public List <Cuenta> findAll();
	
	
	//Devuelve un listado de cuentas que tengan un saldo menor a <saldo>
	public List <Cuenta> findBySaldoLessThan(double saldo);
	
	
	//Busca una cuenta por ID
	public Cuenta findById(int id);
	
	

	
	
	

	
	
}
