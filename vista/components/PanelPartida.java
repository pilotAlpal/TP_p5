package tp.pr5.vista.components;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tp.pr5.control.ControladorGui;
import tp.pr5.control.Observador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;

public class PanelPartida  extends JPanel implements Observador
{

	private static final long serialVersionUID = 1L;
	private ControladorGui myController;
	private JButton botonDeshacer,botonReiniciar;
	
	public PanelPartida(ControladorGui wC)
		{
			myController=wC;
			this.setBorder(javax.swing.BorderFactory.createTitledBorder("Partida"));
			botonDeshacer=new JButton(new ImageIcon("src/images/undo.png","Deshacer"));
			botonReiniciar=new JButton(new ImageIcon("src/images/reset.png","Reiniciar"));
			botonDeshacer.addActionListener(new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					myController.undo();
				}
			});
			botonDeshacer.setEnabled(false);
			botonReiniciar.addActionListener(new ActionListener() 
			{
				
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					myController.reset();
				}
			});
			botonReiniciar.setEnabled(false);
			this.setLayout(new FlowLayout());
			this.add(botonDeshacer);
			this.add(botonReiniciar);
			myController.addPartidaObserver(this);
		}
	
		
		private void partidaTerminada(Ficha ganador) 
		{
			botonDeshacer.setEnabled(false);
			botonReiniciar.setEnabled(true);
		}
		
		@Override
		public void onReset(TableroInmutable t,Ficha f) 
		{
			botonReiniciar.setEnabled(false);
			botonDeshacer.setEnabled(false);
		}
		private void pilaVacia() 
		{
			botonDeshacer.setEnabled(false);
		}
		private void pilaNoVacia(Ficha turno) 
		{
			boolean habilitar=myController.dameJugador(turno).isHuman();
			botonDeshacer.setEnabled(habilitar);
			botonReiniciar.setEnabled(true);
		}

		@Override
		public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) 
		{
			final Ficha winner=ganador;
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() 
				{
					partidaTerminada(winner);
				}
			});
		}

		@Override
		public void onCambioJuego(TableroInmutable tab, Ficha turno) 
		{
			botonReiniciar.setEnabled(false);
			botonDeshacer.setEnabled(false);
		}

		@Override
		public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) 
		{	
			botonDeshacer.setEnabled(false);
		}

		@Override
		public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) 
		{
			if (!hayMas)
				pilaVacia();
		}

		@Override
		public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
				Ficha turno) 
		{
			final Ficha t=turno;
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() 
				{
					pilaNoVacia(t);
				}
			});
		}

		@Override
		public void onMovimientoIncorrecto(
				MovimientoInvalido movimientoException) {
			// TODO Auto-generated method stub
			
		}
		/**
		 * 
		 * @return botón de deshacer
		 */
		public JButton getBotonDeshacer()
		{
			return botonDeshacer;
		}
	}
