import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditorIUG implements ActionListener
{
	private JPanel panelFlow = new JPanel();
	private JPanel panel = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JTextArea taDatos = new JTextArea(30,60);
	private JTextField tfNombre = new JTextField();
	private JButton bGuardar, bDescartar; 
	private Color[] colores = {Color.BLUE, Color.CYAN, Color.RED,Color.GREEN,Color.YELLOW,Color.GRAY,Color.BLACK,Color.WHITE};

	private EditorAD editorAD = new EditorAD();

	public EditorIUG()
	{
		panelFlow.setLayout(new FlowLayout());
		panel.setLayout(new GridLayout(2,1));
		panel2.setLayout(new GridLayout(2,2));
		panel3.setLayout(new GridLayout(2,2));

		bGuardar = new JButton("Guardar Archivo");
		bGuardar.addActionListener(this);
		bDescartar = new JButton("Limpiar Campos");
		bDescartar.addActionListener(this);
	
		panel2.add(new JLabel("Nombre del archivo:  "));
		panel2.add(tfNombre);
		panel2.add(new JLabel(""));
		panel2.add(new JLabel(""));

		panel3.add(bGuardar);
		panel3.add(bDescartar);
		panel3.add(new JLabel(""));
		panel3.add(new JLabel(""));

		panel.add(panel2);
		panel.add(panel3);

		panelFlow.add(panel);
		panelFlow.add(new JScrollPane(taDatos));
		panelFlow.add(new JLabel("Si quieres saber mas de Markdown visita http://daringfireball.net/projects/markdown/syntax "));

		
	}

	public JPanel abrirArchivo(String ruta, String nombre)
	{

		String datos = "";
		datos = editorAD.abrirArchivo(ruta);
		tfNombre.setText(nombre);
		taDatos.setText(datos);
		return panelFlow;
	}

	public void setEstilo(String opcion)
	{
		if (opcion.equals("Predeterminado")) {
			taDatos.setBackground(Color.WHITE);
			taDatos.setForeground(Color.BLACK);
			taDatos.setSelectionColor(new Color(184,207,229));
		}

		if (opcion.equals("Oscuro")) {
			taDatos.setBackground(new Color(39,40,34));
			taDatos.setForeground(Color.WHITE);
			taDatos.setSelectionColor(Color.WHITE);
		}

		if (opcion.equals("Solarizado")) {
			taDatos.setBackground(new Color(4,32,41));
			taDatos.setForeground(new Color(99,148,150));
			taDatos.setSelectionColor(new Color(0,0,0));
		}
		panelFlow.updateUI();

	}

	public void cambiarColor(int opcion, int color)
	{
		color = color - 1;
		if(opcion == 1)	taDatos.setBackground(colores[color]);
		if(opcion == 2) taDatos.setForeground(colores[color]);
		if(opcion == 3) taDatos.setSelectionColor(colores[color]);
		panelFlow.updateUI();
	}

	public JPanel getPanel()
	{
		return panelFlow;
	}

	public void setNombre(String nombre)
	{
		tfNombre.setText(nombre);
	}

	public void setDatos(String datos)
	{
		taDatos.setText(datos);
		
	}

	public String getNombre()
	{
		return tfNombre.getText();
	}

	public String getDatos()
	{
		return taDatos.getText();
	}

	public void actionPerformed(ActionEvent event)
	{
		if(event.getSource()==bGuardar)
		{
			if(tfNombre.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null,"Debes proporcionar un nombre de archivo");
			}
			else
			{
				Object[] valoresPosibles = { "Texto plano", "PDF", "HTML" };
				Object tipoArchivo = JOptionPane.showInputDialog(null,"Escoge el tipo de archivo", "Input",JOptionPane.INFORMATION_MESSAGE, null,valoresPosibles, valoresPosibles[0]);
				String nombre = tfNombre.getText();
				String datos = taDatos.getText();
				try{
					String respuesta =editorAD.guardarArchivo(nombre, datos, tipoArchivo.toString());
					JOptionPane.showMessageDialog(null, respuesta);
				}
				catch(NullPointerException npe)
				{
					
				}
			}
		}

		if(event.getSource()==bDescartar)
		{
			taDatos.setText("");
			tfNombre.setText("");
		}

		/*if(event.getSource()==bPersonalizar)
		{
			int opcion = 0;
			int color = 0;
			do
			{
				opcion = 0;
				color = 0;
				try
				{
					opcion = Integer.parseInt(JOptionPane.showInputDialog("Cambiar color...\n1) Fondo\n2) Letra \n3) Seleccionador\n4) Salir"));
				}
				catch(NumberFormatException e)
				{
					//pass
				}
				if((opcion<1)||(opcion>4))
				{
					JOptionPane.showMessageDialog(null, "Opcion invalida");
				}
				else
				{
					if(opcion!=4)
					{
						try 
						{
							color = Integer.parseInt(JOptionPane.showInputDialog("Elige un color\n1) Azul \n2) Azul Claro\n3) Rojo\n4) Verde\n5) Amarillo\n6) Gris\n7) Negro\n8) Blanco"));
							cambiarColor(opcion, color);
							panelFlow.updateUI();
						}
						catch(NumberFormatException e)
						{
							JOptionPane.showMessageDialog(null, "Opcion invalida");
						}
					}
				}
			}
			while(opcion!=4);
		}*/
	}
}