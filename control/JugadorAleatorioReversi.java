package tp.pr5.control;

import java.util.Random;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.MovimientoReversi;
import tp.pr5.logica.Punto;
import tp.pr5.logica.ReglasReversi;
import tp.pr5.logica.TableroInmutable;

public class JugadorAleatorioReversi implements Jugador 
{
	@Override
	public Movimiento getMovimiento(TableroInmutable t, Ficha turno) 
	{
		Random rnd=new Random();
		int aleCol = rnd.nextInt(t.getColumnas())+1;
		int aleFil = rnd.nextInt(t.getFilas())+1;
		Punto puntoAleatorio=new Punto(aleFil, aleCol);
		while(!ReglasReversi.flanquea(puntoAleatorio, t, turno))
		{
			aleCol = rnd.nextInt(t.getColumnas())+1;
			aleFil = rnd.nextInt(t.getFilas())+1;
			puntoAleatorio=new Punto(aleFil,aleCol);
		}
		return new MovimientoReversi(aleCol, aleFil, turno);
	}
}
