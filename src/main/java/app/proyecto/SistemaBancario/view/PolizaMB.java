package app.proyecto.SistemaBancario.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import app.proyecto.SistemaBancario.Entidades.Poliza;
import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.negocio.PolizaON;

@ManagedBean
public class PolizaMB {
	@Inject
	PolizaON polizaon;
	
	private Poliza poliza;
	private List<Poliza> polizas;
	
	@PostConstruct
	public void init() {
		this.poliza = new Poliza();
		listarPolizas();
	}

	public String agregarPoliza() {
		this.polizaon.crearPoliza(poliza);
		this.poliza = null;
		return null;
	}
	
	public void listarPolizas() {
		this.polizas =  this.polizaon.mostrarPolizas();

	}

	public Poliza getPoliza() {
		return poliza;
	}

	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}

	public List<Poliza> getPolizas() {
		return polizas;
	}

	public void setPolizas(List<Poliza> polizas) {
		this.polizas = polizas;
	}
	
	

}
