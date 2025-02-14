import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class acercaDe extends JFrame
{
	public acercaDe()
	{
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		setSize(400, 200);
		setTitle("About...");
		JTextArea texto = new JTextArea("BygfootTeamCreator " + BygfootTeamCreator.version + "\n\nSebastin Jos Moncho Maquet\n\n(GNU) 2006\n\ncomandantecobra@gmail.com\nhttp://comandantecobra.googlepages.com/bygfootteamcreator");
		texto.setEditable(false);
		add(texto);
		JButton cerrar = new JButton("Cerrar");
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