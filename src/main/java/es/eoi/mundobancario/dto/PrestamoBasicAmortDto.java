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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrestamoBasicAmortDto implements DtoEntity{
	
	//private int id;
	
	private String descripcion;
	
	private LocalDate fecha;
	
	private double importe;

	private int plazos;
	
	private String pagado;
	
	// CLAVE FORANEA A TABLA CUENTA, 1-N
	//private CuentaDto cuenta;
	
	//AMORTIZACIONES TIENE CLAVE FORANEA DE PRESTAMOS
    private List<AmortizacionDto> amortizaciones;
	
	
	

}
