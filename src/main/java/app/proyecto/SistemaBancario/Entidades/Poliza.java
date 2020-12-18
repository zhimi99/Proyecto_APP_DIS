package app.proyecto.SistemaBancario.Entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Poliza implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	private int plazo;
	private Double monto;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;
	@Temporal(TemporalType.TIMESTAMP)
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
	@Override
	public String toString() {
		return "Poliza [id=" + id + ", plazo=" + plazo + ", monto=" + monto + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + ", porcentajePoliza=" + porcentajePoliza + ", estadoVigencia="
				+ estadoVigencia + "]";
	}
	
	

	
}
