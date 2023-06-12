
 package Servicios;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

import Entidad.Paciente;

public class ImplFichero implements IntFichero {
	
	@Override
	public void EscribirFichero(String ruta, String mensaje) {
		FileWriter fichero=null;
		LocalDateTime fechaHoraActual = LocalDateTime.now();
		try {
			fichero= new FileWriter(ruta,true);
			PrintWriter pw = new PrintWriter(fichero, true);
			pw.println("");
			pw.println("[" + fechaHoraActual + "] " + mensaje);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CerrarFichero(fichero);
		}

	}
	@Override
	 public void escribirEnArchivoPaciente(List<Paciente> listaPaciente, String rutaArchivo, boolean append) {
	        try (FileWriter fileWriter = new FileWriter(rutaArchivo,append)) {
	        	for (int i = 0; i < listaPaciente.size(); i++) {
	            String contenido = "\n"+ listaPaciente.get(i).getTelefono() + ";" + listaPaciente.get(i).getNombre() + ";" +
	            		listaPaciente.get(i).getFechaIngreso() + ";" + listaPaciente.get(i).getFechaAlta()+";"+ listaPaciente.get(i).isAlta();
	            fileWriter.write(contenido);
	        	}
	            System.out.println("Paciente escrito en el archivo correctamente.");
	        } catch (IOException e) {
	            System.out.println("Error al escribir el paciente en el archivo: " + e.getMessage());
	        }
	 }
	
	//Metodo para cerar los ficheros
	 public void CerrarFichero(FileWriter fichero) {

			try {
				// Nuevamente aprovechamos el finally para
				// asegurarnos que se cierra el fichero.
				if (null != fichero)
					fichero.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

}
