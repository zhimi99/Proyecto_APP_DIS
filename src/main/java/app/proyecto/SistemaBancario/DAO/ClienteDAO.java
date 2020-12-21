package app.proyecto.SistemaBancario.DAO;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sound.midi.MidiSystem;

import app.proyecto.SistemaBancario.Entidades.Cliente;

/**
 * 
 * @author andres Clase java encargada del manejo de opraciones sobre la base de
 *         datos, tendremos operaciones CRUD si los requerimientos nos lo pide
 * 
 */
@Stateless
public class ClienteDAO {
	/**
	 * Injeccion del entity manager
	 */

	@Inject
	private EntityManager em;

	/**
	 * Metodo que permite crear un objeto mediante la persitencia
	 * 
	 * @param cliente pide el objeto que va aser insertado en nuestra DB
	 */
	public void crearCliente(Cliente cliente) {
		em.persist(cliente);
	}

	/**
	 * Metodo que permite eliminar un cliente, esto lo realizara después de realizar
	 * un find mediante la cedula, si este existe lo procederá a eliiminar
	 * 
	 * @param cedula parametro unico que buscará una cedula dentro del cliente
	 */
	public void eliminarCliente(String cedula) {
		em.remove(buscarClienteCedula(cedula));
	}

	/**
	 * Metodo para buscar un cliente, mediante la persistencia realizará un find del
	 * cliente mediante la cedula, permitiendonos tener un objeto de retorno
	 * 
	 * @param cedula parametro unico que buscará una cedula dentro del cliente
	 * @return un objeto de tipo Cliente
	 */
	public Cliente buscarCliente(String cedula) {
		Cliente cli = em.find(Cliente.class, cedula);
		System.out.println(cli.getCedula());
		return em.find(Cliente.class, cedula);
	}

	/**
	 * Metodo que permite actualizar la información de un cliente mediante un merge
	 * de la persistencia
	 * 
	 * @param cliente mediante un cliente actualizara este cliente
	 */
	public void actualizarCliente(Cliente cliente) {
		em.merge(cliente);
	}

	/**
	 * Metodo que permite tener un arreglo de todos los clientes, esto lo realizamos
	 * mediante jpql
	 * 
	 * @return arreglo de clientes
	 */
	public List<Cliente> mostrarClientes() {
		String jpql = "SELECT a FROM Cliente a";
		Query query = em.createQuery(jpql, Cliente.class);
		List<Cliente> clientes = query.getResultList();

		return clientes;
	}

	/**
	 * Metodo para buscar un cliente,esto mediante jpql, tenemos un paramtero de
	 * ceula y nos retorno un objeto e tipo cliente
	 * 
	 * @param cedula parametro unico que buscará una cedula dentro del cliente
	 * @return un objeto de tipo Cliente
	 */

	public Cliente buscarClienteCedula(String cedula) {
		Cliente cli = new Cliente();
		try {
			String jpql = "SELECT l FROM Cliente l where l.cedula = :cedula";
			Query query = em.createQuery(jpql, Cliente.class);
			query.setParameter("cedula", cedula);
			cli = (Cliente) query.getSingleResult();
		} catch (Exception e) {
			cli = null;
		}

		return cli;
	}

}
