package tp.pr5.control;

import java.util.Random;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoComplica;
import tp.pr5.logica.TableroInmutable;
/**
 * Jugador que juega de forma aleatoria a Complica. 
 * En este caso cualquier columna es válida, pues si está llena, se hará hueco.
 * @author Lucas y
 *
 */
public class JugadorAleatorioComplica implements Jugador
{
	@Override
	public Movimiento getMovimiento(TableroInmutable t, Ficha turno) 
	{
		Random rnd=new Random();
		int aleatorio = rnd.nextInt(t.getColumnas())+1;
	
		return new MovimientoComplica(aleatorio, turno);
	}
}