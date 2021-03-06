package es.eoi.mundobancario.entity;

import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "CUENTAS")
@Entity
public class Cuenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NUM_CUENTA")
	private int numCuenta;
	
	@Column
	private String alias;
	
	
	@Column(columnDefinition = "double default 0")
	private double saldo;
	
	
	// CLAVE FORANEA A TABLA CLIENTE, 1-N
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_CLIENTE")
	private Cliente cliente;
	
	
	//PRESTAMO TIENE CLAVE FORANEA DE CUENTA
	@OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<Prestamo> prestamos;
	
	//MOVIMIENTO TIENE CLAVE FORANEA DE CUENTA
	@OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<Movimiento> movimientos;
	
	

}
