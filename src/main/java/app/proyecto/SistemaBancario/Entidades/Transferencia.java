package app.proyecto.SistemaBancario.Entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Transferencia implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private Date fechaRegistro;
	private Double monto;
	private String tipoTransferencia;
	private String cuentaOrigen;
	private String bancoDestino;
	private String cedulaDestino;
	private String numeroCuentaDestino;
	private String nombresClienteDestino;
	private String emailDestino;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public String getTipoTransferencia() {
		return tipoTransferencia;
	}
	public void setTipoTransferencia(String tipoTransferencia) {
		this.tipoTransferencia = tipoTransferencia;
	}
	public String getCuentaOrigen() {
		return cuentaOrigen;
	}
	public void setCuentaOrigen(String cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	public String getBancoDestino() {
		return bancoDestino;
	}
	public void setBancoDestino(String bancoDestino) {
		this.bancoDestino = bancoDestino;
	}
	public String getCedulaDestino() {
		return cedulaDestino;
	}
	public void setCedulaDestino(String cedulaDestino) {
		this.cedulaDestino = cedulaDestino;
	}
	public String getNumeroCuentaDestino() {
		return numeroCuentaDestino;
	}
	public void setNumeroCuentaDestino(String numeroCuentaDestino) {
		this.numeroCuentaDestino = numeroCuentaDestino;
	}
	public String getNombresClienteDestino() {
		return nombresClienteDestino;
	}
	public void setNombresClienteDestino(String nombresClienteDestino) {
		this.nombresClienteDestino = nombresClienteDestino;
	}
	public String getEmailDestino() {
		return emailDestino;
	}
	public void setEmailDestino(String emailDestino) {
		this.emailDestino = emailDestino;
	}
	@Override
	public String toString() {
		return "Transferencia [id=" + id + ", fechaRegistro=" + fechaRegistro + ", monto=" + monto
				+ ", tipoTransferencia=" + tipoTransferencia + ", cuentaOrigen=" + cuentaOrigen + ", bancoDestino="
				+ bancoDestino + ", cedulaDestino=" + cedulaDestino + ", numeroCuentaDestino=" + numeroCuentaDestino
				+ ", nombresClienteDestino=" + nombresClienteDestino + ", emailDestino=" + emailDestino + "]";
	}

}
