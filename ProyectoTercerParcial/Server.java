import javax.swing.*;
import java.awt.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import com.github.rjeschke.txtmark.Processor;

public class Server
{
	
	private ServerSocket server;
	private Socket 		 socket;

	private BufferedReader bufferEntrada;
	private PrintWriter    bufferSalida;
	private BufferedReader bufferLectura;

	private String codigo ="";	

	public Server()
	{
		
	}

	public void consultar(String datos)
	{
		String markdown = Processor.process(datos);
		codigo = markdown;
	}

	private void iniciarBuffers()
	{
		try
		{
			bufferEntrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bufferSalida  = new PrintWriter(socket.getOutputStream(), true);
			bufferSalida.flush();
		}
		catch(IOException ioe)
		{
			System.out.println("Error "+ ioe);
		}
	}

	private String recibirDatos()
	{
		String datos = "";

		try
		{
			datos = bufferEntrada.readLine();
		}
		catch(IOException ioe)
		{
			System.out.println("Error: "+ioe);
		}

		return datos;
	}

	private void cerrarConexion()
	{
		
		try
		{
			bufferEntrada.close();
			bufferSalida.close();
			socket.close();
		}
		catch(IOException ioe)
		{
			System.out.println("Error: "+ioe);
		}
	}

	private void enviarDatos(String datos)
	{
		bufferSalida.println(datos);
		bufferSalida.flush();
	}

	public void iniciarServer()
	{
		String datos = "";
		String protocolo = "";
		String codigoHtml="";
		String header = "";
		String respuesta = "";

		try
		{
			//1. Inicializar el Server
			server = new ServerSocket(5005,5);

			while(true)
			{
				//2. Aceptar peticion de conexion
					socket = server.accept();

				//3. Preparar buffer de entrada y salida
					iniciarBuffers();

				//4. Recibir datos del cliente
					protocolo = "";
					protocolo = protocolo + recibirDatos() + "\n";
					protocolo = protocolo + recibirDatos() + "\n";
					protocolo = protocolo + recibirDatos() + "\n";
					protocolo = protocolo + recibirDatos() + "\n";
					protocolo = protocolo + recibirDatos() + "\n";

				//5. Preparar respuesta del server

					codigoHtml = "";
					codigoHtml = codigo;

					header = "";
					header = header + "HTTP/1.1 200 OK\n";
					header = header + "Server HTTP v.005 Alpha\n";
					header = header + "Content-length: "+ codigoHtml.length()+"\n";
					header = header + "Content-type: text/html\n\n";;

					respuesta = header + codigoHtml;

				//6. Enviar respuesta al browser
					enviarDatos(respuesta);

				//7. Cerrar conexion
					cerrarConexion();
				}
			}
		catch(IOException ioe)
		{
				System.out.println("Error"+ioe);
		}
	}

	public void abrirNavegador()
	{
		try
		{
			Process p = Runtime.getRuntime().exec("firefox -new-window localhost:5005");
		}
		catch(IOException e)
		{

		}
	}

	/*public static void main(String args[])
	{
		Server objeto = new Server();
		objeto.iniciarServer();
	}*/
}