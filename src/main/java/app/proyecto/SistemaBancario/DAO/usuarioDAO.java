package app.proyecto.SistemaBancario.DAO;

import java.sql.Connection;
import javax.persistence.EntityManager;
import app.proyecto.SistemaBancario.Entidades.Usuario;

public class usuarioDAO {
	
	
	private EntityManager em;
	private Connection con;
	

	public boolean insert(Usuario entity) {
		em.persist(entity);
		return true;
	}

	
	public boolean update(Usuario entity) {
		em.merge(entity);
		return true;
	}

	
	public Usuario read(int id) {
		Usuario usuario = em.find(Usuario.class, id);
		
		return null;
	}


	public boolean delete(int id) {
	
		Usuario usuario = this.read(id);
		em.remove(usuario);
		
		return true;
	}

}
