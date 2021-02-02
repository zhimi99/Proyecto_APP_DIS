package app.proyecto.SistemaBancario.SERVICES;

import java.util.List;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import app.proyecto.SistemaBancario.Entidades.Cliente;
import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.Entidades.Transaccion;
import app.proyecto.SistemaBancario.Utils.TransaccionFachada;
import app.proyecto.SistemaBancario.Utils.UsuarioSesion;
import app.proyecto.SistemaBancario.negocio.ClienteON;
import app.proyecto.SistemaBancario.negocio.CuentaON;
import app.proyecto.SistemaBancario.negocio.TransaccionON;

@Path("serv")
public class ClienteServiceRest {
	@Inject
	private TransaccionON transaccionon;
	
	@Inject
	private ClienteON clienteon;
	
	@Inject
	private CuentaON cuentaon;
	
	/**
	 * Servicio Post que ppermite realizar una transferencia entre cuentas de la misma cooperativa
	 * @param fachada una fahcada que tiene los atributos necesarios para realizar una transferencia entre cuetnas de la misma cooperativa
	 * @return una respuesta si, la transaccion se realizó con exito, caso contrario un error
	 */
	@POST
	@Path("/transferirinterna")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta transferirInternamente(TransaccionFachada fachada) {
		Respuesta respuesta = null;
		try {
			respuesta = transaccionon.transferenciainterna(fachada);
		} catch (Exception e) {
			e.printStackTrace();
			respuesta.setCodigo(99);
			respuesta.setMensaje(e.getMessage());
		}
		return respuesta;
	}

	/**
	 * 
	 * @param fachada
	 * @return
	 */
	@POST
	@Path("/depositar")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta deposito(TransaccionFachada fachada) {
		Respuesta r = new Respuesta();
		try {
			r = transaccionon.deposito(fachada);
		} catch (Exception e) {
			e.printStackTrace();
			r.setCodigo(99);
			r.setMensaje(e.getMessage());
		}
		return r;
	}

	/**
	 * 
	 * @param fachada
	 * @return
	 */
	@POST
	@Path("/retirar")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta retiro(TransaccionFachada fachada) {
		Respuesta r = new Respuesta();
		try {
			r = transaccionon.retiro(fachada);
		} catch (Exception e) {
			e.printStackTrace();
			r.setCodigo(99);
			r.setMensaje(e.getMessage());
		}
		return r;
	}
	
	/**
	 * 
	 * @param usuarioSesion
	 * @return
	 */
	@POST
	@Path("/login")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta logIn(UsuarioSesion usuarioSesion) {
		Respuesta r = new Respuesta();
		try {
			r = clienteon.clienteLogIn(usuarioSesion);
		} catch (Exception e) {
			e.printStackTrace();
			r.setCodigo(99);
			r.setMensaje(e.getMessage());
		}
		return r;
	}
	
	/*public Cliente login(UsuarioSesion usuarioSesion) throws Exception {
		return clientedao.clienteLogIn(usuarioSesion);
	}*/
	
	@GET
	@Path("clientelogincorreo")
	@Produces("application/json")
	public Cliente buscarClienteLogin(@QueryParam("correo") String correo) {
		try {
			return clienteon.obtenerClienteLogin(correo);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Path("transacciones")
	@Produces("application/json")
	public List<Transaccion> getTransacciones() {
		try {
			return transaccionon.mostrarTransacciones();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	
	@GET
	@Path("clientes")
	@Produces("application/json")

	public List<Cliente> getClientes() {
		try {
			return clienteon.mostrarClientes();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	@GET
	@Path("cuentas")
	@Produces("application/json")

	public List<Cuenta> getCuentas() {
		try {
			return cuentaon.mostrarCuentas();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	
	
	/**
	 * Servicio clientecedula, devuelve un cliente segun el numero de cedula
	 * @param cedula de tipo String envía la cedula para buscar una objeto de tipo cliente
	 * @return un objeto cliente según la cedula
	 */
	@GET
	@Path("clientecedula")
	@Produces("application/json")
	public Cliente buscarClienteCedula(@QueryParam("cedula") String cedula) {
		try {
			return clienteon.buscarClienteCedula(cedula);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	
	
	
	@GET
	@Path("transaccionid")
	@Produces("application/json")
	public Transaccion buscartransaccionID(@QueryParam("id") int id) {
		try {
			return transaccionon.getTransaccion(id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	/**
	 * Metodo que permite obtener una cuetna especifica 
	 * @param id buscara a la cuenta segun su id, en este caso el id será el numero de la cuenta
	 * @return un objeto de tipo cuenta 
	 */
	@GET
	@Path("cuentaid")
	@Produces("application/json")
	public Cuenta buscarCuentaid(@QueryParam("id") int id) {
		try {
			return cuentaon.mostrarCuenta(id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}		
		
	/**
	 * Metodo que permite obtener todas las cuentas de un cliente 
	 * @param id de tipo entero, permite buscar las cuentas mediante el id del cliente
	 * @return una lista de de cuentas pertenecientes a un cliente
	 */
	@GET
	@Path("cuentasclienteid")
	@Produces("application/json")
	public List <Cuenta> buscarCuentasClienteId(@QueryParam("id") int id) {
		try {
			return cuentaon.listarCuentasCliente(id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	/**
	 * Servicio que permite obtener todas las transacciones de una cuenta especifica
	 * @param id de tipo entero, permite buscar llas transacciones de la cuenta unun id que el cliente solicite
	 * @return lista de transacciones que tienen el id de la cuenta
	 */
	@GET
	@Path("transaccionescuentaid")
	@Produces("application/json")
	public List <Transaccion> buscarTransaccionesCuenta(@QueryParam("id") int id) {
		try {
			System.out.println("En servicio en id de la cuenta >>>>>> "+ id);
			return transaccionon.listarTransaccionesCuenta(id); 
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
}
