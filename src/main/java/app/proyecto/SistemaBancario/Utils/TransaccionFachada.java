package app.proyecto.SistemaBancario.Utils;

import java.io.Serializable;

public class TransaccionFachada implements Serializable {
	private int cuentaorigen;
	private int cuentadestino;
	private Double monto;
	private String tipo;
	public int getCuentaorigen() {
		return cuentaorigen;
	}
	public void setCuentaorigen(int cuentaorigen) {
		this.cuentaorigen = cuentaorigen;
	}
	public int getCuentadestino() {
		return cuentadestino;
	}
	public void setCuentadestino(int cuentadestino) {
		this.cuentadestino = cuentadestino;
	}
	public Double getMonto() {
		return monto;
	}
	public void setMonto(Double monto) {
		this.monto = monto;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public String toString() {
		return "TransaccionFachada [cuentaorigen=" + cuentaorigen + ", cuentadestino=" + cuentadestino + ", monto="
				+ monto + ", tipo=" + tipo + "]";
	}

}
