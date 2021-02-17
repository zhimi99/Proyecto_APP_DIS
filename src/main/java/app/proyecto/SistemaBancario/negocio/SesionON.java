package app.proyecto.SistemaBancario.negocio;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.Query;

import app.proyecto.SistemaBancario.DAO.SesionDAO;
import app.proyecto.SistemaBancario.Entidades.Cliente;
import app.proyecto.SistemaBancario.Entidades.Sesion;
import app.proyecto.SistemaBancario.SERVICES.Respuesta;

@Stateless
public class SesionON {

	@Inject
	SesionDAO sesiondao;
	
	public void crearSesion(Sesion sesion) {
		this.sesiondao.crearSesion(sesion);
	}
	
	public Sesion buscarSesionCorreo(String correo)  {
		return this.sesiondao.buscarSesionCorreo(correo);
	}
	
	public List<Sesion>mostrarSesiones() {
		return this.sesiondao.mostrarSesiones();
	}
	public List<Sesion>mostrarSesionesCorreo(String correo) {
		return this.sesiondao.getSesionesCorreo(correo);
	}
}