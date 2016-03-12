package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoConecta4;
import tp.pr5.logica.TableroInmutable;

public class JugadorHumanoConecta4 implements Jugador {

	Scanner in;
	/**
	 * Constructor de la clase
	 * @param e entrada
	 */
	public JugadorHumanoConecta4(Scanner e)
	{
		in=e;
	}
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
		return new MovimientoConecta4(columna, turno);
	}

}
