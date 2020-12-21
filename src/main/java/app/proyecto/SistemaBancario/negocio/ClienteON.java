package app.proyecto.SistemaBancario.negocio;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import app.proyecto.SistemaBancario.DAO.ClienteDAO;
import app.proyecto.SistemaBancario.Entidades.Cliente;


@Stateless
public class ClienteON {
	@Inject

	ClienteDAO clientedao;

	public boolean crearCliente(Cliente cliente) throws Exception {
		//boolean retorno;
		// System.out.println("En dao"+cliente.getCedula());
		Cliente cli = clientedao.buscarClienteCedula(cliente.getCedula());
		//System.out.println("en dao after: " + cli.getCedula());
		if (cli != null)

			throw new Exception("Cliente ya existe");
		else
			cliente.setFechaRegistro(new Date());
			clientedao.crearCliente(cliente);
		
		return true;
	}
	
	/*public void crearCliente(Cliente cliente) {
		this.clientedao.crearCliente(cliente);
	}*/

	public void eliminarCliente(String cedula) throws Exception {

		Cliente cli = clientedao.buscarClienteCedula(cedula);
		if (cli == null)
			throw new Exception("Cliente no existe");

		else

			clientedao.eliminarCliente(cedula);
	}

	public Cliente buscarCliente(String cedula) {
		return this.clientedao.buscarClienteCedula(cedula);
	}

	public Cliente buscarClienteCedula(String cedula) throws Exception {
		Cliente cli = clientedao.buscarClienteCedula(cedula);
		if (cli == null)
			throw new Exception("Cliente no existe");

		else
			return cli;

	}

	public void actualizarCliente(Cliente cliente) throws Exception {
		Cliente cli = clientedao.buscarClienteCedula(cliente.getCedula());
		if (cli == null)
			throw new Exception("Cliente no existe");

		else
			clientedao.actualizarCliente(cliente);
	}

	public List<Cliente> mostrarClientes() {
		System.out.println(this.clientedao.mostrarClientes());
		return this.clientedao.mostrarClientes();
	}

}
