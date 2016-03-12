package tp.pr5.logica;

import tp.pr5.control.FactoriaComplica;
import tp.pr5.control.FactoriaConecta4;
import tp.pr5.control.FactoriaGravity;
import tp.pr5.control.FactoriaReversi;
import tp.pr5.control.FactoriaTipoJuego;

public enum TipoJuego 
{
	Conecta4,Complica,Gravity, Reversi;

	/**
	 * Indica si un tipo de juego es o no redimensionable
	 * @return
	 */
	public boolean isResizable() 
	{
		if(this.equals(Gravity))
			return true;
		return false;
	}
	/**
	 * Devuelve la factoria redimensionable correspondiente a un tipo de jugador
	 * @param f
	 * @param c
	 * @return
	 */
	public FactoriaTipoJuego dameFactoria(int f, int c) 
	{
		return new FactoriaGravity(c, f);
	}
	/**
	 * @return la factoria no redimensionable correspondiente a un tipo de jugador
	 */
	public FactoriaTipoJuego dameFactoria() 
	{
		if(this.equals(Complica))
			return new FactoriaComplica();
		if (this.equals(Conecta4))
			return new FactoriaConecta4();
		if (this.equals(Gravity))
			return new FactoriaGravity();
			return new FactoriaReversi();
	}
}
