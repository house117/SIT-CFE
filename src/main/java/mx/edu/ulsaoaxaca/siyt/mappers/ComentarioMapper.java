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
import mx.edu.ulsaoaxaca.siyt.model.Departamento;
@Mapper
public interface ComentarioMapper {
	@Select("select * from comentario")
	public List<Comentario> read();
	
	@Select("select * from comentario where idencargo = #{idencargo}")
	public List<Comentario> readByIdEncargo(@Param("idencargo") int idencargo);
	
	
	@Select("select idencargo from comentario where idcomentario = #{idcomentario}")
	public int readByIdComentario(@Param("idcomentario") int idcomentario);
	
	@Insert("INSERT INTO comentario(idcolaborador, comentario, fecha, idencargo) values(#{idcolaborador}, #{comentario}, #{fecha}, #{idencargo})")
	public void create(@Param("idcolaborador") int idcolaborador, @Param("comentario") String comentario, @Param("fecha") Date date,@Param("idencargo") int idencargo);
	
	@Insert("INSERT INTO comentario(idcolaborador, comentario, idencargo) values(#{idcolaborador}, #{comentario}, #{idencargo})")
	public void createDefDate(@Param("idcolaborador") int idcolaborador, @Param("comentario") String comentario, @Param("idencargo") int idencargo);
	@Delete("delete from comentario where idcomentario = #{idcomentario}")
	public void delete(@Param("idcomentario") int idcomentario);
	
	@Update("UPDATE usuarios SET  idcolaborador=#{idcolaborador}, comentario = #{comentario}, fecha = #{fecha} where idcomentario = #{idcomentario}")
	public void update(@Param("idcolaborador") int idcolaborador, @Param("comentario") String comentario, @Param("fecha") Date fecha, @Param("idcomentario") int idcomentario);
/*jdbc:h2:~/test*/
	/*jdbc:h2:tcp://localhost/~/test*/
}
