package es.eoi.mundobancario.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import es.eoi.mundobancario.enums.TipoMov;

@Converter(autoApply = true)
public class TipoMovEnumConverter implements AttributeConverter<TipoMov, String> { 

	public String convertToDatabaseColumn(TipoMov tipoMov) {
        return tipoMov.getTipoNombre();
    }
 
    public TipoMov convertToEntityAttribute(String dbData) {
        return  dbData == null ? null : TipoMov.fromDBName(dbData);
    }
		
}
	