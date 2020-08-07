package es.eoi.mundobancario.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.eoi.mundobancario.entity.Amortizacion;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.entity.Movimiento;
import es.eoi.mundobancario.entity.Prestamo;
import es.eoi.mundobancario.entity.TipoMovimiento;
import es.eoi.mundobancario.enums.Pagado;
import es.eoi.mundobancario.enums.TipoMov;
import es.eoi.mundobancario.repository.AmortizacionRepository;
import es.eoi.mundobancario.repository.CuentaRepository;
import es.eoi.mundobancario.repository.PrestamoRepository;

@Service
public class AmortizacionServiceImpl implements AmortizacionService {

	@Autowired
	AmortizacionRepository amortizacionRepo;

	@Autowired
	PrestamoRepository prestamoRepo;

	@Autowired
	CuentaRepository cuentaRepo;

	//@Scheduled(cron = "0 0 * * *") //una vez al dia
	@Transactional(rollbackFor=Exception.class)
	public void ejecutarAmortizacionesDiarias(List<Prestamo> prestamosVivos) {

		prestamosVivos = prestamoRepo.findAllByPagado(Pagado.Pendiente);
		List<Movimiento> movimientos = new ArrayList<Movimiento>();
		List<Amortizacion> amortizacionesDone = new ArrayList<Amortizacion>();

		for (Prestamo prestamo : prestamosVivos) {
			List<Amortizacion> amortizaciones = prestamo.getAmortizaciones();
			boolean prestamoPagado=true;

			for (Amortizacion amortizacion : amortizaciones) {
				
				if (amortizacion.getFecha().isEqual(LocalDate.now()) && (amortizacion.getPagado() == Pagado.Pendiente)) {	
					// Crear interés(movimiento)
					double interes = 2 * amortizacion.getImporte() / 100;

					Movimiento movInteres = new Movimiento();
					movInteres.setDescripcion("Interes " + prestamo.getDescripcion());
					movInteres.setFecha(LocalDate.now());
					movInteres.setImporte(interes);

					TipoMovimiento tipo = new TipoMovimiento();
					tipo.setTipo(TipoMov.INTERES);
					tipo.setId(5);
					movInteres.setTipoMov(tipo);
					movimientos.add(movInteres);

					Cuenta cuenta = prestamo.getCuenta();
					cuenta.setSaldo(cuenta.getSaldo() - (amortizacion.getImporte()+interes));
					cuentaRepo.save(cuenta);

					amortizacion.setPagado(Pagado.Pagado);
					amortizacionesDone.add(amortizacion);

				}
				
				if (amortizacion.getPagado() == Pagado.Pendiente) {
					prestamoPagado = false;
				}
				
			}
			if(prestamoPagado==true) {
				prestamo.setPagado(Pagado.Pagado);
				prestamoRepo.save(prestamo);
			}

		}
		amortizacionRepo.saveAll(amortizacionesDone);

	}

}
