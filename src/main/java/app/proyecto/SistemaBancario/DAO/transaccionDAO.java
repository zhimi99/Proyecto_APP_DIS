package app.proyecto.SistemaBancario.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import app.proyecto.SistemaBancario.Entidades.Usuario;

@Stateless
public class transaccionDAO {
	
	@Inject
	private Connection con;
	
	@Inject
	private EntityManager em;
	

	//gracias a JPA podemos hacer la insercion con el persist
	public boolean insert(Usuario entity) throws SQLException{
		
		em.persist(entity);
		return true;
	}

	
	public boolean update(Usuario entity){
		
		em.merge(entity);
		return true;
	}

	
	public Usuario read(int id) {
		
		Usuario usuario = em.find(Usuario.class, id);
		return usuario;
	}


	public boolean delete(int id) {
	
		Usuario usuario = this.read(id);
		em.remove(usuario);
		
		return true;
	}
	
	//revisarrrr la forma de buscar
	public List<Usuario> getUsuarios(){
		
		String jpql = "SELECT u FROM usuario u WHERE tipoDocumento = ?1 ";
		
		Query q = em.createQuery(jpql, Usuario.class);
		q.setParameter(1, 1);
		
		return (List<Usuario>) q.getResultList();
	}

}
