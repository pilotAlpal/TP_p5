package tp.pr5.logica;

public class Punto 
{
	private int fila,columna;
	/**
	 * Constructor de la clase
	 * @param fi fila
	 * @param co columna
	 */
	public Punto(int fi,int co)
	{
		fila=fi;
		columna=co;
	}
	/**
	 * 
	 * @return fila asignada
	 */
	public int getFila(){return fila;}
	/**
	 * 
	 * @return columna asignada
	 */
	public int getColumna(){return columna;}
}
