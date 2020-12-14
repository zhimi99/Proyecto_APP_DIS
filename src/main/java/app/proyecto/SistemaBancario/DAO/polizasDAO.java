package app.proyecto.SistemaBancario.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import app.proyecto.SistemaBancario.Entidades.Poliza;

@Stateless
public class polizasDAO {
	
	
	@Inject
	private Connection con;
	
	@Inject
	private EntityManager em;
	
	public boolean insert(Poliza entity) throws SQLException{
		em.persist(entity);
		return true;
			
	}
	
	
	public boolean update(Poliza entity) {
		em.merge(entity);
		return true;
		
	}
	
	
	public Poliza read(int id) {
		Poliza poliza = em.find(Poliza.class, id);
		return poliza;
	}
	
	
	public boolean delete(int id) {
		Poliza poliza = this.read(id);
		em.remove(poliza);
		return true;
	}
	
	//revisarrrr la forma de buscar
	public List<Poliza> getClientes() {
			
		String jpql = "SELECT p FROM poliza p WHERE tipoDocumento = ?1 ";
		Query q = em.createQuery(jpql, Poliza.class);
		q.setParameter(1, 1);
		return (List<Poliza>) q.getResultList();	
	}
	

}
