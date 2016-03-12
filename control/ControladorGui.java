package tp.pr5.control;

import javax.swing.JButton;

import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.logica.TipoJuego;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Partida;
import tp.pr5.logica.ReglasJuego;

public class ControladorGui extends Controller 
{

	private JugadorVentana jugadorBlanco,jugadorNegro;
	/**
	 * Constructor de la clase
	 * @param modelo partida inicial
	 * @param fTj tipo de juego inicial
	 */
	public ControladorGui(Partida modelo, FactoriaTipoJuego fTj) 
	{
		miFactoria=fTj;
		miModelo=modelo;
		initPlayers();
	}
	/**
	 * Genera un movimiento aleatorio del jugador al que le toca jugar
	 * Le llama el listener del botonAleatorio
	 */
	public void generaMovimientoAleatorio() 
	{
		Ficha turno = miModelo.getTurno();
		JugadorVentana player=dameJugador(turno);
		Jugador aleatorio = miFactoria.creaJugadorAleatorio();
		Movimiento movimientoAleatorio=miModelo.ponga(aleatorio);
		player.poneFicha(movimientoAleatorio,miModelo);
	}
	/**
	 * Genera y ejecuta un movimiento en una determinada casilla
	 * @param columna de la casilla
	 * @param fila de la casilla
	 */
	public void generaMovimientoJugador(int columna, int fila) 
	{
		Ficha turno = miModelo.getTurno();
		JugadorVentana player=dameJugador(turno);
		Movimiento movimientoConcreto =miFactoria.creaMovimiento(columna, fila, miModelo.getTurno());
		player.poneFicha(movimientoConcreto, miModelo);
	}
	/**
	 * permite cambiar el juego
	 * @param tipoResizable nuevo  juego(redimensionable)
	 * @param f numero de filas
	 * @param c numero de columnas
	 */
	public void cambiaTipoJuego(TipoJuego tipoResizable,int f,int c) 
	{
		stopAutoPlayers();
		FactoriaTipoJuego factoriaNueva =tipoResizable.dameFactoria(f,c);
		miFactoria=factoriaNueva;
		ReglasJuego reglasIni =factoriaNueva.creaReglas();
		miModelo.reset(reglasIni);
	}
	/**
	 * permite cambiar el juego
	 * @param tipoNueva nuevo  juego
	 */
	public void cambiaTipoJuego(TipoJuego tipoNueva) 
	{
		stopAutoPlayers();
		FactoriaTipoJuego factoriaNueva =tipoNueva.dameFactoria();
		miFactoria=factoriaNueva;
		ReglasJuego reglasIni =factoriaNueva.creaReglas();
		miModelo.reset(reglasIni);
	}

	/**
	 * Ejecuta un nuevo movimiento aleatorio(en un nuevo hilo)
	 * @param color del movimiento
	 */
	public void empiezaAleatorio(Ficha color) 
	{
		if(color.equals(miModelo.getTurno()))//evita que el otro jugador fuerce un mov aleatorio!
		{
			JugadorVentana implicado=dameJugador(color);
			implicado.comienza(miFactoria,miModelo);
		}
		
	}
	/**
	 * Interrumpe el posible jugador auto y asigna un nuevo jugador humano para un
	 * color determinado
	 * @param color del jugador que se para
	 */
	public void para(Ficha color) 
	{
		JugadorVentana implicado=dameJugador(color);
		implicado.para();
		implicado=new JugadorVentanaHumano();
	}
	/**
	 * Devuelve el jugador asignado a un determinado color
	 * @param color del que se quiere conocer el jugador
	 * @return
	 */
	public JugadorVentana dameJugador(Ficha color)
	{
		if(color.equals(Ficha.BLANCA))
			return jugadorBlanco;
		return jugadorNegro;
	}
	/**
	 * Inicializa ambos jugadores(a humano)
	 */
	private void initPlayers()
	{
		jugadorBlanco=new JugadorVentanaHumano();
		jugadorNegro=new JugadorVentanaHumano();
	}
	/**
	 * Para y resetea ambos jugadores
	 */
	public void resetPlayers() 
	{
		stopAutoPlayers();
		initPlayers();
		
	}

	/**
	 * Establece un nuevo jugador automatico para el jugador correspondiente
	 * @param color del jugador que se asigna a auto
	 */
	public void setAutoPlayer(Ficha color) 
	{
		if(color.equals(Ficha.BLANCA))
			jugadorBlanco=new JugadorVentanaAutomatico();
		else jugadorNegro=new JugadorVentanaAutomatico();
	}
	/**
	 * Establece un nuevo jugador humano para el jugador que se correspondiente con 
	 * un color determinado
	 * @param color del jugador que se asigna a humano
	 */
	public void setHumanPlayer(Ficha color) 
	{
		if(color.equals(Ficha.BLANCA))
			jugadorBlanco=new JugadorVentanaHumano();
		else jugadorNegro=new JugadorVentanaHumano();
	}
	
	@Override
	public void shutDown(int n)
	{
		//preguntar con que numeros se sale con exito
		stopAutoPlayers();
		super.shutDown(n);
	}
	/**
	 * Para ambos jugadores automaticos
	 */
	private void stopAutoPlayers()
	{
		para(Ficha.BLANCA);
		para(Ficha.NEGRA);
		
	}
	/**
	 * Si el turno que recibo coincide con el de la partida deshabilito los botones
	 * @param cambia
	 * @param bDeshacer
	 * @param bPonerAleatorio
	 */
	public void inhabilitaBotones(Ficha cambia, JButton bDeshacer,
			JButton bPonerAleatorio) 
	{
		if (cambia.equals(miModelo.getTurno()))
		{
			bDeshacer.setEnabled(false);
			bPonerAleatorio.setEnabled(false);
		}
	}
	@Override 
	public void undo()
	{
		super.undo();
		empiezaAleatorio(miModelo.getTurno());
	}
}
