package tp.pr5.vista.components;

import java.awt.GridLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import tp.pr5.control.ControladorGui;
import tp.pr5.control.Observador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.vista.components.BotonTablero;
/**
 * Panel que tiene una parrila con las casillas que representan el tablero
 * @author Lucas y
 *
 */
public class PanelCeldas extends JPanel implements Observador
{
	private int filas;
	private int columnas;
	private BotonTablero matriz[][];
	private ControladorGui myController;	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	
	/**
	 * Constructor que evitaria tener que llamar a reset en el main..
	 * pero tendria que conocer al crear el nuevo panel celdas,el tablero
	 * o su ancho y su largo para crearme uno vacio
	 * @param wC
	 * @param t
	 */
	public PanelCeldas(ControladorGui wC,TableroInmutable t) 
	{
		myController=wC;
		myController.addPartidaObserver(this);
		repaintTablero(t);
	}

	

	/**
	 * Pinta en el panel el nuevo tablero
	 * @param tab nuevo tablero
	 */
	private void repaintTablero(TableroInmutable tab)
	{
		this.removeAll();
		filas=tab.getFilas();
		columnas=tab.getColumnas();
		matriz=new BotonTablero[columnas][filas];
		//columnas 7 filas 6
		this.setLayout(new GridLayout(filas,columnas));
		for(int f=0;f<filas;f++)
			for(int c=0;c<columnas;c++)
			{
				Ficha color=tab.getCasilla(f+1, c+1);
				matriz[c][f]=new BotonTablero(c, f,color,myController);
				this.add(matriz[c][f],-1);
			}
		this.revalidate();
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno)
	{
		repaintTablero(tab);
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) 
	{
		final TableroInmutable t=tablero;
		SwingUtilities.invokeLater(new Runnable() 
		{
			
			@Override
			public void run() 
			{
				repaintTablero(t);
			}
		});
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno) 
	{
		repaintTablero(tab);
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) 
	{
		
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) 
	{
		repaintTablero(tablero);
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno) 
	{
		final TableroInmutable t=tablero;
		SwingUtilities.invokeLater(new Runnable() 
		{
			
			@Override
			public void run() 
			{
				repaintTablero(t);
				
			}
		});
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) 
	{
		JOptionPane.showMessageDialog(this, movimientoException.getMessage(), "Disculpe"
				, JOptionPane.ERROR_MESSAGE);
	}

		
}
