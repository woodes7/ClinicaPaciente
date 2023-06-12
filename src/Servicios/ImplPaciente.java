package Servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Entidad.Paciente;
import Servicios.ImplFichero;
import Servicios.IntFichero;
import Utils.Mensajes;

/**
 * Implementación de la IntPaciente
 * 
 * @author csi21-projmez
 *
 */
public class ImplPaciente implements IntPaciente {
	IntFichero implFichero = new ImplFichero();

	private Mensajes mensajes;

	public void listaPaciente(Mensajes mensajes) {
		this.mensajes = mensajes;
	}

	public List<Paciente> IngresarPaciente(List<Paciente> listaPaciente, String ruta) {

		try {				
				
			Scanner entradaValor = new Scanner(System.in);
			System.out.println("Ingresa nuevo Paciente");
			
			System.out.println("Dime el telefono del Paciente: ");
			String telefono = entradaValor.next();
			for(int i =0;i < listaPaciente.size(); i++) {
				
				if(telefono.equals(listaPaciente.get(i).getTelefono())) {
					System.out.println("No puedes ingresar un paciente que no se le dio de alta anteriormente");				
					return listaPaciente;	
				}
			}
			
			System.out.println("Dime el nombre del Paciente: ");
			String nombre = entradaValor.next();			
			
			String fechaIngreso = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a").format(LocalDateTime.now());
			String fechaAlta = "-------";
			
			Paciente paciente = new Paciente(telefono, nombre, fechaIngreso, fechaAlta, false);
			
			listaPaciente.add(paciente);
			System.out.println(listaPaciente);
			return listaPaciente;
		} catch (InputMismatchException e) {
			LocalDateTime fechaHoraActual = LocalDateTime.now();
			implFichero.EscribirFichero(ruta, mensajes.getFormatoERR());
			return listaPaciente;
		} catch (Exception e) {
			System.out.println("Se produjo un error" + e.getMessage());
			return listaPaciente;
		}
	}

	@Override
	public void ListarPaciente(List<Paciente> listaPaciente, File archivo) {

		try {
			if (!listaPaciente.isEmpty()) {

				for (int i = 0; i < listaPaciente.size(); i++) {
					System.out.println(listaPaciente.get(i).toString());

				}
				implFichero.escribirEnArchivoPaciente(listaPaciente, archivo.getPath(), false);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

		}
	}

	public List<Paciente> leerPacientesDeArchivo(String rutaArchivo) {
		List<Paciente> pacientes = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
			String linea;
			while ((linea = br.readLine()) != null) {
				String[] datos = linea.split(";");
				if (datos.length == 4) {
					String telefono = datos[0];
					String nombre = datos[1];
					String fechaIngreso = datos[2];
					String fechaAlta = datos[3];

					Paciente paciente = new Paciente(telefono, nombre, fechaIngreso, fechaAlta, true);
					pacientes.add(paciente);
				}
			}
			System.out.println("Pacientes leídos del archivo correctamente.");
		} catch (IOException e) {
			System.out.println("Error al leer los pacientes del archivo: " + e.getMessage());
		}

		return pacientes;
	}

	//@Override
	public List<Paciente> darDeAltaPaciente(List<Paciente> listaPaciente, File archivo) {
	    Scanner entradavalores = new Scanner(System.in);
	    try {
	        Paciente paciente = new Paciente();
	        System.out.println("Dime el número de teléfono para buscar al paciente: ");
	        String telefono = entradavalores.next();
	        
	        int pacientesSinAlta = 0; // Variable contador
	        
	        for (int i = 0; i < listaPaciente.size(); i++) {
	            if (telefono.equals(listaPaciente.get(i).getTelefono()) ) {
	            	if(listaPaciente.get(i).isAlta()) {
	            		System.out.println("El paciente ya tiene el alta");
	 	               	return listaPaciente;
	            	}
	                String fechaAlta = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a").format(LocalDateTime.now());
	                
	                System.out.println("Esta es la fecha de alta, ¿quieres modificarla? (S=sí, N=no)");
	                System.out.println(fechaAlta);
	                String opcion = entradavalores.next();
	                
	                
	                if (opcion.equalsIgnoreCase("S")) {
	                    fechaAlta = entradavalores.next();
	                    String nuevaFechaAlta= entradavalores.next();
	                    try { 
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm:ss a");
                            LocalDateTime dischargeDateTime = LocalDateTime.parse(nuevaFechaAlta, formatter);
                            fechaAlta = formatter.format(dischargeDateTime);
                        } catch (DateTimeParseException e) {
                            System.out.println("Formato de fecha inválido. La fecha de alta no se ha modificado.");
                        }
	                }
	            
	                listaPaciente.get(i).setAlta(true);
	                listaPaciente.get(i).setFechaAlta(fechaAlta);
	                implFichero.escribirEnArchivoPaciente(listaPaciente, archivo.getPath(), false);
	            } 
	        }
	    	implFichero.escribirEnArchivoPaciente(listaPaciente,archivo.getPath(), false);
	        System.out.println("Número de pacientes sin dar de alta con el mismo número de teléfono: " + pacientesSinAlta);
	    } catch (Exception e) {
	        System.out.println("Se produjo un error: " + e.getMessage());
	        implFichero.EscribirFichero(null, mensajes.getFormatoERR());
	        return listaPaciente;
	    }
	    
	    return listaPaciente;
	}


public List<Paciente> leerArchivoPaciente(File archivo) {
    List<Paciente> listaPaciente = new ArrayList<>();

    try (FileReader fileReader = new FileReader(archivo);
         BufferedReader bufferedReader = new BufferedReader(fileReader)) {

        String linea;
        while ((linea = bufferedReader.readLine()) != null) {
        	String[] datos = linea.split(";");
            if (datos.length == 5) {
                String telefono = datos[0];
                String nombre = datos[1];
                String fechaIngreso = datos[2];
                String fechaAlta = datos[3];
                boolean alta = Boolean.parseBoolean(datos[4]);
                Paciente paciente = new Paciente(telefono, nombre, fechaIngreso, fechaAlta, alta);
                listaPaciente.add(paciente);
            }
            System.out.println(linea);
        }
            
        
        System.out.println("Datos de pacientes cargados desde el archivo.");
    } catch (FileNotFoundException e) {
        System.out.println("El archivo de pacientes no existe.");
    }   catch (IOException e) {
        System.out.println("Error al leer el archivo: " + e.getMessage());
    
    } catch (Exception e) {
        System.out.println("Error al leer los datos de pacientes del archivo: " + e.getMessage());    
    }
    return listaPaciente;
}
}