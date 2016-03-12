package tp.pr5.control;

import java.util.Random;

import tp.pr5.logica.Comprueba;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoComplica;
import tp.pr5.logica.TableroInmutable;

public class JugadorAleatorioConecta4 implements Jugador
/**
 * Jugador que juega de forma aleatoria a Conecta 4. 
 * Simplemente elige una columna aleatoria en el tablero, 
 * comprobando antes que se podrá colocar en ella (no está llena).
 */
{
	@Override
	public Movimiento getMovimiento(TableroInmutable t, Ficha turno) 
	{
		Random rnd=new Random();
		int aleatorio = rnd.nextInt(t.getColumnas())+1;
		while(Comprueba.filaLibre(aleatorio,t)==0)
			aleatorio = rnd.nextInt(t.getColumnas())+1;
		return new MovimientoComplica(aleatorio, turno);
	}

}
