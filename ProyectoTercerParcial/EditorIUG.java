import java.awt.*;
import java.io.*;
import javax.swing.*;

public class EditorIUG
{
	private JPanel panelFlow = new JPanel();
	private JPanel panel = new JPanel();
	private JPanel panel2 = new JPanel();
	private JPanel panel3 = new JPanel();
	private JTextArea taDatos = new JTextArea(30,60);
	private JTextField tfNombre = new JTextField();
	private JButton bGuardar, bDescartar; 

	public EditorIUG()
	{
		panelFlow.setLayout(new FlowLayout());
		panel.setLayout(new GridLayout(2,1));
		panel2.setLayout(new GridLayout(2,2));
		panel3.setLayout(new GridLayout(2,2));

		bGuardar = new JButton("Guardar");
		bDescartar = new JButton("Descartar");

	
		panel2.add(new JLabel("Nombre del archivo"));
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
}