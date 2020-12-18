package app.proyecto.SistemaBancario.negocio;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import app.proyecto.SistemaBancario.DAO.ClienteDAO;
import app.proyecto.SistemaBancario.Entidades.Cliente;
import app.proyecto.SistemaBancario.Entidades.Usuario;

@Stateless
public class ClienteON {
	@Inject
	ClienteDAO clientedao;
	
	public void crearCliente(Cliente cliente) {
		this.clientedao.crearCliente(cliente);
	}
	
	public void eliminarCliente(String cedula) {
		this.clientedao.eliminarCliente(cedula);
	}
	
	public Cliente buscarCliente(String cedula) {
		return this.clientedao.buscarCliente(cedula);
	}
	
	public Cliente buscarClienteCedula(String cedula) {
		Cliente cli=clientedao.buscarClienteCedula(cedula);
		return cli;
	}
	
	public void actualizarCliente(Cliente cliente) {
		this.clientedao.actualizarUsuaurio(cliente);
	}
	
	public List<Cliente>mostrarClientes() {
		System.out.println(this.clientedao.mostrarClientes());
		return this.clientedao.mostrarClientes();
	}


}
