package app.proyecto.SistemaBancario.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import app.proyecto.SistemaBancario.Entidades.Cliente;
import app.proyecto.SistemaBancario.Entidades.Cuenta;
import app.proyecto.SistemaBancario.Entidades.Poliza;
import app.proyecto.SistemaBancario.Entidades.Sesion;
import app.proyecto.SistemaBancario.Entidades.Transaccion;
import app.proyecto.SistemaBancario.Entidades.Usuario;
import app.proyecto.SistemaBancario.Utils.TransaccionFachada;
import app.proyecto.SistemaBancario.negocio.ClienteON;
import app.proyecto.SistemaBancario.negocio.CuentaON;
import app.proyecto.SistemaBancario.negocio.PolizaON;
import app.proyecto.SistemaBancario.negocio.SesionON;
import app.proyecto.SistemaBancario.negocio.TransaccionON;

@Named
@ViewScoped
public class ClienteInB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;

	@Inject
	private ClienteON clienteon;
	
	@Inject
	private CuentaON cuentaon;
	
	@Inject
	private SesionON sesionon;
	
	@Inject
	private PolizaON polizaon;
	
	@Inject
	private TransaccionON transaccionon;
	
	private Cliente clienteLogin;
	
	private List<Sesion> sesiones;
	private Cliente newcliente;
	
	private Cuenta newcuenta;
	private Poliza newPoliza;
	private Transaccion newtransaccion;
	@PostConstruct
	public void init() {
		this.clienteLogin =recuperarClienteLogin();
		//newcliente = new Cliente();

		newcuenta = new Cuenta();
		newPoliza= new Poliza(); 
		this.newtransaccion = new Transaccion();
		//mostrarSesionesCorreo(this.clienteLogin.getCorreo());
	}
	
	public Cliente recuperarClienteLogin() {
		FacesContext fc = FacesContext.getCurrentInstance();
		newcliente = (Cliente) fc.getExternalContext().getSessionMap().get("cliente");
		List<Cuenta> cuentas = cuentaon.listarCuentasCliente(newcliente.getId());
		for (int i = 0; i < cuentas.size(); i++) {
			System.out.println(cuentas.get(i).getId());
		}
		this.sesiones=this.sesionon.mostrarSesionesCorreo(newcliente.getCorreo());
		
		return newcliente;
	}
	

	public String addCuenta() {
		Cuenta cuenta = new Cuenta();
		cuenta.setCliente(newcliente);
		newcliente.addCuenta(cuenta);
		return null;
	}
	
	public List<Cuenta> listarCuentasCliente() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Cliente usuarioLogin = (Cliente) fc.getExternalContext().getSessionMap().get("cliente");
		System.out.println("  Si llega revisa  la vista>>>>>>> "+this.cuentaon.listarCuentasCliente(usuarioLogin.getId()).size());
		return this.cuentaon.listarCuentasCliente(usuarioLogin.getId());
	}
	
	public String hi() {
		System.out.println("parametro de entrada "+newcuenta.getId());
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("ctatr", newcuenta.getId());	
		return "transaccionesCuenta?faces-redirect=true";
		
	}
	public String simularPoliza() {
		Poliza p= new Poliza();
		FacesContext fc = FacesContext.getCurrentInstance();
		int cuenta = (int) fc.getExternalContext().getSessionMap().get("ctatr");
		Cuenta cuent= this.cuentaon.mostrarCuenta(cuenta);
		Poliza polizap= new Poliza();
		polizap.setCuenta(cuent);
		polizap.setMonto(newPoliza.getMonto());
		polizap.setFechaInicio(new Date());
		polizap.setPlazo(newPoliza.getPlazo());
		p=this.polizaon.simularPoliza(polizap);
		newPoliza=p;
		System.out.println("  testinmg data  "+p.getMonto());
		return null;
	}
	
	public String solicitarPoliza() throws Exception {
		Poliza p= new Poliza();
		FacesContext fc = FacesContext.getCurrentInstance();
		int cuenta = (int) fc.getExternalContext().getSessionMap().get("ctatr");
		Cuenta cuent= this.cuentaon.mostrarCuenta(cuenta);
		Poliza polizap= new Poliza();
		if (cuent.getSaldo()>newPoliza.getMonto()) {
			polizap.setCuenta(cuent);
		polizap.setMonto(newPoliza.getMonto());
		polizap.setFechaInicio(new Date());
		polizap.setPlazo(newPoliza.getPlazo());
		polizap.setEstado("Solicitud");
		TransaccionFachada trx= new TransaccionFachada();
		trx.setCuentadestino(cuent.getId());
		trx.setMonto(polizap.getMonto());
		p=this.polizaon.simularPoliza(polizap);
		newPoliza=p;
		this.polizaon.crearPoliza(polizap);
		
		
		this.transaccionon.acreditaPoliza(trx);
		
		}else {
			System.out.println("fondos insuficientes");
		}
		
		return null;
	}
	
	/*public List<Sesion>mostrarSesionesCorreo() {
		
		return this.sesionon.mostrarSesionesCorreo(correo);
	}*/

	public FacesContext getFacesContext() {
		return facesContext;
	}

	public void setFacesContext(FacesContext facesContext) {
		this.facesContext = facesContext;
	}

	public ClienteON getClienteon() {
		return clienteon;
	}

	public void setClienteon(ClienteON clienteon) {
		this.clienteon = clienteon;
	}

	public Cliente getClienteLogin() {
		return clienteLogin;
	}

	public void setClienteLogin(Cliente clienteLogin) {
		this.clienteLogin = clienteLogin;
	}

	public List<Sesion> getSesiones() {
		return sesiones;
	}

	public void setSesiones(List<Sesion> sesiones) {
		this.sesiones = sesiones;
	}

	public Cliente getNewcliente() {
		return newcliente;
	}

	public void setNewcliente(Cliente newcliente) {
		this.newcliente = newcliente;
	}

	public Cuenta getNewcuenta() {
		return newcuenta;
	}

	public void setNewcuenta(Cuenta newcuenta) {
		this.newcuenta = newcuenta;
	}

	public Poliza getNewPoliza() {
		return newPoliza;
	}

	public void setNewPoliza(Poliza newPoliza) {
		this.newPoliza = newPoliza;
	}

	public Transaccion getNewtransaccion() {
		return newtransaccion;
	}

	public void setNewtransaccion(Transaccion newtransaccion) {
		this.newtransaccion = newtransaccion;
	}
	
	
	
}
