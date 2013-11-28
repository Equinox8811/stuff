import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlantillaIUG implements ActionListener
{

	private EditorIUG editorIUG = new EditorIUG();
	private PlantillaAD plantillaAD = new PlantillaAD();

	private JPanel panel = new JPanel();
	private JPanel panel2 = new JPanel();
	private JButton[] botones;

	private String estilo;

	public PlantillaIUG(String nombres, int tamano, String colores)
	{
		//JOptionPane.showMessageDialog(null, nombres);
		StringTokenizer st = new StringTokenizer(nombres,"&");
		botones = new JButton[tamano];
		String nombreBoton;
		panel.setLayout(new GridLayout(1,1));
		panel2.setLayout(new GridLayout(tamano/2,2));
		for (int i = 0;i<tamano;i++ ) 
		{
			nombreBoton = st.nextToken();
			botones[i] = new JButton(nombreBoton);
			botones[i].addActionListener(this);
			botones[i].setName(nombreBoton);
			panel2.add(botones[i]);
		}
		panel.add(panel2);
		panel.updateUI();

		estilo = colores;
	}

	public void updateEstilo()
	{
		editorIUG.setEstilo(estilo);
	}

	public JPanel getPanel()
	{
		return panel;
	}

	public void remove()
	{
		panel.remove(panel2);
	}

	public void actionPerformed(ActionEvent event)
	{

		for (int i =0;i<botones.length ;i++ ) 
		{
			if (event.getSource()==botones[i]) 
			{
				//JOptionPane.showMessageDialog(null,botones[i].getName());
				remove();
				editorIUG.getPanel().setVisible(true);
				editorIUG.setEstilo(estilo);
				panel.add(editorIUG.getPanel());
				editorIUG.setDatos(plantillaAD.getContenido(botones[i].getName()));
				editorIUG.setNombre(botones[i].getName());
				panel.updateUI();
			}
		}
	}
}