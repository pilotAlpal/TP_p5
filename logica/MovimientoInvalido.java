package tp.pr5.logica;

import java.io.Serializable;

/**
 * Excepci�n generada cuando se intenta ejecutar un movimiento incorrecto.
 * @author Lucas y
 *
 */
public class MovimientoInvalido extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor sin par�metros.
	 */
	public MovimientoInvalido()
	{super();}
	/**
	 * Constructor con un par�metro para el mensaje.
	 * @param s parametro
	 */
	public MovimientoInvalido(String s)
	{super(s);}
	/**
	 * Constructor con un par�metro para el mensaje y otro para la causa.
	 * @param msg parametro mensaje.
	 * @param arg parametro causa.
	 */
	public MovimientoInvalido(String msg,Throwable arg)
	{super(msg,arg);}
	/**
	 * Constructor con un par�metro para la causa inicial que provoc� la excepci�n.
	 * @param arg parametro causa
	 */
	public MovimientoInvalido(Throwable arg)
	{
		super(arg);
	}
}
