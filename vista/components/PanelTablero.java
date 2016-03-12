package tp.pr5.vista.components;

import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import tp.pr5.control.ControladorGui;
import tp.pr5.control.Observador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.vista.components.PanelCeldas;

public class PanelTablero extends JPanel implements Observador
{


	private PanelCeldas panelCeldas;
	private JTextField quienJuega;
	private JScrollPane jSp;
	private ControladorGui myController;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor de la clase
	 * @param wC
	 * @param tInicial
	 * @param turnoInicial
	 */
	public PanelTablero(ControladorGui wC,TableroInmutable tInicial,Ficha turnoInicial)
	{
		myController=wC;
		panelCeldas=new PanelCeldas(myController,tInicial);
		quienJuega=new JTextField("            Juegan "+turnoInicial);
		quienJuega.setEditable(false);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		jSp=new JScrollPane(panelCeldas);
		
		jSp.setMinimumSize(new Dimension(100, 100));
		jSp.setPreferredSize(new Dimension(800, 1000));
		jSp.setMaximumSize(new Dimension(800,1100));
		
		
		
		this.add(jSp);
		Dimension minSize = new Dimension(0, 50);
		Dimension prefSize = new Dimension(0, 50);
		Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);
		
		this.add(new Box.Filler(minSize, prefSize, maxSize));
		
		this.add(quienJuega);
		myController.addPartidaObserver(this);
	}

	/**
	 * 
	 * Lanza una ventana informando al usuario del estado en el que ha terminado la partida
	 * @param ganador
	 */
	private void partidaTerminada(Ficha ganador) //(Partida partida,Ficha ganador)
	{
		if(!ganador.equals(Ficha.VACIA))
			JOptionPane.showMessageDialog(this, "Ganan "+ganador, "Ehorabuena", JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog(this, "Tablas", "Reintente", JOptionPane.INFORMATION_MESSAGE);
		quienJuega.setText("            Partida terminada ");
	}


	@Override
	public void onReset(TableroInmutable t,Ficha turno) 
	{
		quienJuega.setText("            Juegan "+turno);
	}
	/**
	 * Actualiza la celda que informa del turno
	 * @param turno nuevo turno
	 * @param t tablero
	 */
	private void afterNewMovement(Ficha turno,TableroInmutable t) 
	{
		quienJuega.setText("            Juegan "+turno);
	}


	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) 
	{
		final Ficha winner=ganador;
		SwingUtilities.invokeLater(new Runnable() 
		{
			
			@Override
			public void run() 
			{
				partidaTerminada(winner);
			}
		});
		//partidaTerminada(ganador);
	}


	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno) 
	{
		quienJuega.setText("            Juegan "+turno);
	}


	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) 
	{
	}


	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) 
	{
		quienJuega.setText("            Juegan "+turno);
	}


	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha antiguoJugador,
			Ficha turno) 
	{
		 //afterNewMovement(turno, tablero);
		final TableroInmutable tF=tablero;
		final Ficha color=turno;
		SwingUtilities.invokeLater(new Runnable() 
		{
			
			@Override
			public void run() 
			{
				afterNewMovement(color, tF);
			}
		});
	}
	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) 
	{
		
	}

}
