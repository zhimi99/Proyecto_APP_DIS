package app.proyecto.SistemaBancario.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import app.proyecto.SistemaBancario.Entidades.Cliente;
import app.proyecto.SistemaBancario.negocio.ClienteON;

@Named
@ConversationScoped
//@ManagedBean
public class ClienteMB {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	ClienteON clienteon;
	
	private Cliente cliente;
	private List<Cliente> clientes;
	
	@PostConstruct
	public void init() {
		this.cliente = new Cliente();
		listarClientes();
	}

	public String agregarCliente() {
		this.clienteon.crearCliente(cliente);
		this.cliente = null;
		return null;
	}
	
	public void listarClientes() {
		System.out.println("HOLA MUNDO");
		this.clientes =  this.clienteon.mostrarClientes();

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
	
	

}
