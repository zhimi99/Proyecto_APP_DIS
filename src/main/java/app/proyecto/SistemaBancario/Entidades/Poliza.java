package app.proyecto.SistemaBancario.Entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Poliza implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private int plazo;
	private Double monto;
	private Date fechaInicio;
	private Date fechaFin;
	private Double porcentajePoliza;
	private Double estadoVigencia;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlazo() {
		return plazo;
	}
	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Double getPorcentajePoliza() {
		return porcentajePoliza;
	}
	public void setPorcentajePoliza(Double porcentajePoliza) {
		this.porcentajePoliza = porcentajePoliza;
	}
	public Double getEstadoVigencia() {
		return estadoVigencia;
	}
	public void setEstadoVigencia(Double estadoVigencia) {
		this.estadoVigencia = estadoVigencia;
	}

	
}
