package Controlador;

import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Entidad.Paciente;
import Servicios.ImplFichero;
import Servicios.ImplMenu;
import Servicios.ImplPaciente;
import Servicios.IntFichero;
import Servicios.IntMenu;
import Servicios.IntPaciente;
import Utils.Mensajes;

public class Menu {

	public static void main(String[] args) {
//Instanciamos  todo Mensajes,fichero, Paciente, Menu
		
		
		IntFichero implFichero = new ImplFichero();
		Mensajes mensajes = new Mensajes();
		IntMenu implMenu = new ImplMenu();		
		IntPaciente paciente = new ImplPaciente();
	//Creamos variables que vamos a usar	
		int opcion;
		boolean opcionValida = false;
		// Lista de Paciente
		
//Rutas de los archivos Log y lista de Pacientes
		File archivo = new File("bin/Logs.txt");
		File archivoPAciente = new File("bin/BasedeDatosPacientes.txt");
		List<Paciente> listaPaciente = new ArrayList<>();
		Scanner entradaOpcion = new Scanner(System.in);
		 // Cargar la lista de pacientes desde el archivo al iniciar el programa
		listaPaciente = paciente.leerArchivoPaciente(new File("bin/BasedeDatosPacientes.txt"));
		int y = 0;
		//Menu donde elgimos la opcion  y mandara mensaje segum lo que suceda
		do {
			try {
				// Mostramos el menú
				implMenu.MostrarMenu();

				opcion = Integer.parseInt(entradaOpcion.nextLine());
				System.out.println("[INFO] - Has seleccionado la opcion " + opcion);

				switch (opcion) {

				case 1:
					listaPaciente = paciente.IngresarPaciente(listaPaciente, archivoPAciente.getPath().toString());
					break;
				case 2:
					listaPaciente = paciente.darDeAltaPaciente(listaPaciente, archivoPAciente);

					break;
				case 3:
					paciente.ListarPaciente(listaPaciente, archivoPAciente);

					break;
				case 4:
					System.out.println("Slaiendo del Progrma. Adios...");
					opcionValida = true;
				default:
					break;
				}
				//si la opcio no es valida mandara el mensaje
			} catch (NumberFormatException e) {
				System.out.println("Error: Opción inválida. Por favor, introduce un número válido.");
			} catch (InputMismatchException e) {
				//mandara mensaje segun que suceda
				implFichero.EscribirFichero(archivo.getPath(), mensajes.getFormatoERR());
			} catch (Exception e) {
				System.out.println("Se produjo un error" + e.getMessage());
			}
			//salimos del Menu
		} while (!opcionValida);
		//nos aseguramos de cerrar  entradaOpciones 
		entradaOpcion.close();
	}

}
