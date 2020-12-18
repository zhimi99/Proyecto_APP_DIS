package app.proyecto.SistemaBancario.Entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Sesion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;
	
	private int intentos;
	private Double estado;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getIntentos() {
		return intentos;
	}
	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}
	public Double getEstado() {
		return estado;
	}
	public void setEstado(Double estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Sesion [id=" + id + ", fecha=" + fecha + ", intentos=" + intentos + ", estado=" + estado + "]";
	}

	
	
	

}
