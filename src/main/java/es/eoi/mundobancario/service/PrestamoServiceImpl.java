package es.eoi.mundobancario.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eoi.mundobancario.dto.ClienteBasicDto;
import es.eoi.mundobancario.dto.ClienteCuentasMovDto;
import es.eoi.mundobancario.dto.CuentaBasicDto;
import es.eoi.mundobancario.dto.CuentaCreateDto;
import es.eoi.mundobancario.dto.CuentaDto;
import es.eoi.mundobancario.dto.DtoEntity;
import es.eoi.mundobancario.dto.PrestamoBasicAmortDto;
import es.eoi.mundobancario.dto.PrestamoCreateDto;
import es.eoi.mundobancario.dto.PrestamoCuentaClienteDto;
import es.eoi.mundobancario.dto.PrestamoDto;
import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TipoMovimiento;
import es.eoi.mundobancario.enums.Pagado;
import es.eoi.mundobancario.enums.TipoMov;
import es.eoi.mundobancario.exception.PrestamoException;
import es.eoi.mundobancario.repository.AmortizacionRepository;
import es.eoi.mundobancario.repository.CuentaRepository;
import es.eoi.mundobancario.repository.MovimientoRepository;
import es.eoi.mundobancario.repository.PrestamoRepository;
import es.eoi.mundobancario.repository.TipoMovimientoRepository;
import es.eoi.utils.DtoUtils;

@Service
public class PrestamoServiceImpl implements PrestamoService {

	@Autowired
	PrestamoRepository prestamoRepo;
	
	@Autowired
	AmortizacionRepository amortizacionRepo;
	
	@Autowired
	CuentaRepository cuentaRepo;
	
	@Autowired
	MovimientoRepository movRepo;
	
	@Autowired
	TipoMovimientoRepository tipoMovRepo;
	
	@Autowired
	DtoUtils dtoUtils;

	
	
	
	public List<DtoEntity> findPrestamosByCuenta(int id) {

		List<Prestamo> prestamos = prestamoRepo.findAllByCuentaNumCuenta(id);
		List<DtoEntity> prestamosDto = new ArrayList();
		
		for (Prestamo prestamo : prestamos) {
			DtoEntity clienteDto= dtoUtils.convertToDto(prestamo, new PrestamoBasicAmortDto());
			prestamosDto.add(clienteDto);
		}

		return prestamosDto;
	}
	
	
	public List<DtoEntity> findPrestamosVivosByCuenta(int id) {

		List<Prestamo> prestamos = prestamoRepo.findAllByCuentaNumCuentaAndPagado(id, Pagado.Pendiente);
		List<DtoEntity> prestamosDto = new ArrayList();
		
		for (Prestamo prestamo : prestamos) {
		
			DtoEntity clienteDto= dtoUtils.convertToDto(prestamo, new PrestamoBasicAmortDto());
			prestamosDto.add(clienteDto);
		}

		return prestamosDto;
	}
	
	public List<DtoEntity> findPrestamosAmortizadosByCuenta(int id) {
		

		List<Prestamo> prestamos = prestamoRepo.findAllByCuentaNumCuentaAndPagado(id, Pagado.Pagado);
		List<DtoEntity> prestamosDto = new ArrayList();
		
		for (Prestamo prestamo : prestamos) {
		
			DtoEntity clienteDto= dtoUtils.convertToDto(prestamo, new PrestamoBasicAmortDto());
			prestamosDto.add(clienteDto);
		}

		return prestamosDto;
	}
	
	
	
	@Transactional(rollbackFor = Exception.class)
	public boolean addPrestamo(CuentaDto cuentaDto, PrestamoCreateDto prestamoDto) throws PrestamoException {
		boolean resul = false;
		Cuenta cuenta = (Cuenta) dtoUtils.convertToEntity(new Cuenta(), cuentaDto);
		for (Prestamo p : cuenta.getPrestamos()) {
			if (p.getPagado() == Pagado.Pendiente) {
				resul = false;
				throw new PrestamoException("No puedes pedir un prestamo mientras tengas otro sin terminar de pagar");
			}
		}
		Prestamo prestamo = (Prestamo) dtoUtils.convertToEntity(new Prestamo(), prestamoDto);
		prestamo.setFecha(LocalDate.now());
		prestamo.setPagado(Pagado.Pendiente);
		prestamo.setCuenta(cuenta);
		prestamoRepo.save(prestamo);
		
		
		// Creacion de amortizaciones
		int plazos = prestamo.getPlazos();
		double importeDiv = prestamo.getImporte() / plazos;
		List<Amortizacion> amortizaciones = new ArrayList();

		for (int i = 1; i <= plazos; i++) {
			Amortizacion amortizacion = new Amortizacion();
			amortizacion.setFecha(prestamo.getFecha().plusMonths(i));
			amortizacion.setImporte(importeDiv);
			amortizacion.setPrestamo(prestamo);
			amortizaciones.add(amortizacion);
			amortizacionRepo.save(amortizacion);
		}
		resul = true;

		return resul;
	}
	
	
	public List<DtoEntity> findAllPrestamosVivos() {

		List<Prestamo> prestamos = prestamoRepo.findAllByPagado(Pagado.Pendiente);
		List<DtoEntity> prestamosDto = new ArrayList();
		
		for (Prestamo prestamo : prestamos) {
		
			DtoEntity clienteDto= dtoUtils.convertToDto(prestamo, new PrestamoCuentaClienteDto());
			prestamosDto.add(clienteDto);
		}

		return prestamosDto;
	}
	
	public List<DtoEntity> findAllPrestamosAmortizados() {
		

		List<Prestamo> prestamos = prestamoRepo.findAllByPagado(Pagado.Pagado);
		List<DtoEntity> prestamosDto = new ArrayList();
		
		for (Prestamo prestamo : prestamos) {
		
			DtoEntity clienteDto= dtoUtils.convertToDto(prestamo, new PrestamoCuentaClienteDto());
			prestamosDto.add(clienteDto);
		}

		return prestamosDto;
	}
	
	
	//Versiones entidad
	
	public List<Prestamo> findAllPrestamosVivosEnt() {

		List<Prestamo> prestamos = prestamoRepo.findAllByPagado(Pagado.Pendiente);
		return prestamos;
	}
	
	public List<Prestamo> findAllPrestamosAmortizadosEnt() {

		List<Prestamo> prestamos = prestamoRepo.findAllByPagado(Pagado.Pagado);
		return prestamos;
	}
	
	
	//Reports
	
	public DtoEntity findClienteAndAmortizacionesByPrestamo(int id) {
		return dtoUtils.convertToDto(prestamoRepo.findById(id), new PrestamoDto());
		

	}
	
	



	
	
			
	
	
	
	
	
	
	
	

	
}
