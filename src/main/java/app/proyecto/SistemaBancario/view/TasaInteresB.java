package app.proyecto.SistemaBancario.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import app.proyecto.SistemaBancario.Entidades.TasaInteres;
import app.proyecto.SistemaBancario.Entidades.Usuario;
import app.proyecto.SistemaBancario.negocio.TasaInteresON;

@Named
//@ConversationScoped
@ViewScoped
public class TasaInteresB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private TasaInteresON tasaIntereson;

	private TasaInteres newtasaInteres;

	private boolean editing;

	private List<TasaInteres> tasaIntereses;

	private String Cedulabusqueda;

private Usuario usuarioLogin;
	
	@PostConstruct
	public void init() {
		this.usuarioLogin=recuperarUsuarioLogin(); 

		this.newtasaInteres = new TasaInteres();
		listarTasaInteres();
	}

	/*
	 * public String updateCliente() { editing=true; return null; }
	 */
	public String agregarTasaInteres() {
		tasaIntereson.crearTasaInteres(newtasaInteres);
		listarTasaInteres();
		return null;

	}

		public void listarTasaInteres() {

		this.tasaIntereses = this.tasaIntereson.mostrarTasaInteres();

	}

	/**
	 * Getteres ansd setters
	 */

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public TasaInteresON getTasaIntereson() {
		return tasaIntereson;
	}

	public void setTasaIntereson(TasaInteresON tasaIntereson) {
		this.tasaIntereson = tasaIntereson;
	}

	public TasaInteres getNewtasaInteres() {
		return newtasaInteres;
	}

	public void setNewtasaInteres(TasaInteres newtasaInteres) {
		this.newtasaInteres = newtasaInteres;
	}

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

	public List<TasaInteres> getTasaIntereses() {
		return tasaIntereses;
	}

	public void setTasaIntereses(List<TasaInteres> tasaIntereses) {
		this.tasaIntereses = tasaIntereses;
	}

	public String getCedulabusqueda() {
		return Cedulabusqueda;
	}

	public void setCedulabusqueda(String cedulabusqueda) {
		Cedulabusqueda = cedulabusqueda;
	}
	
	public Usuario recuperarUsuarioLogin() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) fc.getExternalContext().getSessionMap().get("usuario");
		return usuario;
	}
	public Usuario getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(Usuario usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

}
