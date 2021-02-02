package app.proyecto.SistemaBancario.DAO;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import app.proyecto.SistemaBancario.Entidades.Cliente;
import app.proyecto.SistemaBancario.Entidades.Usuario;

/**
 * 
 * @author andres Clase java encargada del manejo de opraciones sobre la base de
 *         datos, tendremos operaciones CRUD si los requerimientos nos lo pide
 * 
 */
@Stateless
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Método que permite crear un usuario mediante la persistencia
	 * @param usuario objeto a ser insertado en la DB
	 */
	public void crearUsuario(Usuario usuario){
		System.out.println("en dao" + usuario.toString());
		this.em.persist(usuario);
	}
	
	/**
	 * Metodo que permite eliminar al objeto mediante el metodo remove, siempre que haiga encontrado al objeto meidante la cedula
	 * @param cedula de busqueda del objeto para ser eliminado
	 */
	public void eliminarUsuario(String cedula) {
		em.remove(buscarUsuario(cedula));
	}
	/**
	 * Metodo que permite buscar un usuario mediante find
	 * @param cedula para relizar la busqueda por cedula
	 * @return un objeto de tipo usuario
	 */
	public Usuario buscarUsuario(String cedula) {
		return em.find(Usuario.class, cedula);
	}
	
	/**
	 * Metodo que permite actualizar al usuario mediante merge de la perisstencia.
	 * @param usuario pide un objeto usuario para ser acutlizado
	 */
	public void actualizarUsuaurio(Usuario usuario) {
		em.merge(usuario);
	}

	/**
	 * Metodo que permite mostrar un arreglo de usuarioas
	 * @return arreglo de usuarios
	 */
	public List<Usuario> mostrarUsuarios() {
		String jpql = "SELECT a FROM Usuario a";
		Query query = em.createQuery(jpql, Usuario.class);
		List<Usuario> usuarios = query.getResultList();

		return usuarios;
	}
	
	/*
	 * Metodo que busca el usuario mediante corroe y clave
	 */
	public Usuario buscarCorreoyPswrd(Usuario usuario/*String correo, String clave*/) {
		
			Usuario usu = new Usuario();
			try {
				String jpql = "SELECT l FROM Usuario l where l.correo = :correo and l.clave  =:clave";
				Query query = em.createQuery(jpql, Usuario.class);
				query.setParameter("correo", usuario.getCorreo());
				query.setParameter("clave", usuario.getClave());
				usu = (Usuario) query.getSingleResult();
			} catch (Exception e) {
				usu = null;
			}

			return usu;
		}
	
		
}
	
