package app.proyecto.SistemaBancario.Entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TasaInteres implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
    private int minDias;
    private int maxDias;
	private Double tasaInteresPorcentaje;
    private Double interesGanadoValor;
	

	
}
