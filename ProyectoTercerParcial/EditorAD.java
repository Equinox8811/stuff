import java.util.*;
import java.io.*;
import com.github.rjeschke.txtmark.Processor;

import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

public class EditorAD
{
	private BufferedReader archivoEntrada;
	private PrintWriter archivoSalida;

	public String abrirArchivo(String nombre)
	{
		String datos = "";
		try
		{
			archivoEntrada = new BufferedReader(new FileReader(nombre));
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

	public void crear(String nombre, String datos)
	throws DocumentException, IOException 
    {
       
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter.getInstance(document, new FileOutputStream(nombre));
        // step 3
        document.open();
        // step 4
        document.add(new Paragraph(datos));
        // step 5
        document.close();
    }

	public String guardarArchivo(String nombre, String datos,String tipo)
	{
			String respuesta = "";
			try
			{
				File directorio = new File("archivos");
				directorio.mkdir();
				if (tipo.equals("Texto plano")) {
					archivoSalida = new PrintWriter(new FileWriter("archivos/"+nombre+".txt",false));
					archivoSalida.println(datos);
				}
				else if (tipo.equals("PDF")) 
				{
					// archivoSalida = new PrintWriter(new FileWriter("archivos/"+nombre+".pdf",false));
					// archivoSalida.println(datos);

					try
			        {
			            try
			            {
			                new EditorAD().crear("archivos/"+nombre+".pdf",datos);
			                crear("archivos/"+nombre+".pdf", datos);
			            }
			            catch(IOException ioe)
			            {}
			        }
			        catch(DocumentException de)
			        {
			            
			        }
				}
				else
				{
					archivoSalida = new PrintWriter(new FileWriter("archivos/"+nombre+".html",false));
					String markdown = Processor.process(datos);
					archivoSalida.println(markdown);
				}
				try
				{
					archivoSalida.close();
				}
				catch(NullPointerException npe)
				{

				}
				respuesta = "Archivo guardado exitosamente";
			}
			catch(IOException ioe)
			{
				respuesta = "Error al guardar el archivo";
			}

			
			return respuesta;
	}
}
