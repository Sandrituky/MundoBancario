package es.eoi.mundobancario.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import es.eoi.mundobancario.dto.ClienteBasicDto;
import es.eoi.mundobancario.dto.ClienteCreateDto;
import es.eoi.mundobancario.dto.ClienteCuentasDto;
import es.eoi.mundobancario.dto.ClienteCuentasMovDto;
import es.eoi.mundobancario.dto.CuentaMovsDto;
import es.eoi.mundobancario.dto.DtoEntity;
import es.eoi.mundobancario.dto.MovimientoDto;
import es.eoi.mundobancario.entity.Cliente;
import es.eoi.mundobancario.entity.Cuenta;
import es.eoi.mundobancario.enums.TipoMov;
import es.eoi.mundobancario.repository.ClienteRepository;
import es.eoi.mundobancario.utils.DtoUtils;


@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository repository;
	
	@Autowired
	DtoUtils dtoUtils;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public static final String DEST = "./src/main/resources/documentos/";

	public List<DtoEntity> findAll() {

		List<Cliente> clientes = repository.findAll();
		List<DtoEntity> clientesDto = new ArrayList();
		
		for (Cliente cliente : clientes) {
		
			DtoEntity clienteDto= dtoUtils.convertToDto(cliente, new ClienteBasicDto());
			clientesDto.add(clienteDto);
		}

		return clientesDto;
	}

	public DtoEntity findClienteById(int id) {
		return dtoUtils.convertToDto(repository.findById(id), new ClienteBasicDto());
		
	}
	
	
	public DtoEntity login(String usuario, String pass) {
		
		Cliente cliente = repository.findByUsuario(usuario);
		return dtoUtils.convertToDto(cliente, new ClienteBasicDto());

			
	}
	
	public boolean isPasswordCorrect(String usuario, String pass) {

		Cliente cliente = repository.findByUsuario(usuario);
		if(passwordEncoder.matches(pass, cliente.getPass())){
			return true;
		}else {
			return false;
		}
	}
	
	public DtoEntity findCuentasByClienteId(int id) {
		return dtoUtils.convertToDto(repository.findById(id), new ClienteCuentasDto());

	}
	
	@Transactional(rollbackFor=Exception.class)
	public boolean updateClienteEmail(String email, int id) {
		Cliente cliente = repository.findById(id);
		if(Cliente.checkEmail(email)) {
			cliente.setEmail(email);
			repository.save(cliente);
			return true;
		}else {
			return false;
		}
		
	}
	
	@Transactional(rollbackFor=Exception.class)
	public boolean addCliente(ClienteCreateDto clienteDto) {
		if(Cliente.checkEmail(clienteDto.getEmail())) {
			Cliente cliente = (Cliente) dtoUtils.convertToEntity(new Cliente(), clienteDto);
			cliente.setPass(passwordEncoder.encode(clienteDto.getPass()));
			repository.save(cliente);
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
	//Reports
	
	public DtoEntity findCuentasAndMovsByClienteId(int id) {
		return dtoUtils.convertToDto(repository.findById(id), new ClienteCuentasMovDto());

	}
	

	
	public void generarClientesPdf(int id) throws FileNotFoundException {
		
		ClienteCuentasMovDto cliente = (ClienteCuentasMovDto) dtoUtils.convertToDto(repository.findById(id), new ClienteCuentasMovDto());
		
		
		
		//Crear documento
		
		
		PdfWriter writer = new PdfWriter(DEST.concat("EOI_BANK_CLIENTE_").concat(cliente.getId()+".pdf"));
		PdfDocument pdf = new PdfDocument(writer);
		
		Document document = new Document(pdf);
		
		document.add(new Paragraph("Banco EOI").setFontSize(40));
		float[] columnWidths = {3, 3};
		Table tCliente = new Table(columnWidths).setBorder(Border.NO_BORDER);
		
		tCliente.addCell("Usuario").setBorder(Border.NO_BORDER).setMargin(10);
		tCliente.addCell(cliente.getUsuario()).setBorder(Border.NO_BORDER);
		
		tCliente.addCell("Nombre").setBorder(Border.NO_BORDER).setMargin(10);
		tCliente.addCell(cliente.getNombre()).setBorder(Border.NO_BORDER);
		
		tCliente.addCell("Email").setBorder(Border.NO_BORDER).setMargin(10);
		tCliente.addCell(cliente.getEmail()).setBorder(Border.NO_BORDER);
		
		document.add(tCliente);
		
		document.add(new Paragraph("Cuentas").setFontSize(20));
		
		Table tCuentas = new Table(new float[] {1F, 1F});

		
		List<CuentaMovsDto> cuentas = cliente.getCuentas();
		
		for (int i = 0; i < cuentas.size(); i++) {

			String numCuenta=String.valueOf(cliente.getCuentas().get(i).getNumCuenta());
			String alias=cliente.getCuentas().get(i).getAlias();
			String saldo=String.valueOf(cliente.getCuentas().get(i).getSaldo()+ "€");
			
			document.add(new Paragraph("Número de cuenta: "+numCuenta).setFontSize(20));
			document.add(new Paragraph("Alias: "+alias).setFontSize(20));
			document.add(new Paragraph("Saldo: "+saldo).setFontSize(20));
			
			
			
			
			List<MovimientoDto> movimientos = cliente.getCuentas().get(i).getMovimientos();
			
			for (MovimientoDto movimiento : movimientos) {
				Table tMovs = new Table(new float[] {1F, 1F});
				tMovs.setWidthPercent(100);
						
				tMovs.addCell("Descripcion").setBorder(Border.NO_BORDER).setMargin(10);
				tMovs.addCell(movimiento.getDescripcion());
				
				tMovs.addCell("Fecha").setBorder(Border.NO_BORDER).setMargin(10);
				tMovs.addCell(String.valueOf(movimiento.getFecha()));
				
				tMovs.addCell("Importe").setBorder(Border.NO_BORDER).setMargin(10);
				tMovs.addCell(String.valueOf(movimiento.getImporte() + "€"));
				
				tMovs.addCell("Tipo").setBorder(Border.NO_BORDER).setMargin(10);
				if((movimiento.getTipoMov().getTipo()=="INGRESO" || movimiento.getTipoMov().getTipo()=="PRESTAMO")) {
					tMovs.addCell(new Cell().add(movimiento.getTipoMov().getTipo()).setFontColor(Color.GREEN));
				}else {
					tMovs.addCell(new Cell().add(movimiento.getTipoMov().getTipo()).setFontColor(Color.RED));
				}
				
				document.add(tMovs);
			}
			
			
		}
		
		
		
		document.close();
		
	}
	
	
	
	
	

	
	


}
