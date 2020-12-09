package app.proyecto.SistemaBancario.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import app.proyecto.SistemaBancario.Entidades.Cliente;
import app.proyecto.SistemaBancario.Entidades.Cuenta;

@Stateless
public class cuentaDAO {

	@Inject
	private Connection con;
	
	@Inject
	private EntityManager em;
	
	public boolean insert(Cuenta entity) throws SQLException{
		em.persist(entity);
		return true;
			
	}
	
	
	public boolean update(Cuenta entity) {
		em.merge(entity);
		return true;
		
	}
	
	
	public Cuenta read(int id) {
		Cuenta cuenta = em.find(Cuenta.class, id);
		return cuenta;
	}
	
	
	public boolean delete(int id) {
		Cuenta cuenta = this.read(id);
		em.remove(cuenta);
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
