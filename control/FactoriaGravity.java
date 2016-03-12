package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoGravity;
import tp.pr5.logica.ReglasGravity;
import tp.pr5.logica.ReglasJuego;

/**
 *  Implementaci�n de la factor�a para el juego del Gravity.
 *  Los m�todos devuelven los objetos capaces de jugar a ese juego. 
 *  Dado que el tama�o del tablero de Gravity no est� fijo, sino que se puede cambiar en cada partida, 
 *  la factor�a puede configurarse con el tama�o del tablero que se quiere utilizar.
 * @author Lucas y
 *
 */
public class FactoriaGravity implements FactoriaTipoJuego 
{
	private final static int NUM_COLS=10;
	private final static int NUM_FILS=10;
	private int filas,columnas;
	
	/**
	 * Constructor de la clase.
	 * @param c Número de columnas.
	 * @param f Número de filas.
	 */
	public FactoriaGravity(int c,int f)
	{
		filas=f;
		columnas=c;
	}
	/**
	 * Constructor de la clase.
	 * 10 X 10 Por defecto.
	 */
	public FactoriaGravity()
	{
		new FactoriaGravity(NUM_COLS,NUM_FILS);
	}	
	@Override
	public Jugador creaJugadorAleatorio() 
	{
		return new JugadorAleatorioGravity();
	}
	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) 
	{
		return new JugadorHumanoGravity(in);
	}
	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) 
	{
		return new MovimientoGravity(col, fila, color);
	}
	@Override
	public ReglasJuego creaReglas() 
	{	
		return new ReglasGravity(columnas, filas);
	}
	/**
	 * 
	 * @return el número de filas por defecto
	 */
	public static int getDefaultRows() 
	{
		// TODO Auto-generated method stub
		return NUM_FILS;
	}
	/**
	 * 
	 * @return el número de columnas por defecto
	 */
	public static int getDefaultCols() {
		// TODO Auto-generated method stub
		return NUM_COLS;
	}
}
