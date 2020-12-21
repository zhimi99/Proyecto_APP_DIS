package app.proyecto.SistemaBancario.negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import app.proyecto.SistemaBancario.DAO.CuentaDAO;
import app.proyecto.SistemaBancario.DAO.TransaccionDAO;
import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.Entidades.Transaccion;

@Stateless
public class TransaccionON {

	@Inject
	TransaccionDAO transacciondao;
	
	@Inject
	CuentaDAO cuentaDao;

	
	public void nuevaTransaccion(Transaccion transaccion, Cuenta cuenta) {
		if (transaccion.getTipo().equals("Deposito")) {
			TransDeposito(transaccion, cuenta);
		} else if (transaccion.getTipo().equals("Retiro")) {
			TransRetiro(transaccion, cuenta);
		} 

	}
	
	public Cuenta getCuenta(int numeroCuenta) {
		return cuentaDao.buscarCuentaID(numeroCuenta);
	}



	
	
	public List<Transaccion>mostrarTransacciones() {
		return this.transacciondao.mostrarTransacciones();
	}
	
	
	public void TransDeposito(Transaccion transaccion, Cuenta cuenta) {
		double nuevoSaldo = cuenta.getSaldo() + transaccion.getMonto();
		transaccion.setFechaRegistro(new Date());
		cuenta.setSaldo(nuevoSaldo);
		transacciondao.crearTransaccion(transaccion);
		try {
			cuentaDao.actualizarCuenta(cuenta);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void TransRetiro(Transaccion transaccion, Cuenta cuenta) {
		if (cuenta.getSaldo() > transaccion.getMonto()) {
			double nuevoSaldo = cuenta.getSaldo() - transaccion.getMonto();
			transaccion.setFechaRegistro(new Date());
			cuenta.setSaldo(nuevoSaldo);
			transacciondao.crearTransaccion(transaccion);
			try {
				cuentaDao.actualizarCuenta(cuenta);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("La cuenta no tiene fondos suficientes para realizar la transaccion");
		}
	}
	


}
