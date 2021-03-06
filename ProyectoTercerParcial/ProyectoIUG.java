import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProyectoIUG extends JFrame implements ActionListener
{
	private JMenuBar menuBar;
	private JMenu menuArchivos;
	private JMenu menuPlantillas;
	private JMenu menuConfiguracion;
	private JMenuItem miArchivoNuevo, miAbrirArchivo,miVistaPrevia, miSalir;
	private JMenuItem miPlantillas, miGuardarPlantilla;
	private JMenuItem miPersonalizar;

	private JTextArea taDatos = new JTextArea();
	private JPanel panel = new JPanel();
	

	private PlantillaAD plantillaAD = new PlantillaAD(); 
	private PlantillaIUG plantillaIUG;
	private EditorIUG editorIUG = new EditorIUG();
	private Server servidor = new Server();

	private String colores="Predeterminado";

	public ProyectoIUG()
	{
		super("Editor de Markdown");

		menuBar = new JMenuBar();
		menuArchivos = new JMenu("Archivo");
		menuPlantillas = new JMenu("Plantillas");
		menuConfiguracion = new JMenu("Configuracion");

		miPlantillas = new JMenuItem("Plantillas");
		miGuardarPlantilla = new JMenuItem("Guardar como Plantilla");

		miArchivoNuevo = new JMenuItem("Archivo Nuevo");
		miAbrirArchivo = new JMenuItem("Abrir Archivo");
		miVistaPrevia = new JMenuItem("Vista previa HTML");
		miSalir = new JMenuItem("Salir");

		miPersonalizar = new JMenuItem("Personalizar");

		menuPlantillas.add(miPlantillas);
		menuPlantillas.add(miGuardarPlantilla);

		menuArchivos.add(miArchivoNuevo);
		menuArchivos.add(miAbrirArchivo);
		menuArchivos.add(miVistaPrevia);
		menuArchivos.add(miSalir);

		menuConfiguracion.add(miPersonalizar);

		menuBar.add(menuArchivos);
		menuBar.add(menuPlantillas);
		menuBar.add(menuConfiguracion);

		miPlantillas.addActionListener(this);
		miGuardarPlantilla.addActionListener(this);

		miArchivoNuevo.addActionListener(this);
		miAbrirArchivo.addActionListener(this);
		miSalir.addActionListener(this);
		miVistaPrevia.addActionListener(this);

		miPersonalizar.addActionListener(this);


		setJMenuBar(menuBar);

		panel.setLayout(new GridLayout(1,1));
		add(panel);

		setVisible(true);
		setSize(700,700);
		plantillaAD.iniciar();
		servidor.iniciarServer();
	}

	public void ocultarPaneles()
	{
		panel.setVisible(false);
		editorIUG.getPanel().setVisible(false);
		try{
			plantillaIUG.getPanel().setVisible(false);		
		}
		catch(NullPointerException e)
		{

		}
	}
	public void actionPerformed(ActionEvent event)
	{

		if(event.getSource()==miSalir)
			System.exit(0);
		if(event.getSource()==miPlantillas)
		{
			ocultarPaneles();
			plantillaIUG = new PlantillaIUG(plantillaAD.getNombres(), plantillaAD.getTamano(), colores);
			plantillaIUG.getPanel().setVisible(true);
			add(plantillaIUG.getPanel());
			setVisible(true);
		}

		if(event.getSource()==miArchivoNuevo)
		{
			ocultarPaneles();
			editorIUG.getPanel().setVisible(true);
			add(editorIUG.getPanel());
			setVisible(true);
		}
			
		//if(event.getSource()==miMisPlantillas)
		if(event.getSource()==miGuardarPlantilla)
		{
			ocultarPaneles();
			JOptionPane.showMessageDialog(null,plantillaAD.add(editorIUG.getNombre(), editorIUG.getDatos()));
		}
		if(event.getSource()==miAbrirArchivo)
		{
			ocultarPaneles();
			JFileChooser chooser = new JFileChooser();
		    FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de texto", "txt");
		    chooser.setFileFilter(filter);
		    int returnVal = chooser.showOpenDialog(null);
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		    	System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
		    	String nombre = chooser.getSelectedFile().getName();
		    	String ruta = chooser.getSelectedFile().getAbsolutePath();
		    	//String nombre = JOptionPane.showInputDialog("Nombre de archivo a abrir");
		    	editorIUG.abrirArchivo(ruta,nombre).setVisible(true);
				editorIUG.getPanel().setVisible(true);
				add(editorIUG.getPanel());
				setVisible(true);
		   }
			
		}

		if (event.getSource()==miVistaPrevia) 
		{
			servidor.consultar(editorIUG.getDatos());
			servidor.abrirNavegador();
		}

		if (event.getSource()==miPersonalizar) {
			/*Object[] valoresPosibles = { "Esquemas Predefinidos", "Personalizados" };
			Object tipoPersonalizar = JOptionPane.showInputDialog(null,"Tipo de personalizacion", "Input",JOptionPane.INFORMATION_MESSAGE, null,valoresPosibles, valoresPosibles[0]);
			if (tipoPersonalizar.toString()=="Esquemas Predefinidos")
			{*/
			Object[] esquemasPosibles = { "Predeterminado", "Oscuro", "Solarizado" };
			Object estilo = JOptionPane.showInputDialog(null,"Esquema", "Input",JOptionPane.INFORMATION_MESSAGE, null,esquemasPosibles, esquemasPosibles[0]);
			try{
				editorIUG.setEstilo(estilo.toString()); 
				colores = estilo.toString();
			}
			catch(NullPointerException npe)
			{
				//pass
			}
			//}
		}
		
	}
	
	public static void main(String args[])
	{
		ProyectoIUG proyectoIUG = new ProyectoIUG();
		proyectoIUG.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}