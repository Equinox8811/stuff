public class PlantillaDP
{
	private String nombre, contenido;

	public PlantillaDP()
	{
		this.nombre = "";
		this.contenido = ""; 
	}

	public PlantillaDP(String nombre, String contenido)
	{
		this.nombre = nombre;
		this.contenido = contenido;
	}

	public String getNombre()
	{
		return this.nombre;
	}
	public String getContenido()
	{
		return this.contenido;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}
	public void setContenido(String contenido)
	{
		this.contenido = contenido;
	}
}