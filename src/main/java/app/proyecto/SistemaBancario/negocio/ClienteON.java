package app.proyecto.SistemaBancario.negocio;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import app.proyecto.SistemaBancario.DAO.ClienteDAO;
import app.proyecto.SistemaBancario.Entidades.Cliente;
import app.proyecto.SistemaBancario.Entidades.Sesion;
import app.proyecto.SistemaBancario.SERVICES.Respuesta;
import app.proyecto.SistemaBancario.Utils.UsuarioSesion;


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
	
	public Respuesta clienteLogIn(UsuarioSesion usuarioSesion) throws Exception{
		System.out.println("usuario sesion"+usuarioSesion.toString());
		Respuesta respuesta= new Respuesta();
		Cliente  cliente=this.clientedao.clienteLogIn(usuarioSesion);
		System.out.println(cliente.getCorreo());
		if (cliente.getCorreo()!=null) {
			respuesta.setCodigo(200);
			respuesta.setMensaje("ok");
		}else  {
			respuesta.setCodigo(500);
			respuesta.setMensaje("Credenciales incorrectas");
		}
		
		
		return respuesta;
	}
	/**
	 * Metodo que permite obtener un cliente a travez de su correo y contraseña
	 * @param usuarioSesion es una fachada que tiene los datos necesarios de una sesison de cliente
	 * @return Un objeto de tipo cliente 
	 * @throws Exception
	 */
	public Cliente obtenerClienteLogin(String correo) throws Exception {
		//UsuarioSesion usuarioSesion = new UsuarioSesion();
		//Cliente cliente = this.clientedao.buscarClienteCorreo(correo);
		//System.out.println(cliente.getCorreo()+"      "+cliente.getClave() );
		//usuarioSesion.setCorreo(cliente.getCorreo());
		//usuarioSesion.setClave(cliente.getClave());
		return this.clientedao.buscarClienteCorreo(correo);//this.clientedao.clienteLogIn(usuarioSesion);
	}
	/*public Cliente login(UsuarioSesion sesion) throws Exception {
		//return uDAO.getUserbyEmailAndPassword2(sesion);
		return clientedao.getUserbyEmailAndPassword2(sesion);
	}*/

	public Respuesta cambioContrasenia(UsuarioSesion sesion) throws Exception {
		Respuesta respuesta = new Respuesta();
	    //uDAO.cambioContrasenia(sesion);
	    clientedao.cambioContrasena(sesion);
	    respuesta.setCodigo(1);
	    respuesta.setMensaje("Ok");	
	    return respuesta;
	}


}
