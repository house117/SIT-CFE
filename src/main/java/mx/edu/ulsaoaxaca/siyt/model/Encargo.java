package mx.edu.ulsaoaxaca.siyt.model;

import java.sql.Date;

public class Encargo {
	private int idencargo;
	private String descripcion;
	private Date fechainicio;
	private Date fechafin;
	private int colaborador;
	private String status;
	private int responsable;

	public Encargo() {
		super();
	}

	public Encargo(int idencargo, String descripcion, Date fechainicio, Date fechafin, int colaborador, String status,
			int responsable) {
		super();
		this.idencargo = idencargo;
		this.descripcion = descripcion;
		this.fechainicio = fechainicio;
		this.fechafin = fechafin;
		this.colaborador = colaborador;
		this.status = status;
		this.responsable = responsable;
	}

	public int getIdencargo() {
		return idencargo;
	}

	public void setIdencargo(int idencargo) {
		this.idencargo = idencargo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	public Date getFechafin() {
		return fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public int getColaborador() {
		return colaborador;
	}

	public void setColaborador(int colaborador) {
		this.colaborador = colaborador;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getResponsable() {
		return responsable;
	}

	public void setResponsable(int responsable) {
		this.responsable = responsable;
	}

}
