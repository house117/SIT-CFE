package mx.edu.ulsaoaxaca.siyt.model;

import java.sql.Date;

public class Colaborador {
	private int idcolaborador;
	private String usuario;
	private String contrasena;
	private String nombre;

	private String apellido1;
	private String apellido2;
	private String puesto;
	private int departamento;
	private String departamentos;
	private Date fechaalta;
	
	
	public Colaborador(int idcolaborador, String usuario, String contrasena, String nombre, String apellido1,
			String apellido2, String puesto, int departamento, String departamentos, Date fechaalta) {
		super();
		this.idcolaborador = idcolaborador;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.puesto = puesto;
		this.departamento = departamento;
		this.departamentos = departamentos;
		this.fechaalta = fechaalta;
	}

	public Colaborador() {
		super();
	}

	public Colaborador(int idcolaborador, String usuario, String contrasena, String nombre, String apellido1,
			String apellido2, String puesto, int departamento, Date fechaalta) {
		super();
		this.idcolaborador = idcolaborador;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.puesto = puesto;
		this.departamento = departamento;
		this.fechaalta = fechaalta;
	}

	public int getIdcolaborador() {
		return idcolaborador;
	}

	public void setIdcolaborador(int idcolaborador) {
		this.idcolaborador = idcolaborador;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public int getDepartamento() {
		return departamento;
	}

	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}

	public Date getFechaalta() {
		return fechaalta;
	}

	public void setFechaalta(Date fechaalta) {
		this.fechaalta = fechaalta;
	}

	public String getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(String departamentos) {
		this.departamentos = departamentos;
	}
	

}
