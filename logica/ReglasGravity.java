package tp.pr5.logica;

public class ReglasGravity implements ReglasJuego {

	/**
	 * Constructor de la clase.
	 * @param numCols Número de columnas del tablero.
	 * @param numFilas Número de filas del tablero.
	 */
	
	private int filas;
	private int columnas;
	/**
	 * 
	 * Constructor de la clase
	 * @param numCols número de columnas
	 * @param numFilas número de filas
	 */
	public ReglasGravity(int numCols,int numFilas)
	{
		columnas=numCols;
		filas=numFilas;
	}
	@Override
	public Tablero iniciaTablero() 
	{
		return new Tablero(columnas, filas);
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
		int column=ultimoMovimiento.getColumnaFin();
		int fila=ultimoMovimiento.getFilaFin();
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
		return ReglasGravity.tableroGravityLLeno(t);
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, TableroInmutable t) 
	{	
		return ultimoEnPoner.cambiaFicha();
	}
	/**
	 * 
	@param c columna 
	 * @param f fila
	 * @param t tablero
	 * @param color turno
	 *  * @return
	 */
	static int shiftColGrAbjIzq(int c,int f,Tablero t,Ficha color)
	{
		Ficha fich=Ficha.VACIA;
		boolean stop=false;
		int i=0;
		while(!stop)
		{
			if ((c-i)==1) 
				stop=true;
			else
			{
				fich=t.getCasilla(f+i+1,c-i-1 );
				if (!fich.equals(Ficha.VACIA))
					stop=true;
				else
					i++;
			}		
		}
		return -i;
	}
	/**
	 * 
	 * @param c columna 
	 * @param f fila
	 * @param t tablero
	 * @param color turno
	 * @return
	 */
	static int shiftColGrArrIzq(int c,int f,Tablero t,Ficha color)
	{
		Ficha fich=Ficha.VACIA;
		boolean stop=false;
		int i=0;
		while(!stop)
		{
			if ((c-i)==1) 
				stop=true;
			else
			{
				fich=t.getCasilla( f-i-1,c-i-1);
				if (!fich.equals(Ficha.VACIA))
					stop=true;
				else
					i++;
			}		
		}
		return -i;
	}
	/**
	 * 
	 * @param c columna 
	 * @param f fila
	 * @param t tablero
	 * @param color turno
	 * @return
	 */
	static int shiftFilGrArrIzq(int c,int f,Tablero t,Ficha color)
	{
		return ReglasGravity.shiftColGrArrIzq(c, f, t, color);
	}
	/**
	 * @param c columna 
	 * @param f fila
	 * @param t tablero
	 * @param color turno
	 * @return
	 */
	static int shiftFilGrAbjIzq(int c,int f,Tablero t,Ficha color)
	{
		return -ReglasGravity.shiftColGrAbjIzq(c, f, t, color);
	}
	/**
	 * 
	 * @param c
	 * @param f
	 * @param t
	 * @param color
	 * @return
	 */
	static int shiftColGrArrDch(int c,int f,Tablero t,Ficha color)
	{
		Ficha fich=Ficha.VACIA;
		boolean stop=false;
		int i=0;
		while(!stop)
		{
			if ((f-i)==1) 
				stop=true;
			else
			{
				fich=t.getCasilla( f-i-1,c+i+1);
				if (!fich.equals(Ficha.VACIA))
					stop=true;
				else
					i++;
			}		
		}
		return i;
	}
	/**
	 * @param c columna 
	 * @param f fila
	 * @param t tablero
	 * @param color turno
	 * @return
	 */
	static int shiftFilGrArrDch(int c,int f,Tablero t,Ficha color)
	{
		return -ReglasGravity.shiftColGrArrDch(c, f, t, color);
	}
	/**
	 * @param c columna 
	 * @param f fila
	 * @param t tablero
	 * @param color turno
	 * @return
	 */
	static int shiftFilGrAbjDch(int c,int f,Tablero t,Ficha color)
	{
		return shiftColGrAbjDch(c, f, t, color);
	}
	/**
	 * 
	 * @param c columna 
	 * @param f fila
	 * @param t tablero
	 * @param color turno
	 * @return deplazamiento vertical de una ficha que será atraida hacia abajo
	 */
	static int shiftColGrAbjDch(int c,int f,Tablero t,Ficha color)
	{
		Ficha fich=Ficha.VACIA;
		boolean stop=false;
		int i=0;
		while(!stop)
		{
			if ((c+i)==t.getAncho()) 
				stop=true;
			else
			{
				fich=t.getCasilla(f+i+1,c+i+1);
				if (!fich.equals(Ficha.VACIA))
					stop=true;
				else
					i++;
			}		
		}
		return i;
	}
	/**
	 * 
	 *@param c columna 
	 * @param f fila
	 * @param t tablero
	 * @param color turno
	 *  @return desplazamiento horizontal hacia la izquierda
	 */
	static int shiftColGrIzq(int c,int f,Tablero t,Ficha color)
	{
		Ficha fich=Ficha.VACIA;
		boolean stop=false;
		int i=0;
		while(!stop)
		{
			if ((c-i)==1) 
				stop=true;
			else
			{
				fich=t.getCasilla(f,c-i-1);
				if (!fich.equals(Ficha.VACIA))
					stop=true;
				else
					i++;
			}		
		}
		return -i;
	}
	/**
	 * 
	 * @param c columna 
	 * @param f fila
	 * @param t tablero
	 * @param color turno
	 * @return 0
	 */
	static int shiftFilGrIzq(int c,int f,Tablero t,Ficha color)
	{
		return 0;
	}
	/**
	 * 
	 * @param c columna 
	 * @param f fila
	 * @param t tablero
	 * @param color turno
	 * @return 0
	 */
	static int shiftFilGrDch(int c,int f,Tablero t,Ficha color)
	{
		return 0;
	}
	/**
	 *	Método que devuelve el desplazamiento horizontal que debe sufrir una determinada ficha,
	 * en un determinado tablero,cuando se ha colocado en cierta posición,y va a ser atraida
	 * verticalmente hacia la derecha.
	 * @param c columna en la que el usuario colocó la ficha.
	 * @param f	fila en la que el usuario colocó la ficha.
	 * @param t tablero en el que se colocó la ficha
	 * @param color ficha que se colocó
	 * @return Número de columnas que se desplazará la ficha hacia las 3
	 */
	static int shiftColGrDch(int c,int f,Tablero t,Ficha color)
	{
		Ficha fich=Ficha.VACIA;
		boolean stop=false;
		int i=0;
		while(!stop)
		{
			if ((c+i)==t.getAncho()) 
				stop=true;
			else
			{
				fich=t.getCasilla(f,c+i+1);
				if (!fich.equals(Ficha.VACIA))
					stop=true;
				else
					i++;
			}		
		}
		return i;
	}
	/**
	 * Método que devuelve el desplazamiento horizontal que debe sufrir una determinada ficha,
	 * en un determinado tablero,cuando se ha colocado en cierta posición,y va a ser atraida
	 * verticalmente hacia abajo.
	 * @param c columna en la que el usuario colocó la ficha.
	 * @param f	fila en la que el usuario colocó la ficha.
	 * @param t tablero en el que se colocó la ficha
	 * @param color ficha que se colocó
	 * @return 0
	 */
	static int shiftColGrAbj(int c,int f,Tablero t,Ficha color)
	{
		return 0;
	}
	/**
	 *Método que devuelve el desplazamiento vertical que debe sufrir una determinada ficha,
	 * en un determinado tablero,cuando se ha colocado en cierta posición,y va a ser atraida
	 * verticalmente hacia abajo.
	 * @param c columna en la que el usuario colocó la ficha.
	 * @param f	fila en la que el usuario colocó la ficha.
	 * @param t tablero en el que se colocó la ficha
	 * @param color ficha que se colocó
	 * @return Número de casillas que se desplazará hacia las 6
	 */
	static int shiftFilGrAbj(int c,int f,Tablero t,Ficha color)
	{
		Ficha fich=Ficha.VACIA;
		boolean stop=false;
		int i=0;
		while(!stop)
		{
			if ((f+i)==t.getAlto()) 
				stop=true;
			else
			{
				fich=t.getCasilla(f+i+1,c);
				if (!fich.equals(Ficha.VACIA))
					stop=true;
				else
					i++;
			}		
		}
		return i;
	}
	/**
	 * Método que devuelve el desplazamiento horizontal que debe sufrir una determinada ficha,
	 * en un determinado tablero,cuando se ha colocado en cierta posición,y va a ser atraida
	 * verticalmente hacia arriba.
	 * @param c columna en la que el usuario colocó la ficha.
	 * @param f	fila en la que el usuario colocó la ficha.
	 * @param t tablero en el que se colocó la ficha
	 * @param color ficha que se colocó
	 * @return 0
	 */
	static int shiftColGrArr(int c,int f,Tablero t,Ficha color)
	{
		return 0;
	}
	/**
	 Método que devuelve el desplazamiento vertical que debe sufrir una determinada ficha,
	 * en un determinado tablero,cuando se ha colocado en cierta posición,y va a ser atraida
	 * verticalmente hacia arriba.
	 * @param c columna en la que el usuario colocó la ficha.
	 * @param f	fila en la que el usuario colocó la ficha.
	 * @param t tablero en el que se colocó la ficha
	 * @param color ficha que se colocó
	 * @return número de casillas que se desplazará la ficha hacia las 12.
	 */
	static int shiftFilGrArr(int c,int f,Tablero t,Ficha color)
	{
		Ficha fich=Ficha.VACIA;
		boolean stop=false;
		int i=0;
		while(!stop)
		{
			if ((f-i)==1) 
				stop=true;
			else
			{
				fich=t.getCasilla(f-i-1,c);
				if (!fich.equals(Ficha.VACIA))
					stop=true;
				else
					i++;
			}		
		}
		return -i;
	}
	/**
	 *	Método que comprueba que un tablero está lleno.
	 * 	Esto es,que no tiene ninguna casilla vacía.
	 * 	Sólo será llamado en el modo gravity.
	 * @param t tablero
	 * @return true si esta lleno,false si no
	 */
	static boolean tableroGravityLLeno(TableroInmutable t)
	{
		boolean lleno=true;
		for(int i=1;i<=t.getColumnas()&&lleno;i++)
			for(int j=1;j<=t.getFilas()&&lleno;j++)
			{
				if(t.getCasilla( j,i).equals(Ficha.VACIA))
					lleno=false;
			}
		return lleno;
	}
}
