package app.proyecto.SistemaBancario.view;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import app.proyecto.SistemaBancario.Entidades.Cliente;
import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.negocio.ClienteON;
import app.proyecto.SistemaBancario.negocio.CuentaON;

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

}
