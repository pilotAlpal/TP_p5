package tp.pr5.control;

import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Partida;

public class JugadorVentanaHumano extends JugadorVentana {

	@Override
	public void comienza(FactoriaTipoJuego f,Partida p) 
	{
	}

	@Override
	public void para() 
	{
	
	}

	@Override
	public void poneFicha(Movimiento m,Partida p) 
	{
		p.ejecutaMovimiento(m);
	}

	@Override
	public boolean isHuman() {
		return true;
	}

}
