package tp.pr5.logica;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

public class ReglasComplica implements ReglasJuego {

	private static final int FILAS=7;
	private static final int COLUMNAS=4;

	
	/**
	 * Constructor de la clase.
	 * Sin parámetros.
	 */
	public ReglasComplica()
	{
		
	}
	@Override
	public Tablero iniciaTablero() 
	{	
		return new Tablero(COLUMNAS, FILAS);
	}

	@Override
	public Ficha jugadorInicial() 
	{	
		return Ficha.BLANCA;
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) 
	{
		boolean gananBlancas=false;
		boolean gananNegras=false;
		int col=ultimoMovimiento.getColumn();
		int tope=Comprueba.filaLibre(col,t)+1;
		for(int i=tope;i<=t.getAlto();i++)
		{
			if((t.getCasilla(i, col).equals(Ficha.BLANCA))&&!gananBlancas)
			{
				if((Comprueba.horizontales(col, i,t))
						||(Comprueba.diagonalCreciente(col, i,t))
						||(Comprueba.diagonalDecreciente(col, i,t))
						||(Comprueba.verticales(col, i,t)))
				gananBlancas=true;
			}
			else if ((t.getCasilla(i, col).equals(Ficha.NEGRA))&&!gananNegras)
			{
				if((Comprueba.horizontales(col, i,t))
						||(Comprueba.diagonalCreciente(col, i,t))
						||(Comprueba.diagonalDecreciente(col, i,t))
						||(Comprueba.verticales(col, i,t)))
				gananNegras=true;
			}
		}
		if ((gananBlancas)&&(!gananNegras))
			return Ficha.BLANCA;
		else if((!gananBlancas)&&(gananNegras))
			return Ficha.NEGRA;
		else return Ficha.VACIA;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, TableroInmutable t) 
	{
		return false;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, TableroInmutable t) 
	{	
		return ultimoEnPoner.cambiaFicha();
	}
}
