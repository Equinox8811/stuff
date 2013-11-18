import java.util.*;
import java.io.*;

public class PlantillaAD
{
	private PlantillaDP actual;
	private BufferedReader archivoEntrada, archivoEntradaNuevo;
	private PrintWriter archivoSalida, archivoSalidaNuevo; 

	private LinkedList listaPlantillas;

	public PlantillaAD()
	{
		listaPlantillas = new LinkedList();
	}

	public void iniciar()
	{
		String datos = "";
		try
		{
			archivoEntrada = new BufferedReader(new FileReader("indice.txt"));
			while(archivoEntrada.ready())
			{
				datos = archivoEntrada.readLine();
				PlantillaDP temporal = new PlantillaDP();
				temporal.setNombre(datos);
				listaPlantillas.add(temporal);
			}
			archivoEntrada.close();
		}
		catch(FileNotFoundException inte)
		{
			System.out.println("Error "+ inte);
		}
		catch(IOException ioe)
		{
			System.out.println("Error "+ ioe);
		}
	}

	public String add(String nombre, String contenido)
	{
		String respuesta = "";

		PlantillaDP temporal = new PlantillaDP(nombre, contenido);
		listaPlantillas.add(actual);
		
		try 
		{
			File directorio = new File("plantillas");
			directorio.mkdir();
			archivoSalida = new PrintWriter(new FileWriter("indice.txt",true));
			archivoSalida.println(temporal.getNombre());
			archivoSalida.close();

			archivoSalidaNuevo = new PrintWriter(new FileWriter("plantillas/"+nombre+".txt"),false);
			archivoSalidaNuevo.println(temporal.getContenido());
			archivoSalidaNuevo.close();

			respuesta = "Plantilla guardada Exitosamente";

		}
		catch(IOException ioe)
		{
			System.out.println("Error "+ ioe);
			respuesta = "Fallo al escribir el archivo";
		}
		
		return respuesta;
	}

	public String getNombres()
	{
		String respuesta = "";
		for(int i=0; i<listaPlantillas.size();i++)
		{
			PlantillaDP temporal = (PlantillaDP)listaPlantillas.get(i);
			respuesta = respuesta+temporal.getNombre()+"&";
		}
		return respuesta;
	}

	public int getTamano()
	{

		return listaPlantillas.size();
	}
	public String getContenido(String nombre)
	{
		String datos ="";
		try
		{
			archivoEntrada = new BufferedReader(new FileReader(nombre + ".txt"));
			while(archivoEntrada.ready())
			{
				datos = datos +"\n" +archivoEntrada.readLine();
			}
			archivoEntrada.close();
		}
		catch(IOException ioe)
		{

		}
		return datos;
	}
}