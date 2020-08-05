package es.eoi.mundobancario.enums;


public enum TipoMov {
	
	INGRESO("Ingreso"),
	PRESTAMO("Préstamo"),
	PAGO("Pago"),
	AMORTIZACION("Amortización"),
	INTERES("Interés");
	
	private String tipoNombre;
	
	
	public String getTipoNombre() {
		return tipoNombre;
	}


	TipoMov(String tipoNombre) {
		this.tipoNombre = tipoNombre;
	}
	
	
	public void setTipoNombre(String tipoNombre) {
		this.tipoNombre = tipoNombre;
	}


	public static TipoMov fromDBName(String tipoNombre) {
		switch (tipoNombre) {
        case "Ingreso":
            return TipoMov.INGRESO;
 
        case "Préstamo":
        	 return TipoMov.PRESTAMO;
 
        case "Pago":
        	 return TipoMov.PAGO;
 
        case "Amortización":
        	 return TipoMov.AMORTIZACION;
        	 
        case "Interés":
       	 return TipoMov.INTERES;
 
        default:
            throw new IllegalArgumentException("ShortName [" + tipoNombre
                    + "] not supported.");
        }
	}


	

}
