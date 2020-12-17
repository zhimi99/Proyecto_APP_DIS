package app.proyecto.SistemaBancario.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.negocio.CuentaON;

@ManagedBean
public class CuentaMB {
	@Inject
	CuentaON cuentaon;
	
	private Cuenta cuenta;
	private List<Cuenta> cuentas;
	
	@PostConstruct
	public void init() {
		this.cuenta= new Cuenta();
		
		listarCuentas();
	}

	public String agregarCuenta() {
		this.cuentaon.crearCuenta(cuenta);
		this.cuenta = null;
		return null;
	}
	
	public void listarCuentas() {
		this.cuentas =this.cuentaon.mostrarCuentas();
	}

	
	
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public List<Cuenta> getCuentas() {
		return cuentas;
	}
	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	

}
