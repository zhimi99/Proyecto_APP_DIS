package app.proyecto.SistemaBancario.Entidades;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Usuario {
	
	@Id
	private int id;
	private String cedulaU;
	private String nombresU;
	private String apellidosU;
	private String telefonoU;
	private String rolU;
	private String correoU;
	private String claveU;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCedulaU() {
		return cedulaU;
	}
	public void setCedulaU(String cedulaU) {
		this.cedulaU = cedulaU;
	}
	public String getNombresU() {
		return nombresU;
	}
	public void setNombresU(String nombresU) {
		this.nombresU = nombresU;
	}
	public String getApellidosU() {
		return apellidosU;
	}
	public void setApellidosU(String apellidosU) {
		this.apellidosU = apellidosU;
	}
	public String getTelefonoU() {
		return telefonoU;
	}
	public void setTelefonoU(String telefonoU) {
		this.telefonoU = telefonoU;
	}
	public String getRolU() {
		return rolU;
	}
	public void setRolU(String rolU) {
		this.rolU = rolU;
	}
	public String getCorreoU() {
		return correoU;
	}
	public void setCorreoU(String correoU) {
		this.correoU = correoU;
	}
	public String getClaveU() {
		return claveU;
	}
	public void setClaveU(String claveU) {
		this.claveU = claveU;
	}
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", cedulaU=" + cedulaU + ", nombresU=" + nombresU + ", apellidosU=" + apellidosU
				+ ", telefonoU=" + telefonoU + ", rolU=" + rolU + ", correoU=" + correoU + ", claveU=" + claveU + "]";
	}
	
}
