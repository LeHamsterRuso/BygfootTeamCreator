import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class BygfootTeamCreator extends JFrame
{
	public static String version = "v6.07.06";
	private acercaDe acercaDeForm = new acercaDe();
	public static JTextField slotJugador[] = new JTextField[20];
	public static futbolista jugadores[] = new futbolista[20];
	private crearFutbolista formFutbolistas[] = new crearFutbolista[20];
	private JTextField nombre = new JTextField();
	private JTextField estadio = new JTextField();
	private JTextField escudo = new JTextField();
	private JTextField talento = new JTextField();
	private JTextField formacion = new JTextField();
	private JTextField pais = new JTextField();
	private consola miConsola = new consola();
	
	public BygfootTeamCreator()
	{
		setTitle("BygfootTeamCreator");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(17,2));
		JMenuBar barra = new JMenuBar();
		JMenu filemenu = new JMenu("File");
		JMenuItem save = new JMenuItem("Save as");
		JMenuItem close = new JMenuItem("Close");
		close.addActionListener(new cerrarPrograma());
		JMenu viewmenu = new JMenu("View");
		JMenuItem terminal = new JMenuItem("Terminal");
		terminal.addActionListener(new mostrarConsola());
		JMenu helpmenu = new JMenu("Help");
		JMenuItem about = new JMenuItem("About...");
		about.addActionListener(new mostrarAcercaDe());
		save.addActionListener(new guardarXML());
		filemenu.add(save);
		filemenu.add(close);
		viewmenu.add(terminal);
		helpmenu.add(about);
		barra.add(filemenu);
		barra.add(viewmenu);
		barra.add(helpmenu);
		setJMenuBar(barra);
		setLocation(320, 100);
		add(new JLabel("Team name:"));
		add(nombre);
		add(new JLabel("Stadium name:"));
		add(estadio);
		add(new JLabel("Image symbol:"));
		add(escudo);
		add(new JLabel("Average talent:"));
		add(talento);
		add(new JLabel("Formation:"));
		add(formacion);
		add(new JLabel("Default contry names:"));
		add(pais);
		for(int i = 0; i < 20; i ++)
		{
			slotJugador[i] = new JTextField("No soccer player");
			slotJugador[i].setEditable(false);
			slotJugador[i].addMouseListener(new mostrarFormFutobilsta());
			add(slotJugador[i]);
			formFutbolistas[i] = new crearFutbolista();
		}
		JButton crear = new JButton("Create");
		crear.addActionListener(new guardarXML());
		add(crear);
		JButton cerrar = new JButton("Close");
		cerrar.addActionListener(new cerrarPrograma());
		add(cerrar);
		pack();
		miConsola.show();
		show();
	}
	public static void main(String[] args)
	{
		new BygfootTeamCreator();
	}
	class cerrarPrograma implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	class mostrarConsola implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			miConsola.show();
		}
	}
	class mostrarAcercaDe implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			acercaDeForm.show();
		}
	}
	class mostrarFormFutobilsta extends MouseAdapter
	{
		public void mousePressed(MouseEvent e)
		{
			Object componente = e.getSource();
			for(int i = 0; i < 20; i++)
				if (componente.equals(slotJugador[i]))
				{
					formFutbolistas[i].actualizar(i + 1);
					formFutbolistas[i].show();
				}
		}
	}
	class guardarXML implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JFileChooser dialogo = new JFileChooser();
			if (dialogo.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
			{
				File archivo = dialogo.getSelectedFile();
				try
				{
					BufferedWriter escritor = new BufferedWriter(new FileWriter(archivo));
					escritor.write("<team>\n");
					if (nombre.getText().length() > 0) escritor.write("\t<team_name>" + nombre.getText() +"</team_name>\n");
					if (estadio.getText().length() > 0) escritor.write("\t<stadium_name>" + estadio.getText() +"</stadium_name>\n");
					if (escudo.getText().length() > 0) escritor.write("\t<symbol>" + escudo.getText() +"</symbol>\n");
					if (talento.getText().length() > 0) escritor.write("\t<average_talent>" + talento.getText() +"</average_talent>\n");
					if (formacion.getText().length() == 3) escritor.write("\t<formation>" + formacion.getText() +"</formation>\n");
					if (pais.getText().length() > 0) escritor.write("\t<names_file>" + pais.getText() +"</names_file>\n");
					for(int i = 0; i < 20; i++)
					{
						if (slotJugador[i].isEditable())
						{
							escritor.write("<player>\n");
							escritor.write("\t\t<player_name>" + jugadores[i].nombre +"</player_name>\n");
							escritor.write("\t\t<birth_year>" + jugadores[i].ao +"</birth_year>\n");
							escritor.write("\t\t<birth_month>" + jugadores[i].mes +"</birth_month>\n");
							escritor.write("\t\t<skill>" + jugadores[i].skill +"</skill>\n");
							escritor.write("\t\t<talent>" + jugadores[i].talento +"</talent>\n");
							escritor.write("\t\t<position>" + jugadores[i].rol +"</position>\n");
							escritor.write("</player>\n");
						}
					}
					escritor.write("</team>");
					consola.texto.setText("XML saved\n" + consola.texto.getText());
					escritor.close();
				}catch(IOException excep){}
			}
		}
	}
}
