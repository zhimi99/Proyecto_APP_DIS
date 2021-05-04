package app.proyecto.SistemaBancario.SERVICES;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import app.proyecto.SistemaBancario.Utils.TransaccionFachada;
import app.proyecto.SistemaBancario.negocio.TransaccionON;

@WebService
public class ClienteServiceSoap {
	@Inject
	TransaccionON on;

	@WebMethod
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

	@WebMethod
	public Respuesta deposito(TransaccionFachada trx) {
		Respuesta r = new Respuesta();
		System.out.print("entraaa a  depositooo");
		try {
			r = on.deposito(trx);
		} catch (Exception e) {
			e.printStackTrace();
			r.setCodigo(99);
			r.setMensaje(e.getMessage());
		}
		return r;
	}

	@WebMethod
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