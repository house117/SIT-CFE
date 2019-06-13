package mx.edu.ulsaoaxaca.siyt.model;

public class Departamento {
	private int iddepartamento;
	private String nombre;
	public Departamento() {
	}
	public Departamento(int iddepartamento, String nombre) {
		super();
		this.iddepartamento = iddepartamento;
		this.nombre = nombre;
	}
	public int getIddepartamento() {
		return iddepartamento;
	}
	public void setIddepartamento(int iddepartamento) {
		this.iddepartamento = iddepartamento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
