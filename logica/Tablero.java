package tp.pr5.logica;

import tp.pr5.logica.Ficha;

/**
 * Almacena la información de un tablero rectangular. 
 * El tamaño se fija en el momento de la construcción, 
 * y contiene métodos para acceder a la información de cada celda 
 * y para colocar fichas.


 * @author lucas
 *
 */
public class Tablero implements TableroInmutable
{
	private int columnas;
	private int filas;
	private Ficha[][] fichas; 
	/**
	 *Construye un tablero vacío. 
	 * @param tx - Tamaño en la coordenada x (o n�mero de columnas).
	 * @param ty - Tamaño en la coordenada y y (o n�mero de filas).
	 */
	public Tablero(int tx, int ty)
	{
		if((ty<=0)||(tx<=0))
		{
			columnas=1;filas=1;
		}
		else
		{
			columnas=tx;
			filas=ty;
		}
		
		fichas=new Ficha[columnas][filas];
		initialize();
	}
		
	/**
	 *Método para obtener el alto del tablero. 
	 * @return
	 * Número de filas del tablero.
	 */
    public int getAlto()
    {
    	return filas;
    }
    /**
     *Método para obtener el ancho del tablero. 
     * @return Número de columnas del tablero.
     */
    public int getAncho()
    {return columnas;}
    @Override
    public Ficha getCasilla(int fila,int columna)
    {
    	if (!isValidCell(columna, fila))
    		return Ficha.VACIA;
    	return fichas[columna-1][fila-1];
    }

    
    /**
     *Permite especificar el nuevo contenido de una casilla. 
		También puede utilizarse para quitar una ficha.
     * @param x - Numero de columna(1..ancho)
     * @param y - Número de fila (1..alto)
     * @param color - Color de la casilla. Si se indica Ficha.VACIA, 
     * la celda quedar� sin ficha.
     */
    public void setCasilla(int x,int y,Ficha color)
    {
    	if (isValidCell(x, y))
    	{
    		fichas[x-1][y-1]=color;
    	}
    }

    /**
     * Método que inicializa,
     * colocando una ficha vacía en todas sus posiciones, 
     * el teclado.
     * */
    private void initialize()
    {
    	for(int ancho=1;ancho<=getAncho();ancho++)
    	{
    		for(int alto=1;alto<=getAlto();alto++)
      			setCasilla(ancho, alto, Ficha.VACIA);
    	}	
  	
    }
    
    /**
     * Devuelve cierto si una columna pertenece al rango de columnas del tablero
     * @param columna columna que se quiere conocer si pertenece al tablero
     * @return true si columna pertenece a [1..ancho],false si no
     */
    public boolean isValidColumn(int columna)
    {
    	return((columna>0)&&(getAncho()>=columna));
    }
    /**
     * Devuelve cierto si una columna pertenece al rango de columnas del tablero
     * @param fila -fila que se quiere conocer si pertenece al tablero
     * @return true si fila pertenece a [1..alto],false si no
     */
    
    public boolean isValidRow(int fila)
    {
    	return((fila>0)&&(getAlto()>=fila));
    }
    
    /**
     * Comprueba si una casilla pertenece a nuestro tablero,
     * ([1..ancho]x[1..alto]).
     * @param columna -Coordenada x.
     * @param fila -Coordenada y.
     * @return True si una determinada casilla pertenece al rango de filas
     * y columnas del tablero,false si no.
     */
    public boolean isValidCell(int columna,int fila)
    {
    	return (isValidColumn(columna)&&isValidRow(fila));
    }
        
    /**
	 *@Override 
	 */
	public String toString()
	{
		String devuelve="";
		for(int i=0;i<getAlto();i++)
		{
			devuelve+="|";
			for(int j=0;j<getAncho();j++)
			{
				devuelve+=fichas[j][i].toTablero()/*+" "*/;
			}
			devuelve+="|";
			devuelve+=System.getProperty("line.separator");
		}
		devuelve+="+";
		for(int j=0;j<getAncho();j++)
		{
			devuelve+="-";
		}
		devuelve+="+";
		devuelve+=System.getProperty("line.separator");
		devuelve+=" ";
		for(int j=0;j<getAncho();j++)
		{
			devuelve+=(j+1);
		}			
		return devuelve;
	}

	@Override
	public int getFilas() 
	{return getAlto();}

	@Override
	public int getColumnas() 
	{
		return getAncho();
	}

}
