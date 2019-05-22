package mx.edu.ulsaoaxaca.siyt.mappers;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mx.edu.ulsaoaxaca.siyt.model.Comentario;
@Mapper
public interface ComentarioMapper {
	@Select("select id, nombre, semestre from materias")
	public List<Comentario> read();
	@Insert("INSERT INTO comentario(idcolaborador, comentario, fecha) values(#{idcolaborador}, #{comentario}, #{fecha})")
	public void create(@Param("idcolaborador") int idcolaborador, @Param("comentario") String comentario, @Param("fecha") Date date);
	@Delete("delete from comentario where idcomentario = #{idcomentario}")
	public void delete(@Param("idcomentario") int idcomentario);
	@Update("UPDATE usuarios SET  idcolaborador=#{idcolaborador}, comentario = #{comentario}, fecha = #{fecha} where idcomentario = #{idcomentario}")
	public void update(@Param("idcolaborador") int idcolaborador, @Param("comentario") String comentario, @Param("fecha") Date fecha, @Param("idcomentario") int idcomentario);
/*jdbc:h2:~/test*/
	/*jdbc:h2:tcp://localhost/~/test*/
}
