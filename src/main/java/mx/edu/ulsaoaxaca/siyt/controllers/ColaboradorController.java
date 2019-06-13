package mx.edu.ulsaoaxaca.siyt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.ulsaoaxaca.siyt.mappers.ColaboradorMapper;
import mx.edu.ulsaoaxaca.siyt.mappers.DepartamentoMapper;
import mx.edu.ulsaoaxaca.siyt.model.Colaborador;
import mx.edu.ulsaoaxaca.siyt.model.Departamento;
import mx.edu.ulsaoaxaca.siyt.model.Sesion;

@RestController
public class ColaboradorController {
	private ArrayList<Sesion> listaSesiones = new ArrayList<Sesion>();
	@Autowired
	private ColaboradorMapper colaboradorMapper;
	@Autowired
	private DepartamentoMapper departamentoMapper;

	@GetMapping("/colaborador/read")
	public List<Colaborador> read() {

		List<Colaborador> retorno = colaboradorMapper.read();
		for (int i = 0; i < retorno.size(); i++) {
			Departamento aux = departamentoMapper.readByIdColaborador(retorno.get(i).getDepartamento());
			retorno.get(i).setDepartamentos(aux.getNombre());
		}
		return retorno;
	}

	@RequestMapping(value = "/colaborador/login", method = { RequestMethod.GET, RequestMethod.POST })
	public Colaborador readByUser(@RequestParam String usuario, @RequestParam String contrasena) {

		List<Colaborador> response = colaboradorMapper.readByUser(usuario, contrasena);
		listaSesiones.add(new Sesion(response.get(0), true));
		System.out.println("usuario: " + response.get(0).getUsuario());
		System.out.println("pw: " + response.get(0).getContrasena());
		/*
		 * if (colaboradorMapper.readByUser(usuario, contrasena) != null) {
		 * System.out.println("200"); return response; }else {
		 * System.out.println("204"); return null; }
		 */
		return response.get(0);
	}

	@PostMapping("/colaborador/create")
	public List<Colaborador> create(@RequestParam String usuario, @RequestParam String contrasena,
			@RequestParam String nombre, @RequestParam String apellido1, @RequestParam String apellido2,
			@RequestParam String email, @RequestParam String puesto, @RequestParam int departamento) {
		colaboradorMapper.create(usuario, contrasena, nombre, apellido1, apellido2, email, puesto, departamento);
		// Departamento depto = departamentoMapper.readByIdColaborador(departamento);
		List<Colaborador> listaColaboradores = colaboradorMapper.read();
		for (int i = 0; i < listaColaboradores.size(); i++) {
			Departamento depto = departamentoMapper.readByIdColaborador(listaColaboradores.get(i).getDepartamento());
			listaColaboradores.get(i).setDepartamentos(depto.getNombre());
		}
		return listaColaboradores;
	}

	@RequestMapping(value = "/colaborador/delete/", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Colaborador> delete(@RequestParam int idcolaborador) {
		System.out.println("asdasdasdASDASD");
		colaboradorMapper.delete(idcolaborador);
		List<Colaborador> listaColaboradores = colaboradorMapper.read();
		for (int i = 0; i < listaColaboradores.size(); i++) {
			Departamento depto = departamentoMapper.readByIdColaborador(listaColaboradores.get(i).getDepartamento());
			listaColaboradores.get(i).setDepartamentos(depto.getNombre());
		}
		return listaColaboradores;
	}

	@RequestMapping(value = "/colaborador/update/", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Colaborador> update(@RequestParam String usuario, @RequestParam String contrasena,
			/*
			 * @RequestParam String nombre, @RequestParam String apellido1, @RequestParam
			 * String apellido2,
			 * 
			 * @RequestParam String puesto, @RequestParam int departamento,
			 */ @RequestParam int idcolaborador) {
		colaboradorMapper.update(usuario, contrasena, idcolaborador);
		return colaboradorMapper.read();
	}
}
