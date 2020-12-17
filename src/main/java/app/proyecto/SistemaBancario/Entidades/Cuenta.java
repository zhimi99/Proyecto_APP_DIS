package app.proyecto.SistemaBancario.Entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Cuenta implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private int numeroCuenta;
	private Double saldo;
	private String tipoCuenta;
	private Date fechaRegistro;
	private Boolean estado;
	//private Cliente unCliente;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumeroCuenta() {
		return numeroCuenta;
	}
	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	public Double getSaldo() {
		return saldo;
	}
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	public String getTipoCuenta() {
		return tipoCuenta;
	}
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", numeroCuenta=" + numeroCuenta + ", saldo=" + saldo + ", tipoCuenta=" + tipoCuenta
				+ ", fechaRegistro=" + fechaRegistro + ", estado=" + estado +  "]";
	}
	

}
