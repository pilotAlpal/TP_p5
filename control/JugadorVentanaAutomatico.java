package tp.pr5.control;

import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Partida;

public class JugadorVentanaAutomatico extends JugadorVentana 
{
	private FactoriaTipoJuego miF;
	private Partida miP;
	private MiHilo hilo;
	private class MiHilo extends Thread
	{
		/**
		 * Construye el nuevo hilo
		 */
		public MiHilo() 
		{
			super();
		}
		@Override
		public void run()
		{
			try 
			{
				sleep(1600);
				generaMovimientoAleatorio();
				
			} 
			catch (InterruptedException e) 
			{
				this.interrupt();
			}
		}
	}

	@Override
	public void comienza(FactoriaTipoJuego f,Partida p) 
	{
		miF=f;miP=p;
		hilo=new MiHilo();
		hilo.start();
		//SwingUtilities.invokeLater(hilo);
	}
	/**
	 * Genera un movimiento aleatorio(en un nuevo hilo)
	 */
	protected void generaMovimientoAleatorio() 
	{
		if(!miP.isTerminada())//quitar???
		{
			Jugador aleatorio = miF.creaJugadorAleatorio();
			Movimiento movimientoAleatorio=miP.ponga(aleatorio);
			miP.ejecutaMovimiento(movimientoAleatorio);	
		}
			
	}
	@Override
	public void para() 
	{
		hilo.interrupt();
	}
	@Override
	public void poneFicha(Movimiento m,Partida p) 
	{
	}
	@Override
	public boolean isHuman() 
	{
		return false;
	}
}
