import java.util.*;
import java.io.*;
import javax.swing.*;

public class PlantillaAD
{
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
			archivoEntrada = new BufferedReader(new FileReader("indice.dat"));
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
		boolean repetido = false;
		if(nombre.equals(""))
		{
			respuesta = "La plantilla debe de tener nombre";
		}
		else
		{
			for (int i=0;i<listaPlantillas.size();i++ ) 
			{
				PlantillaDP temporalRepetido = (PlantillaDP)listaPlantillas.get(i);
				if (nombre.equals(temporalRepetido.getNombre())) 
				{
					repetido = true;
				}
			}
			if(!repetido)
			{
				PlantillaDP temporal = new PlantillaDP(nombre, contenido);
				listaPlantillas.add(temporal);
				try 
				{
					File directorio = new File("plantillas");
					directorio.mkdir();
					archivoSalida = new PrintWriter(new FileWriter("indice.dat",true));
					archivoSalida.println(temporal.getNombre());
					archivoSalida.close();

					archivoSalidaNuevo = new PrintWriter(new FileWriter("plantillas/"+nombre+".dat"),false);
					archivoSalidaNuevo.println(temporal.getContenido());
					archivoSalidaNuevo.close();

					respuesta = "Plantilla guardada Exitosamente";

				}
				catch(IOException ioe)
				{
					System.out.println("Error "+ ioe);
					respuesta = "Fallo al escribir el archivo";
				}
			}
			else
			{
				respuesta = "El nombre de la plantilla ya existe";
			}
		}
		
		return respuesta;
	}

	public String getNombres()
	{
		String respuesta = "";
		int i;
		//JOptionPane.showMessageDialog(null, listaPlantillas.size());
		for(i=0; i<listaPlantillas.size();i++)
		{
			PlantillaDP temporal = (PlantillaDP)listaPlantillas.get(i);
			//JOptionPane.showMessageDialog(null,temporal.getNombre());
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
			archivoEntrada = new BufferedReader(new FileReader("plantillas/"+nombre + ".dat"));
			while(archivoEntrada.ready())
			{
				datos = datos +"\n" +archivoEntrada.readLine();
			}
			archivoEntrada.close();
		}
		catch(IOException ioe)
		{
			System.out.println(ioe);
		}
		return datos;
	}
}