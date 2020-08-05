package es.eoi.mundobancario.entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
@Table(name = "MOVIMIENTOS")
@Entity
public class Movimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String descripcion;
	
	@Column
	private LocalDate fecha;
	
	
	@Column
	private double importe;
	
	// CLAVE FORANEA A TABLA CUENTAS, 1-N
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_CUENTA")
	Cuenta cuenta;
	
	// CLAVE FORANEA A TABLA TIPOS_MOVIMIENTO, 1-N
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_TIPO")
	TipoMovimiento tipoMov;
	
	

}
