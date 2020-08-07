package es.eoi.mundobancario.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import edu.es.eoi.converter.TipoMovEnumConverter;
import es.eoi.mundobancario.enums.TipoMov;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Table(name = "TIPOS_MOVIMIENTO")
@Entity
public class TipoMovimiento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	@Convert(converter = TipoMovEnumConverter.class)
	private TipoMov tipo;
	
	
	//MOVIMIENTO TIENE CLAVE FORANEA DE TIPOS_MOVIMIENTO
	@OneToMany(mappedBy = "tipoMov", cascade = CascadeType.ALL)
    private List<Movimiento> movimientos;

}
