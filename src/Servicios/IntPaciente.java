package Servicios;

import java.io.File;
import java.util.List;

import Entidad.Paciente;
/**
 * Interfaz que define los métodos que dan servicio a la gestión de paciente
 * 
 * @author csi21-projmez
 *
 */
public interface IntPaciente {
	/**
	 * Actualiza la lista de paciente con el nuevo paciente
	 * 
	 * @param listapaciente  
	 * @return lista actualizada
	 */
	public List<Paciente> IngresarPaciente(List<Paciente> listaPaciente,String ruta);
	/**
	 * Actualiza la lista de paciente con el alta dada al paciente y su fecha 
	 * 
	 * @param listapaciente lista actual
	 * @return lista actualizada
	 */
	public List<Paciente> darDeAltaPaciente(List<Paciente> listaPaciente, File archivo);
	/**
	 * Listar todos los Pacientes
	 * 
	 * @param listapaciente
	 */
	public void ListarPaciente(List<Paciente> listaPaciente, File archivo);
	/**
	 * Recoge los datos de los pacientes del fichero BaseDeDatosPacientes
	 * @param rutaArchivo
	 * @return
	 */
	public  List<Paciente> leerArchivoPaciente(File archivo);
	
}
