package app.proyecto.SistemaBancario.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.Entidades.Transaccion;
import app.proyecto.SistemaBancario.Entidades.Usuario;

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
		this.em.persist(transaccion);
	}
	
	public Transaccion buscarTransaccion(int id) {
		return em.find(Transaccion.class, id);
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
	/*public List<Transaccion> listaTransacionesCuenta(int cuenta_numeroCuenta) {
		String jqpl = "SELECT c FROM Cuenta c JOIN FETCH c.listaTra where c.id = :id";
		Query query = em.createQuery(jqpl, Cuenta.class);
		query.setParameter("cuenta_numeroCuenta", cuenta_numeroCuenta);
		 Cuenta cuenta = (Cuenta) query.getSingleResult();
		List<Transaccion> trans = new ArrayList<>();
		for (Transaccion t : cuenta.getListaTra()) {
			System.out.println(t.toString());
			trans.add(t);	
			
		}
		return trans;
		}*/
	/*public List<Transaccion> listaTransacionesCuenta(int cuenta_numeroCuenta) {
	String jpql = "SELECT a FROM Transaccion a where c.cuenta_numeroCuenta=cuenta_numeroCuenta ";
	Query query = em.createQuery(jpql, Transaccion.class);
	query.setParameter("cuenta_numeroCuenta", cuenta_numeroCuenta);
	List<Transaccion> transacciones = query.getResultList();
for (int i = 0; i < transacciones.size(); i++) {
	System.out.println(transacciones.get(i));
}
	
	return transacciones;
	}
	*/
	public List<Transaccion> listaTransaccionesCuenta(int id) {
		System.out.println("en dao>>> "+id);
		//String jpql = "SELECT cu FROM Cliente cl JOIN cl.cuentas cu WHERE cl.id= :id";
		String jpql = "SELECT cu FROM Cuenta cl JOIN cl.listaTra cu WHERE cl.id= :id";
		Query query = em.createQuery(jpql, Transaccion.class);
		query.setParameter("id", id);
		List<Transaccion> transacciones = query.getResultList();
		for (int i = 0; i < transacciones.size(); i++) {
			System.out.println("Hola mundo   "+ transacciones.get(i).getCuenta().getTipoCuenta());
		}
		return transacciones;
		}
}
