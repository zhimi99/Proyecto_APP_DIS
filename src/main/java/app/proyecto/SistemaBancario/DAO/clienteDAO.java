package app.proyecto.SistemaBancario.DAO;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import app.proyecto.SistemaBancario.Entidades.Cliente;

@Stateless
public class ClienteDAO {

	@PersistenceContext
	private EntityManager em;

	public void crearCliente(Cliente cliente){
		System.out.println("en dao" + cliente.toString());

		this.em.persist(cliente);

	}

	public void eliminarCliente(String cedula) {
		em.remove(buscarCliente(cedula));
	}
	
	public Cliente buscarCliente(String cedula) {
		return em.find(Cliente.class, cedula);
	}
	
	public void actualizarUsuaurio(Cliente cliente) {
		em.merge(cliente);
	}

	public List<Cliente> mostrarClientes() {
		String jpql = "SELECT a FROM Cliente a";
		Query query = em.createQuery(jpql, Cliente.class);
		List<Cliente> clientes = query.getResultList();

		return clientes;
	}
	
	public Cliente buscarClienteCedula(String cedula) {
		String jpql = "SELECT l FROM Cliente l where l.cedula = :cedula";
		Query query = em.createQuery(jpql, Cliente.class);
		query.setParameter("cedula", cedula);
		Cliente cliente = (Cliente) query.getSingleResult();
		return cliente;
	}



}
