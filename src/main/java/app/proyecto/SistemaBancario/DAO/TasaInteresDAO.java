package app.proyecto.SistemaBancario.DAO;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import app.proyecto.SistemaBancario.Entidades.TasaInteres;

@Stateless
public class TasaInteresDAO {

	@Inject
	private EntityManager em;

	public void crearTasaInteres(TasaInteres tasaInteres) {
		em.persist(tasaInteres);
	}

	public void eliminarTasaInteres(int id) {
		em.remove(buscarTasaInteres(id));
	}

	public TasaInteres buscarTasaInteres(int id) {
		// Cliente cli =em.find(Cliente.class, cedula);
		// System.out.println(cli.getCedula());
		return em.find(TasaInteres.class, id);
	}

	public void actualizarTasaInteres(TasaInteres tasaInteres) {
		em.merge(tasaInteres);
	}

	public List<TasaInteres> mostrarTasaIntereses() {
		String jpql = "SELECT a FROM TasaInteres a";
		Query query = em.createQuery(jpql, TasaInteres.class);
		List<TasaInteres> tasaIntereses = query.getResultList();

		return tasaIntereses;
	}

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
