package tp.pr5.vista;

import tp.pr5.control.ControladorConsola;
import tp.pr5.control.Observador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;

public class VistaConsola implements Observador 
{
	private ControladorConsola myController;
	/**
	 * Constructor de la clase.
	 * @param consoController controlador consola
	 * @param tableroInicial 
	 * @param turnoInicial
	 */
	public VistaConsola(ControladorConsola consoController,TableroInmutable tableroInicial,
			Ficha turnoInicial) 
	{
		myController=consoController;
		muestraTableroYturno(tableroInicial, turnoInicial);
	}

	/**
	 * 
	 * @param tInmutable
	 * @return representación textual de tInmutable
	 */
	private String tableroInmutableToString(TableroInmutable tInmutable)
	{
		String devuelve="";
		for(int i=0;i<tInmutable.getFilas();i++)
		{
			devuelve+="|";
			for(int j=0;j<tInmutable.getColumnas();j++)
			{
				devuelve+=//fichas[j][i].toTablero();
						tInmutable.getCasilla(i+1, j+1).toTablero();
			}
			devuelve+="|";
			devuelve+=System.getProperty("line.separator");
		}
		devuelve+="+";
		for(int j=0;j<tInmutable.getColumnas();j++)
		{
			devuelve+="-";
		}
		devuelve+="+";
		devuelve+=System.getProperty("line.separator");
		devuelve+=" ";
		for(int j=0;j<tInmutable.getColumnas();j++)
		{
			devuelve+=(j+1);
		}			
		return devuelve;
	}
	/**
	 * Imprime una representación de un tabler
	 * @param tab
	 */
	private void pintaTablero(TableroInmutable tab)
	{
		System.out.println(tableroInmutableToString(tab));
	}
	/**
	 * Imprime el estado actual del tablero y el turno del siguiente jugador
	 * @param tab
	 * @param turno
	 */
	private void muestraTableroYturno(TableroInmutable tab,Ficha turno)
	{
		pintaTablero(tab);
		System.out.println("Juegan "+turno);
		System.out.print("Que quieres hacer?");
	}

	@Override
	public void onReset(TableroInmutable tab, Ficha turno)
	{
		System.out.println("Partida reiniciada.");
		muestraTableroYturno(tab, turno);
	}

	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) 
	{
		pintaTablero(tablero);
		if(ganador.equals(Ficha.VACIA))
			System.out.println("Partida terminada en tablas.");
		else System.out.println("Ganan las "+ganador+" enhorabuena.");
		myController.shutDown(0);
	}

	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno) 
	{
		System.out.print("Modo cambiado.");
		onReset(tab, turno);
	}

	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) 
	{
		System.err.println("Imposible deshacer.");
		muestraTableroYturno(tablero, turno);
	}

	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) 
	{
		muestraTableroYturno(tablero, turno);
	}

	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,Ficha turno) 
	{
		muestraTableroYturno(tablero, turno);
	}

	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) 
	{
		System.err.println(movimientoException.getMessage());
	}
	
}
