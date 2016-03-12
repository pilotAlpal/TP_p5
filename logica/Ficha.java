package tp.pr5.logica;

import java.awt.Color;


public enum Ficha {
	BLANCA,NEGRA,VACIA;
	
	/**
	 * @Override
	 */
	public String toString()
	{
		if (this.equals(NEGRA)) return "negras";
		else if(this.equals(BLANCA))return "blancas";
		else return "Vac�as";
	}
	/**
	 * devuelve una representacion de cada casilla para 
	 * dibujar el tablero por consola
	 * @return X si la ficha es negra,O si es blanca,espacio en otro caso.
	 */
	public String toTablero()
	{
		if (this.equals(NEGRA)) return "X";
		else if(this.equals(BLANCA))return "O";
		else return " ";
	}
	
	/**
	 * M�todo que devuelve una ficha del color contrario al que recibe por parametro
	 * @param f ficha de entrada
	 * @return Negra si f es blanca,
	 * blanca si es negra,vac�a si no.
	 */
	public Ficha cambiaFicha()
	{
		if(this.equals(BLANCA)) return NEGRA;
		else if (this.equals(NEGRA)) return BLANCA;
		else return VACIA;
	}
	/**
	 * 
	 * @return java.awt.Color asignado al valor del enumerado
	 */
	public Color getColor()
	{
		if(this.equals(BLANCA)) return Color.WHITE;
		else if (this.equals(NEGRA)) return Color.BLACK;
	//	else if (this.equals(PJ)) return Color.BLUE;
		else return Color.GRAY;
	}
}
