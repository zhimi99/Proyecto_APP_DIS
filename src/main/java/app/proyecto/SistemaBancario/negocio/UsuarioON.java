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
	
	
	public List<Usuario>mostrarUsuarios() {
		return this.usuariodao.mostrarUsuarios();
	}


}
