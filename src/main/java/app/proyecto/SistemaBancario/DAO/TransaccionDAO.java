package app.proyecto.SistemaBancario.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.Entidades.Transaccion;

/**
 * 
 * @author andres Clase java encargada del manejo de opraciones sobre la base de
 *         datos, tendremos operaciones CRUD si los requerimientos nos lo pide
 * 
 */
@Stateless
public class TransaccionDAO {

	@PersistenceContext
	private EntityManager em;

	/**
	 * Metodo que permite crear un objeto mediante la persitencia
	 * 
	 * @param transaccion pide el objeto que va a ser insertado en nuestra DB
	 */
	public void crearTransaccion(Transaccion transaccion){
		System.out.println("en dao" + transaccion.toString());
		this.em.persist(transaccion);
	}

	/**
	 * Método que permite mostrar un arreglo de transacciones
	 * @return arreglo de transacciones
	 */
	public List<Transaccion> mostrarTransacciones() {
		String jpql = "SELECT a FROM Transaccion a";
		Query query = em.createQuery(jpql, Transaccion.class);
		List<Transaccion> transacciones = query.getResultList();

		return transacciones;
	}


	/**
	 * Método que permite listar las transacciones de una cuenta
	 * @param id de la cuenta
	 * @return las transacciones de una cuenta
	 */
	public List<Transaccion> listaTransacionesCuenta(int id) {
		String jqpl = "SELECT c FROM Cuenta c JOIN FETCH c.listaTra where c.id = :id";
		Query query = em.createQuery(jqpl, Cuenta.class);
		query.setParameter("numeroCuenta", id);
		 Cuenta cuenta = (Cuenta) query.getSingleResult();
		List<Transaccion> trans = new ArrayList<>();
		for (Transaccion t : cuenta.getListaTra()) {
			trans.add(t);	
		}
		return trans;
		}

}
