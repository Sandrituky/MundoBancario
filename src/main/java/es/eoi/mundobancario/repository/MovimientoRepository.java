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
		
		
		
		

		
		@Query(value="SELECT MOV.* FROM CUENTAS CUE INNER JOIN MOVIMIENTOS MOV ON MOV.ID_CUENTA = CUE.NUM_CUENTA WHERE MOV.ID_TIPO=?1", nativeQuery = true)
		public List <Movimiento> findByCuentaId(int id);
		
		
	
}
