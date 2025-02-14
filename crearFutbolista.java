import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class crearFutbolista extends JFrame
{
	public int quien = 0;
	private JTextField nombre = new JTextField();
	private JTextField año = new JTextField();
	private JTextField mes = new JTextField();
	private JTextField skill = new JTextField();
	private JTextField talento = new JTextField();
	JComboBox demarcacion = new JComboBox();
	public crearFutbolista()
	{
		setLayout(new GridLayout(7,2));
		setTitle("Soccer Player N" + quien);
		add(new JLabel("Name:"));
		add(nombre);
		add(new JLabel("Birth year:"));
		add(año);
		add(new JLabel("Birth month:"));
		add(mes);
		add(new JLabel("Skill:"));
		add(skill);
		add(new JLabel("Talent:"));
		add(talento);
		add(new JLabel("Position:"));
		demarcacion.addItem("Goal Keeper");
		demarcacion.addItem("Defender");
		demarcacion.addItem("Midfield");
		demarcacion.addItem("Forward");
		add(demarcacion);
		JButton botonCrear = new JButton("Create player");
		botonCrear.addActionListener(new crear());
		add(botonCrear);
		JButton botonCancelar = new JButton("Cancel");
		botonCancelar.addActionListener(new cancelar());
		add(botonCancelar);
		pack();
	}
	public void actualizar(int quien)
	{
		this.quien = quien;
		setTitle("Soccer Player N" + quien);
	}
	class cancelar implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			show(false);
		}
	}
	class crear implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				if (Integer.parseInt(talento.getText()) < 10000 && Integer.parseInt(skill.getText()) < 10000 && Integer.parseInt(mes.getText()) < 13 && Integer.parseInt(mes.getText()) > 0 && Integer.parseInt(talento.getText()) >= 0 && Integer.parseInt(skill.getText()) >= 0)
				{
					BygfootTeamCreator.jugadores[quien-1] = new futbolista(nombre.getText(), Integer.parseInt(año.getText()), Integer.parseInt(mes.getText()), Integer.parseInt(skill.getText()), Integer.parseInt(talento.getText()), demarcacion.getSelectedIndex());
					BygfootTeamCreator.slotJugador[quien-1].setText(nombre.getText());
					BygfootTeamCreator.slotJugador[quien-1].setEditable(true);
					BygfootTeamCreator.slotJugador[quien-1].setEnabled(false);
					show(false);
				}else
				{
					if (Integer.parseInt(talento.getText()) > 10000)
						consola.texto.setText("Talent must be < 1000\n" + consola.texto.getText());
					else if (Integer.parseInt(skill.getText()) < 10000)
							consola.texto.setText("Skill must be < 1000\n" + consola.texto.getText());
					else if (Integer.parseInt(mes.getText()) < 13)
						consola.texto.setText("Month must be < 13\n" + consola.texto.getText());
					else
						consola.texto.setText("Skill, Talent and month can't be negative\n" + consola.texto.getText());
				}
			}catch(NumberFormatException exc)
			{
				consola.texto.setText("Problems parsing: Review the values or check that you wrote all the parameters\n" + consola.texto.getText());
			}
		}
	}
}
