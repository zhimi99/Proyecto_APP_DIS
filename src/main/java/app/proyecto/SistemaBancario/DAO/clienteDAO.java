package app.proyecto.SistemaBancario.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import app.proyecto.SistemaBancario.Entidades.Cliente;

@Stateless
public class clienteDAO {

	@Inject
	private Connection con;
	
	@Inject
	private EntityManager em;
	
	
	public boolean insert(Cliente entity) throws SQLException{
		
		em.persist(entity);
		return true;
	}
	
	
	public boolean update(Cliente entity){
		
		em.merge(entity);
		return true;
	}
	
	
	public Cliente read(int id) {
		
		Cliente cliente = em.find(Cliente.class, id);
		return cliente;
	}
	
	
	public boolean delete(int id) {
	
		Cliente cliente = this.read(id);
		em.remove(cliente);
		return true;
	}
	
		//revisarrrr la forma de buscar
	public List<Cliente> getClientes() {
			
		String jpql = "SELECT c FROM cliente c WHERE tipoDocumento = ?1 ";
		Query q = em.createQuery(jpql, Cliente.class);
		q.setParameter(1, 1);
		return (List<Cliente>) q.getResultList();	
	}
	
	
}
