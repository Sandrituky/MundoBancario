package es.eoi.mundobancario.entity;

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
@Table(name = "CLIENTES")
@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String usuario;
	
	@Column
	private String pass;
	
	@Column
	private String nombre;
	
	@Column
	private String email;
	
	//Cuenta TIENE CLAVE FORANEA DE Cliente
	@OneToMany(mappedBy = "cliente")
    private List<Cuenta> cuentas;
	
	public static boolean checkEmail(String email) {
		 String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      return email.matches(regex);
	}

}
