package es.eoi.mundobancario.dto;

import javax.persistence.Convert;

import edu.es.eoi.converter.TipoMovEnumConverter;
import es.eoi.mundobancario.enums.TipoMov;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TipoMovimientoDto implements DtoEntity {
	

	//private int id;
	
	//@Convert(converter = TipoMovEnumConverter.class)
	private String tipo;
	

	
	//MOVIMIENTO TIENE CLAVE FORANEA DE TIPOS_MOVIMIENTO
    //private List<Movimiento> movimientos;


}
