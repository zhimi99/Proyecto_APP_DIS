package app.proyecto.SistemaBancario.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import app.proyecto.SistemaBancario.Entidades.Transferencia;
/**
 * 
 * @author andres Clase java encargada del manejo de opraciones sobre la base de
 *         datos, tendremos operaciones CRUD si los requerimientos nos lo pide
 * 
 */
@Stateless
public class TransferenciaDAO {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Metodo que permite crear un objeto mediante la persitencia
	 * 
	 * @param transferecnia pide el objeto que va a ser insertado en nuestra DB
	 */
	public void crearTransferencia(Transferencia transferencia){
		this.em.persist(transferencia);
	}

	/*
	 * public Cuenta buscarCuenta(String nombres) { String jpql =
	 * "SELECT a FROM Autor a JOIN FETCH a where a.nombres = :nombres"; Query query
	 * = em.createQuery(jpql, Autor.class); query.setParameter("nombres", nombres);
	 * Autor autor = (Autor) query.getSingleResult(); return autor; }
	 */

	/**
	 * Metodo que permite mostrar un arreglo de transferencias
	 * @return arreglo de transaferencias
	 */
	public List<Transferencia> mostrarTransferencias() {
		String jpql = "SELECT a FROM Transferencia a";
		Query query = em.createQuery(jpql, Transferencia.class);
		List<Transferencia> transferencias = query.getResultList();

		return transferencias;
	}

}
