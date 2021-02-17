package app.proyecto.SistemaBancario.DAO;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import app.proyecto.SistemaBancario.Entidades.Cliente;
import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.Entidades.Transaccion;
/**
 * 
 * @author andres Clase java encargada del manejo de opraciones sobre la base de
 *         datos, tendremos operaciones CRUD si los requerimientos nos lo pide
 * 
 */
@Stateless
public class CuentaDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Metodo que permite crear un objeto mediante la persitencia
	 * 
	 * @param cuenta pide el objeto que va a ser insertado en nuestra DB
	 */
	public void crearCuenta(Cuenta cuenta) {
		this.em.persist(cuenta);
		
	}
	
	/*public Cuenta buscarCuenta(String nombres) {
		String jpql = "SELECT a FROM Autor a JOIN FETCH a where a.nombres = :nombres";
		Query query = em.createQuery(jpql, Autor.class);
		query.setParameter("nombres", nombres);
		Autor autor = (Autor) query.getSingleResult();
		return autor;
	}*/
	
	/**
	 * Metodo que permite actualizar la información de una cuenta mediante un merge
	 * de la persistencia
	 * 
	 * @param cuenta mediante una actualizara la cuenta
	 */
	
	public void actualizarCuenta(Cuenta cuenta ) {
		em.merge(cuenta);
	}

	/**
	 * Metodo que permite tener un arreglo de todas las cuentas, esto lo realizamos
	 * mediante jpql
	 * 
	 * @return arreglo de cuentas
	 */
	public List<Cuenta> mostrarCuentas() {
		String jpql = "SELECT a FROM Cuenta a";
		Query query = em.createQuery(jpql, Cuenta.class);
		List<Cuenta> cuentas = query.getResultList();
		
		return cuentas;
	}
	
	
	/**
	 * Metodo para buscar una cuenta, mediante la persistencia realizará un find de la
	 * cuenta mediante el id, permitiendonos tener un objeto de retorno
	 * 
	 * @param id parametro unico que buscará un id una cuenta dependiendo su id
	 * @return un objeto de tipo Cuenta
	 */
	public Cuenta buscarCuentaID(int id) {
		Cuenta cli= new Cuenta();
		try {
			String jpql = "SELECT l FROM Cuenta l where l.id = :id";
			Query query = em.createQuery(jpql, Cuenta.class);
			query.setParameter("id", id);
			cli = (Cuenta) query.getSingleResult();
		} catch (Exception e) {
			cli=null;
		}
		
		return cli;
	}
	
	/**
	 * Metodo para buscar un  Cuentae, mediante la persistencia realizará un find del
	 *  Cuenta mediante el numero de tipo entero, permitiendonos tener un objeto de retorno
	 * 
	 * @param numero parametro unico que buscará un a cuenta dentro del Cuenta
	 * @return un objeto de tipo  Cuenta
	 */
	public Cuenta buscarCuenta(int id) {
		 //Cuenta cuenta = em.find( Cuenta.class, id);
		return em.find( Cuenta.class, id);
	}
	
	public List<Cuenta> mostrarCuentasClienteID(int idCliente) {
		String jpql = "SELECT l FROM Cuenta l where l.cliente_cuenta = :idCliente ORDER BY DESC";
		Query query = em.createQuery(jpql, Cuenta.class);
		query.setParameter("idCliente", idCliente);
		List<Cuenta> cuentas = query.getResultList();
		for (int i = 0; i < cuentas.size(); i++) {
			System.out.println(cuentas.get(i).toString());
		}
		
		return cuentas;
	}
	
	public List<Cuenta> mostrarCuentasClientexCedula(String cedula) {
		String jpql = "SELECT c FROM Cuenta c where c.cuenta_cliente_id = :cedula";
		Query query = em.createQuery(jpql, Cuenta.class);
		query.setParameter("cedula", cedula);
		List<Cuenta> cuentas = query.getResultList();
	
		return cuentas;
	}
	public List<Cuenta> listaCuentasCliente(int id) {
		//String jpql = "SELECT l FROM Cuenta l where l.cliente = :id";
		//SELECT d FROM Employee e INNER JOIN e.department d
		//String jpql = "SELECT c FROM Cuenta  JOIN FETCH c.cliente where c.id = :id";
		//String jpql = "SELECT c FROM Cuenta c where c.Cliente.id = :id";
		//SELECT c FROM CuentaAhorro c JOIN FETCH c.listaTra where c.numeroCuenta = :numeroCuenta";
		//"SELECT ph FROM Employee e JOIN e.phones ph WHERE ph LIKE '1%'", Phone.class);
		String jpql = "SELECT cu FROM Cliente cl JOIN cl.cuentas cu WHERE cl.id= :id ORDER BY cu.id DESC";
		Query query = em.createQuery(jpql, Cuenta.class);
		query.setParameter("id", id);
		List<Cuenta> cuentas = query.getResultList();
		for (int i = 0; i < cuentas.size(); i++) {
			System.out.println("Hola mundo   "+ cuentas.get(i).getCliente().getNombres());
		}
		return cuentas;
		}

}
