package app.proyecto.SistemaBancario.negocio;

import javax.ejb.Stateless;
import javax.inject.Inject;
import app.proyecto.SistemaBancario.DAO.clienteDAO;
import app.proyecto.SistemaBancario.DAO.cuentaDAO;
import app.proyecto.SistemaBancario.Entidades.Cliente;
import app.proyecto.SistemaBancario.Entidades.Cuenta;

@Stateless
public class gestionCuentaON  {
	
	@Inject
	private clienteDAO daoCliente;
	
	@Inject
	private cuentaDAO  daoCuenta;
	
	
	public boolean registrarCuenta(Cuenta cuenta, int idCliente) {
		Cliente cliente = daoCliente.read(idCliente);
		
		if(cliente != null) {
			
			//daoCuenta.insert(cuenta);
			
			
		}else {
			
			
			
		}
		
	
	return true;
	
	}
	
}
