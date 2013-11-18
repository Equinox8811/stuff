import java.util.*;
import java.io.*;

public class EditorAD
{
	private BufferedReader archivoEntrada;
	private PrintWriter archivoSalida;

	public String abrirArchivo(String nombre)
	{
		String datos = "";
		try
		{
			archivoEntrada = new BufferedReader(new FileReader("archivos/"+nombre+".txt"));
			while(archivoEntrada.ready())
			{
				datos = datos +archivoEntrada.readLine()+"\n";
			}
			archivoEntrada.close();
		}
		catch(FileNotFoundException inte)
		{
			System.out.println("Error "+ inte);
			datos = "..El archivo no existe..";
		}
		catch(IOException ioe)
		{
			System.out.println("Error "+ ioe);
		}
		return datos;
	}

	public String guardarArchivo(String nombre, String datos)
	{
			String respuesta = "";
			try
			{
				File directorio = new File("archivos");
				directorio.mkdir();

				archivoSalida = new PrintWriter(new FileWriter("archivos/"+nombre+".txt",false));
				archivoSalida.println(datos);
				archivoSalida.close();
				respuesta = "Archivo guardado exitosamente";
			}
			catch(IOException ioe)
			{
				respuesta = "Error al guardar el archivo";
			}

			
			return respuesta;
	}
}
