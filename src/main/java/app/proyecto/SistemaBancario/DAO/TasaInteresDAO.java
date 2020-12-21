package app.proyecto.SistemaBancario.DAO;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import app.proyecto.SistemaBancario.Entidades.TasaInteres;

/**
 * 
 * @author andres Clase java encargada del manejo de opraciones sobre la base de
 *         datos, tendremos operaciones CRUD si los requerimientos nos lo pide
 * 
 */

@Stateless
public class TasaInteresDAO {

	@Inject
	private EntityManager em;

	/**
	 * Metodo que permite crear un objeto mediante la persitencia
	 * 
	 * @param tasaInteres pide el objeto que va a ser insertado en nuestra DB
	 */
	public void crearTasaInteres(TasaInteres tasaInteres) {
		em.persist(tasaInteres);
	}

	/**
	 * Método que permite eliminar las tasas de interes parametrizadas, esto
	 * mediante una busqueda que respalde la existencia del objeto
	 * 
	 * @param id parametro que permite buscar la tasa de interes
	 */
	public void eliminarTasaInteres(int id) {
		em.remove(buscarTasaInteres(id));
	}

	/*
	 * Metodo que permite la busqueda de tasa de interes mediante la persistencia
	 * find, esto gracias al id que lo enviaremos como parametros
	 * 
	 */
	public TasaInteres buscarTasaInteres(int id) {
		// Cliente cli =em.find(Cliente.class, cedula);
		// System.out.println(cli.getCedula());
		return em.find(TasaInteres.class, id);
	}

	/**
	 * Metodo que permite actualizar la tasa de interes mediante merge de la
	 * persistencia
	 * 
	 * @param tasaInteres pido el objeto completo a ser actualizado
	 */
	public void actualizarTasaInteres(TasaInteres tasaInteres) {
		em.merge(tasaInteres);
	}

	/**
	 * Mtodo que permite tener un arreglo de tasas de interes mediante jpql
	 * 
	 * @return un arreglo de tasa de interes
	 */
	public List<TasaInteres> mostrarTasaIntereses() {
		String jpql = "SELECT a FROM TasaInteres a";
		Query query = em.createQuery(jpql, TasaInteres.class);
		List<TasaInteres> tasaIntereses = query.getResultList();

		return tasaIntereses;
	}

	/**
	 * Metodo que permite buscar las tasa de interes mediante el id, esto gracias a
	 * jpql
	 * 
	 * @param id permite un id para la busqueda
	 * @return retorna un objeto tasa de interes cuyo id sea el del parametro
	 */
	public TasaInteres buscarTasaInteresId(int id) {
		TasaInteres cli = new TasaInteres();
		try {
			String jpql = "SELECT l FROM TasaInteres l where l.id = :id";
			Query query = em.createQuery(jpql, TasaInteres.class);
			query.setParameter("id", id);
			cli = (TasaInteres) query.getSingleResult();
			System.out.println("Dao test >>>>>>>   " + cli.getId());
		} catch (Exception e) {
			cli = null;
		}

		return cli;
	}

}
