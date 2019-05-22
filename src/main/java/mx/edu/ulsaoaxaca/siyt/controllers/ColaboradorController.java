package mx.edu.ulsaoaxaca.siyt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import mx.edu.ulsaoaxaca.siyt.mappers.ColaboradorMapper;
import mx.edu.ulsaoaxaca.siyt.model.Colaborador;
import mx.edu.ulsaoaxaca.siyt.model.Sesion;


@RestController
public class ColaboradorController {
	private ArrayList<Sesion> listaSesiones;
	@Autowired
	private ColaboradorMapper colaboradorMapper;

	@GetMapping("/colaborador/read")
	public List<Colaborador> read() {
		return colaboradorMapper.read();
	}

	@RequestMapping(value = "/colaborador/login", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Colaborador> readByUser(@RequestParam String usuario, @RequestParam String contrasena) {

		List<Colaborador> response = colaboradorMapper.readByUser(usuario, contrasena);
		listaSesiones.add(new Sesion(response.get(0), true));
		System.out.println("usuario: " + response.get(0).getUsuario());
		System.out.println("pw: " + response.get(0).getContrasena());

		/*
		 * if (colaboradorMapper.readByUser(usuario, contrasena) != null) {
		 * System.out.println("200"); return response; }else {
		 * System.out.println("204"); return null; }
		 */
		return response;
	}

	@PostMapping("/colaborador/create")
	public void create(@RequestParam String usuario, @RequestParam String contrasena, @RequestParam String nombre,
			@RequestParam String apellido1, @RequestParam String apellido2, @RequestParam String puesto,
			@RequestParam int departamento) {
		colaboradorMapper.create(usuario, contrasena, nombre, apellido1, apellido2, puesto, departamento);
	}

	@RequestMapping(value = "/colaborador/delete/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public void delete(@PathVariable int idcolaborador) {
		System.out.println("asdasdasdASDASD");
		colaboradorMapper.delete(idcolaborador);
	}

	@RequestMapping(value = "/colaborador/update/")
	public void update(@RequestParam String usuario, @RequestParam String contrasena, @RequestParam String nombre,
			@RequestParam String apellido1, @RequestParam String apellido2, @RequestParam String puesto,
			@RequestParam int departamento, @RequestParam int idcolaborador) {
		colaboradorMapper.update(usuario, contrasena, nombre, apellido1, apellido2, puesto, departamento,
				idcolaborador);
	}
}