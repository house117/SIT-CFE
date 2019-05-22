package mx.edu.ulsaoaxaca.siyt.controllers;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.ulsaoaxaca.siyt.mappers.ComentarioMapper;
import mx.edu.ulsaoaxaca.siyt.model.Comentario;
@RestController
public class ComentarioController {
	@Autowired
	private ComentarioMapper comentarioMapper;

	@GetMapping("/comentario/read")
	public List<Comentario> findAll() {
		return comentarioMapper.read();
	}

	@PostMapping("/comentario/create")
	public void create(@RequestParam int idcolaborador, @RequestParam String comentario, @Param("fecha") Date fecha) {
		comentarioMapper.create(idcolaborador, comentario, fecha);
	}

	@RequestMapping(value = "/comentario/delete/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public void delete(@PathVariable int idcolaborador) {
		System.out.println("asdasdasdASDASD");
		comentarioMapper.delete(idcolaborador);
	}

	@RequestMapping(value = "/comentario/update/")
	public void update(@RequestParam int idcolaborador, @RequestParam String comentario, @Param("fecha") Date fecha,
			@RequestParam int idcomentario) {
		comentarioMapper.update(idcolaborador, comentario, fecha, idcomentario);

	}
}
