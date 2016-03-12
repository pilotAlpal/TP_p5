package tp.pr5.logica;

public enum TipoJugador
{
	Humano,Automatico;
	/**
	 * Informa si un tipo de jugador es humano o automático
	 * @return
	 */
	public boolean esRobot()
	{
		if (this.equals(Automatico))
			return true;
		return false;
		
	}
	
}