package mx.edu.ulsaoaxaca.siyt.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.ulsaoaxaca.siyt.mappers.EncargoMapper;
import mx.edu.ulsaoaxaca.siyt.model.Encargo;
@RestController
public class EncargoController {
	@Autowired
	private EncargoMapper encargoMapper;

	@GetMapping("/encargo/read")
	public List<Encargo> read() {
		return encargoMapper.read();
	}

	@PostMapping("/encargo/create")
	public void create(@RequestParam String descripcion, @RequestParam Date fechainicio, @RequestParam Date fechafin,
			@RequestParam int colaborador, @RequestParam String status, @RequestParam int responsable) {
		encargoMapper.create(descripcion, fechainicio, fechafin, colaborador, status, responsable);
	}

	@RequestMapping(value = "/encargo/delete/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public void delete(@PathVariable int idencargo) {
		System.out.println("asdasdasdASDASD");
		encargoMapper.delete(idencargo);
	}

	@RequestMapping(value = "/encargo/update/")
	public void update(@RequestParam String descripcion, @RequestParam Date fechainicio, @RequestParam Date fechafin,
			@RequestParam int colaborador, @RequestParam String status, @RequestParam int responsable,
			@RequestParam int idencargo) {
		encargoMapper.update(descripcion, fechainicio, fechafin, colaborador, status, responsable, idencargo);

	}
}
