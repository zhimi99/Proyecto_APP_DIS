package app.proyecto.SistemaBancario.negocio;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import app.proyecto.SistemaBancario.DAO.UsuarioDAO;
import app.proyecto.SistemaBancario.Entidades.Cliente;
import app.proyecto.SistemaBancario.Entidades.Usuario;

@Stateless
public class UsuarioON {
	@Inject
	UsuarioDAO usuariodao;
	
	/*public void crearUsuario(Usuario usuario) {
		System.out.println("en on "+ usuario.toString());
		this.usuariodao.crearUsuario(usuario);
	}*/
	
	public boolean crearUsuario(Usuario usuario) throws Exception {
		Usuario cli = usuariodao.buscarUsuarioCedula(usuario.getCedula()); 	
		if (cli != null)
			throw new Exception("Usuario ya existe");
		else
			/*cliente.setFechaRegistro(new Date());
			clientedao.crearCliente(cliente);*/
			usuariodao.crearUsuario(usuario);
		
		return true;
	}
	
	public void eliminarUsuario(String cedula) {
		usuariodao.eliminarUsuario(cedula);
	}
	
	public Usuario buscarUsuario(String cedula) {
		return usuariodao.buscarUsuario(cedula);
	}
	
	/*public void actualizarUsuaurio(Usuario usuario) {
		usuariodao.actualizarUsuaurio(usuario);
	}*/
	public void actualizarUsuario(Usuario usuario) throws Exception {
		Usuario cli = usuariodao.buscarUsuarioCedula(usuario.getCedula());
		if (cli == null)
			throw new Exception("Cliente no existe");

		else
			usuariodao.actualizarUsuaurio(usuario);
			//clientedao.actualizarCliente(cliente);
	}
	
	public List<Usuario>mostrarUsuarios() {
		return this.usuariodao.mostrarUsuarios();
	}
	
	
	public Usuario buscarUsuarioCorreo(Usuario usuario) {
		return usuariodao.buscarCorreo(usuario);
	}
	/*
	public boolean verificarUsuario(String  correo, String clave) {
		
		if(usuariodao.buscarCorreo(correo, clave) !=null) {
			
			return true;
			
		}
		
		return false;
		
	}*/
	public Usuario buscarUsuarioCedula(String cedula) {
		
		return usuariodao.buscarUsuarioCedula(cedula);
	}
	


}