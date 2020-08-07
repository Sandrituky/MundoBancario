package es.eoi.mundobancario.utils;

import org.modelmapper.ModelMapper;

import es.eoi.mundobancario.dto.DtoEntity;

public class DtoUtils {

 public DtoEntity convertToDto(Object obj, DtoEntity mapper) {
   return new ModelMapper().map(obj, mapper.getClass());
 }

 public Object convertToEntity(Object obj, DtoEntity mapper) {
   return new ModelMapper().map(mapper, obj.getClass());
 }
}