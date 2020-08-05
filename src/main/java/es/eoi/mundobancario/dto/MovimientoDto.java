package es.eoi.mundobancario.dto;

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
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientoDto implements DtoEntity {
	
	//private int id;
	
	private String descripcion;
	
	private LocalDate fecha;
	
	private double importe;
	
	// CLAVE FORANEA A TABLA CUENTAS, 1-N
	//CuentaBasicDto cuenta;
	
	// CLAVE FORANEA A TABLA TIPOS_MOVIMIENTO, 1-N
	TipoMovimientoDto tipoMov;
	
	

}
