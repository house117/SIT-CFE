package mx.edu.ulsaoaxaca.siyt.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import mx.edu.ulsaoaxaca.siyt.model.Colaborador;

@Mapper
public interface ColaboradorMapper {
	@Select("Select * from colaborador")
	public List<Colaborador> read();

	@Select("Select * from colaborador where usuario=#{usuario} AND contrasena=#{contrasena}")
	public List<Colaborador> readByUser(@Param("usuario") String usuario, @Param("contrasena") String contrasena);

	@Insert("INSERT INTO colaborador(usuario, contrasena, nombre,  apellido1, apellido2, puesto, departamento) values(#{usuario}, #{contrasena}, #{nombre}, #{apellido1}, #{apellido2}, #{puesto}, #{departamento})")
	public void create(@Param("usuario") String usuario, @Param("contrasena") String contrasena,
			@Param("nombre") String nombre, @Param("apellido1") String apellido1, @Param("apellido2") String apellido2,
			@Param("puesto") String puesto, @Param("departamento") int departamento);

	@Delete("delete from colaborador where idcolaborador = #{idcolaborador}")
	public void delete(@Param("idcolaborador") int idcolaborador);

	@Update("UPDATE usuarios SET  usuario=#{usuario}, contrasena = #{contrasena}, nombre = #{nombre}, apellido1 = #{apellido1}, apellido2 = #{apellido2}, puesto = #{puesto}, departamento = #{departamento} where idcolaborador = #{idcolaborador}")
	public void update(@Param("usuario") String usuario, @Param("contrasena") String contrasena,
			@Param("nombre") String nombre, @Param("apellido1") String apellido1, @Param("apellido2") String apellido2,
			@Param("puesto") String puesto, @Param("departamento") int departamento,
			@Param("idcolaborador") int idcolaborador);
	/* jdbc:h2:~/test */
	/* jdbc:h2:tcp://localhost/~/test */
	/*TODO jdbc:h2:tcp://192.168.1.64/~/test*/
}
