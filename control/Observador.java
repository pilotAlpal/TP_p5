package tp.pr5.control;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.MovimientoInvalido;
import tp.pr5.logica.TableroInmutable;

public interface Observador 
{
	// g e s t i ó n de p a r t i d a
	/**
	 * Método invocado por la clase Partida que permite notificar a sus observadores(las
vistas) que se ha reiniciado la partida.   

	 * @param tab Proporciona información del
estado inicial del tablero
	 * @param turno el turno (que será una ficha blanca o negra).
	 */
	void onReset(TableroInmutable tab, Ficha turno);
	/**
	 * La partida notifica a los observadores que ha terminado la partida llamando a
este método. Además proporciona al observador una vista.  
	 * @param tablero el tablero de sólo
lectura
	 * @param ganador el ganador
	 */
	void onPartidaTerminada(TableroInmutable tablero, Ficha ganador);
	/**
	 * La partida notifica a los observadores que se ha cambiado el juego. Se proporciona
el estado inicial del tablero y el turno
	 * @param tab
	 * @param turno
	 */
	void onCambioJuego(TableroInmutable tab, Ficha turno);
	// G e s t i ó n de m ovimi e n t o s
	/**
	 * La partida notifica a los observadores que se ha deshecho un movimiento. Además,
proporciona el estado final del tablero, el turno del siguiente jugador y si
hay más movimientos a deshacer o no.

	 * @param tablero
	 * @param turno
	 */
	void onUndoNotPossible(TableroInmutable tablero, Ficha turno);
	/**
	 * 
La partida notifica a los observadores que se ha deshecho un movimiento. Además,
proporciona el estado final del tablero, el turno del siguiente jugador y si
hay más movimientos a deshacer o no.

	 * @param tablero
	 * @param turno
	 * @param hayMas
	 */
	void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas);
	/**La partida notifica a los observadores que se ha terminado de realizar un movimiento.
Se proporciona además una vista del tablero de sólo lectura, el jugador
que ha jugado, y el turno del siguiente jugador
*/
	void onMovimientoEnd(TableroInmutable tablero,
	Ficha jugador, Ficha turno);
	
	/**
	 * La partida notifica que se ha producido un movimiento incorrecto proporcionando
el objeto MovimientoInvalido con una explicación del problema que se
ha producido.

	 * @param movimientoException
	 */
	void onMovimientoIncorrecto(MovimientoInvalido movimientoException);
	
}
