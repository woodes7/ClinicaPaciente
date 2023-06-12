package Entidad;

public class Paciente {
	//Atributos
	private String telefono;
	private String nombre;		
	private String fechaIngreso;
	private String fechaAlta;
	private boolean alta;
	//Constructor
	public Paciente(String telefono, String nombre, String fechaIngreso, String fechaAlta, boolean alta) {
		super();
		this.telefono = telefono;
		this.nombre = nombre;	
		this.fechaIngreso = fechaIngreso;
		this.fechaAlta = fechaAlta;
		this.alta = alta;
	}

	public Paciente() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Getters && Setters
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public boolean isAlta() {
		return alta;
	}
	public void setAlta(boolean alta) {
		this.alta = alta;
	}

	@Override
	public String toString() {
		return "Paciente [telefono=" + telefono + ", nombre=" + nombre + ", fechaIngreso=" + fechaIngreso
				+ ", fechaAlta=" + fechaAlta + ", alta=" + alta + "]";
	}	
	
}
