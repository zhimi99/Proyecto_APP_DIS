package app.proyecto.SistemaBancario.negocio;

import java.util.List;


import javax.ejb.Remote;

import app.proyecto.SistemaBancario.Entidades.Cliente;



@Remote
public interface gestionCuentaRemoto {
	
	public boolean registrarCliente(Cliente cliente) throws Exception;
	public boolean registrarVehiculo( ) throws Exception;
	public List<Cliente> getClientesTipo1();
	


}
