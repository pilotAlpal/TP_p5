package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoComplica;
import tp.pr5.logica.ReglasComplica;
import tp.pr5.logica.ReglasJuego;

/**
 * Implementación de la factoría para el juego del Complica. 
 * Los métodos devuelven los objetos capaces de jugar a ese juego.
 * @author Lucas y
 *
 */
public class FactoriaComplica implements FactoriaTipoJuego
{	
	@Override
	public ReglasJuego creaReglas()
	{
		return new ReglasComplica();
	}

	@Override
	public Jugador creaJugadorAleatorio() 
	{
		return new JugadorAleatorioComplica();
	}

	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) 
	{	
		return new JugadorHumanoComplica(in);
	}

	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) 
	{	
		return new MovimientoComplica(col, color) ;
	}	
}