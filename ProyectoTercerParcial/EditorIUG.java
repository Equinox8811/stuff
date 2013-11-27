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
	}

	public JPanel abrirArchivo(String nombre)
	{
		String datos = "";
		datos = editorAD.abrirArchivo(nombre);
		tfNombre.setText(nombre);
		taDatos.setText(datos);
		return panelFlow;
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
					//pass
				}
			}
		}
		if(event.getSource()==bDescartar)
		{
			taDatos.setText("");
			tfNombre.setText("");
		}
	}
}