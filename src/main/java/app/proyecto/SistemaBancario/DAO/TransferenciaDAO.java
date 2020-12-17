package app.proyecto.SistemaBancario.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import app.proyecto.SistemaBancario.Entidades.Transferencia;

@Stateless
public class TransferenciaDAO {

	@PersistenceContext
	private EntityManager em;

	public void crearTransferencia(Transferencia transferencia){
		System.out.println("en dao" + transferencia.toString());
		this.em.persist(transferencia);
	}

	/*
	 * public Cuenta buscarCuenta(String nombres) { String jpql =
	 * "SELECT a FROM Autor a JOIN FETCH a where a.nombres = :nombres"; Query query
	 * = em.createQuery(jpql, Autor.class); query.setParameter("nombres", nombres);
	 * Autor autor = (Autor) query.getSingleResult(); return autor; }
	 */

	public List<Transferencia> mostrarTransferencias() {
		String jpql = "SELECT a FROM Transferencia a";
		Query query = em.createQuery(jpql, Transferencia.class);
		List<Transferencia> transferencias = query.getResultList();

		return transferencias;
	}

}
