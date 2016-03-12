package tp.pr5.logica;

import java.util.LinkedList;

import tp.pr5.control.Jugador;
import tp.pr5.control.Observador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.OurStack;

/**
 * Clase para representar la información de una partida. 
 * Se encarga de almacenar el estado del tablero, 
 * a quién le toca, si ya hay un ganador, etc., así como la lista de movimientos que se han ido 
 * realizando para poder deshacerlos. 
 * La partida guarda al menos los 10 últimos movimientos.
 * @author Lucas y
 *
 */
public class Partida 
{
	private ReglasJuego misReglas;
	private boolean terminada;
	private Tablero tablero;
	private OurStack<Movimiento> pila;
	private Ficha ganador,turno;
	private  LinkedList<Observador> observerList;
	/**
	 * Construye una partida nueva.
	 * @param reglas Reglas del juego que se utilizarán durante la partida 
	 * (al menos hasta que se ejecute reset).
	 */
	public Partida(ReglasJuego reglas)
	{
		observerList=new LinkedList<Observador>();
		reset(reglas);
	}
	/**
	 * Reinicia la partida en curso. Esta operación no puede deshacerse. 
	 * Gracias al parámetro, permite cambiar el tipo de juego al que se juega.
	 * @param reglas  Las reglas del juego a utilizar a partir de ahora.
	 */
	public void reset(ReglasJuego reglas)
	{
		ganador=Ficha.VACIA;
		terminada=false;
		pila=new OurStack<Movimiento>();
		misReglas=reglas;
		tablero=misReglas.iniciaTablero();
		turno=misReglas.jugadorInicial();
		notificoReset();
	}
	/**
	 * Informa a la lista de observadores de que se ha producido un reset
	 */
	private void notificoReset() 
	{
			for(int i=0;i<observerList.size();i++)
			{
				observerList.get(i).onReset(tablero, turno);
			}
	}
	/**
	 * Ejecuta el movimiento indicado.
	 * @param mov  Movimiento a ejecutar. 
	 * Se asume que el movimiento es compatible con las reglas de la partida que se está jugando
	 *  (si se está jugando a Conecta 4, el tipo de movimiento es Conecta 4, etc.).
	 *  En caso contrario, el comportamiento es inderminado.
	 * @throws MovimientoInvalido si no se puede efectuar el movimiento.
	 *  Es un error intentar colocar una ficha del jugador que no tiene el turno, 
	 * cuando la partida está terminada, columna llena, etc.
	 */
	public void ejecutaMovimiento(Movimiento mov)   
    {
		if(terminada)
			fallo("LLamada a ejecutaMovimiento con la partida terminda");
		else if(!mov.getJugador().equals(turno))
			fallo("Los jugadores no coinciden");
		else 
			{
				try 
				{
					mov.ejecutaMovimiento(tablero);
					actualizaEstado(mov);
				} 
				catch (MovimientoInvalido e) 
				{
					notificoFallo(e);
				}
				//actualizaEstado(mov);
			}
				
    }
	/**
	 * Genera un Movimiento invalido y llama al método que lo notifica
	 * @param string texto del movimiento invalido
	 */
	private void fallo(String string) 
	{
		MovimientoInvalido error = new MovimientoInvalido(string);
		notificoFallo(error);
	}
	/**
	 * Notifica un nuevo movimiento invalido
	 * @param error
	 */
	private void notificoFallo(MovimientoInvalido error) 
	{
		for(int i=0;i<observerList.size();i++)
		{
			observerList.get(i).onMovimientoIncorrecto(error);
		}	
	}
	/**
	 * Deshace el último movimiento ejecutado.
	 * @return True si se pudo deshacer.
	 */
	public boolean undo()
	{
		if(pila.isVacia())
			return false;
		Movimiento aDeshacer=pila.top();
		aDeshacer.undo(tablero);
		turno=aDeshacer.getJugador();
		notifyUndo();
		return true;
	}
	/**
	 * Notifica a los observadores de que se ha deshecho el último movimiento
	 */
	private void notifyUndo() 
	{
		boolean apanio=pila.isVacia();
		for(int i=0;i<observerList.size();i++)
		{
			observerList.get(i).onUndo(tablero, turno, !apanio);
		}
	}
	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * @return Color del jugador, o Ficha.VACIA si la partida ha terminado.
	 */
	public Ficha getTurno()
	{
		return turno;
	}
	/**
	 * Devuelve el color del ganador. Sólo válido si la partida ya ha terminado 
	 * (isTerminada() == true).
	 * @return Color del ganador. Si la partida terminó en tablas, 
	 * Ficha.VACIA. Si la partida no ha terminado aín, el resultado es indeterminado.
	 */
	public Ficha getGanador()
	{
		return ganador;
	}
	/**
	 * Método para saber si la partida ha conluído ya o no.
	 * @return true si la partida ha acabado.
	 */
	public boolean isTerminada()
	{
		return terminada;
	}
	/*
	 * Método de acceso al tablero. 
	 * Dependiendo de cómo se haga la implementación del resto de clases 
	 * (principalmente de la clase Controlador), 
	 * es posible que no se utilice para nada en la práctica. 
	 * Sin embargo, es necesario implementarlo para poder ejecutar los test de unidad.
	 * metodo que tenemos que implementar y hacer publico pero como 
	 * lo usemos nos tirais la practica,para otra vez especificar que no se debe usar
	 * por favor!
	 * @return Estado del tablero actual.
	 public Tablero getTablero()
	{
		return tablero;
	}*/
	
	
	/**
	 *	Devuelve el movimiento a ejecutar por un determinado jugador 
	 * @param jugador
	 * @return
	 */
	public Movimiento ponga(Jugador jugador) 
	{
		return jugador.getMovimiento(tablero, turno);	
	}
	/**
     * Actualiza el valor de los atributos(terminada,ganador..)
     * suponiendo que se ha ejecutado un nuevo movimiento.
     * También apila el ultimo movimiento para poder deshacerlo en caso de que
     * el usuario lo solicite.
     * @param m -movimiento que suponemos,se ejecutó correctamente.
     */
    private void actualizaEstado(Movimiento m)
    {
    //	boolean tablas;boolean ganada;
    	Ficha viejoTurno=turno;//??
    	turno=misReglas.siguienteTurno(m.getJugador(), tablero);
    	ganador=misReglas.hayGanador(m, tablero);
    /*	ganada=(!ganador.equals(Ficha.VACIA));
    	tablas=(misReglas.tablas(viejoTurno, tablero));*/
    	terminada=((!ganador.equals(Ficha.VACIA))
    			||(misReglas.tablas(viejoTurno, tablero)));
    	pila.apila(m);
    	if (terminada)
    		notificoPartidaTerminada();
    	else
    		notificoMovimiento(viejoTurno);
    }
    /**
     * Notifica a los observadores que la partida a terminado
     */
	private void notificoPartidaTerminada() 
	{
		for(int i=0;i<observerList.size();i++)
		{
			observerList.get(i).onPartidaTerminada(tablero,ganador);
		}
	}
	/**
	 * Notifica a los observadores que se ha ejecutado(con éxito) un nuevo movimiento
	 * @param viejo turno del jugador que ejecutó el movimiento
	 */
	private void notificoMovimiento(Ficha viejo) 
	{
		for(int i=0;i<observerList.size();i++)
		{
			observerList.get(i).onMovimientoEnd(tablero, viejo, turno);
		}
	}
	/**
	 * aniade un observador 
	 * @param o
	 */
	public void addObserver(Observador o) 
	{
		observerList.add(o);
	}
}
