package tp.pr5.logica;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

/**
 * Clase que implementa el movimiento para el juego del Complica.
 *  Se deben implementar los métodos abstractos de la clase padre.
 * @author Lucas y
 *
 */
public class MovimientoComplica extends Movimiento 
{
	private Ficha leBorre;

	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido 
	{
		compruebaColumna(tab);
		if(tab.isValidColumn(columnaMovimiento))	
		{
			int fila=Comprueba.filaLibre(columnaMovimiento,tab);
			if(fila==0)
			{
				desplazaAbajo(tab);
				fila++;
			}
			tab.setCasilla(columnaMovimiento, fila, fichaMovimento);
		}	
	}
	/**
	 * Método llamado cuando coloco una ficha en una columna llena.
	 * Desplaza hacia abajo las fichas de mi fila y guardo la ficha olvidada.
	 * @param tab -Tablero sobre el que se realiza la acción.
	 */
	private void desplazaAbajo(Tablero tab)
	{
		leBorre=tab.getCasilla(tab.getAlto(),columnaMovimiento);
		for(int i=tab.getAlto();i>1;i--)
			tab.setCasilla(columnaMovimiento, i, tab.getCasilla(i-1, columnaMovimiento));	
	}
	/**
	 * Método usado para deshacer un movimiento cuando hay fichas olvidadas 
	 * en su columna.
	 * Desplaza todas las fichas de mi columna hacia arriba y coloca en la ultima
	 * fila la ultima ficha olvidada en esa columna.
	 * @param tab -tablero sobre el que se ejecuta la operación.
	 * @param f -ultima ficha "olvidada" en mi columna.
	 * 			(se añade al final de la columna).
	 */
	private void desplazaArriba(Tablero tab,Ficha f)
	{
		for(int i=1;i<tab.getAlto();i++)
			tab.setCasilla(columnaMovimiento, i, tab.getCasilla(i+1,columnaMovimiento));
		tab.setCasilla(columnaMovimiento, tab.getAlto(), f);
	}
	@Override
	public void undo(Tablero tab) 
	{
		int fila=Comprueba.filaLibre(columnaMovimiento,tab);
		if ((tab.isValidRow(fila))&&(leBorre.equals(Ficha.VACIA))) 
			tab.setCasilla(columnaMovimiento, fila+1, Ficha.VACIA);		
		else if (fila==0) 
		{
			if(leBorre.equals(Ficha.VACIA))
			{
				tab.setCasilla(columnaMovimiento, fila+1, Ficha.VACIA);
			}
			else
			{
				desplazaArriba(tab,leBorre);
				tab.setCasilla(columnaMovimiento, tab.getAlto(), leBorre);
			}
		}	
	}
	/**
	 * Constructor del objeto.
	 * @param donde Columna en la que se colocará la ficha
	 * @param color Color de la ficha que se pondrá (o jugador que pone).
	 */
	public MovimientoComplica(int donde,Ficha color)
	{
		columnaMovimiento=donde;
		fichaMovimento=color;
		leBorre=Ficha.VACIA;
	}
}
