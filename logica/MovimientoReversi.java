package tp.pr5.logica;

import java.util.ArrayList;

public class MovimientoReversi extends Movimiento 
{
	private Tablero tableroAntiguo;
	private int filaReversi;
	public MovimientoReversi(int c, int f, Ficha turno) 
	{
		tableroAntiguo=new ReglasReversi().iniciaTablero();
		fichaMovimento=turno;
		columnaMovimiento=c;
		filaReversi=f;
	}

	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido 
	{
		copiaTablero(tab, tableroAntiguo);
		Punto elegidoPorUser =new Punto(filaReversi, columnaMovimiento);
		//comprobar que la fila y la columna que eligio el usuario pertenecen al tablero
		compruebaPunto(elegidoPorUser, tab);
		//comprobar que ese jugador puede ejecutar en ese turno
		compruebaCome(elegidoPorUser,tab,fichaMovimento);
		//lamar a setCasilla con la fila y la columna en la que acaba
	}
	/**
	 * Comprueba que un determinado punto flanquea alguna ficha del rival
	 * si es así come esas casillas
	 * @param elegidoPorUser punto en cuestión
	 * @param tab tablero sobre el que se realiza la consulta
	 * @param fichaMovimento turno
	 * @throws MovimientoInvalido si no flanquea fichas del rival
	 */
	private void compruebaCome(Punto elegidoPorUser, Tablero tab,
			Ficha fichaMovimento) throws MovimientoInvalido 
	{
			ArrayList<Punto> puntosFlanqueo 
					=ReglasReversi.aQuienesFlanqueo(elegidoPorUser, fichaMovimento, tab);
			if(puntosFlanqueo.isEmpty())
				throw new MovimientoInvalido("Debes flanquear las fichas del rival.");
			else
			{
				puntosFlanqueo.add(elegidoPorUser);
				flanquea(puntosFlanqueo,tab,fichaMovimento);
			}
	}
	/**
	 * Método encargado de comer una serie de fichas del rival
	 * @param puntosFlanqueo collecion de casillas del rival que flanqueo
	 * @param tab tablero sobre el que se realiza la operación
	 * @param color turno
	 */
	private void flanquea(ArrayList<Punto> puntosFlanqueo, Tablero tab,Ficha color) 
	{
		Punto p;int c,f;
		for(int i=0;i<puntosFlanqueo.size();i++)
		{
			p=puntosFlanqueo.get(i);
			f=p.getFila();
			c=p.getColumna();
			tab.setCasilla(c, f, color);
		}
		
	}

	@Override
	public void undo(Tablero tab) 
	{
		copiaTablero(tableroAntiguo, tab);
	}
	/**
	 * Copia un tablero en otro
	 * @param origen
	 * @param destino
	 */
	private void copiaTablero(Tablero origen,Tablero destino)
	{	
		for(int c=1; c <= origen.getAncho(); c++)
			for(int f=1; f <= destino.getAlto(); f++)
				destino.setCasilla(c, f, origen.getCasilla(c, f));		
	}

}
