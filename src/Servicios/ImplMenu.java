package Servicios;
/**
 * Implementación de la IntMenu
 * @author csi21-projmez
 *
 */

public class ImplMenu implements IntMenu {

	
	@Override
	public void MostrarMenu() {
		
		System.out.println("Elige un opción de Menu");
		System.out.println("---------Menu--------------");
		System.out.println("1. Ingresar nuevo paciente");
		System.out.println("2. Dar Alta Paciente");
		System.out.println("3. Listar Paciente");
	}

}
