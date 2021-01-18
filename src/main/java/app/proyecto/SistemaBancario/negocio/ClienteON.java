package app.proyecto.SistemaBancario.negocio;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import app.proyecto.SistemaBancario.DAO.ClienteDAO;
import app.proyecto.SistemaBancario.Entidades.Cliente;
import app.proyecto.SistemaBancario.Entidades.Sesion;
import app.proyecto.SistemaBancario.SERVICES.Respuesta;
import app.proyecto.SistemaBancario.Utils.UsuarioTemp;


@Stateless
public class ClienteON {
	@Inject
	ClienteDAO clientedao;

	public boolean crearCliente(Cliente cliente) throws Exception {
		Cliente cli = clientedao.buscarClienteCedula(cliente.getCedula());		
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
	
	public Cliente buscarClienteCedulaP(String cedula) throws Exception {
		Cliente cli = clientedao.buscarClienteCedulaP(cedula);
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
		
		return this.clientedao.mostrarClientes();
	}
	
	public Cliente login(Sesion sesion) throws Exception {

		//return uDAO.getUserbyEmailAndPassword(sesion);
		return clientedao.getUserbyEmailAndPassword(sesion);
	}
	public Cliente login(UsuarioTemp sesion) throws Exception {
		//return uDAO.getUserbyEmailAndPassword2(sesion);
		return clientedao.getUserbyEmailAndPassword2(sesion);
	}

	public Respuesta cambioContrasenia(UsuarioTemp sesion) throws Exception {
		Respuesta respuesta = new Respuesta();
	    //uDAO.cambioContrasenia(sesion);
	    clientedao.cambioContrasena(sesion);
	    respuesta.setCodigo(1);
	    respuesta.setMensaje("Ok");	
	    return respuesta;
	}


}
