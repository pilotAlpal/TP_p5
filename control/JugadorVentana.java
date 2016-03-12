package tp.pr5.control;

import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Partida;
import tp.pr5.logica.TipoJugador;

public abstract class JugadorVentana 
{
	protected TipoJugador t;
	/*protected ControladorGui myController;
	protected Ficha color;
	*/
	/**
	 * Permite cambiar el tipo de jugador
	 * @param ti nuevo tipo de jugador
	 */
	public void cambiaTipoJugador(TipoJugador ti){t=ti;}
	/**
	 * Informa de si un jugador es humano o no
	 * @return true si es humano,false si es automático
	 */
	public abstract boolean isHuman();
	/**
	 * Empieza el juego aleatorio para un jugador
	 * @param f factoría del tipo al que se juega
	 * @param p partida sobre la que se empieza
	 */
	public abstract void comienza(FactoriaTipoJuego f,Partida p);
	/**
	 * permite detener el juego aleatorio de un jugador
	 */
	public abstract void para();
	/**
	 * permite ejecutar un movimiento concreto
	 * @param m movimiento a ejecutar
	 * @param p partida sobre la que se ejecuta
	 */
	public abstract void poneFicha(Movimiento m,Partida p);
}
