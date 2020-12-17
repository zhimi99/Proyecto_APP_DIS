package app.proyecto.SistemaBancario.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import app.proyecto.SistemaBancario.DAO.TransferenciaDAO;
import app.proyecto.SistemaBancario.Entidades.Transferencia;

@Stateless
public class TransferenciaON {

	@Inject
	TransferenciaDAO transferenciadao;
	
	public void crearTransferencia(Transferencia transferencia) {
		this.transferenciadao.crearTransferencia(transferencia);
	}
	
	
	public List<Transferencia>mostrarTransferencias() {
		return this.transferenciadao.mostrarTransferencias();
	}
}
