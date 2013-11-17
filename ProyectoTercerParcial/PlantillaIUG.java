import java.util.*;
import javax.swing.*;
import java.awt.*;

public class PlantillaIUG
{
	private JPanel panel = new JPanel();

	public PlantillaIUG(String nombres, int tamano)
	{
		JButton[] botones;
		StringTokenizer st = new StringTokenizer(nombres,"&");
		botones = new JButton[tamano];
		String nombreBoton;
		panel.setLayout(new GridLayout(tamano/2,2));
		for (int i = 0;i<tamano;i++ ) 
		{
			nombreBoton = st.nextToken();
			botones[i] = new JButton(nombreBoton);
			panel.add(botones[i]);
		}		
	}

	public JPanel getPanel()
	{
		return panel;
	}
}