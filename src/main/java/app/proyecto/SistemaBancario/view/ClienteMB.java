package app.proyecto.SistemaBancario.view;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import app.proyecto.SistemaBancario.Entidades.Cliente;
import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.negocio.ClienteON;

@Named
@ConversationScoped
public class ClienteMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ClienteON clienteon;

	private Cliente cliente;

	private String cedulaCliente;
	private List<Cliente> clientes;

	@PostConstruct
	public void init() {
		cliente = new Cliente();
		String contrasena = "" + UUID.randomUUID().toString().toLowerCase().substring(0, 11);
		cliente.setClave(contrasena);

		listarClientes();
	}

	public String agregarCliente() {
		this.clienteon.crearCliente(cliente);
		this.cliente = null;
		return null;
	}

	public String eliminarCliente(String cedula) {
		this.clienteon.eliminarCliente(cedula);
		return null;
	}

	public String buscarCliente(String cedula) {
		this.clienteon.buscarCliente(cedula);
		return null;
	}

	public String buscarClienteCedula() {
		cliente = this.clienteon.buscarClienteCedula(cliente.getCedula());
		System.out.println(cliente.toString());
		return null;
	}

	public String acutalizarCliente(Cliente cliente) {
		this.clienteon.actualizarCliente(cliente);
		return null;
	}

	public void listarClientes() {
		this.clientes = this.clienteon.mostrarClientes();

	}

	public String addCuenta() {

		cliente.getCuentas().add(new Cuenta());
		//cliente.addCuenta(new Cuenta());
		return null;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ClienteON getClienteon() {
		return clienteon;
	}

	public void setClienteon(ClienteON clienteon) {
		this.clienteon = clienteon;
	}

	public String getCedulaCliente() {
		return cedulaCliente;
	}

	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}

}
