package tp.pr5.vista.components;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tp.pr5.control.ControladorGui;
import tp.pr5.control.Observador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.vista.components.PanelTablero;

public class PanelIzquierdo extends JPanel implements Observador
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelTablero;
	private JButton botonAleatorio;
	private ControladorGui myController;
	
	
	public PanelIzquierdo(ControladorGui wC, TableroInmutable tableroInicial,Ficha turnoInicial) 
	{
		myController=wC;
		botonAleatorio=new JButton(new ImageIcon("src/images/ponerAleatorio.png","Poner Aleatorio"));//("Poner Aleatorio");
		panelTablero=new PanelTablero(wC,tableroInicial,turnoInicial);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		Dimension dimensionTablero=new Dimension(350, 400);
		panelTablero.setMaximumSize(dimensionTablero);
		panelTablero.setMinimumSize(dimensionTablero);
		panelTablero.setPreferredSize(dimensionTablero);
		this.add(panelTablero);
		Dimension minSize = new Dimension(0, 100);
		Dimension prefSize = new Dimension(0, 100);
		Dimension maxSize = new Dimension(0, 100);
		
		this.add(new Box.Filler(minSize, prefSize, maxSize));
		
		this.add(botonAleatorio);
		
		botonAleatorio.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				myController.generaMovimientoAleatorio();
			}
		});
	}


	@Override
	public void onReset(TableroInmutable tab, Ficha turno) 
	{
		botonAleatorio.setEnabled(true);
	}


	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) 
	{
		botonAleatorio.setEnabled(false);	
	}


	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno) 
	{
		botonAleatorio.setEnabled(true);
	}


	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) 
	{
		
	}


	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno) 
	{
		final boolean habilitar=myController.dameJugador(turno).isHuman();
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() 
			{
				botonAleatorio.setEnabled(habilitar);
			}
		});
	}


	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		// TODO Auto-generated method stub
		
	}
	
	public JButton getBotonAleatorio()
	{
		return botonAleatorio;
	}

	public void setAleatorioEnabled(boolean b) 
	{
		botonAleatorio.setEnabled(b);
	}
}
