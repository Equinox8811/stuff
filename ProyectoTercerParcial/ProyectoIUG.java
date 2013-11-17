import javax.swing.*;
import java.awt.*;

public class ProyectoIUG //extends JFrame implements ActionListener
{
	private JMenuBar menuBar;
	private JMenu menuArchivos;
	private JMenu menuPlantillas;
	private JMenuItem miPlantillas, miMisPlantillas, miGuardarPlantilla;
	private JMenuItem miAbrirArchivo, miGuardarArchivo, miGuardarArchivoComo, miSalir; 

	private JTextArea taDatos = new JTextArea();
	private JPanel panel = new JPanel();

	public ProyectoIUG()
	{
		super("Editor de Texto");

		menuBar = new JMenuBar();
		menuArchivos = new JMenu();
		menuPlantillas = new JMenu();

		miPlantillas = new JMenuItem("Plantillas");
		miMisPlantillas = new JMenuItem("Mis Plantillas");
		miGuardarPlantilla = new JMenuItem("Guardar Plantilla");
		miAbrirArchivo = new JMenuItem("Abrir Archivo");
		miGuardarArchivo = new JMenuItem("Guardar Archivo");
		miGuardarArchivoComo = new JMenuItem("Guardar como");
		miSalir = new JMenuItem("Salir");

		menuPlantillas.add(miPlantillas);
		menuPlantillas.add(miMisPlantillas);
		menuPlantillas.add(miGuardarPlantilla);

		menuArchivos.add(miAbrirArchivo);
		menuArchivos.add(miGuardarArchivo);
		menuArchivos.add(miGuardarArchivoComo);
		menuArchivos.add(miSalir);

		menuBar.add(menuPlantillas);
		menuBar.add(menuArchivos);
	}
}