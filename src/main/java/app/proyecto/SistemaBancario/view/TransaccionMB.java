package app.proyecto.SistemaBancario.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import app.proyecto.SistemaBancario.Entidades.Transaccion;
import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.negocio.TransaccionON;

@ManagedBean
public class TransaccionMB {
	@Inject
	TransaccionON transaccionon;
	
	private Transaccion transaccion;
	private List<Transaccion> transacciones;
	
	@PostConstruct
	public void init() {
		this.transaccion = new Transaccion();
		listarTransacciones();
	}

	public String agregarTransaccion() {
		this.transaccionon.crearTransaccion(transaccion);
		this.transaccion = null;
		return null;
	}
	
	public void listarTransacciones() {
		this.transacciones =  this.transaccionon.mostrarTransacciones();

	}

	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}

	public List<Transaccion> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}
	
	

}
