package es.eoi.mundobancario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.enums.Pagado;

@Repository
public interface PrestamoRepository extends JpaRepository<Prestamo, Integer> {
	
	
	
	public List <Prestamo> findAllByCuentaNumCuenta(int id);
	

	public List <Prestamo> findAllByCuentaNumCuentaAndPagado(int id, Pagado pagado);
	
	public List <Prestamo> findAllByPagado(Pagado pagado);

}
