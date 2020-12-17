package app.proyecto.SistemaBancario.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import app.proyecto.SistemaBancario.Entidades.Usuario;

@Stateless
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;

	public void crearUsuario(Usuario usuario){
		System.out.println("en dao" + usuario.toString());
		this.em.persist(usuario);
	}
	
	public void crearUsuario1(Usuario usuario){
		Query query = em.createNativeQuery("INSERT INTO Usuario (cedula, apellidos, "
				+ "nombres, telefono, rol, correo, clave) VALUES (?,?,?,?,?,?,?);");
		em.getTransaction().begin();
		query.setParameter(1, usuario.getCedula());
		query.setParameter(2, usuario.getApellidos());
		query.setParameter(3, usuario.getNombres());
		query.setParameter(4, usuario.getTelefono());
		query.setParameter(5, usuario.getRol());
		query.setParameter(6, usuario.getCorreo());
		query.setParameter(7, usuario.getClave());
		query.executeUpdate();
		em.getTransaction().commit();
	}

	/*
	 * public Cuenta buscarCuenta(String nombres) { String jpql =
	 * "SELECT a FROM Autor a JOIN FETCH a where a.nombres = :nombres"; Query query
	 * = em.createQuery(jpql, Autor.class); query.setParameter("nombres", nombres);
	 * Autor autor = (Autor) query.getSingleResult(); return autor; }
	 */

	public List<Usuario> mostrarUsuarios() {
		String jpql = "SELECT a FROM Usuario a";
		Query query = em.createQuery(jpql, Usuario.class);
		List<Usuario> usuarios = query.getResultList();

		return usuarios;
	}

}
