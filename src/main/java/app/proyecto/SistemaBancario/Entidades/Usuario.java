package app.proyecto.SistemaBancario.Entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private String cedula;
	private String nombres;
	private String apellidos;
	private String telefono;
	private String rol;
	private String correo;
	private String clave;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", telefono=" + telefono + ", rol=" + rol + ", correo=" + correo + ", clave=" + clave + "]";
	}
	
	
}
