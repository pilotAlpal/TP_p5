package tp.pr5.logica;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

public class ReglasConecta4 implements ReglasJuego {

	private static final int NUM_FILAS=6;
	private static final int NUM_COLUMNAS=7;
	/**
	 * Constructor de la clase.
	 * Sin parametros.
	 */
	public ReglasConecta4()
	{
		
	}
	@Override
	public Tablero iniciaTablero() 
	{
		return new Tablero(NUM_COLUMNAS, NUM_FILAS);
	}

	@Override
	public Ficha jugadorInicial() 
	{	
		return Ficha.BLANCA;
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t)
	{
		Ficha presuntoGana=ultimoMovimiento.getJugador();
		if (tablas(presuntoGana,t)) 
			return Ficha.VACIA;
		int column=ultimoMovimiento.getColumn();
		int fila=Comprueba.filaLibre(column,t)+1;
		if (Comprueba.horizontales(column,fila,t))
			return presuntoGana;
		if(Comprueba.verticales(column,fila,t))
			return presuntoGana;
		if (Comprueba.diagonalCreciente(column,fila,t))
			return presuntoGana;
		if (Comprueba.diagonalDecreciente(column, fila,t))
			return presuntoGana;
		return Ficha.VACIA;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, TableroInmutable t) 
	{
		int i=1;
		while(i<=t.getColumnas())
		{
			if(t.getCasilla(1, i).equals(Ficha.VACIA))
				return false;
			i++;
		}
		return true;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, TableroInmutable t) 
	{
		return ultimoEnPoner.cambiaFicha();
	}
}