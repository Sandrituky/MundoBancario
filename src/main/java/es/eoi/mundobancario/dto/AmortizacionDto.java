package es.eoi.mundobancario.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AmortizacionDto {
	

	private int id;
	
	private LocalDate fecha;
	
	private double importe;
	
	// CLAVE FORANEA A TABLA PRESTAMOS, 1-N
	private PrestamoDto prestamo;

}
