package app.proyecto.SistemaBancario.DAO;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.Entidades.Transaccion;

@Stateless
public class TransaccionDAO {

	@PersistenceContext
	private EntityManager em;

	public void crearTransaccion(Transaccion transaccion){
		System.out.println("en dao" + transaccion.toString());
		this.em.persist(transaccion);
	}

	public List<Transaccion> mostrarTransacciones() {
		String jpql = "SELECT a FROM Transaccion a";
		Query query = em.createQuery(jpql, Transaccion.class);
		List<Transaccion> transacciones = query.getResultList();

		return transacciones;
	}

	
	public List<Transaccion> listaTransacionesCuenta(int id) {
		String jqpl = "SELECT c FROM Cuenta c JOIN FETCH c.listaTra where c.id = :id";
		Query query = em.createQuery(jqpl, Cuenta.class);
		query.setParameter("numeroCuenta", id);
		 Cuenta cuenta = (Cuenta) query.getSingleResult();
		List<Transaccion> trans = new ArrayList<>();
		for (Transaccion t : cuenta.getListaTra()) {
			trans.add(t);	
		}
		return trans;
		}

}
