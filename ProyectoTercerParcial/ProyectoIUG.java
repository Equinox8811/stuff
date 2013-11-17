import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProyectoIUG extends JFrame implements ActionListener
{
	private JMenuBar menuBar;
	private JMenu menuArchivos;
	private JMenu menuPlantillas;
	private JMenuItem miPlantillas, miMisPlantillas, miGuardarPlantilla;
	private JMenuItem miArchivoNuevo, miAbrirArchivo, miGuardarArchivo, miGuardarArchivoComo, miSalir; 

	private JTextArea taDatos = new JTextArea();
	private JPanel panel = new JPanel();
	private JPanel panelPlantillas = new JPanel();
	private JPanel panelEditor = new JPanel();

	private PlantillaAD plantillaAD = new PlantillaAD(); 
	private PlantillaIUG plantillaIUG;

	public ProyectoIUG()
	{
		super("Editor de Texto");

		menuBar = new JMenuBar();
		menuArchivos = new JMenu("Archivo");
		menuPlantillas = new JMenu("Plantillas");

		miPlantillas = new JMenuItem("Plantillas");
		miMisPlantillas = new JMenuItem("Mis Plantillas");
		miGuardarPlantilla = new JMenuItem("Guardar Plantilla");

		miArchivoNuevo = new JMenuItem("Archivo Nuevo");
		miAbrirArchivo = new JMenuItem("Abrir Archivo");
		miGuardarArchivo = new JMenuItem("Guardar Archivo");
		miGuardarArchivoComo = new JMenuItem("Guardar como");
		miSalir = new JMenuItem("Salir");

		menuPlantillas.add(miPlantillas);
		menuPlantillas.add(miMisPlantillas);
		menuPlantillas.add(miGuardarPlantilla);

		menuArchivos.add(miArchivoNuevo);
		menuArchivos.add(miAbrirArchivo);
		menuArchivos.add(miGuardarArchivo);
		menuArchivos.add(miGuardarArchivoComo);
		menuArchivos.add(miSalir);

		menuBar.add(menuArchivos);
		menuBar.add(menuPlantillas);

		miPlantillas.addActionListener(this);
		miMisPlantillas.addActionListener(this);
		miGuardarPlantilla.addActionListener(this);

		miArchivoNuevo.addActionListener(this);
		miAbrirArchivo.addActionListener(this);
		miGuardarArchivo.addActionListener(this);
		miGuardarArchivoComo.addActionListener(this);
		miSalir.addActionListener(this);

		setJMenuBar(menuBar);

		panel.setLayout(new GridLayout(1,1));
		add(panel);

		panelEditor.add(new JScrollPane(taDatos));

		setVisible(true);
		setSize(500,500);
		plantillaAD.iniciar();
	}

	public void actionPerformed(ActionEvent event)
	{

		if(event.getSource()==miSalir)
			System.exit(0);
		if(event.getSource()==miPlantillas)
		{
			plantillaIUG = new PlantillaIUG(plantillaAD.getNombres(), plantillaAD.getTamano());
			plantillaIUG.getPanel().setVisible(true);
			add(plantillaIUG.getPanel());
			setVisible(true);
		}
			
		/*if(event.getSource()==miMisPlantillas)
		if(event.getSource()==miGuardarPlantilla)

		if(event.getSource()==miAbrirArchivo)
		if(event.getSource()==miGuardarArchivo)
		if(event.getSource()==miGuardarArchivoComo)*/
		
		
	}
	
	public static void main(String args[])
	{
		ProyectoIUG proyectoIUG = new ProyectoIUG();
		proyectoIUG.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}