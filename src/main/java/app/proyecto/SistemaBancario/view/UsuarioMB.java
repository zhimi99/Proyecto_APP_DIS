package app.proyecto.SistemaBancario.view;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import app.proyecto.SistemaBancario.Entidades.Usuario;
import app.proyecto.SistemaBancario.negocio.UsuarioON;

@Named
@ConversationScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	UsuarioON usuarioon;

	private Usuario usuario;
	private List<Usuario> usuarios;

	@PostConstruct
	public void init() {
		this.usuario = new Usuario();
		String contrasena = "" + UUID.randomUUID().toString().toLowerCase().substring(0, 11);
		usuario.setClave(contrasena);
		listarUsuarios();
	}

	public void agregarUsuario() {

		this.usuarioon.crearUsuario(usuario);
		enviarConGMail( usuario.getCorreo(), "Usuario Creado con exito", "Usuario: "+usuario.getCorreo()+"\nPassword: "+usuario.getClave());
		listarUsuarios();

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

	/*
	 * public Usuario buscar(String cedula) { usuarioon.buscarUsuario(cedula); }
	 * 
	 * public void actualizarUsuaurio(Usuario usuario) {
	 * usuariodao.actualizarUsuaurio(usuario); }
	 */
	


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
	
	public static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
	    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
	    String remitente = "asagbay1995@gmail.com";  //Para la dirección nomcuenta@gmail.com

	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
	    props.put("mail.smtp.user", remitente);
	    props.put("mail.smtp.clave", "Octubre24");    //La clave de la cuenta
	    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
	    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
	    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

	    Session session = Session.getDefaultInstance(props);
	    MimeMessage message = new MimeMessage(session);

	    try {
	        message.setFrom(new InternetAddress(remitente));
	        message.addRecipients(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
	        message.setSubject(asunto);
	        message.setText(cuerpo);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", remitente, "Octubre24");
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();   //Si se produce un error
	    }
	}

}
