package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoReversi;
import tp.pr5.logica.TableroInmutable;

public class JugadorHumanoReversi implements Jugador 
{

	private Scanner entrada;
	/**
	 * Constructor de la clase
	 * @param in entrada
	 */
	public JugadorHumanoReversi(Scanner in) 
	{
		entrada=in;
	}

	@Override
	public Movimiento getMovimiento(TableroInmutable t, Ficha turno) 
	{
		  int f=0;int c=0;
		
		System.out.print("Introduce la columna: ");
		try 
		{
			c=Integer.parseInt(entrada.next());
		} 
		catch (NumberFormatException e) 
		{
			System.err.println("Columnas enteras");
		}
		System.out.print("Introduce la fila: ");
		try 
		{
			f=Integer.parseInt(entrada.next());
		} 
		catch (NumberFormatException e) 
		{
			System.err.println("Filas enteras");
		}
		return new MovimientoReversi(c, f, turno);
	}
}
