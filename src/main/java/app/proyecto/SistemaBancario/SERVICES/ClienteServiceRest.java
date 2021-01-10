package app.proyecto.SistemaBancario.SERVICES;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import app.proyecto.SistemaBancario.Utils.TransaccionFachada;
import app.proyecto.SistemaBancario.negocio.TransaccionON;

@Path("serv")
public class ClienteServiceRest {
	@Inject
	private TransaccionON on;
	
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

}
