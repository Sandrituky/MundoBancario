package es.eoi.mundobancario.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "PRESTAMOS")
@Entity
public class Prestamo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String descripcion;
	
	@Column
	private LocalDate fecha;
	
	@Column
	private double importe;
	
	@Column
	private int plazos;
	
	// CLAVE FORANEA A TABLA CUENTA, 1-N
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_CUENTA")
	private Cuenta cuenta;
	
	//AMORTIZACIONES TIENE CLAVE FORANEA DE PRESTAMOS
	@OneToMany(mappedBy = "prestamo")
    private List<Amortizacion> amortizaciones;
	
	
	

}
