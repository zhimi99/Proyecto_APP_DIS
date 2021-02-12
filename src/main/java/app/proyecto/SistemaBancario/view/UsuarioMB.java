package app.proyecto.SistemaBancario.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import app.proyecto.SistemaBancario.Entidades.Sesion;
import app.proyecto.SistemaBancario.Entidades.Usuario;
import app.proyecto.SistemaBancario.negocio.SesionON;
import app.proyecto.SistemaBancario.negocio.UsuarioON;

@Named
//@ConversationScoped
@ViewScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	UsuarioON usuarioon;

	@Inject
	SesionON sesionon;

	@Inject
	private FacesContext facesContext;

	private Usuario usuario;
	private List<Usuario> usuarios;

	int intento = 1;

private Usuario usuarioLogin;
	
	@PostConstruct
	public void init() {
		this.usuarioLogin=recuperarUsuarioLogin(); 
		this.usuario = new Usuario();
		listarUsuarios();
	}

	public String agregarUsuario() {
		Usuario user = usuarioon.buscarUsuarioCedula(usuario.getCedula());
		if (user != null) {
			try {
				usuarioon.actualizarUsuario(usuario);
			} catch (Exception e) {
				// TODO: handle exception
			}

		} else {
			String contrasena = "" + UUID.randomUUID().toString().toLowerCase().substring(0, 11);
			usuario.setClave(contrasena);
			try {
				usuarioon.crearUsuario(usuario);
				enviarConGMail(usuario.getCorreo(), "Usuario Creado con exito",
						"Usuario: " + usuario.getCorreo() + "\nPassword: " + usuario.getClave());
				listarUsuarios();
			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		return null;
	}

	public void listarUsuarios() {
		this.usuarios = this.usuarioon.mostrarUsuarios();
	}

	public void eliminarUsuario(String cedula) {
		usuarioon.eliminarUsuario(cedula);
	}

	public void buscarUsuario(String cedula) {
		if (cedula != null) {
			usuario = usuarioon.buscarUsuario(cedula);
		} else {
			return;
		}
	}

	public String buscarUsuarioCedula() {
		try {
			this.usuario = this.usuarioon.buscarUsuarioCedula(usuario.getCedula());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public String login() throws Exception {
		String retorno = null;
		Usuario usuarioLogeado = usuarioon.buscarUsuarioCorreo(usuario);
		Sesion sesion = new Sesion();

		if (usuarioLogeado.getClave().equals(usuario.getClave()) && usuarioLogeado.getEstado().equals("Act")) {
			setUsuarioOK(sesion, usuarioLogeado);
			intento = 1;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioLogeado);
			if (usuarioLogeado.getRol().equals("Administrador")) {
				retorno = "Usuario?faces-redirect=true";

			} else if (usuarioLogeado.getRol().equals("Cajero")) {
				retorno = "Transaccion?faces-redirect=true";

			} else if (usuarioLogeado.getRol().equals("Asistente de captaciones")) {
				retorno = "poliza?faces-redirect=true";

			} else {
				retorno = "login?faces-redirect=true";
			}

		} else {

			sesion.setCorreo(this.usuario.getCorreo());
			sesion.setClave(this.usuario.getClave());
			sesion.setEstado("fallido");
			sesion.setFecha(new Date());
			sesion.setIntentos(intento++);
			this.sesionon.crearSesion(sesion);
			if (intento > 3) {
				usuarioLogeado.setEstado("InAct");
				this.usuarioon.actualizarUsuario(usuarioLogeado);

			}

		}

		System.out.println("Contador >>>>>>> " + intento);
		return retorno;
	}

	public void setUsuarioOK(Sesion sesion, Usuario usuarioLogeado) {
		sesion.setCorreo(usuarioLogeado.getCorreo());
		sesion.setClave(usuarioLogeado.getClave());
		sesion.setEstado("satisfactorio");
		sesion.setFecha(new Date());
		sesion.setIntentos(1);
		this.sesionon.crearSesion(sesion);

	}

	public void usuarioAdminInLogin() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Usuario usuarioLogin = (Usuario) fc.getExternalContext().getSessionMap().get("usuario");
		try {
			if (usuarioLogin.getRol().equals("Administrador")) {
			} else {
				fc.getExternalContext().redirect("login.xhtml");
			}
		} catch (IOException e) {

		}
	}

	public void usuarioCajeroInLogin() {
		FacesContext fc = FacesContext.getCurrentInstance();
		
		try {
			Usuario usuarioLogin = (Usuario) fc.getExternalContext().getSessionMap().get("usuario");
			System.out.println(usuarioLogin.getNombres());
			if (usuarioLogin.getRol().equals("Cajero") || usuarioLogin.getRol().equals("Administrador")) {
			} else {
				fc.getExternalContext().redirect("login.xhtml");
			}
		} catch (IOException e) {

		}
	}

	public void usuarioAsistenteInLogin() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			Usuario usuarioLogin = (Usuario) fc.getExternalContext().getSessionMap().get("usuario");
			System.out.println(usuarioLogin.getNombres());
			if (usuarioLogin.getRol().equals("Asistente de captaciones")
					|| usuarioLogin.getRol().equals("Administrador")) {
			} else {
				fc.getExternalContext().redirect("login.xhtml");
			}
		} catch (IOException e) {

		}
	}

	public static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el
		// remitente también.
		String remitente = "asagbay1995@gmail.com"; // Para la dirección nomcuenta@gmail.com

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", "Octubre24"); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));
			message.addRecipients(Message.RecipientType.TO, destinatario); // Se podrían añadir varios de la misma
																			// manera
			message.setSubject(asunto);
			message.setText(cuerpo);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, "Octubre24");
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			me.printStackTrace(); // Si se produce un error
		}
	}

	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
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
