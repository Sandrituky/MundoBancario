package es.eoi.mundobancario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.eoi.mundobancario.entity.Amortizacion;

@Repository
public interface AmortizacionRepository extends JpaRepository<Amortizacion, Integer>{

}
