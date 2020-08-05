package es.eoi.mundobancario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.TipoMovimiento;

@Repository
public interface TipoMovimientoRepository extends JpaRepository<TipoMovimiento, Integer> {

	
	//PRUEBAS
	public List <TipoMovimiento> findAll();
	
	
}
