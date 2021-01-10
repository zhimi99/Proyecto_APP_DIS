package app.proyecto.SistemaBancario.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
//import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import app.proyecto.SistemaBancario.Entidades.Sesion;
import app.proyecto.SistemaBancario.negocio.SesionON;

@Named
@ConversationScoped
//@ManagedBean
public class SesionMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	SesionON sesionon;
	
	private Sesion sesion;
	private List<Sesion> sesiones;
	
	@PostConstruct
	public void init() {
		this.sesion= new Sesion();
		
		listarSesiones();
	}

	public String agregarSesion() {
		this.sesionon.crearSesion(sesion);
		this.sesion = null;
		return null;
	}
	
	public void listarSesiones() {
		this.sesiones =this.sesionon.mostrarSesiones();
	}

	public void verificarSesion() {
		
		
		
	}
	
	
	public Sesion getSesion() {
		return sesion;
	}
	public void setSesion(Sesion sesion) {
		this.sesion = sesion;
	}
	public List<Sesion> getSesiones() {
		return sesiones;
	}
	public void setSesiones(List<Sesion> sesiones) {
		this.sesiones = sesiones;
	}
	

}
