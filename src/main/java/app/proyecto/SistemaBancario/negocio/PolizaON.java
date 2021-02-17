package app.proyecto.SistemaBancario.negocio;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import app.proyecto.SistemaBancario.DAO.PolizaDAO;
import app.proyecto.SistemaBancario.DAO.TasaInteresDAO;
import app.proyecto.SistemaBancario.Entidades.Poliza;

@Stateless
public class PolizaON {
	@Inject
	PolizaDAO polizadao;
	
	@Inject
	TasaInteresDAO tasainteresdao;
	
	public void crearPoliza(Poliza poliza) {
		this.polizadao.crearPoliza(poliza);
	}
	public Poliza simularPoliza(Poliza polizap) {
		Poliza poliza= new Poliza();
		poliza.setCuenta(polizap.getCuenta());
		poliza.setMonto(polizap.getMonto());
		poliza.setFechaInicio(new Date());
		
		for (int i = 0; i < tasainteresdao.mostrarTasaIntereses().size(); i++) {
			int maximo=tasainteresdao.mostrarTasaIntereses().get(i).getMaxDias();
			int minimo=tasainteresdao.mostrarTasaIntereses().get(i).getMinDias();
			if (polizap.getPlazo()>=minimo && polizap.getPlazo()<=maximo) {
				poliza.setPlazo(polizap.getPlazo());
				poliza.setPorcentajePoliza(tasainteresdao.mostrarTasaIntereses().get(i).getTasaInteresPorcentaje());
				poliza.setTasaItnteres(tasainteresdao.mostrarTasaIntereses().get(i));
				poliza.setMonto((tasainteresdao.mostrarTasaIntereses().get(i).getTasaInteresPorcentaje()*polizap.getMonto())
						+polizap.getMonto());
			}else {
				
			}
		}
		
		return poliza;
	}
	
	public List<Poliza>mostrarPolizas() {
		return this.polizadao.mostrarPolizas();
	}


}