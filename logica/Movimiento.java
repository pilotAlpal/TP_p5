package tp.pr5.logica;

/**
 * Clase que representa el movimiento de un jugador.
 * Tiene un m�todo para ejecutar el movimiento sobre la partida, y otro para deshacerlo.
 * Es una clase abstracta; habr� una clase no abstracta por cada tipo de juego soportado.
 * @author Lucas y
 *
 */
public abstract class Movimiento
{
	protected Ficha fichaMovimento;
	protected int columnaMovimiento;
	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como par�metro. 
	 * Se puede dar por cierto que tablero recibido sigue las reglas del tipo de juego al que pertenece el movimiento. 
	 * En caso contrario, el comportamiento es indeterminado.
	 * @param tab Tablero de donde deshacer el movimiento.
	 */
	public abstract void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido;
	/**
	 * Devuelve el color del jugador al que pertenece el movimiento. 
	 * (Puede hacerse abstracto)
	 * @return Color del jugador (coincide con el pasado al constructor).
	 */
	public Ficha getJugador(){return fichaMovimento;}
	/**
	 * Deshace el movimiento en el tablero recibido como par�metro. 
	 * Se puede dar por cierto que el movimiento se ejecut� sobre ese tablero; en caso contrario, el comportamiento es indeterminado. 
	 * Por lo tanto, es de suponer que el m�todo siempre funcionar� correctamente.
	 * @param tab Tablero de donde deshacer el movimiento.
 	 */
	public abstract void undo(Tablero tab);
	/**
	 * Devuelve la columna en la que el usuarió colocó la ficha.
	 * @return
	 */
	public int getColumn(){return columnaMovimiento;}

	/**
	 * Devuelve la fila en la que acaba siendo colocada una ficha en un tablero.
	 * @return -1 salvo en el modo gravity.
	 */
	public int getFilaFin()
	{
		return -1;
	}
	/**
	 * Devuelve la columna en la que acaba siendo colocada una ficha en un tablero.
	 * @return -1 salvo en el modo gravity.
	 */
	public int getColumnaFin()
	{
		return -1;
	}
	/**
	 * Lanza una excepción si la columna del movimiento no pertenece al rango de columnas de 
	 * un tablero.
	 * @param t tablero
	 * @throws MovimientoInvalido si la columna de mi movimiento no pertenece a [1..anchoTablero]
	 */
	protected void compruebaColumna(Tablero t) throws MovimientoInvalido
	{
		if ((columnaMovimiento<=0)||(columnaMovimiento>t.getAncho()))
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y "+t.getAncho()+".");
	}
	/**
	 * Comprueba que un punto perteneza al rango de filas y columnas de un tablero
	 * y que la casilla asignada a dicho punto no esté ocupada
	 * @param p punto en cuestión
	 * @param t tablero sobre el que se realiza la consulta
	 * @throws MovimientoInvalido si p no pertenece al rango o está ocupada la casilla-p
	 */
	protected void compruebaPunto(Punto p,Tablero t) throws MovimientoInvalido
	{
		int col=p.getColumna();
		int fil=p.getFila();
		int columnas=t.getAncho();
		int filas=t.getAncho();
		if ((col<=0)||(col>t.getAncho()))
			throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y "+columnas+".");
		if ((fil<=0)||(fil>t.getAlto()))
			throw new MovimientoInvalido("Fila incorrecta. Debe estar entre 1 y "+filas+".");
		if(!t.getCasilla(fil, col).equals(Ficha.VACIA))
			throw new MovimientoInvalido("Casilla Ocupada.");
	}
}
