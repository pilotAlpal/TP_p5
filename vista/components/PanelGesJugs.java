package tp.pr5.vista.components;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tp.pr5.control.Observador;
import tp.pr5.control.ControladorGui;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJugador;

public class PanelGesJugs extends JPanel implements Observador
{
	private static final long serialVersionUID = 1L;
	private ControladorGui myController;
	private final JComboBox<TipoJugador> comboBlanco=new JComboBox<TipoJugador>(TipoJugador.values());
	private final JComboBox<TipoJugador> comboNegro=new JComboBox<TipoJugador>(TipoJugador.values());
	private JButton bDeshacer,bAleatorio;
	/**
	 * Constructor de la clase
	 * @param wC controlador swing
	 * @param botonDeshacer de otro panel
	 * @param botonAleatorio de otro panel
	 */
	public PanelGesJugs(ControladorGui wC, JButton botonDeshacer, JButton botonAleatorio )
	{
		bDeshacer=botonDeshacer;
		bAleatorio=botonAleatorio;
		myController=wC;
		this.setBorder(BorderFactory.createTitledBorder("Gestion de Jugadores"));
		JLabel tituloBlancas=new JLabel("Jugador Blancas");
		JLabel tituloNegras=new JLabel("Jugador Negras");
		
		comboBlanco.setSelectedItem(TipoJugador.Humano);
		comboNegro.setSelectedItem(TipoJugador.Humano);
		comboBlanco.addItemListener(new ItemListener() 
		{
			@Override
			public void itemStateChanged(ItemEvent e) 
			{
				if(e.getSource()==comboBlanco && e.getStateChange() == 1)
				{
					TipoJugador tJ=(TipoJugador) comboBlanco.getSelectedItem();
					if (!tJ.esRobot())//combo blanco ha cambiado a humano
					{
						myController.para(Ficha.BLANCA);
						myController.setHumanPlayer(Ficha.BLANCA);
					}
					else //combo blanco ha cambiado a aleatorio
					{
						myController.setAutoPlayer(Ficha.BLANCA);
						myController.empiezaAleatorio(Ficha.BLANCA);
						inhabilitaOtrosPaneles(Ficha.BLANCA);
					}
				}
			}
		});
		comboNegro.addItemListener(new ItemListener() 
		{	
			@Override
			public void itemStateChanged(ItemEvent e) 
			{
				if(e.getSource()==comboNegro && e.getStateChange() == 1)
				{
					TipoJugador tJ=(TipoJugador) comboNegro.getSelectedItem();
					if (!tJ.esRobot()) //combo negro ha cambiado a humano
					{
						myController.para(Ficha.NEGRA);
						myController.setHumanPlayer(Ficha.NEGRA);
					}
					else //combo negro ha cambiado a automatico
					{
						myController.setAutoPlayer(Ficha.NEGRA);
						myController.empiezaAleatorio(Ficha.NEGRA);
						inhabilitaOtrosPaneles(Ficha.NEGRA);
					}
				}
			}

			
		});
	this.setLayout(new GridLayout(2, 2));
	this.add(tituloNegras);
	this.add(tituloBlancas);
	this.add(comboNegro);
	this.add(comboBlanco);
	wC.addPartidaObserver(this);
	}

	
	@Override
	public void onReset(TableroInmutable tablero, Ficha tur) 
	{
		comboBlanco.setSelectedItem(TipoJugador.Humano);
		comboNegro.setSelectedItem(TipoJugador.Humano);
		myController.resetPlayers();
	}

	/**
	 * Intenta llamar al jugador aleatorio del jugador al que le toca
	 * @param turno
	 */
	private void afterNewMovement(Ficha turno) 
	{
		myController.empiezaAleatorio(turno);
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) 
	{
		
		
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno) {
		
		
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
				
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
				
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno) 
	{
		final Ficha juega=turno;
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				afterNewMovement(juega);
			}
		});
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Hace que el controlador inhabilite los botones de deshacer y salir (si procede)
	 * @param cambia
	 */
	private void inhabilitaOtrosPaneles(Ficha cambia)
	{
		myController.inhabilitaBotones(cambia,bDeshacer,bAleatorio);
	}
}