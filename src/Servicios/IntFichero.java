package Servicios;

import java.util.List;

import Entidad.Paciente;
/**
 * Interfaz que define los métodos que dan servicio a Ficheros 
 * 
 * @author csi21-projmez
 *
 */
public interface IntFichero {
	/**	
	 *  Escribe mensajes en un fichero log.txt
	 * @param ruta
	 * @param mensaje
	 */
	public void EscribirFichero(String ruta, String mensaje);
	/**
	 * 	Escribe paciente en un fichero Pacientes.txt
	 * @param listaPaciente
	 * @param rutaArchivo
	 */
	public void escribirEnArchivoPaciente(List<Paciente> listaPaciente, String rutaArchivo, boolean append);
}
