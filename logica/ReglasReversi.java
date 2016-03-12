package tp.pr5.logica;

import java.util.ArrayList;

public class ReglasReversi implements ReglasJuego 
{
	private final int N_FILAS=8;
	private final int N_COLUMNAS=8;
	

	@Override
	public Tablero iniciaTablero() 
	{
		Tablero devuelvo=new Tablero(N_COLUMNAS, N_FILAS);
		devuelvo.setCasilla((N_COLUMNAS/2)+1, N_FILAS/2, Ficha.NEGRA);
		devuelvo.setCasilla((N_COLUMNAS/2), (N_FILAS/2), Ficha.BLANCA);
		devuelvo.setCasilla((N_COLUMNAS/2)+1, N_FILAS/2+1, Ficha.BLANCA);
		devuelvo.setCasilla((N_COLUMNAS/2), (N_FILAS/2)+1, Ficha.NEGRA);
		return devuelvo;
	}

	@Override
	public Ficha jugadorInicial() 
	{
		return Ficha.NEGRA;
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) 
	{
		if (siguienteTurno(ultimoMovimiento.fichaMovimento,t).equals(Ficha.VACIA))
				return quienTieneMas(t);
		return Ficha.VACIA;
	}

	@Override
	public boolean tablas(Ficha ultimoEnPoner, TableroInmutable t) 
	{
		if (siguienteTurno(ultimoEnPoner, t).equals(Ficha.VACIA))
				return(quienTieneMas(t).equals(Ficha.VACIA));
		return false;
	}
	/**
	 * Comprueba que jugador tiene más fichas colocadas en un tablero
	 * @param t tablero en cuestión
	 * @return color del jugador que más fichas a colocado
	 * ,ficha vacia si ninguno tiene más
	 */
	private Ficha quienTieneMas(TableroInmutable t) 
	{
		int negras=0;int blancas=0;
		for(int f=1;f<t.getFilas();f++)
			for(int c=1;c<t.getColumnas();c++)
			{
				if(t.getCasilla(f, c).equals(Ficha.BLANCA))
					blancas++;
				else if (t.getCasilla(f, c).equals(Ficha.NEGRA))
					negras++;
			}
		if(negras>blancas)
			return Ficha.NEGRA;
		if(negras<blancas)
			return Ficha.BLANCA;
		return Ficha.VACIA;
	}

	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, TableroInmutable t) 
	{
		Ficha rival=ultimoEnPoner.cambiaFicha();
		ArrayList<Punto>puntos=puntosJugables(t,rival);
		if(!puntos.isEmpty())
			return rival;
		else puntos=puntosJugables(t, ultimoEnPoner);
		if(!puntos.isEmpty())
			return ultimoEnPoner;
		return Ficha.VACIA;
	}
	/**
	 * Informa de en que puntos puede colocar un determinado jugador sobre un tablero concreto
	 * @param t
	 * @param color
	 * @return
	 */
	private static ArrayList<Punto> puntosJugables(TableroInmutable t,Ficha color)
	{
		Punto p;
		ArrayList<Punto> retorno =new ArrayList<Punto>();
		for(int c=1;c<=t.getColumnas();c++)
			for(int f=1;f<=t.getFilas();f++)
			{
				p=new Punto(f, c);
				if(flanquea(p,t,color))
					retorno.add(p);
			}
		return retorno;
	}
	/**
	 * Informa de si una casilla de un color flanque al menos una casilla del rival sobre un 
	 * tablero concreto
	 * @param p
	 * @param t
	 * @param color
	 * @return
	 */
	public static boolean flanquea(Punto p, TableroInmutable t,Ficha color) 
	{
		ArrayList<Punto> puntos=aQuienesFlanqueo(p, color, t);
		return  !puntos.isEmpty();
	}

	/**
	 * Informa de a que casillas flanquearia colocando una determinada ficha en un
	 * determinado punto de un tablero concreto
	 * @param elegidoPorUser punto el que se colocaria la ficha
	 * @param fichaMovimento color de la ficha
	 * @param tab tablero sobre el que se realiza la consulta
	 * @return coleccion con todos los puntos que flanqueo,si es que flanque alguno
	 * si no sera una coleccion vacia
	 */
	public static ArrayList<Punto> aQuienesFlanqueo(Punto elegidoPorUser,
			Ficha fichaMovimento, TableroInmutable tab) 
	{
		int col=elegidoPorUser.getColumna();
		int fil=elegidoPorUser.getFila();
		ArrayList<Punto> retorno =new ArrayList<Punto>();
		
		if(!tab.getCasilla(fil,col).equals(Ficha.VACIA))
			return retorno;//si la casilla no esta vacia no puedo poner,luego no flanqueo a nadie
		
		comeArriba(retorno,col,fil,fichaMovimento,tab);
		
		comeArrDch(retorno,col,fil,fichaMovimento,tab);
		
		comeDerecha(retorno,col,fil,fichaMovimento,tab);
		
		comeDchAbj(retorno,col,fil,fichaMovimento,tab);
		
		comeAbajo(retorno,col,fil,fichaMovimento,tab);
				
		comeAbjIzq(retorno,col,fil,fichaMovimento,tab);
		
		comeIzquierda(retorno,col,fil,fichaMovimento,tab);
		
		comeIzqArr(retorno,col,fil,fichaMovimento,tab);
		
		return retorno;
	}

	private static void comeIzqArr(ArrayList<Punto> puntosComo, int col, int fil,
			Ficha fichaMovimento, TableroInmutable tab) 
	{
		ArrayList<Punto> flanqueadas=new ArrayList<Punto>();
		Ficha rival=fichaMovimento.cambiaFicha();
		int f=fil-1;
		int c=col-1;
		if(tab.getCasilla(f, c).equals(rival))
		{
			flanqueadas.add(new Punto(f,c));
			f--;c--;
			boolean stop=!((c>0)&&(f>0));
			while(!stop)
			{
				if(tab.getCasilla(f, c).equals(rival))
					flanqueadas.add(new Punto(f,c));
				else
				{
					if(tab.getCasilla(f, c).equals(fichaMovimento))
						anadePuntos(flanqueadas,puntosComo);
					stop=true;
				}
				c--;f--;
				stop=stop||(c<=0)||(f<=0);
			}
		}		
	}

	private static void comeIzquierda(ArrayList<Punto> puntosComo, int col,
			int fil, Ficha fichaMovimento, TableroInmutable tab) 
	{
		ArrayList<Punto> flanqueadas=new ArrayList<Punto>();
		Ficha rival=fichaMovimento.cambiaFicha();
		int c=col-1;
		int f=fil;
		if(tab.getCasilla(f, c).equals(rival))
		{
			flanqueadas.add(new Punto(f,c));
			c--;
			boolean stop=(c<=0);
			while(!stop)
			{
				if(tab.getCasilla(f, c).equals(rival))
					flanqueadas.add(new Punto(f,c));
				else
				{
					if(tab.getCasilla(f, c).equals(fichaMovimento))
						anadePuntos(flanqueadas,puntosComo);
					stop=true;
				}
				c--;
				stop=stop||(c<=0);
			}
		}		
	}

	private static void comeAbjIzq(ArrayList<Punto> puntosComo, int column, int row,
			Ficha pone, TableroInmutable t) 
	{
		ArrayList<Punto> flanqueadas=new ArrayList<Punto>();
		Ficha rival=pone.cambiaFicha();
		int f=row+1;
		int c=column-1;
		if(t.getCasilla(f, c).equals(rival))
		{
			flanqueadas.add(new Punto(f,c));
			f++;c--;
			boolean stop=((c<=0)||(f>t.getFilas()));
			while(!stop)
			{
				if(t.getCasilla(f, c).equals(rival))
					flanqueadas.add(new Punto(f,c));
				else
				{
					if(t.getCasilla(f, c).equals(pone))
						anadePuntos(flanqueadas,puntosComo);
					stop=true;
				}
				c--;f++;
				stop=stop||(c<=0)||(f>t.getFilas());
			}
		}
		
	}

	private static void comeAbajo(ArrayList<Punto> puntosComo, int col, int fil,
			Ficha fichaMovimento, TableroInmutable tab) 
	{
		Ficha rival=fichaMovimento.cambiaFicha();
		int f=fil+1;
		int c=col;
		if(tab.getCasilla(f,c).equals(rival))
		{
			ArrayList<Punto> flanqueadas=new ArrayList<Punto>();
			flanqueadas.add(new Punto(f,c));
			f++;
			boolean stop=(f>tab.getFilas());
			while(!stop)
			{
				if(tab.getCasilla(f, c).equals(rival))
					flanqueadas.add(new Punto(f,c));
				else
				{
					if(tab.getCasilla(f, c).equals(fichaMovimento))
						anadePuntos(flanqueadas,puntosComo);
					stop=true;
				}
				f++;
				stop=stop||(f>tab.getFilas());
			}
		}
	}

	private static void comeDchAbj(ArrayList<Punto> puntosComo, int col, int fil,
			Ficha fichaMovimento, TableroInmutable tab) 
	{
		Ficha rival=fichaMovimento.cambiaFicha();
		int f=fil+1;
		int c=col+1;
		if(tab.getCasilla(f, c).equals(rival))
		{
			ArrayList<Punto> flanqueadas=new ArrayList<Punto>();
			flanqueadas.add(new Punto(f,c));
			f++;c++;
			boolean stop=((c>tab.getColumnas())||(f>tab.getFilas()));
			while(!stop)
			{
				if(tab.getCasilla(f,c).equals(rival))
					flanqueadas.add(new Punto(f,c));
				else
				{
					if(tab.getCasilla(f, c).equals(fichaMovimento))
						anadePuntos(flanqueadas,puntosComo);
					stop=true;
				}
					
				c++;f++;
				stop=stop||(c>tab.getColumnas())||(f>tab.getFilas());
			}
		}
	}

	private static void comeDerecha(ArrayList<Punto> puntosComo, int col, int fil,
			Ficha fichaMovimento, TableroInmutable tab) 
	{
		Ficha rival=fichaMovimento.cambiaFicha();
		int c=col+1;
		int f=fil;
		if(tab.getCasilla(f, c).equals(rival))
		{
			ArrayList<Punto> flanqueadas=new ArrayList<Punto>();
			flanqueadas.add(new Punto(f,c));
			c++;
			boolean stop=(c>tab.getColumnas());
			while(!stop)
			{
				if(tab.getCasilla(f, c).equals(rival))
					flanqueadas.add(new Punto(f,c));
				else
				{
					if(tab.getCasilla(f, c).equals(fichaMovimento))
						anadePuntos(flanqueadas,puntosComo);
					stop=true;
				}
				c++;
				stop=stop||(c>tab.getColumnas());
			}
		}

	}

	private static void comeArrDch(ArrayList<Punto> puntosComo, int col, int fil,
			Ficha fichaMovimento, TableroInmutable tab) 
	{
		Ficha rival=fichaMovimento.cambiaFicha();
		int f=fil-1;
		int c=col+1;
		if(tab.getCasilla(f,c).equals(rival))
		{	
			ArrayList<Punto> flanqueadas=new ArrayList<Punto>();
			flanqueadas.add(new Punto(f,c));
			f--;c++;
			boolean stop=((c>tab.getColumnas())||(f<=0));
			while(!stop)
			{
				if(tab.getCasilla(f, c).equals(rival))
					flanqueadas.add(new Punto(f,c));
				else
				{
					if(tab.getCasilla(f, c).equals(fichaMovimento))
						anadePuntos(flanqueadas,puntosComo);
					stop=true;
				}
				c++;f--;
				stop=stop||(c>tab.getColumnas())||(f<=0);
			}
		}
		
	}
	private static void comeArriba(ArrayList<Punto> puntosComo, int col, int fil,
			Ficha fichaMovimento, TableroInmutable tab) 
	{
		Ficha rival=fichaMovimento.cambiaFicha();
		int f=fil-1;
		int c=col;
		if(tab.getCasilla(f, c).equals(rival))
		{
			ArrayList<Punto> flanqueadas=new ArrayList<Punto>();
			flanqueadas.add(new Punto(f,c));
			f--;
			boolean stop=(f<=0);
			while(!stop)
			{
				if(tab.getCasilla(f, c).equals(rival))
					flanqueadas.add(new Punto(f,c));
				else
				{
					if(tab.getCasilla(f, c).equals(fichaMovimento))
						anadePuntos(flanqueadas,puntosComo);
					stop=true;
				}	
				f--;
				stop=stop||(f<=0);
			}
		}
	
	}
	/**
	 * añade todos los puntos de un arraylist a otro
	 * @param origen
	 * @param destino
	 */
	private static void anadePuntos(ArrayList<Punto> origen,
			ArrayList<Punto> destino) 
	{
		for(int i=0;i<origen.size();i++)
		{
			destino.add(origen.get(i));
		}
	}

}
