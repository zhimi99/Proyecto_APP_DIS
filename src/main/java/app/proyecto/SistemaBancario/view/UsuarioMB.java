package app.proyecto.SistemaBancario.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.inject.Named;

import app.proyecto.SistemaBancario.Entidades.Usuario;
import app.proyecto.SistemaBancario.negocio.UsuarioON;

@Named
@ConversationScoped
//@ManagedBean
public class UsuarioMB implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	UsuarioON usuarioon;
	
	private Usuario usuario;
	private List<Usuario> usuarios;
	
	@PostConstruct
	public void init() {
		this.usuario = new Usuario();
		listarUsuarios();
	}

	public void agregarUsuario() {
		System.out.println("bean "+usuario.toString());
		this.usuarioon.crearUsuario(usuario);

	}
	
	public void listarUsuarios() {
		this.usuarios =  this.usuarioon.mostrarUsuarios();

	}

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
