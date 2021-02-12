package app.proyecto.SistemaBancario.Entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * 
 * @author andres Clase java encargada de la creacion de la tabla a nivel
 *         de Base de datos mediante la persistencia, asi mismo definr los
 *         atributos necesarios que estos requieran
 * 
 */
@Entity
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 10)
	private String cedula;
	private String nombres;
	private String apellidos;
	private String telefono;
	private String rol;
	private String correo;
	private String clave;
	private String estado;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_poliza")
    private List<Poliza> listaSolicitudesPoliza;

	
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", cedula=" + cedula + ", nombres=" + nombres + ", apellidos=" + apellidos
				+ ", telefono=" + telefono + ", rol=" + rol + ", correo=" + correo + ", clave=" + clave + ", estado="
				+ estado + "]";
	}
	
	
	
}
