package app.proyecto.SistemaBancario.negocio;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import app.proyecto.SistemaBancario.DAO.usuarioDAO;
import app.proyecto.SistemaBancario.Entidades.Usuario;

@Stateless
public class UsuarioON {
	@Inject
	usuarioDAO usuariodao;
	
	public void crearUsuario(Usuario usuario) {
		System.out.println("en on "+ usuario.toString());
		this.usuariodao.crearUsuario(usuario);
	}
	
	public void eliminarUsuario(String cedula) {
		usuariodao.eliminarUsuario(cedula);
	}
	
	public Usuario buscarUsuario(String cedula) {
		return usuariodao.buscarUsuario(cedula);
	}
	
	public void actualizarUsuaurio(Usuario usuario) {
		usuariodao.actualizarUsuaurio(usuario);
	}
	
	public List<Usuario>mostrarUsuarios() {
		return this.usuariodao.mostrarUsuarios();
	}
	
	
	
	public Usuario obtenerCredenciales(String correo, String clave) {
		
		Usuario usu  = this.usuariodao.mostrarUsuarioCorreo(correo, clave);
	
		if(usu.equals(usu)) {
			
			return usu;
			
		}else {
			
			return null;
		}

	}
	
	
	public boolean validarCedula(String ced) {
        boolean verdadera = false;
        int num = 0;
        int ope = 0;
        int suma = 0;
        for (int cont = 0; cont < ced.length(); cont++) {
            num = Integer.valueOf(ced.substring(cont, cont + 1));
            if (cont == ced.length() - 1) {
                break;
            }
            if (cont % 2 != 0 && cont > 0) {
                suma = suma + num;
            } else {
                ope = num * 2;
                if (ope > 9) {
                    ope = ope - 9;
                }
                suma = suma + ope;
            }
        }
        if (suma != 0) {
            suma = suma % 10;
            if (suma == 0) {
                if (suma == num) {
                    verdadera = true;
                }
            } else {
                ope = 10 - suma;
                if (ope == num) {
                    verdadera = true;
                }
            }
        } else {
            verdadera = false;
        }
        return verdadera;
    }

	
	public boolean validarIngresoCorreo(String correo) {
		if (correo.matches("[^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$]")) {
			System.out.println("Correo valido");
		} else {
			System.out.println("Correo no valido");
		}
		return true;
	}
	
	
}