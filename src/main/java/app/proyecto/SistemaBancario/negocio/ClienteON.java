package app.proyecto.SistemaBancario.negocio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import app.proyecto.SistemaBancario.DAO.ClienteDAO;
import app.proyecto.SistemaBancario.Entidades.Cliente;

@Stateless
public class ClienteON {
	@Inject
	ClienteDAO clientedao;
	
	public void crearCliente(Cliente cliente) {
		this.clientedao.crearCliente(cliente);
	}
	
	
	public List<Cliente>mostrarClientes() {
		System.out.println(this.clientedao.mostrarClientes());
		return this.clientedao.mostrarClientes();
	}


}
