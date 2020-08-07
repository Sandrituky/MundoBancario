package es.eoi.mundobancario.dto;

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
public class CuentaBasicDto implements DtoEntity  {
	
	private int numCuenta;
	
	private String alias;
	
	private double saldo;
	
	
	// CLAVE FORANEA A TABLA CLIENTE, 1-N
	//private ClienteDto cliente;
	
	//PRESTAMO TIENE CLAVE FORANEA DE CUENTA
    //private List<PrestamoDto> prestamos;
	
	//MOVIMIENTO TIENE CLAVE FORANEA DE CUENTA
    //private List<MovimientoDto> movimientos;
	
	

}
