package es.eoi.mundobancario.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto implements DtoEntity {
	
	//private int id;
	
	private String usuario;
	
	private String pass;
	
	//private String nombre;
	
	//private String email;
	
	//Cuenta TIENE CLAVE FORANEA DE Cliente
    //private List<CuentaDto> cuentas;

}
