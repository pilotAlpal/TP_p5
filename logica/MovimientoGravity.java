package tp.pr5.logica;

/**
 * Clase que implementa el movimiento para el juego del Gravity. 
 * Se deben implementar los m�todos abstractos de la clase padre.
 * @author Lucas y
 *
 */
public class MovimientoGravity extends Movimiento 
{
	private int filaGr;
	private int filaFin;
	private int colFin;
	/**
	 * 
	 * @param columna Columna en la que se colocar� la ficha.
	 * @param fila Fila en la que se colocar� la ficha.
	 * @param color Color de la ficha que se pondr� (o jugador que pone).
	 */
	public MovimientoGravity(int columna,int fila,Ficha color)
	{
		fichaMovimento=color;
		filaGr=fila;
		columnaMovimiento=columna;
		filaFin=-1;
		colFin=-1;
	}
	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido 
	{
		compruebaFila(tab);
		compruebaCasilla(tab);
		int arriba=filaGr-1;
		int abajo=tab.getAlto()-filaGr;
		int dcha=tab.getAncho()-columnaMovimiento;
		int izq=columnaMovimiento-1;	
		if((abajo==arriba)&&(dcha==izq))
		{
			tab.setCasilla(columnaMovimiento, filaGr, fichaMovimento);
			setFinalPos(columnaMovimiento, filaGr);
			
		}
		else if(abajo==arriba)
		{
			//Que pasa si dcha e izquierda son mayores que arriba y abajo?
			//			-se anulan
			if(dcha<izq)
			{
				int col=ReglasGravity.shiftColGrDch(columnaMovimiento, filaGr, tab, fichaMovimento);
				int fil=ReglasGravity.shiftFilGrDch(columnaMovimiento, filaGr, tab, fichaMovimento);
				tab.setCasilla(columnaMovimiento+col, filaGr+fil, fichaMovimento);
				setFinalPos(columnaMovimiento+col, filaGr+fil);
			}
			else//dcha>izq
			{
				int fil=ReglasGravity.shiftFilGrIzq(columnaMovimiento, filaGr, tab, fichaMovimento);
				int col=ReglasGravity.shiftColGrIzq(columnaMovimiento, filaGr, tab, fichaMovimento);
				tab.setCasilla(columnaMovimiento+col, filaGr+fil, fichaMovimento);
				setFinalPos(columnaMovimiento+col, filaGr+fil);
			}
		}
		else if(dcha==izq)
		{
			if (arriba<abajo)
			{
				int fil=ReglasGravity.shiftFilGrArr(columnaMovimiento, filaGr, tab, fichaMovimento);
				int col=ReglasGravity.shiftColGrArr(columnaMovimiento, filaGr, tab, fichaMovimento);
				tab.setCasilla(columnaMovimiento+col, filaGr+fil, fichaMovimento);
				setFinalPos(columnaMovimiento+col, filaGr+fil);
			}
			else//abajo<arriba
			{
				int fil=ReglasGravity.shiftFilGrAbj(columnaMovimiento, filaGr, tab, fichaMovimento);
				int col=ReglasGravity.shiftColGrAbj(columnaMovimiento, filaGr, tab, fichaMovimento);
				tab.setCasilla(columnaMovimiento+col, filaGr+fil, fichaMovimento);
				setFinalPos(columnaMovimiento+col, filaGr+fil);
			}
		}
		else if (abajo<arriba)
		{
			if(dcha<izq)
			{
				if(abajo<dcha)
					//Comprueba.despGrAbajo(columnaMovimiento, filaGr, tab,fichaMovimento);
				{
					int fil=ReglasGravity.shiftFilGrAbj(columnaMovimiento, filaGr, tab, fichaMovimento);
					int col=ReglasGravity.shiftColGrAbj(columnaMovimiento, filaGr, tab, fichaMovimento);
					tab.setCasilla(columnaMovimiento+col, filaGr+fil, fichaMovimento);
					setFinalPos(columnaMovimiento+col, filaGr+fil);
				}
				else if(abajo>dcha)
				//Comprueba.despGrDch(columnaMovimiento, filaGr, tab,fichaMovimento);
				{
					int col=ReglasGravity.shiftColGrDch(columnaMovimiento, filaGr, tab, fichaMovimento);
					int fil=ReglasGravity.shiftFilGrDch(columnaMovimiento, filaGr, tab, fichaMovimento);
					tab.setCasilla(columnaMovimiento+col, filaGr+fil, fichaMovimento);
					setFinalPos(columnaMovimiento+col, filaGr+fil);
				}
				
				else//abajo==dcha
				{
					int col=ReglasGravity.shiftColGrAbjDch(columnaMovimiento, filaGr, tab, fichaMovimento);
					int fil=ReglasGravity.shiftFilGrAbjDch(columnaMovimiento, filaGr, tab, fichaMovimento);
					tab.setCasilla(columnaMovimiento+col, filaGr+fil, fichaMovimento);
					setFinalPos(columnaMovimiento+col, filaGr+fil);
				}
			}
			else//dcha>=izq
			{
				if(abajo<izq)
					//Comprueba.despGrAbajo(columnaMovimiento, filaGr, tab,fichaMovimento);
				{
					int fil=ReglasGravity.shiftFilGrAbj(columnaMovimiento, filaGr, tab, fichaMovimento);
					int col=ReglasGravity.shiftColGrAbj(columnaMovimiento, filaGr, tab, fichaMovimento);
					//t.setCasilla(c, f+i, color);
					tab.setCasilla(columnaMovimiento+col, filaGr+fil, fichaMovimento);
					setFinalPos(columnaMovimiento+col, filaGr+fil);
				}
				else if(abajo>izq)
				//	Comprueba.despGrAbjIzd(columnaMovimiento, filaGr, tab,fichaMovimento);
				{
					int fil=ReglasGravity.shiftFilGrIzq(columnaMovimiento, filaGr, tab, fichaMovimento);
					int col=ReglasGravity.shiftColGrIzq(columnaMovimiento, filaGr, tab, fichaMovimento);
					tab.setCasilla(columnaMovimiento+col, filaGr+fil, fichaMovimento);
					setFinalPos(columnaMovimiento+col, filaGr+fil);
				}
				else //abajo==izq
					//Comprueba.despGrAbjIzd(columnaMovimiento, filaGr, tab,fichaMovimento);
				{
					int col=ReglasGravity.shiftColGrAbjIzq(columnaMovimiento, filaGr, tab, fichaMovimento);
					int fil=ReglasGravity.shiftFilGrAbjIzq(columnaMovimiento, filaGr, tab, fichaMovimento);
					tab.setCasilla(columnaMovimiento+col, filaGr+fil, fichaMovimento);
					setFinalPos(columnaMovimiento+col, filaGr+fil);
				}
			}
		}
		else //(abajo>=arriba)
		{
			if(dcha<izq)
			{
				if(arriba<dcha)
				{
					int fil=ReglasGravity.shiftFilGrArr(columnaMovimiento, filaGr, tab, fichaMovimento);
					int col=ReglasGravity.shiftColGrArr(columnaMovimiento, filaGr, tab, fichaMovimento);
					tab.setCasilla(columnaMovimiento+col, filaGr+fil, fichaMovimento);
					setFinalPos(columnaMovimiento+col, filaGr+fil);
				}	
				else if(arriba>dcha)
				{
					int fil=ReglasGravity.shiftFilGrDch(columnaMovimiento, filaGr, tab, fichaMovimento);
					int col=ReglasGravity.shiftColGrDch(columnaMovimiento, filaGr, tab, fichaMovimento);
					tab.setCasilla(columnaMovimiento+col, filaGr+fil, fichaMovimento);
					setFinalPos(columnaMovimiento+col, filaGr+fil);
				}
				else//arriba==dcha
				{
					int fil=ReglasGravity.shiftFilGrArrDch(columnaMovimiento, filaGr, tab, fichaMovimento);
					int col=ReglasGravity.shiftColGrArrDch(columnaMovimiento, filaGr, tab, fichaMovimento);
					tab.setCasilla(columnaMovimiento+col, filaGr+fil, fichaMovimento);
					setFinalPos(columnaMovimiento+col, filaGr+fil);
				}
			}
			else//dcha>=izq
			{
				if(arriba<izq)
				{
					int fil=ReglasGravity.shiftFilGrArr(columnaMovimiento, filaGr, tab, fichaMovimento);
					int col=ReglasGravity.shiftColGrArr(columnaMovimiento, filaGr, tab, fichaMovimento);
					tab.setCasilla(columnaMovimiento+col, filaGr+fil, fichaMovimento);
					setFinalPos(columnaMovimiento+col, filaGr+fil);
				}
				else if(arriba>izq)
				{
					int fil=ReglasGravity.shiftFilGrIzq(columnaMovimiento, filaGr, tab,fichaMovimento);
					int col=ReglasGravity.shiftColGrIzq(columnaMovimiento, filaGr, tab, fichaMovimento);
					tab.setCasilla(columnaMovimiento+col, filaGr+fil, fichaMovimento);
					setFinalPos(columnaMovimiento+col, filaGr+fil);
				}
				else//arriba==izq
				{
					int fil=ReglasGravity.shiftFilGrArrIzq(columnaMovimiento, filaGr, tab,fichaMovimento);
					int col=ReglasGravity.shiftColGrArrIzq(columnaMovimiento, filaGr, tab, fichaMovimento);
					tab.setCasilla(columnaMovimiento+col, filaGr+fil, fichaMovimento);
					setFinalPos(columnaMovimiento+col, filaGr+fil);
				}
			}
		}
		
	}
	@Override
	public
	void undo(Tablero tab)
	{
		if((filaFin!=-1)&&(colFin!=-1))
		tab.setCasilla(colFin, filaFin, Ficha.VACIA);
	}
	
	@Override
	public int getFilaFin()
	{
		return filaFin;
	}
	@Override
	public int getColumnaFin()
	{
		return colFin;
	}
	/**
	 * Método que lanza una excepción si la fila o la columna no pertenecen
	 * al rango de filas y columnas del tablero[1..ancho][1..alto]
	 * @param t tablero sobre el que se realiza dicha comprobación
	 * @throws MovimientoInvalido si los atributos del movimiento no se 
	 * corresponden con una casilla válida del tablero.
	 */
	private void compruebaFila(Tablero t) throws MovimientoInvalido
	{
		if ((filaGr<=0)
				||(filaGr>t.getAlto())
				||(columnaMovimiento<=0)
				||(columnaMovimiento>t.getAncho()))
			throw new MovimientoInvalido("Posición incorrecta.");
	}
	/**
	 * Método que lanza una excepción si la casilla de un determinado tablero
	 * cuya posición coincide con los atributos de este moviento está ocupada
	 * @param t tablero sobre el que se realiza la comprobacion
	 * @throws MovimientoInvalido si dicha casilla está ocupada en t
	 */
	private void compruebaCasilla(Tablero t) throws MovimientoInvalido
	{
		if(!t.getCasilla( filaGr,columnaMovimiento).equals(Ficha.VACIA))
			throw new MovimientoInvalido("Casilla ocupada.");
	}
	/**
	 * Método que guarda la posición en la que se coloca finalmente el movimiento en cualquier
	 * tablero.
	 * @param c Columna en la que acaba la ficha.
	 * @param f Fila en la que acaba la ficha.
	 */
	 private void setFinalPos(int c,int f)
	 {
		 colFin=c;
		 filaFin=f;
	 }
}
