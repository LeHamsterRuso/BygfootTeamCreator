import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class consola extends JFrame
{
	public static JTextArea texto = new JTextArea(BygfootTeamCreator.version);
	public consola()
	{
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.add(texto);
		this.setSize(450, 300);
		this.setLocation(450, 200);
		this.setTitle("Terminal");
		texto.setEditable(false);
		JButton cerrar = new JButton("Close");
		cerrar.addActionListener(new ocultar());
		add(cerrar);
	}
	class ocultar implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			show(false);
		}
	}
}
