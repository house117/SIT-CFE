package mx.edu.ulsaoaxaca.siyt.controllers;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.ulsaoaxaca.siyt.mappers.ColaboradorMapper;
import mx.edu.ulsaoaxaca.siyt.mappers.ComentarioMapper;
import mx.edu.ulsaoaxaca.siyt.mappers.EncargoMapper;
import mx.edu.ulsaoaxaca.siyt.model.Colaborador;
import mx.edu.ulsaoaxaca.siyt.model.Comentario;
@RestController
public class ComentarioController {
	@Autowired
	private ComentarioMapper comentarioMapper;
	@Autowired
	private ColaboradorMapper colaboradorMapper;
	@Autowired
	private EncargoMapper encargoMapper;
	@GetMapping("/comentario/read")
	public List<Comentario> findAll() {
		System.out.println("Recibiendo READ comentarios");
		
		List<Comentario> retorno = comentarioMapper.read();
		for (int i = 0; i < retorno.size(); i++) {
			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			Colaborador aux = colaboradorMapper.getById(retorno.get(i).getIdcolaborador());
			System.out.println("El nommbre a devolver es: "+ aux.getNombre()+" "+aux.getApellido1()+" "+aux.getApellido2());
			retorno.get(i).setColaborador(aux.getNombre()+" "+aux.getApellido1()+" "+aux.getApellido2());
		}
		return retorno;
	}
	
	@GetMapping("/comentario/readPorEncargo")
	public List<Comentario> findByEncargo(@RequestParam int id) {
		System.out.println("Recibiendo READ de comentarios");
		List<Comentario> retorno = comentarioMapper.readByIdEncargo(id);
		for (int i = 0; i < retorno.size(); i++) {
			Colaborador aux = colaboradorMapper.getById(retorno.get(i).getIdcolaborador());
			retorno.get(i).setColaborador(aux.getNombre()+" "+aux.getApellido1()+" "+aux.getApellido2());
		}
		return retorno;
		 
	}
	@PostMapping("/comentario/create")
	public List<Comentario> create(@RequestParam("idcolaborador")  int idcolaborador, 
			@RequestParam("comentario")  String comentario, 
			@RequestParam("idencargo") int idencargo,
			@RequestParam("status")  String status) {
		System.out.println("Recibiendo POST comentario");
		comentarioMapper.createDefDate(idcolaborador, comentario, idencargo);
		encargoMapper.updateStatus(status, idencargo);
		List<Comentario> retorno = comentarioMapper.readByIdEncargo(idencargo);
		for (int i = 0; i < retorno.size(); i++) {
			Colaborador aux = colaboradorMapper.getById(retorno.get(i).getIdcolaborador());
			retorno.get(i).setColaborador(aux.getNombre()+" "+aux.getApellido1()+" "+aux.getApellido2());
			retorno.get(i).setNewStatus(encargoMapper.readStatusById(idencargo));
			
		}
		return retorno;
	}

	@RequestMapping(value = "/comentario/delete/", method = { RequestMethod.GET, RequestMethod.POST })
	public List<Comentario> delete(@RequestParam int idcomentario) {
		System.out.println("asdasdasdASDASD");
		int a = comentarioMapper.readByIdComentario(idcomentario);
		comentarioMapper.delete(idcomentario);
		return comentarioMapper.readByIdEncargo(a);
	}

	@RequestMapping(value = "/comentario/update/")
	public void update(@RequestParam int idcolaborador, @RequestParam String comentario, @Param("fecha") Date fecha,
			@RequestParam int idcomentario) {
		comentarioMapper.update(idcolaborador, comentario, fecha, idcomentario);

	}
}
