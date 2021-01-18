package app.proyecto.SistemaBancario.negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import app.proyecto.SistemaBancario.DAO.CuentaDAO;
import app.proyecto.SistemaBancario.DAO.TransaccionDAO;
import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.Entidades.Transaccion;
import app.proyecto.SistemaBancario.SERVICES.Respuesta;
import app.proyecto.SistemaBancario.Utils.TransaccionFachada;

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

	public List<Transaccion> mostrarTransacciones() {
		return this.transacciondao.mostrarTransacciones();
	}
	
	public List<Transaccion> mostrarTransaccionesCuenta(int cuenta_numeroCuenta) {
		return this.transacciondao.listaTransacionesCuenta(cuenta_numeroCuenta);
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

	public Respuesta transferenciainterna(TransaccionFachada trx) throws Exception {

		Respuesta respuesta = null;
		Cuenta origen = cuentaDao.buscarCuenta(trx.getCuentaorigen());
		Cuenta destino = cuentaDao.buscarCuenta(trx.getCuentadestino());

		if (trx.getTipo().equals("transferencia")) {
			if (destino == null)
				throw new Exception("La cuentas no existe");

			if (trx.getCuentaorigen() != 0 && trx.getCuentadestino() != 0) {
				if (origen.getSaldo() > trx.getMonto()) {
					Double saldoOri = origen.getSaldo() - trx.getMonto();
					Double saldoDes = destino.getSaldo() + trx.getMonto();

					origen.setSaldo(saldoOri);
					destino.setSaldo(saldoDes);

					// tOri.setCuent(trx.getCuentaorigen());
					Transaccion tOri = new Transaccion();
					Cuenta co = new Cuenta();
					co.setId(trx.getCuentaorigen());
					tOri.setCuenta(co);
					tOri.setFechaRegistro(new Date());
					tOri.setTipo("transferencia");
					tOri.setMonto(trx.getMonto());

					Transaccion tDes = new Transaccion();

					// tDes.setCuent(trx.getCuentadestino());

					Cuenta cd = new Cuenta();
					cd.setId(trx.getCuentadestino());
					tDes.setCuenta(cd);
					tDes.setFechaRegistro(new Date());
					tDes.setTipo("transferencia");
					tDes.setMonto(trx.getMonto());

					origen.addTransaccion(tOri);
					destino.addTransaccion(tDes);

					transacciondao.crearTransaccion(tOri);
					transacciondao.crearTransaccion(tDes);
					cuentaDao.actualizarCuenta(origen);
					cuentaDao.actualizarCuenta(destino);

					respuesta = new Respuesta();
					respuesta.setCodigo(1);
					respuesta.setMensaje("Ok");
				} else {
					respuesta.setCodigo(90);
					respuesta.setMensaje("Fondos Insuficientes");

				}

			}
		}
		return respuesta;
	}

	public Respuesta deposito(TransaccionFachada trx) throws Exception {

		Respuesta respuesta = new Respuesta();
		Cuenta destino = cuentaDao.buscarCuenta(trx.getCuentadestino());

		if (trx.getTipo().equals("deposito")) {
			if (destino == null)
				throw new Exception("La cuentas no existe");

			if (trx.getCuentadestino() != 0) {

				Double saldoDes = destino.getSaldo() + trx.getMonto();
				destino.setSaldo(saldoDes);
				Transaccion tDes = new Transaccion();
				// tDes.setCuent(trx.getCuentadestino());
				Cuenta cd = new Cuenta();
				cd.setId(trx.getCuentadestino());
				tDes.setCuenta(cd);
				tDes.setFechaRegistro(new Date());
				tDes.setTipo("deposito");
				tDes.setMonto(trx.getMonto());

				destino.addTransaccion(tDes);

				transacciondao.crearTransaccion(tDes);

				cuentaDao.actualizarCuenta(destino);

				respuesta = new Respuesta();
				respuesta.setCodigo(1);
				respuesta.setMensaje("Ok");

			}
		}
		return respuesta;
	}

	public Respuesta retiro(TransaccionFachada trx) throws Exception {

		Respuesta respuesta = new Respuesta();
		Cuenta destino = cuentaDao.buscarCuenta(trx.getCuentadestino());

		if (trx.getTipo().equals("retiro")) {
			if (destino == null)
				throw new Exception("La cuentas no existe");

			if (trx.getCuentadestino() != 0) {
				if (destino.getSaldo() > trx.getMonto()) {
					Double saldoDes = destino.getSaldo() - trx.getMonto();
					destino.setSaldo(saldoDes);
					Transaccion tDes = new Transaccion();

					// tDes.setCuent(trx.getCuentadestino());
					Cuenta cd = new Cuenta();
					cd.setId(trx.getCuentadestino());
					tDes.setCuenta(cd);

					tDes.setFechaRegistro(new Date());
					tDes.setTipo("retiro");
					tDes.setMonto(trx.getMonto());

					destino.addTransaccion(tDes);
					transacciondao.crearTransaccion(tDes);
					cuentaDao.actualizarCuenta(destino);

					respuesta = new Respuesta();
					respuesta.setCodigo(1);
					respuesta.setMensaje("Ok");

				} else {
					respuesta.setCodigo(90);
					respuesta.setMensaje("Fondos Insuficientes");

				}

			}
		}
		return respuesta;
	}

}