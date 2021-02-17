package app.proyecto.SistemaBancario.DAO;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import app.proyecto.SistemaBancario.Entidades.Poliza;
import app.proyecto.SistemaBancario.Entidades.Transaccion;
/**
 * 
 * @author andres Clase java encargada del manejo de opraciones sobre la base de
 *         datos, tendremos operaciones CRUD si los requerimientos nos lo pide
 * 
 */
@Stateless
public class PolizaDAO {

	@PersistenceContext
	private EntityManager em;


	/**
	 * Metodo que permite crear un objeto mediante la persitencia
	 * 
	 * @param poliza pide el objeto que va a ser insertado en nuestra DB
	 */
	public void crearPoliza(Poliza poliza){
		System.out.println("en dao " + poliza.getMonto());
		this.em.persist(poliza);
	}

	/*
	 * public Cuenta buscarCuenta(String nombres) { String jpql =
	 * "SELECT a FROM Autor a JOIN FETCH a where a.nombres = :nombres"; Query query
	 * = em.createQuery(jpql, Autor.class); query.setParameter("nombres", nombres);
	 * Autor autor = (Autor) query.getSingleResult(); return autor; }
	 */

	/**
	 * Metodo que permite tener un arreglo de todas las polizas, esto lo realizamos
	 * mediante jpql
	 * 
	 * @return arreglo de polizas
	 */
	public List<Poliza> mostrarPolizas() {
		String jpql = "SELECT a FROM Poliza a";
		Query query = em.createQuery(jpql, Poliza.class);
		List<Poliza> polizas = query.getResultList();
		return polizas;
	}

	
	public List<Poliza> listaPolizasCuenta(int id) {
		System.out.println("en dao>>> "+id);
		//String jpql = "SELECT cu FROM Cliente cl JOIN cl.cuentas cu WHERE cl.id= :id";
		String jpql = "SELECT cu FROM Cuenta cl JOIN cl.listaPoliza cu WHERE cl.id= :id ORDER BY cu.id DESC";
		Query query = em.createQuery(jpql, Poliza.class);
		query.setParameter("id", id);
		List<Poliza> transacciones = query.getResultList();
		for (int i = 0; i < transacciones.size(); i++) {
			System.out.println("Hola mundo   "+ transacciones.get(i).getCuenta().getTipoCuenta());
		}
		return transacciones;
		}
}
