package app.proyecto.SistemaBancario.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import app.proyecto.SistemaBancario.Entidades.Sesion;

@Stateless
public class SesionDAO {

	@PersistenceContext
	private EntityManager em;

	public void crearSesion(Sesion sesion){
		System.out.println("en dao" + sesion.toString());
		this.em.persist(sesion);
	}

	/*
	 * public Cuenta buscarCuenta(String nombres) { String jpql =
	 * "SELECT a FROM Autor a JOIN FETCH a where a.nombres = :nombres"; Query query
	 * = em.createQuery(jpql, Autor.class); query.setParameter("nombres", nombres);
	 * Autor autor = (Autor) query.getSingleResult(); return autor; }
	 */

	public List<Sesion> mostrarSesiones() {
		String jpql = "SELECT a FROM Sesion a";
		Query query = em.createQuery(jpql, Sesion.class);
		List<Sesion> sesiones = query.getResultList();

		return sesiones;
	}

}
