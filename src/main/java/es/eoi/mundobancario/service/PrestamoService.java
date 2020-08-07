package es.eoi.mundobancario.service;

import java.util.List;

import es.eoi.mundobancario.dto.CuentaBasicDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.DtoEntity;
import es.eoi.mundobancario.dto.PrestamoCreateDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.enums.Pagado;
import es.eoi.mundobancario.exception.PrestamoException;

public interface PrestamoService {

	public List<DtoEntity> findPrestamosByCuenta(int id);

	public List<DtoEntity> findPrestamosVivosByCuenta(int id);

	public List<DtoEntity> findPrestamosAmortizadosByCuenta(int id);

	public boolean addPrestamo(CuentaDto cuentaDto, PrestamoCreateDto prestamoDto) throws PrestamoException;
	
	public List<DtoEntity> findAllPrestamosVivos();
	
	public List<DtoEntity> findAllPrestamosAmortizados();
	
	public List<Prestamo> findAllPrestamosVivosEnt();
	
	public List<Prestamo> findAllPrestamosAmortizadosEnt();
	
	public DtoEntity findClienteAndAmortizacionesByPrestamo(int id);
	
}
