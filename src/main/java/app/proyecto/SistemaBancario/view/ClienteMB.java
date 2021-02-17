package app.proyecto.SistemaBancario.view;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIData;
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

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import app.proyecto.SistemaBancario.Entidades.Cliente;
import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.Entidades.Sesion;
import app.proyecto.SistemaBancario.Entidades.Usuario;
import app.proyecto.SistemaBancario.negocio.ClienteON;
import app.proyecto.SistemaBancario.negocio.CuentaON;
import app.proyecto.SistemaBancario.negocio.SesionON;

@Named
@ViewScoped
public class ClienteMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private ClienteON clienteon;

	@Inject
	private SesionON sesionon;
	
	@Inject
	private CuentaON cuentaon;

	private Cliente newcliente;
	
	private Cliente clienteLogin;
	private Cuenta newcuenta;

	//private boolean editing;

	private List<Cliente> clientes;

	private String Cedulabusqueda;

	private UIData usersDataTable;
	
	int idCli=0;

	int intento = 1;

	public void setUsersDataTable(UIData usersDataTable) {
		this.usersDataTable = usersDataTable;
	}

	public UIData getUsersDataTable() {
		return usersDataTable;
	}

private Usuario usuarioLogin;
	
	@PostConstruct
	public void init() {
		this.usuarioLogin=recuperarUsuarioLogin(); 
		newcliente = new Cliente();

		listarClientes();
	}

	public String agregarCliente() throws Exception {
		Cliente user = clienteon.buscarClienteCedula(newcliente.getCedula());

			String contrasena = "" + UUID.randomUUID().toString().toLowerCase().substring(0, 11);
			newcliente.setClave(contrasena);
			// usuario.setClave(contrasena);
			try {
				// usuarioon.crearUsuario(usuario);
				clienteon.crearCliente(newcliente);
				enviarConGMail(newcliente.getCorreo(), "Usuario Creado con exito",
						"Usuario: " + newcliente.getCorreo() + "\nPassword: " + newcliente.getClave());
				listarClientes();
			} catch (Exception e) {
				// TODO: handle exception
			}

		//}
		return null;
	}

	public String eliminarCliente(String cedula) {
		try {
			clienteon.eliminarCliente(cedula);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	public String buscarCliente(String cedula) {
		System.out.println("Buscar " + cedula);
		this.clienteon.buscarCliente(cedula);
		return null;
	}

	public String buscarClienteCedula() {
		try {
			newcliente = this.clienteon.buscarClienteCedula(newcliente.getCedula());
			// System.out.println(newcliente.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	public String acutalizarCliente(Cliente cliente) {
		System.out.println("upadte>>>>>> " + cliente.toString());
		try {
			this.clienteon.actualizarCliente(cliente);
		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;
	}

	public void listarClientes() {
		this.clientes = this.clienteon.mostrarClientes();

	}

	public String addCuenta() {
		Cuenta cuenta = new Cuenta();
		cuenta.setCliente(newcliente);
		newcliente.addCuenta(cuenta);
		return null;
	}

	

	public void onRowSelect(SelectEvent<Cliente> event) {
		FacesMessage msg = new FacesMessage("Cliente Selected", event.getObject().getCedula());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent<Cliente> event) {
		FacesMessage msg = new FacesMessage("Cliente Unselected", event.getObject().getCedula());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public String login() throws Exception {
		String retorno = null;
		Cliente clienteLogeado = clienteon.buscarClienteCorreo(newcliente);
		Sesion sesion = new Sesion();
		if (clienteLogeado.getCorreo().equals(newcliente.getCorreo())) {
			if (clienteLogeado.getClave().equals(newcliente.getClave()) && clienteLogeado.getEstado().equals("Act")) {
				setUsuarioOK(sesion, clienteLogeado);
				intento = 1;
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("cliente", clienteLogeado);
				retorno = "cuentasCliente?faces-redirect=true";
			} else {
				sesion.setCorreo(this.newcliente.getCorreo());
				sesion.setClave(this.newcliente.getClave());
				sesion.setEstado("fallido");
				sesion.setFecha(new Date());
				sesion.setIntentos(intento++);
				this.sesionon.crearSesion(sesion);
				retorno = "loginCliente?faces-redirect=true";
				if (intento > 3) {
					clienteLogeado.setEstado("InAct");
					this.clienteon.actualizarCliente(clienteLogeado);
				}
				
			} 
			}/*else {
				retorno = "loginCliente?faces-redirect=true";

		}*/

		return retorno;
	}

	public void setUsuarioOK(Sesion sesion, Cliente clienteLogeado) {
		sesion.setCorreo(clienteLogeado.getCorreo());
		sesion.setClave(clienteLogeado.getClave());
		sesion.setEstado("satisfactorio");
		sesion.setFecha(new Date());
		sesion.setIntentos(1);
		this.sesionon.crearSesion(sesion);

	}

	public void clienteInLogin() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Cliente usuarioLogin = (Cliente) fc.getExternalContext().getSessionMap().get("cliente");
		try {
			if (usuarioLogin!=null) {
			} else {
				fc.getExternalContext().redirect("loginCliente.xhtml");
			}
		} catch (IOException e) {

		}
	}
	public List<Cuenta> listarCuentasCliente() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Cliente usuarioLogin = (Cliente) fc.getExternalContext().getSessionMap().get("cliente");
		System.out.println("  Si llega revisa  la vista>>>>>>> "+this.cuentaon.listarCuentasCliente(usuarioLogin.getId()).size());
		return this.cuentaon.listarCuentasCliente(usuarioLogin.getId());
	}
	
	public String transaccionesCuenta(int cuentaID) throws Exception {
		System.out.println(cuentaID);
		String retorno=null;
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ctatr", cuentaID);
				//retorno = "transaccionesCuenta?faces-redirect=true";

		return retorno;
	}
	
	public String hi() {
		System.out.println("parametro de entrada "+newcuenta.getId());
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ctatr", newcuenta.getId());		
		return "transaccionesCuenta?faces-redirect=true";
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

	public ClienteON getClienteon() {
		return clienteon;
	}

	public void setClienteon(ClienteON clienteon) {
		this.clienteon = clienteon;
	}

	public Cliente getNewcliente() {
		return newcliente;
	}

	public void setNewcliente(Cliente newcliente) {
		this.newcliente = newcliente;
	}

	/*public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}*/

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String getCedulabusqueda() {
		return Cedulabusqueda;
	}

	public void setCedulabusqueda(String cedulabusqueda) {
		Cedulabusqueda = cedulabusqueda;
	}
	
	

	public Cuenta getNewcuenta() {
		return newcuenta;
	}

	public void setNewcuenta(Cuenta newcuenta) {
		this.newcuenta = newcuenta;
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

}
