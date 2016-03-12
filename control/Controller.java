package tp.pr5.control;

import tp.pr5.logica.Partida;

public abstract class Controller 
{
	protected Partida miModelo;
	protected FactoriaTipoJuego miFactoria;
	/**
	 * Anade un observador a la partida
	 * @param o nuevo observador
	 */
	public void addPartidaObserver(Observador o)
	{
		miModelo.addObserver(o);
	}
	/**
	 * Permite apagar la aplicacion
	 * @param n de salida
	 	 */
	public void shutDown(int n){System.exit(n);}
	/**
	 * Permite reiniciar la partida
	 */
	public void reset(){miModelo.reset(miFactoria.creaReglas());}
	/**
	 * Permite deshacer el ultimo movimiento
	 */
	public void undo()
	{
		miModelo.undo();
	}
}
