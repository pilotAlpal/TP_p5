package tp.pr5.control;

import java.util.Random;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoGravity;
import tp.pr5.logica.TableroInmutable;

/**
 * Jugador que juega de forma aleatoria a Gravity. 
 * Simplemente elige una posición aleatoria en el tablero, 
 * comprobando antes que esa casilla esté vacía (se podrá poner).
 * @author Lucas y
 *
 */
public class JugadorAleatorioGravity implements Jugador 
{
	@Override
	public Movimiento getMovimiento(TableroInmutable t, Ficha turno) 
	{
		Random rnd=new Random();
		int aleCol = rnd.nextInt(t.getColumnas())+1;
		int aleFil = rnd.nextInt(t.getFilas())+1;
		while(!t.getCasilla(aleFil, aleCol).equals(Ficha.VACIA))
		{
			aleCol = rnd.nextInt(t.getColumnas())+1;
			aleFil = rnd.nextInt(t.getFilas())+1;
		}
		return new MovimientoGravity(aleCol, aleFil, turno);
	}
}
