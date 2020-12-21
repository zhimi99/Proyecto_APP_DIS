package app.proyecto.SistemaBancario.Entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TasaInteres implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private int minDias;
    private int maxDias;
	private Double tasaInteresPorcentaje;
    private Double interesGanadoValor;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMinDias() {
		return minDias;
	}
	public void setMinDias(int minDias) {
		this.minDias = minDias;
	}
	public int getMaxDias() {
		return maxDias;
	}
	public void setMaxDias(int maxDias) {
		this.maxDias = maxDias;
	}
	public Double getTasaInteresPorcentaje() {
		return tasaInteresPorcentaje;
	}
	public void setTasaInteresPorcentaje(Double tasaInteresPorcentaje) {
		this.tasaInteresPorcentaje = tasaInteresPorcentaje;
	}
	public Double getInteresGanadoValor() {
		return interesGanadoValor;
	}
	public void setInteresGanadoValor(Double interesGanadoValor) {
		this.interesGanadoValor = interesGanadoValor;
	}
	@Override
	public String toString() {
		return "TasaInteres [id=" + id + ", minDias=" + minDias + ", maxDias=" + maxDias + ", tasaInteresPorcentaje="
				+ tasaInteresPorcentaje + ", interesGanadoValor=" + interesGanadoValor + "]";
	}
    
	
}
