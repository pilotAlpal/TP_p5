package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoComplica;
import tp.pr5.logica.TableroInmutable;

public class JugadorHumanoComplica implements Jugador
{
	Scanner in;
	/**
	 * Constructor de la clase.
	 * 
	 * @param a Buffer de entrada
	 */
	public JugadorHumanoComplica(Scanner a){in=a;}
	@Override
	public Movimiento getMovimiento(TableroInmutable t, Ficha turno) 
	{
			System.out.print("Introduce la columna: ");
			int columna=-1;
			String col=in.nextLine();
			col=col.trim();
			try 
			{
				columna=Integer.parseInt(col);		
			} 
			catch (NumberFormatException e) 
			{
				System.err.println("Columnas enteras");
			}
			return new MovimientoComplica(columna, turno);
	}
}