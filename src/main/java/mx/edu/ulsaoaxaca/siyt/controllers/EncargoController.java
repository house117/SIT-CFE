package mx.edu.ulsaoaxaca.siyt.controllers;

import java.sql.Date;
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
			Colaborador auxiliarColaborador = colaboradorMapper.getById(retorno.get(i).getColaborador());
			System.out.println("El colaborador es: " + auxiliarColaborador.getNombre());
			retorno.get(i).setColaboradors(auxiliarColaborador.getNombre() + " " + auxiliarColaborador.getApellido1()
					+ " " + auxiliarColaborador.getApellido2());
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
	public List<Encargo> create(@RequestParam String descripcion, @RequestParam Date fechainicio,
			@RequestParam Date fechafin, @RequestParam int colaborador, @RequestParam String status,
			@RequestParam int responsable, @RequestParam int idColaboradorActual) {
		encargoMapper.create(descripcion, fechainicio, fechafin, colaborador, status, responsable);
		List<Encargo> retorno = encargoMapper.readByColaborador(idColaboradorActual);
		System.out.println("EL ID DE COLABORADOR RECIBIDO ES: " + idColaboradorActual);
		for (int i = 0; i < retorno.size(); i++) {
			System.out.println("Desc tarea de colaborador: " + retorno.get(i).getDescripcion());
		}
		for (int i = 0; i < retorno.size(); i++) {
			Colaborador auxiliarColaborador = colaboradorMapper.getById(retorno.get(i).getColaborador());
			System.out.println("El colaborador es: " + auxiliarColaborador.getNombre());
			retorno.get(i).setColaboradors(auxiliarColaborador.getNombre() + " " + auxiliarColaborador.getApellido1()
					+ " " + auxiliarColaborador.getApellido2());
			Colaborador auxiliarResponsable = colaboradorMapper.getById(retorno.get(i).getResponsable());
			retorno.get(i).setResponsables(auxiliarResponsable.getNombre() + " " + auxiliarResponsable.getApellido1()
					+ " " + auxiliarResponsable.getApellido2());
		}
		Colaborador colaboradorEnviador = colaboradorMapper.getById(idColaboradorActual);
		Colaborador colaboradorReceptor = colaboradorMapper.getById(colaborador);

		// Gmail username
		final String username = colaboradorEnviador.getEmail();

		// Gmail password
		final String password = "bxD7KZAJmZSmScqQAU";

		// Receiver's email ID
		String receiver = colaboradorReceptor.getEmail();

		// Sender's email ID
		String sender = colaboradorEnviador.getEmail();

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
			message.setSubject("SIT - CFE, Nueva Tarea Asignada");

			// Body of the email
			message.setContent("<h2>Nueva tarea de SIT - CFE</h2>" + "<h3>Buen día <b>"
					+ colaboradorReceptor.getNombre() + ",</b></h3>" + " <p>Usted ha sido asignad@ como "
					+ "colaborador/a en la siguiente tarea: <br>" + descripcion
					+ ".<br><br> La cual tiene fecha límite: " + fechafin.toString() + "<br>" + "Y como responsable a: "
					+ colaboradorEnviador.getNombre() + colaboradorEnviador.getApellido1() + " "
					+ colaboradorEnviador.getApellido2() + "<br>" + " del departamento "
					+ departamentoMapper.readByIdColaborador(colaboradorEnviador.getIdcolaborador()).getNombre()
					+ "Sin más por el momento, reciba un grato saludo,</p>"
					+ "<center><img width=\"200px\" src=\"https://www.brujuleamex.com/wp-content/uploads/Pagar-Recibos-CFE-en-Aguascalientes.jpg\" alt=\"CFE\"></center>"
					+ "<center><small>Este mensaje ha sido auto-generado, por favor no responda al mismo</small></center>",
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
			Colaborador auxiliarColaborador = colaboradorMapper.getById(retorno.get(i).getColaborador());
			System.out.println("El colaborador es: " + auxiliarColaborador.getNombre());
			retorno.get(i).setColaboradors(auxiliarColaborador.getNombre() + " " + auxiliarColaborador.getApellido1()
					+ " " + auxiliarColaborador.getApellido2());
			Colaborador auxiliarResponsable = colaboradorMapper.getById(retorno.get(i).getResponsable());
			retorno.get(i).setResponsables(auxiliarResponsable.getNombre() + " " + auxiliarResponsable.getApellido1()
					+ " " + auxiliarResponsable.getApellido2());
		}
		return retorno;
	}

	@RequestMapping(value = "/encargo/update/", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Encargo> update(@RequestParam String descripcion, @RequestParam Date fechainicio,
			@RequestParam Date fechafin, @RequestParam int colaborador, @RequestParam String status,
			@RequestParam int responsable, @RequestParam int idencargo, @RequestParam int idColaboradorActual) {
		encargoMapper.update(descripcion, fechainicio, fechafin, colaborador, status, responsable, idencargo);
		List<Encargo> retorno = encargoMapper.readByColaborador(idColaboradorActual);
		System.out.println("EL ID DE COLABORADOR RECIBIDO ES: " + idColaboradorActual);
		for (int i = 0; i < retorno.size(); i++) {
			System.out.println("Desc tarea de colaborador: " + retorno.get(i).getDescripcion());
		}
		for (int i = 0; i < retorno.size(); i++) {
			Colaborador auxiliarColaborador = colaboradorMapper.getById(retorno.get(i).getColaborador());
			System.out.println("El colaborador es: " + auxiliarColaborador.getNombre());
			retorno.get(i).setColaboradors(auxiliarColaborador.getNombre() + " " + auxiliarColaborador.getApellido1()
					+ " " + auxiliarColaborador.getApellido2());
			Colaborador auxiliarResponsable = colaboradorMapper.getById(retorno.get(i).getResponsable());
			retorno.get(i).setResponsables(auxiliarResponsable.getNombre() + " " + auxiliarResponsable.getApellido1()
					+ " " + auxiliarResponsable.getApellido2());
		}
		return retorno;

	}
}
