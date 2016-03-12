package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoConecta4;
import tp.pr5.logica.ReglasConecta4;
import tp.pr5.logica.ReglasJuego;
/**
 * Implementación de la factoría para el juego del Conecta 4. 
 * Los métodos devuelven los objetos capaces de jugar a ese juego.
 * @author Lucas y
 *
 */
public class FactoriaConecta4 implements FactoriaTipoJuego 
{
	@Override
	public Jugador creaJugadorAleatorio() 
	{
		return new JugadorAleatorioConecta4();
	}
	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) 
	{
		return new JugadorHumanoConecta4(in);
	}
	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) 
	{
		return new MovimientoConecta4(col, color);
	}
	@Override
	public ReglasJuego creaReglas() 
	{
		return new ReglasConecta4();
	}
}