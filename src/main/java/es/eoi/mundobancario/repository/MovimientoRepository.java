package es.eoi.mundobancario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.TipoMovimiento;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Integer> {

	
	//PRUEBA
		public List <Movimiento> findAll();
		
		

		public List <Movimiento> findByCuentaNumCuenta(int id);
		
		
	
}
