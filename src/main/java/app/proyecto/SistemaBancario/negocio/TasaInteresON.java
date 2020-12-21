package app.proyecto.SistemaBancario.negocio;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import app.proyecto.SistemaBancario.DAO.CuentaDAO;
import app.proyecto.SistemaBancario.DAO.TasaInteresDAO;
import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.Entidades.TasaInteres;

@Stateless
public class TasaInteresON {

	@Inject
	TasaInteresDAO tasaInteresdao;
	
	public void crearTasaInteres(TasaInteres tasaInteres) {
		this.tasaInteresdao.crearTasaInteres(tasaInteres);
		//this.cuentadao.crearCuenta(cuenta);
	}
	
	
	public List<TasaInteres>mostrarTasaInteres() {
		return this.tasaInteresdao.mostrarTasaIntereses();
	}
}
