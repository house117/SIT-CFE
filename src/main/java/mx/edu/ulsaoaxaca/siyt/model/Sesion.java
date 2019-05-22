package mx.edu.ulsaoaxaca.siyt.model;

public class Sesion {
	private Colaborador colaborador;
	private boolean sesion = false;

	public Sesion() {
		super();
	}
	public Sesion(Colaborador colaborador, boolean sesion) {
		super();
		this.colaborador = colaborador;
		this.sesion = sesion;
	}
	
	public void iniciarSesion() {
		this.sesion = true;
	}
	public void cerrarSesion() {
		this.sesion = false;
	}
	public boolean estaEnSesion() {
		return this.sesion;
	}
	public Colaborador getColaborador() {
		return colaborador;
	}
	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}
	public boolean isSesion() {
		return sesion;
	}
	public void setSesion(boolean sesion) {
		this.sesion = sesion;
	}
	
	
	
}
