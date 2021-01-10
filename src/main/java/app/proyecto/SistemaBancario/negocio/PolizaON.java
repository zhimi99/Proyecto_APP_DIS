package app.proyecto.SistemaBancario.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import app.proyecto.SistemaBancario.DAO.PolizaDAO;
import app.proyecto.SistemaBancario.Entidades.Poliza;

@Stateless
public class PolizaON {
	@Inject
	PolizaDAO polizadao;
	
	public void crearPoliza(Poliza poliza) {
		this.polizadao.crearPoliza(poliza);
	}
	
	
	public List<Poliza>mostrarPolizas() {
		return this.polizadao.mostrarPolizas();
	}


}