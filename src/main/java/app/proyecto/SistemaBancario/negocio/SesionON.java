package app.proyecto.SistemaBancario.negocio;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

import app.proyecto.SistemaBancario.DAO.SesionDAO;
import app.proyecto.SistemaBancario.Entidades.Sesion;

@Stateless
public class SesionON {

	@Inject
	SesionDAO sesiondao;
	
	public void crearSesion(Sesion sesion) {
		this.sesiondao.crearSesion(sesion);
	}
	
	
	public List<Sesion>mostrarSesiones() {
		return this.sesiondao.mostrarSesiones();
	}
}
