package app.proyecto.SistemaBancario.DAO;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sound.midi.MidiSystem;

import app.proyecto.SistemaBancario.Entidades.Cliente;

@Stateless
public class clienteDAO {

	@Inject
	private EntityManager em;

	public void crearCliente(Cliente cliente){
			em.persist(cliente);
	}

	public void eliminarCliente(String cedula) {
		em.remove(buscarClienteCedula(cedula));
	}
	
	public Cliente buscarCliente(String cedula) {
		Cliente cli =em.find(Cliente.class, cedula);
		System.out.println(cli.getCedula());
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
		Cliente cli= new Cliente();
		try {
			String jpql = "SELECT l FROM Cliente l where l.cedula = :cedula";
			Query query = em.createQuery(jpql, Cliente.class);
			query.setParameter("cedula", cedula);
			cli = (Cliente) query.getSingleResult();
			System.out.println("Dao test >>>>>>>   "+cli.getId());
		} catch (Exception e) {
			cli=null;
		}
		
		return cli;
	}



}
