import java.util.*;
import java.io.*;

public class PlantillaAD
{
	private PlantillaDP actual;
	private BufferedReader archivoEntrada;
	private PrintWriter archivoSalida;

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

		actual = new PlantillaDP(nombre, contenido);
		listaPlantillas.add(actual);
		try 
		{
			archivoSalida = new PrintWriter(new FileWriter("indice.txt"),false);
			archivoSalida.println(actual.getNombre());
			respuesta = "Plantilla guardada Exitosamente";
			archivoSalida.close();
		}
		catch(FileNotFoundException inte)
		{
			System.out.println("Error "+ inte);
			respuesta = "Fallo al escribir el archivo";
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
	
}