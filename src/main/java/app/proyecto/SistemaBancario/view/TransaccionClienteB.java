package app.proyecto.SistemaBancario.view;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import app.proyecto.SistemaBancario.Entidades.Transaccion;
import app.proyecto.SistemaBancario.negocio.TransaccionON;

@Named
//@ConversationScoped
@ViewScoped
public class TransaccionClienteB implements Serializable{
	
	@Inject
	TransaccionON transaccionon;
	private int numeroCuenta;
	
	
	private List<Transaccion> transacciones;
	private List<Transaccion> transaccionesFiltradas;
	private Transaccion transaccion;
	private Date fecha ;
	
	private Date fecha1 ;private Date fecha2 ;
	private DateRange range;

	@PostConstruct
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		numeroCuenta= (int) fc.getExternalContext().getSessionMap().get("ctatr");
	this.transacciones = this.transaccionon.listarTransaccionesCuenta(numeroCuenta);
		/*try {
		FacesContext fc = FacesContext.getCurrentInstance();
		numeroCuenta= (int) fc.getExternalContext().getSessionMap().get("ctatr");
		
	listarTransaccionefecha(numeroCuenta, "2021-01-01");
	} catch (Exception e) {
		// TODO: handle exception
	}*/
	}
	
	public void listarTransaccionefecha(int id /*, String fecha2 */) throws ParseException{
		String fecha1="2021-01-01";
		SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
		Date f= formater.parse(fecha1);
		//Timestamp fech= new Timestamp();
		System.out.println(new Timestamp(f.getTime()));
		List<Transaccion> trs=this.transaccionon.listaTransaccionesCuentaRangoFecha(id, new Timestamp(f.getTime())); //this.transaccionon.listaTransaccionesCuentaRangoFecha(numeroCuenta, new Date(), new Date());
		
	}

	public TransaccionON getTransaccionon() {
		return transaccionon;
	}

	public void setTransaccionon(TransaccionON transaccionon) {
		this.transaccionon = transaccionon;
	}

	public List<Transaccion> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<Transaccion> transacciones) {
		this.transacciones = transacciones;
	}

	public Transaccion getTransaccion() {
		return transaccion;
	}

	public void setTransaccion(Transaccion transaccion) {
		this.transaccion = transaccion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Transaccion> getTransaccionesFiltradas() {
		return transaccionesFiltradas;
	}

	public void setTransaccionesFiltradas(List<Transaccion> transaccionesFiltradas) {
		this.transaccionesFiltradas = transaccionesFiltradas;
	}

	public Date getFecha1() {
		return fecha1;
	}

	public void setFecha1(Date fecha1) {
		this.fecha1 = fecha1;
	}

	public Date getFecha2() {
		return fecha2;
	}

	public void setFecha2(Date fecha2) {
		this.fecha2 = fecha2;
	}

	public DateRange getRange() {
		return range;
	}

	public void setRange(DateRange range) {
		this.range = range;
	}
	
	

	
	public boolean filterByDate(Object value, Object filter, Locale locale) {
		
	    Date colDate = (Date) value;
	    return this.range.getFrom().before(colDate) && this.range.getTo().after(colDate);
	}
	
	
}
