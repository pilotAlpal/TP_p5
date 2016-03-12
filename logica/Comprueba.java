package tp.pr5.logica;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

public class Comprueba 
{
	/**
	 * Método que comprueba una victoria diagonal creciente desde 
	 * la ficha situada en t(col,fila)
	 * @param col  Columna.
	 * @param fila  Fila.
	 * @param t  Tablero.
	 * @return True si la ha habido,false si no.
	 */
	static boolean diagonalCreciente(int col,int fila,Tablero t)
	{
		int arriDch=3;
		int abaIzq=-3;
		while( ((fila-abaIzq)>t.getAlto())||((col+abaIzq)<1) )
			abaIzq++;
		while( ((fila-arriDch)<1) || ((col+arriDch)>t.getAncho()) )
			arriDch--;
		int i=-1;int j=1;int suma=1; 
		while((i>=abaIzq) && t.getCasilla(fila,col).equals(t.getCasilla(fila-i,col+i)))
		{
			i--;
			suma++;
		}
		while((j<=arriDch) && t.getCasilla(fila,col).equals(t.getCasilla(fila-j,col+j)))
		{
			j++;
			suma++;
		}
		if (suma>=4) return true;
		return false;
	}
	/**
	 * Método que comprueba una victoria diagonal decreciente desde 
	 * la ficha situada en t(col,fila)
	 * @param col - Columna.
	 * @param fila - Fila.
	 * @param t - Tablero.
	 * @return True si la ha habido,false si no.
	 */
	static boolean diagonalDecreciente(int col,int fila,Tablero t)
	{
		int arriIzq=-3;
		int abaDch=3;
		while( ((fila+arriIzq)<1)||((col+arriIzq)<1) )
			arriIzq++;
		while( ((fila+abaDch)>t.getAlto()) || ((col+abaDch)>t.getAncho()) )
			abaDch--;
		int i=-1;int j=1;int suma=1; 
		while((i>=arriIzq) && t.getCasilla(fila,col).equals(t.getCasilla(fila+i,col+i)))
		{
			i--;
			suma++;
		}
		while((j<=abaDch) && t.getCasilla(fila,col).equals(t.getCasilla(fila+j,col+j)))
		{
			j++;
			suma++;
		}
		if (suma>=4) return true;
		return false;
	}
	
	/**
	 * Método que comprueba una victoria vertical de la ficha situada en t(column,fila)
	 * @param col - Columna.
	 * @param fila - Fila.
	 * @param t - Tablero.
	 * @return True si la ha habido,false si no.
	 */
	static	boolean verticales(int column, int fila,Tablero t) 
	{
		int minF=fila-3;
		int maxF=fila+3;
		if(minF<1)minF=1;
		if(maxF>t.getAlto()) maxF=t.getAlto();
		int acum=0;
		Ficha ficha=t.getCasilla(fila, column);
		for(int i=minF;i<=maxF;i++)
		{
			if(t.getCasilla(i,column).equals(ficha))
				acum++;
			else acum=0;
			if (acum>=4)
				return true;
			
		}
		
		return false;
	}
	
	
	 /**
	  *Método que comprueba si se ha producido una victoria horizontal 
	  * @param col - Columna.
	 * @param fila - Fila.
	 * @param t - Tablero.
	 * @return True si la ha habido,false si no.
	 */
		
	static boolean horizontales(int col, int fila,Tablero t) 
	{
		int minC=col-3;
		int maxC=col+3;
		if (minC<1) minC=1;
		if(maxC>t.getAncho()) maxC=t.getAncho();
		int acum=0;
		Ficha ficha=t.getCasilla(fila,col);
		for(int i=minC;i<=maxC;i++)
		{
			if(t.getCasilla(fila,i).equals(ficha))
				acum++;
			else acum=0;
			if (acum>=4)
				return true;
		}
		return false;
	}
	/**
	 * Método que devuelve la posicion de la fila(1..alto) libre más baja 
	 * en la posicion columna (1..ancho).
	 * 
	 * @param columna-columna que se quiere comprobar. 
	 * @return la posición en la que hay que colocar la siguiente ficha
	 * ,0 si columna está llena.
	 */
	public static int filaLibre(int columna,TableroInmutable t)
	{
		int fila=t.getFilas();
		Ficha ficha=t.getCasilla(fila, columna);
		while((fila>0)&&!ficha.equals(Ficha.VACIA))
		{
			fila--;
			ficha=t.getCasilla( fila,columna);
		}
		return fila;
	}
}
