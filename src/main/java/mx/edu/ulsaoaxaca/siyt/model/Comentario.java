package mx.edu.ulsaoaxaca.siyt.model;

import java.sql.Date;

public class Comentario {
	private int idcomentario;
	private int idcolaborador;
	private String comentario;
	private Date fecha;

	public Comentario() {
		super();
	}

	public Comentario(int idcomentario, int idcolaborador, String comentario) {
		super();
		this.idcomentario = idcomentario;
		this.idcolaborador = idcolaborador;
		this.comentario = comentario;
	}

	public int getIdcomentario() {
		return idcomentario;
	}

	public void setIdcomentario(int idcomentario) {
		this.idcomentario = idcomentario;
	}

	public int getIdcolaborador() {
		return idcolaborador;
	}

	public void setIdcolaborador(int idcolaborador) {
		this.idcolaborador = idcolaborador;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
