package app.proyecto.SistemaBancario.view;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import app.proyecto.SistemaBancario.Entidades.Usuario;
import app.proyecto.SistemaBancario.negocio.UsuarioON;

@Named
@ConversationScoped
public class UsuarioMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	UsuarioON usuarioon;

	private Usuario usuario;
	private List<Usuario> usuarios;

	@PostConstruct
	public void init() {
		this.usuario = new Usuario();
		String contrasena = "" + UUID.randomUUID().toString().toLowerCase().substring(0, 11);
		usuario.setClave(contrasena);
		listarUsuarios();
	}

	public void agregarUsuario() {

		this.usuarioon.crearUsuario(usuario);
		listarUsuarios();

	}

	public void listarUsuarios() {
		this.usuarios = this.usuarioon.mostrarUsuarios();

	}

	public void eliminarUsuario(String cedula) {
		usuarioon.eliminarUsuario(cedula);
	}

	public void buscarUsuario(String cedula) {
		if (cedula != null) {
			usuario = usuarioon.buscarUsuario(cedula);
		} else {
			return;
		}
	}

	/*
	 * public Usuario buscar(String cedula) { usuarioon.buscarUsuario(cedula); }
	 * 
	 * public void actualizarUsuaurio(Usuario usuario) {
	 * usuariodao.actualizarUsuaurio(usuario); }
	 */

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}
