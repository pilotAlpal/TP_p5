package tp.pr5.logica;

import java.io.Serializable;

/**
 * Excepción generada cuando se intenta ejecutar un movimiento incorrecto.
 * @author Lucas y
 *
 */
public class MovimientoInvalido extends Exception implements Serializable
{
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor sin parámetros.
	 */
	public MovimientoInvalido()
	{super();}
	/**
	 * Constructor con un parámetro para el mensaje.
	 * @param s parametro
	 */
	public MovimientoInvalido(String s)
	{super(s);}
	/**
	 * Constructor con un parámetro para el mensaje y otro para la causa.
	 * @param msg parametro mensaje.
	 * @param arg parametro causa.
	 */
	public MovimientoInvalido(String msg,Throwable arg)
	{super(msg,arg);}
	/**
	 * Constructor con un parámetro para la causa inicial que provocó la excepción.
	 * @param arg parametro causa
	 */
	public MovimientoInvalido(Throwable arg)
	{
		super(arg);
	}
}
