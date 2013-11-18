import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlantillaIUG implements ActionListener
{
	private JPanel panel = new JPanel();
	private JButton[] botones;

	public PlantillaIUG(String nombres, int tamano)
	{
		JOptionPane.showMessageDialog(null, nombres);
		StringTokenizer st = new StringTokenizer(nombres,"&");
		botones = new JButton[tamano];
		String nombreBoton;
		panel.setLayout(new GridLayout(tamano/2,2));
		for (int i = 0;i<tamano;i++ ) 
		{
			nombreBoton = st.nextToken();
			botones[i] = new JButton(nombreBoton);
			botones[i].addActionListener(this);
			botones[i].setName(nombreBoton);
			panel.add(botones[i]);
		}		
	}

	public JPanel getPanel()
	{
		return panel;
	}

	public void actionPerformed(ActionEvent event)
	{
		for (int i =0;i<botones.length ;i++ ) 
		{
			if (event.getSource()==botones[i]) 
			{
				JOptionPane.showMessageDialog(null,botones[i].getName());
			}
		}
	}
}