package mx.edu.ulsaoaxaca.siyt.controllers;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.ulsaoaxaca.siyt.mappers.ColaboradorMapper;
import mx.edu.ulsaoaxaca.siyt.mappers.DepartamentoMapper;
import mx.edu.ulsaoaxaca.siyt.mappers.EncargoMapper;
import mx.edu.ulsaoaxaca.siyt.model.Colaborador;
import mx.edu.ulsaoaxaca.siyt.model.Departamento;
import mx.edu.ulsaoaxaca.siyt.model.Encargo;

@RestController
public class EncargoController {
	@Autowired
	private EncargoMapper encargoMapper;
	@Autowired
	private DepartamentoMapper departamentoMapper;
	@Autowired
	private ColaboradorMapper colaboradorMapper;

	/*
	 * @GetMapping("/encargo/read") public List<Encargo> read() { List<Encargo>
	 * retorno = encargoMapper.read(); for (int i = 0; i < retorno.size(); i++) {
	 * Colaborador auxiliarColaborador =
	 * colaboradorMapper.getById(retorno.get(i).getColaborador());
	 * retorno.get(i).setColaboradors(auxiliarColaborador.getNombre() + " " +
	 * auxiliarColaborador.getApellido1() + " " +
	 * auxiliarColaborador.getApellido2()); Colaborador auxiliarResponsable =
	 * colaboradorMapper.getById(retorno.get(i).getColaborador());
	 * retorno.get(i).setResponsables(auxiliarResponsable.getNombre() + " " +
	 * auxiliarResponsable.getApellido1() + " " +
	 * auxiliarResponsable.getApellido2()); } return retorno;
	 * 
	 * }
	 */

	@GetMapping("/encargo/read")
	public List<Encargo> read(@RequestParam int id) {
		List<Encargo> retorno = encargoMapper.readByColaborador(id);
		System.out.println("EL ID DE COLABORADOR RECIBIDO ES: " + id);
		for (int i = 0; i < retorno.size(); i++) {
			System.out.println("Desc tarea de colaborador: " + retorno.get(i).getDescripcion());
		}
		for (int i = 0; i < retorno.size(); i++) {
			Colaborador auxiliarResponsable = colaboradorMapper.getById(retorno.get(i).getResponsable());
			retorno.get(i).setResponsables(auxiliarResponsable.getNombre() + " " + auxiliarResponsable.getApellido1()
					+ " " + auxiliarResponsable.getApellido2());
		}
		return retorno;

	}

	@GetMapping("/encargo/getdepto")
	public List<Departamento> readDepto() {
		return departamentoMapper.read();
	}

	@PostMapping("/encargo/create")
		public List<Encargo> create(@RequestParam String descripcion,
		@RequestParam String status,
			@RequestParam int responsable, @RequestParam int idColaboradorActual) {
		encargoMapper.create(descripcion, status, responsable);
		List<Encargo> retorno = encargoMapper.readByColaborador(idColaboradorActual);
		System.out.println("EL ID DE COLABORADOR RECIBIDO ES: " + idColaboradorActual);
		for (int i = 0; i < retorno.size(); i++) {
			System.out.println("Desc tarea de colaborador: " + retorno.get(i).getDescripcion());
		}
		for (int i = 0; i < retorno.size(); i++) {
			Colaborador auxiliarResponsable = colaboradorMapper.getById(retorno.get(i).getResponsable());
			retorno.get(i).setResponsables(auxiliarResponsable.getNombre() + " " + auxiliarResponsable.getApellido1()
					+ " " + auxiliarResponsable.getApellido2());
		}
		Colaborador colaboradorEnviador = colaboradorMapper.getById(idColaboradorActual);

		// Gmail username
		final String username = "arropandoalmundo@gmail.com";

		// Gmail password
		final String password = "house117!";

		// Receiver's email ID
		String receiver = colaboradorEnviador.getEmail();

		// Sender's email ID
		String sender = "arropandoalmundo@gmail.com";

		// Sending email from gmail
		String host = "smtp.gmail.com";

		// Port of SMTP
		String port = "587";

		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);

		// Create session object passing properties and authenticator instance
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create MimeMessage object
			MimeMessage message = new MimeMessage(session);

			// Set the Senders mail to From
			message.setFrom(new InternetAddress(sender));

			// Set the recipients email address
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

			// Subject of the email
			message.setSubject("Su petición ha sido recibida");

			// Body of the email
			Calendar fecha = new GregorianCalendar();
	        //Obtenemos el valor del año, mes, día,
	        //hora, minuto y segundo del sistema
	        //usando el método get y el parámetro correspondiente
	        int año = fecha.get(Calendar.YEAR);
	        int mes = fecha.get(Calendar.MONTH);
	        int dia = fecha.get(Calendar.DAY_OF_MONTH);
	        int hora = fecha.get(Calendar.HOUR_OF_DAY);
	        int minuto = fecha.get(Calendar.MINUTE);
	        int segundo = fecha.get(Calendar.SECOND);
	        System.out.println("Fecha Actual: "
	                           + dia + "/" + (mes+1) + "/" + año);
			message.setContent("<!DOCTYPE html>"
					+ "<head>\n" + 
					"        <meta charset=\"utf-8\">"
					+ "</head>"
					+ "<body>"
					+ "<h2>Nueva petición en arropando al mundo</h2>" + "<h3>Buen día, <b>"
					+ colaboradorEnviador.getNombre() + ",</b></h3>" + " <p>Usted ha creado  "
					+ " la siguiente preticion: <br>" + descripcion
					+ ".<br><br> La cual ha sido recibida en la fecha: " + dia + "/" + (mes+1) + "/" + año + "<br>"
					+ "En breve nos pondremos en contacto</p>"
					+ "<center><img width=\"200px\" src=\"http://img.fenixzone.net/i/ECsZurm.png\"></center>"
					+ "<center><small>Este mensaje ha sido auto-generado, por favor no responda al mismo</small></center>"
					+ "</body>"
					+ "</html>",
					"text/html");
			// Send email.
			Transport.send(message);
			System.out.println("email enviado correctamente");
		} catch (MessagingException me) {
			me.printStackTrace();
		}
		return retorno;

	}

	@RequestMapping(value = "/encargo/delete/", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Encargo> delete(@RequestParam int idencargo, @RequestParam int idColaboradorActual) {
		System.out.println("asdasdasdASDASD");
		// hay que eliminar todos sus comentarios xd
		encargoMapper.deleteComments(idencargo);
		encargoMapper.delete(idencargo);
		List<Encargo> retorno = encargoMapper.readByColaborador(idColaboradorActual);
		for (int i = 0; i < retorno.size(); i++) {
			System.out.println("Desc tarea de colaborador: " + retorno.get(i).getDescripcion());
		}
		for (int i = 0; i < retorno.size(); i++) {
			Colaborador auxiliarResponsable = colaboradorMapper.getById(retorno.get(i).getResponsable());
			retorno.get(i).setResponsables(auxiliarResponsable.getNombre() + " " + auxiliarResponsable.getApellido1()
					+ " " + auxiliarResponsable.getApellido2());
		}
		return retorno;
	}

	@RequestMapping(value = "/encargo/update/", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Encargo> update(@RequestParam String descripcion, @RequestParam Date fechainicio,
			 @RequestParam String status,
			@RequestParam int responsable, @RequestParam int idencargo, @RequestParam int idColaboradorActual) {
		encargoMapper.update(descripcion, fechainicio, status, responsable, idencargo);
		List<Encargo> retorno = encargoMapper.readByColaborador(idColaboradorActual);
		System.out.println("EL ID DE COLABORADOR RECIBIDO ES: " + idColaboradorActual);
		for (int i = 0; i < retorno.size(); i++) {
			System.out.println("Desc tarea de colaborador: " + retorno.get(i).getDescripcion());
		}
		for (int i = 0; i < retorno.size(); i++) {
			Colaborador auxiliarResponsable = colaboradorMapper.getById(retorno.get(i).getResponsable());
			retorno.get(i).setResponsables(auxiliarResponsable.getNombre() + " " + auxiliarResponsable.getApellido1()
					+ " " + auxiliarResponsable.getApellido2());
		}
		return retorno;

	}
}
