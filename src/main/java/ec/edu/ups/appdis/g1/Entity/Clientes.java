package ec.edu.ups.appdis.g1.Entity;

import java.util.Date;

public class Clientes {
	private int id;
	private double saldo;
	private Date fechaRegistro;
	private String telefono;
	private String apellidos;
	private String nombres;
	private String cedula;
	private String estado;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Clientes [id=" + id + ", saldo=" + saldo + ", fechaRegistro=" + fechaRegistro + ", telefono=" + telefono
				+ ", apellidos=" + apellidos + ", nombres=" + nombres + ", cedula=" + cedula + ", estado=" + estado
				+ "]";
	}
	

}
