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
import app.proyecto.SistemaBancario.negocio.ClienteON;
import app.proyecto.SistemaBancario.negocio.CuentaON;
import app.proyecto.SistemaBancario.negocio.TransaccionON;

@Path("serv")
public class ClienteServiceRest {
	@Inject
	private TransaccionON on;
	
	@Inject
	private ClienteON clienteon;
	
	@Inject
	private CuentaON cuentaon;
	
	@POST
	@Path("/transferir")
	@Produces("application/json")
	@Consumes("application/json")

	public Respuesta transacciones(TransaccionFachada trx) {
		Respuesta r = new Respuesta();
		try {
			r = on.transferenciainterna(trx);
		} catch (Exception e) {
			e.printStackTrace();
			r.setCodigo(99);
			r.setMensaje(e.getMessage());
		}
		return r;
	}

	@POST
	@Path("/depositar")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta deposito(TransaccionFachada trx) {
		Respuesta r = new Respuesta();
		try {
			r = on.deposito(trx);
		} catch (Exception e) {
			e.printStackTrace();
			r.setCodigo(99);
			r.setMensaje(e.getMessage());
		}
		return r;
	}

	@POST
	@Path("/retirar")
	@Produces("application/json")
	@Consumes("application/json")
	public Respuesta retiro(TransaccionFachada trx) {
		Respuesta r = new Respuesta();
		try {
			r = on.retiro(trx);
		} catch (Exception e) {
			e.printStackTrace();
			r.setCodigo(99);
			r.setMensaje(e.getMessage());
		}
		return r;
	}
	
	@GET
	@Path("transacciones")
	@Produces("application/json")
	public List<Transaccion> getTransacciones() {
		try {
			return on.mostrarTransacciones();
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
	
	/**
	 * REISAR
	 * @param cedula
	 * @return
	 */
	@GET
	@Path("clientecedulap")
	@Produces("application/json")
	public Cliente buscarClienteCedulaP(@QueryParam("cedula") String cedula) {
		try {
			return clienteon.buscarClienteCedulaP(cedula);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	/**
	 * revisar
	 * @param cliente_cuenta
	 * @return
	 */
	@GET
	@Path("clientecuenta")
	@Produces("application/json")
	public List<Cuenta> buscarClienteCuenta(@QueryParam("cliente_cuenta") int cliente_cuenta) {
		try {
			return cuentaon.mostrarCuentasID(cliente_cuenta);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	/**
	 * ERROR 500
	 * @param cedula
	 * @return
	 */
	@GET
	@Path("clientecedulacuentas")
	@Produces("application/json")
	public List <Cuenta> buscarClienteCuentasCedula(@QueryParam("cedula") String cedula) {
		try {
			Cliente cliente = new  Cliente();
			cliente= clienteon.buscarClienteCedula(cedula);
			System.out.println(cliente.toString());

			return cuentaon.mostrarCuentasID(cliente.getId());
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
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
	

}
