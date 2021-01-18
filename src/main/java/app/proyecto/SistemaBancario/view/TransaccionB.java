package app.proyecto.SistemaBancario.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
//import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import app.proyecto.SistemaBancario.DAO.CuentaDAO;
import app.proyecto.SistemaBancario.Entidades.Cliente;
import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.Entidades.Transaccion;
import app.proyecto.SistemaBancario.Utils.TransaccionFachada;
import app.proyecto.SistemaBancario.negocio.CuentaON;
//import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.negocio.TransaccionON;

@Named
@ViewScoped
public class TransaccionB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	TransaccionON transaccionon;
	
	@Inject 
	CuentaON cuentaon;
	@Inject 
	CuentaDAO cuentadao;
	
	private Transaccion transaccion;
	
	
	private Cuenta cuenta;
	private List<Transaccion> transacciones;
	
	private int numeroCuenta;
	
	@PostConstruct
	public void init() {
		this.transaccion = new Transaccion();
		listarTransacciones();
	}

	public String guardarTransaccion(/*int numeroCuenta*/) throws Exception {

		TransaccionFachada trx= new TransaccionFachada();
		trx.setCuentadestino(numeroCuenta);
		trx.setMonto(transaccion.getMonto());
		if (transaccion.getTipo().equals("deposito")) {
			trx.setTipo("deposito");
			this.transaccionon.deposito(trx);
		}else if (transaccion.getTipo().equals("retiro")) {
			trx.setTipo("retiro");
			this.transaccionon.retiro(trx);
		}
		
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
	 public void onRowSelect(SelectEvent<Cliente> event) {
	        FacesMessage msg = new FacesMessage("Cliente Selected", event.getObject().getCedula());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	 
	    public void onRowUnselect(UnselectEvent<Cliente> event) {
	        FacesMessage msg = new FacesMessage("Cliente Unselected", event.getObject().getCedula());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	

}
