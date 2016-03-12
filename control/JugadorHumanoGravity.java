package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoGravity;
import tp.pr5.logica.TableroInmutable;

public class JugadorHumanoGravity implements Jugador {

	private Scanner in;
	/**
	 * Constructor de la clase
	 * @param ent entrada
	 */
	public JugadorHumanoGravity(Scanner ent) 
	{
		in=ent;
	}
	@Override
	public Movimiento getMovimiento(TableroInmutable t, Ficha turno) {
		int f=0;int c=0;
		
		System.out.print("Introduce la columna: ");
		try 
		{
			c=Integer.parseInt(in.next());
			
		} 
		catch (NumberFormatException e) 
		{
			System.err.println("Columnas enteras");
		}
		System.out.print("Introduce la fila: ");
		try 
		{
			f=Integer.parseInt(in.next());
		} 
		catch (NumberFormatException e) 
		{
			System.err.println("Filas enteras");
		}
		return new MovimientoGravity(c, f, turno);
	}

}