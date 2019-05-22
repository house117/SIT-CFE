package mx.edu.ulsaoaxaca.siyt.mappers;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mx.edu.ulsaoaxaca.siyt.model.Encargo;
@Mapper
public interface EncargoMapper {
	@Select("select id, nombre, semestre from materias")
	public List<Encargo> read();

	@Insert("INSERT INTO Encargo(descripcion, fechainicio, fechafin, colaborador, status, responsable) "
			+ "values(#{descripcion}, #{fechainicio}, #{fechafin}, #{colaborador}, #{status}, #{responsable})")
	public void create(@Param("descripcion") String descripcion, @Param("fechainicio") Date fechainicio,
			@Param("fechafin") Date fechafin, @Param("colaborador") int colaborador, @Param("status") String status,
			@Param("responsable") int responsable);

	@Delete("delete from comentario where idcomentario = #{idcomentario}")
	public void delete(@Param("idcomentario") int idcomentario);

	@Update("UPDATE usuarios SET  descripcion = #{descripcion}, fechainicio = #{fechainicio}, fechafin = #{fechafin}, "
			+ "colaborador = #{colaborador}, status = #{status}, responsable = #{responsable} where idencargo = #{idencargo}")
	public void update(@Param("descripcion") String descripcion, @Param("fechainicio") Date fechainicio,
			@Param("fechafin") Date fechafin, @Param("colaborador") int colaborador, @Param("status") String status,
			@Param("responsable") int responsable, @Param("idencargo") int idencargo);
	/* jdbc:h2:~/test */
	/* jdbc:h2:tcp://localhost/~/test */
}