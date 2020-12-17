package app.proyecto.SistemaBancario.DAO;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import app.proyecto.SistemaBancario.Entidades.Transaccion;

@Stateless
public class TransaccionDAO {

	@PersistenceContext
	private EntityManager em;

	public void crearTransaccion(Transaccion transaccion){
		System.out.println("en dao" + transaccion.toString());
		this.em.persist(transaccion);
	}

	/*
	 * public Cuenta buscarCuenta(String nombres) { String jpql =
	 * "SELECT a FROM Autor a JOIN FETCH a where a.nombres = :nombres"; Query query
	 * = em.createQuery(jpql, Autor.class); query.setParameter("nombres", nombres);
	 * Autor autor = (Autor) query.getSingleResult(); return autor; }
	 */

	public List<Transaccion> mostrarTransacciones() {
		String jpql = "SELECT a FROM Transaccion a";
		Query query = em.createQuery(jpql, Transaccion.class);
		List<Transaccion> transacciones = query.getResultList();

		return transacciones;
	}

}
