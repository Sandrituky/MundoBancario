package es.eoi.mundobancario.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.eoi.mundobancario.enums.Pagado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "AMORTIZACIONES")
@Entity
public class Amortizacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private LocalDate fecha;
	
	@Column
	private double importe;
	
	@Column(name = "PAGADO")
	@Enumerated(EnumType.STRING)
	private Pagado pagado = Pagado.Pendiente;
	
	// CLAVE FORANEA A TABLA PRESTAMOS, 1-N
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_PRESTAMO")
	private Prestamo prestamo;

}
