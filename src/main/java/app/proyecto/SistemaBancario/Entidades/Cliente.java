package app.proyecto.SistemaBancario.Entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@JsonIgnore
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_cuenta")
    private List<Cuenta> ListaCuentas;

	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public List<Cuenta> getListaCuentas() {
		return ListaCuentas;
	}

	public void setListaCuentas(List<Cuenta> listaCuentas) {
		ListaCuentas = listaCuentas;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", telefono=" + telefono + ", fechaRegistro=" + fechaRegistro + ", saldo=" + saldo + ", estado="
				+ estado + ", email=" + email + ", clave=" + clave + ", ListaCuentas=" + ListaCuentas + "]";
	}
		

}
