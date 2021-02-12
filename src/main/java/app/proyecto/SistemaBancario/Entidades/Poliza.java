package app.proyecto.SistemaBancario.Entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * 
 * @author andres Clase java encargada de la creacion de la tabla a nivel
 *         de Base de datos mediante la persistencia, asi mismo definr los
 *         atributos necesarios que estos requieran
 * 
 */
@Entity
public class Poliza implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private int plazo;
	private Double monto;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;
	
	private Double porcentajePoliza;
	private Double estadoVigencia;
	private boolean isSolicitud;
	
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private TasaInteres tasaItnteres;
	
	@Lob
	private byte[] cedulaImagen;
	@Lob
	private byte[] planillaImagen;

	@OneToOne
	@JoinColumn(name = "poliza_cuenta", referencedColumnName = "id")
	private Cuenta cuenta;
	
	@OneToOne
	@JoinColumn(name = "poliza_empleado", referencedColumnName = "id")
	private Usuario empleado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlazo() {
		return plazo;
	}

	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Double getPorcentajePoliza() {
		return porcentajePoliza;
	}

	public void setPorcentajePoliza(Double porcentajePoliza) {
		this.porcentajePoliza = porcentajePoliza;
	}

	public Double getEstadoVigencia() {
		return estadoVigencia;
	}

	public void setEstadoVigencia(Double estadoVigencia) {
		this.estadoVigencia = estadoVigencia;
	}

	public boolean isSolicitud() {
		return isSolicitud;
	}

	public void setSolicitud(boolean isSolicitud) {
		this.isSolicitud = isSolicitud;
	}

	public TasaInteres getTasaItnteres() {
		return tasaItnteres;
	}

	public void setTasaItnteres(TasaInteres tasaItnteres) {
		this.tasaItnteres = tasaItnteres;
	}

	public byte[] getCedulaImagen() {
		return cedulaImagen;
	}

	public void setCedulaImagen(byte[] cedulaImagen) {
		this.cedulaImagen = cedulaImagen;
	}

	public byte[] getPlanillaImagen() {
		return planillaImagen;
	}

	public void setPlanillaImagen(byte[] planillaImagen) {
		this.planillaImagen = planillaImagen;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Usuario getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Usuario empleado) {
		this.empleado = empleado;
	}


	
	/**
	 * Getters and setters: Metodos encargados del acceso a datos declarados
	 * públicos,los setters nos sirven para asignar un valor inicial a un atributo,
	 * pero de forma explícita, además el Setter nunca retorna nada (Siempre es
	 * void), y solo nos permite dar acceso público a ciertos atributos que deseemos
	 * el usuario pueda modificar, miestras que los getters nos sirven para obtener
	 * (recuperar o acceder) el valor ya asignado a un atributo y utilizarlo para
	 * cierto método.
	 * 
	 */
	
}
