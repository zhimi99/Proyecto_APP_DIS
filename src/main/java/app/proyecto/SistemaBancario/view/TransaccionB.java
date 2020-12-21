package app.proyecto.SistemaBancario.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.view.ViewScoped;
//import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.Entidades.Transaccion;
//import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.negocio.TransaccionON;

@Named
//@ConversationScoped
@ViewScoped
public class TransaccionB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	TransaccionON transaccionon;
	
	private Transaccion transaccion;
	
	
	private Cuenta cuenta;
	private List<Transaccion> transacciones;
	
	private int numeroCuenta;
	
	@PostConstruct
	public void init() {
		this.transaccion = new Transaccion();
		cuenta= new Cuenta();
		listarTransacciones();
	}

	public String guardarTransaccion(/*int numeroCuenta*/) {
		cuenta = transaccionon.getCuenta(numeroCuenta) ;
		transaccion.setCuenta(cuenta);
		transaccionon.nuevaTransaccion(transaccion,cuenta);
		this.transaccion = null;
		listarTransacciones();
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

	public TransaccionON getTransaccionon() {
		return transaccionon;
	}

	public void setTransaccionon(TransaccionON transaccionon) {
		this.transaccionon = transaccionon;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
	

}
