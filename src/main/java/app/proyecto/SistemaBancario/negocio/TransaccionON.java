package app.proyecto.SistemaBancario.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import app.proyecto.SistemaBancario.DAO.TransaccionDAO;
import app.proyecto.SistemaBancario.Entidades.Transaccion;

@Stateless
public class TransaccionON {

	@Inject
	TransaccionDAO transacciondao;
	
	public void crearTransaccion(Transaccion transaccion) {
		this.transacciondao.crearTransaccion(transaccion);
	}
	
	
	public List<Transaccion>mostrarTransacciones() {
		return this.transacciondao.mostrarTransacciones();
	}
}
