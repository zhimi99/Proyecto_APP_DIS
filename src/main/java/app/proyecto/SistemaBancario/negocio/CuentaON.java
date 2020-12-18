package app.proyecto.SistemaBancario.negocio;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import app.proyecto.SistemaBancario.DAO.CuentaDAO;
import app.proyecto.SistemaBancario.Entidades.Cuenta;

@Stateless
public class CuentaON {

	@Inject
	CuentaDAO cuentadao;
	
	public void crearCuenta(Cuenta cuenta) {
		this.cuentadao.crearCuenta(cuenta);
	}
	
	
	public List<Cuenta>mostrarCuentas() {
		return this.cuentadao.mostrarCuentas();
	}
}
