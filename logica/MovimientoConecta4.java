package tp.pr5.logica;

/**
 * Clase que implementa el movimiento para el juego del Conecta 4.
 *  Se deben implementar los métodos abstractos de la clase padre.
 * @author Lucas y
 *
 */
public class MovimientoConecta4 extends Movimiento 
{
	@Override protected void compruebaColumna(Tablero tab) throws MovimientoInvalido
	{
		super.compruebaColumna(tab);
		int fila=Comprueba.filaLibre(columnaMovimiento,tab);
		if(!tab.isValidCell(columnaMovimiento, fila))
			throw new MovimientoInvalido();
	}
	@Override	
	public	void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido 
	{
		compruebaColumna(tab);
		int fila=Comprueba.filaLibre(columnaMovimiento,tab);
		tab.setCasilla(columnaMovimiento, fila, fichaMovimento);
		
	}
	@Override
	public	void undo(Tablero tab) 
	{	
		int fila=Comprueba.filaLibre(columnaMovimiento,tab);
			tab.setCasilla(columnaMovimiento, fila+1, Ficha.VACIA);		
	}
	/**
	 * Constructor del objeto.
	 * @param donde Columna en la que se colocar� la ficha
	 * @param color Color de la ficha que se pondr� (o jugador que pone).
	 */
	public MovimientoConecta4(int donde,Ficha color)
	{
		columnaMovimiento=donde;
		fichaMovimento=color;
	}
}
