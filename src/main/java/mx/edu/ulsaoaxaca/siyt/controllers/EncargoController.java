package mx.edu.ulsaoaxaca.siyt.controllers;

import java.sql.Date;
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

	/*@GetMapping("/encargo/read")
	public List<Encargo> read() {
		List<Encargo> retorno = encargoMapper.read();
		for (int i = 0; i < retorno.size(); i++) {
			Colaborador auxiliarColaborador = colaboradorMapper.getById(retorno.get(i).getColaborador());
			retorno.get(i).setColaboradors(auxiliarColaborador.getNombre() + " " + auxiliarColaborador.getApellido1()
					+ " " + auxiliarColaborador.getApellido2());
			Colaborador auxiliarResponsable = colaboradorMapper.getById(retorno.get(i).getColaborador());
			retorno.get(i).setResponsables(auxiliarResponsable.getNombre() + " " + auxiliarResponsable.getApellido1()
					+ " " + auxiliarResponsable.getApellido2());
		}
		return retorno;

	}*/
	
	
	@GetMapping("/encargo/read")
	public List<Encargo> read(@RequestParam int id) {
		List<Encargo> retorno = encargoMapper.readByColaborador(id);
		System.out.println("EL ID DE COLABORADOR RECIBIDO ES: "+id);
		for (int i = 0; i < retorno.size(); i++) {
			System.out.println("Desc tarea de colaborador: "+retorno.get(i).getDescripcion());
		}
		for (int i = 0; i < retorno.size(); i++) {
			Colaborador auxiliarColaborador = colaboradorMapper.getById(retorno.get(i).getColaborador());
			System.out.println("El colaborador es: "+auxiliarColaborador.getNombre());
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
		System.out.println("EL ID DE COLABORADOR RECIBIDO ES: "+idColaboradorActual);
		for (int i = 0; i < retorno.size(); i++) {
			System.out.println("Desc tarea de colaborador: "+retorno.get(i).getDescripcion());
		}
		for (int i = 0; i < retorno.size(); i++) {
			Colaborador auxiliarColaborador = colaboradorMapper.getById(retorno.get(i).getColaborador());
			System.out.println("El colaborador es: "+auxiliarColaborador.getNombre());
			retorno.get(i).setColaboradors(auxiliarColaborador.getNombre() + " " + auxiliarColaborador.getApellido1()
					+ " " + auxiliarColaborador.getApellido2());
			Colaborador auxiliarResponsable = colaboradorMapper.getById(retorno.get(i).getResponsable());
			retorno.get(i).setResponsables(auxiliarResponsable.getNombre() + " " + auxiliarResponsable.getApellido1()
					+ " " + auxiliarResponsable.getApellido2());
		}
		return retorno;

	}

	@RequestMapping(value = "/encargo/delete/", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Encargo> delete(@RequestParam int idencargo , @RequestParam int idColaboradorActual) {
		System.out.println("asdasdasdASDASD");
		// hay que eliminar todos sus comentarios xd
		encargoMapper.deleteComments(idencargo);
		encargoMapper.delete(idencargo);
		List<Encargo> retorno = encargoMapper.readByColaborador(idColaboradorActual);
		for (int i = 0; i < retorno.size(); i++) {
			System.out.println("Desc tarea de colaborador: "+retorno.get(i).getDescripcion());
		}
		for (int i = 0; i < retorno.size(); i++) {
			Colaborador auxiliarColaborador = colaboradorMapper.getById(retorno.get(i).getColaborador());
			System.out.println("El colaborador es: "+auxiliarColaborador.getNombre());
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
		System.out.println("EL ID DE COLABORADOR RECIBIDO ES: "+idColaboradorActual);
		for (int i = 0; i < retorno.size(); i++) {
			System.out.println("Desc tarea de colaborador: "+retorno.get(i).getDescripcion());
		}
		for (int i = 0; i < retorno.size(); i++) {
			Colaborador auxiliarColaborador = colaboradorMapper.getById(retorno.get(i).getColaborador());
			System.out.println("El colaborador es: "+auxiliarColaborador.getNombre());
			retorno.get(i).setColaboradors(auxiliarColaborador.getNombre() + " " + auxiliarColaborador.getApellido1()
					+ " " + auxiliarColaborador.getApellido2());
			Colaborador auxiliarResponsable = colaboradorMapper.getById(retorno.get(i).getResponsable());
			retorno.get(i).setResponsables(auxiliarResponsable.getNombre() + " " + auxiliarResponsable.getApellido1()
					+ " " + auxiliarResponsable.getApellido2());
		}
		return retorno;

	}
}
