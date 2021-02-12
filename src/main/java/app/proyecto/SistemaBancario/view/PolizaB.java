package app.proyecto.SistemaBancario.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
//import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.file.UploadedFile;

import app.proyecto.SistemaBancario.Entidades.Cliente;
import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.Entidades.Poliza;
import app.proyecto.SistemaBancario.Entidades.Usuario;
import app.proyecto.SistemaBancario.negocio.ClienteON;
import app.proyecto.SistemaBancario.negocio.CuentaON;
//import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.negocio.PolizaON;

@Named
@ConversationScoped
//@ManagedBean
public class PolizaB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	PolizaON polizaon;
	
	@Inject ClienteON clienteon;
	
	@Inject CuentaON cuentaon;
	
	private Poliza poliza;
	private List<Poliza> polizas;
	
	private UploadedFile fileCedula;
	private UploadedFile filePlanilla;


	
private Usuario usuarioLogin;
	
	@PostConstruct
	public void init() {
		this.usuarioLogin=recuperarUsuarioLogin(); 
		this.poliza = new Poliza();
		listarPolizas();
	}

	public String agregarPoliza() {
		/*this.polizaon.crearPoliza(poliza);
		this.poliza = null;*/
		FacesContext fc = FacesContext.getCurrentInstance();
		//Cliente clienteLogin = (Cliente) fc.getExternalContext().getSessionMap().get("cliente");
		//Cuenta cuenta = (Cuenta) fc.getExternalContext().getSessionMap().get("ctatr");
		int num= (int) fc.getExternalContext().getSessionMap().get("ctatr");
		Cuenta cuenta=this.cuentaon.mostrarCuenta(num);
		this.poliza.setCuenta(cuenta);
		uploadFile();
		this.polizaon.crearPoliza(poliza);
		
		return null;
	}
	
	public void listarPolizas() {
		this.polizas =  this.polizaon.mostrarPolizas();

	}

	
	public void uploadFile() {
		if (fileCedula != null /*&& filep != null && filer != null */) {
			byte[] contenido = fileCedula.getContent();
			String nombre = fileCedula.getFileName();
			poliza.setCedulaImagen(contenido);
			System.err.println("hOLa mundo  MAGEN >> "+nombre);
			/*byte[] contenido1 = filep.getContent();
			String nombre1 = filep.getFileName();
			solicitud.setPlanillaImagen(contenido1);
			
			*/
		}else {
			System.out.println("Igen no entro");
		}
	}

	
	
	
	public Poliza getPoliza() {
		return poliza;
	}

	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}

	public List<Poliza> getPolizas() {
		return polizas;
	}

	public void setPolizas(List<Poliza> polizas) {
		this.polizas = polizas;
	}

	public UploadedFile getFileCedula() {
		return fileCedula;
	}

	public void setFileCedula(UploadedFile fileCedula) {
		this.fileCedula = fileCedula;
	}

	public UploadedFile getFilePlanilla() {
		return filePlanilla;
	}

	public void setFilePlanilla(UploadedFile filePlanilla) {
		this.filePlanilla = filePlanilla;
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
