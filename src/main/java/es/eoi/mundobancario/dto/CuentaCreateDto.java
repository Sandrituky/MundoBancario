package es.eoi.mundobancario.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CuentaCreateDto implements DtoEntity {
	
	//private int numCuenta;
	
	private String alias;
	
	//private double saldo;
	
	
	// CLAVE FORANEA A TABLA CLIENTE, 1-N
	private ClienteIdDto cliente;
	
	//PRESTAMO TIENE CLAVE FORANEA DE CUENTA
    //private List<PrestamoDto> prestamos;
	
	//MOVIMIENTO TIENE CLAVE FORANEA DE CUENTA
    //private List<MovimientoDto> movimientos;
	
	

}
