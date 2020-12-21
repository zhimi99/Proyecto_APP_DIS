package app.proyecto.SistemaBancario.view;

import java.io.IOException;
import java.io.Serializable;
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
import app.proyecto.SistemaBancario.negocio.ClienteON;

@Named
//@ConversationScoped
@ViewScoped
public class ClienteMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private ClienteON clienteon;

	private Cliente newcliente;

	private boolean editing;

	private List<Cliente> clientes;

	private String Cedulabusqueda;
	
private UIData usersDataTable;
	
	public void setUsersDataTable(UIData usersDataTable) {
		this.usersDataTable = usersDataTable;
	}
 
	public UIData getUsersDataTable() {
		return usersDataTable;
	}


	@PostConstruct
	public void init() {
		newcliente = new Cliente();
		newcliente.addCuenta(new Cuenta());
		String contrasena = "" + UUID.randomUUID().toString().toLowerCase().substring(0, 11);
		newcliente.setClave(contrasena);
		editing = false;

		listarClientes();
	}

	public String updateCliente() {
		editing = true;
		return null;
	}

	public String agregarCliente() {
		try {
			if (editing) {
				clienteon.actualizarCliente(newcliente);
				listarClientes();
			}
				
				
			else {
				clienteon.crearCliente(newcliente);
				enviarConGMail( newcliente.getCorreo(), "Usuario Creado con exito", "Usuario: "+newcliente.getCorreo()+"\nPassword: "+newcliente.getClave());
				listarClientes();
			}

				
				
			
		} catch (Exception e) {
			System.out.println("Error al guardar");
			e.printStackTrace();
						
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					e.getMessage(), "Error");
            facesContext.addMessage(null, m);

		}
		
		editing=false;

		return "Clientes";
		
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
			System.out.println(newcliente.toString());
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

		newcliente.addCuenta(new Cuenta());
		return null;
	}

	public void loadData() {

		System.out.println("load data " + Cedulabusqueda);
		if (Cedulabusqueda == null)
			return;
		try {
			newcliente = clienteon.buscarClienteCedula(Cedulabusqueda);
			editing = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "Error");
			facesContext.addMessage(null, m);
		}
	}
	
	
	 public void onRowSelect(SelectEvent<Cliente> event) {
	        FacesMessage msg = new FacesMessage("Cliente Selected", event.getObject().getCedula());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	 
	    public void onRowUnselect(UnselectEvent<Cliente> event) {
	        FacesMessage msg = new FacesMessage("Cliente Unselected", event.getObject().getCedula());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
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

	public boolean isEditing() {
		return editing;
	}

	public void setEditing(boolean editing) {
		this.editing = editing;
	}

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
