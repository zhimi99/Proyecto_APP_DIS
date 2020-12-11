package app.proyecto.SistemaBancario.Entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Cliente implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
	@Column(length = 10)
	private String cedula;
	
	private String nombres;
	private String apellidos;
	private String telefono;
	private Date fechaRegistro;
	private double saldo;
	private Boolean estado;
	private String email;
	private String clave;
	
	
	
	public Cliente() {
		super();
		this.id = id;
		this.cedula = cedula;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.fechaRegistro = fechaRegistro;
		this.saldo = saldo;
		this.estado = estado;
		this.email = email;
		this.clave = clave;
	}
	
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
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Clientes [id=" + id + ", saldo=" + saldo + ", fechaRegistro=" + fechaRegistro + ", telefono=" + telefono
				+ ", apellidos=" + apellidos + ", nombres=" + nombres + ", cedula=" + cedula + ", estado=" + estado
				+ "]";
	}
	

}
