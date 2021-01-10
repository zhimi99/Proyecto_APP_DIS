package app.proyecto.SistemaBancario.negocio;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import app.proyecto.SistemaBancario.DAO.UsuarioDAO;
import app.proyecto.SistemaBancario.Entidades.Usuario;

@Stateless
public class UsuarioON {
	@Inject
	UsuarioDAO usuariodao;
	
	public void crearUsuario(Usuario usuario) {
		System.out.println("en on "+ usuario.toString());
		this.usuariodao.crearUsuario(usuario);
	}
	
	public void eliminarUsuario(String cedula) {
		usuariodao.eliminarUsuario(cedula);
	}
	
	public Usuario buscarUsuario(String cedula) {
		return usuariodao.buscarUsuario(cedula);
	}
	
	public void actualizarUsuaurio(Usuario usuario) {
		usuariodao.actualizarUsuaurio(usuario);
	}
	
	public List<Usuario>mostrarUsuarios() {
		return this.usuariodao.mostrarUsuarios();
	}
	
	/*
	public boolean verificarUsuario(String  correo, String clave) {
		
		if(usuariodao.buscarCorreo(correo, clave) !=null) {
			
			return true;
			
		}
		
		return false;
		
	}*/


}