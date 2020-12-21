package app.proyecto.SistemaBancario.DAO;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import app.proyecto.SistemaBancario.Entidades.Usuario;

@Stateless
public class usuarioDAO {

	@PersistenceContext
	private EntityManager em;

	public void crearUsuario(Usuario usuario){
		System.out.println("en dao" + usuario.toString());
		this.em.persist(usuario);
	}
	
	public void eliminarUsuario(String cedula) {
		em.remove(buscarUsuario(cedula));
	}
	
	public Usuario buscarUsuario(String cedula) {
		return em.find(Usuario.class, cedula);
	}
	
	public void actualizarUsuaurio(Usuario usuario) {
		em.merge(usuario);
	}

	public List<Usuario> mostrarUsuarios() {
		String jpql = "SELECT a FROM Usuario a";
		Query query = em.createQuery(jpql, Usuario.class);
		List<Usuario> usuarios = query.getResultList();

		return usuarios;
	}
	
	
	
	public Usuario mostrarUsuarioCorreo(String correo, String clave) {
		String jpql = "SELECT a FROM Usuario where correo="+correo + "and clave="+clave;
		Query query = em.createQuery(jpql, Usuario.class);
		Usuario usuario = (Usuario) query.getResultList();

		return usuario;
	}
	

}
