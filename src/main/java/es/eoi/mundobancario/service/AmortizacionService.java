package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.entity.Prestamo;

public interface AmortizacionService {
	
	public void ejecutarAmortizacionesDiarias(List<Prestamo> prestamosVivos);

}
