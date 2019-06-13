package mx.edu.ulsaoaxaca.siyt.model;

import java.sql.Date;

public class Comentario {
	private int idcomentario;
	private int idcolaborador;
	private String colaborador;
	private String comentario;
	private Date fecha;
	private String newStatus;
	
	
	public Comentario(int idcomentario, int idcolaborador, String colaborador, String comentario, Date fecha,
			String newStatus) {
		super();
		this.idcomentario = idcomentario;
		this.idcolaborador = idcolaborador;
		this.colaborador = colaborador;
		this.comentario = comentario;
		this.fecha = fecha;
		this.newStatus = newStatus;
	}

	public Comentario(int idcomentario, int idcolaborador, String colaborador, String comentario, Date fecha) {
		super();
		this.idcomentario = idcomentario;
		this.idcolaborador = idcolaborador;
		this.colaborador = colaborador;
		this.comentario = comentario;
		this.fecha = fecha;
	}

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

	public String getColaborador() {
		return colaborador;
	}

	public void setColaborador(String colaborador) {
		this.colaborador = colaborador;
	}

	public String getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}

}
