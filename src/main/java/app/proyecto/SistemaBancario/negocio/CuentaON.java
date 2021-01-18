package app.proyecto.SistemaBancario.negocio;

import java.util.Date;
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
		cuenta.setFechaRegistro(new Date());
		this.cuentadao.crearCuenta(cuenta);
	}

	public List<Cuenta> mostrarCuentas() {
		return this.cuentadao.mostrarCuentas();
	}

	public List<Cuenta> mostrarCuentasID(int id) {
		return this.cuentadao.mostrarCuentasClienteID(id);
	}
	public Cuenta mostrarCuenta(int id) {
		return this.cuentadao.buscarCuenta(id);
	}
}