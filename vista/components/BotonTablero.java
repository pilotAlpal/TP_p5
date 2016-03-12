package tp.pr5.vista.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import tp.pr5.control.ControladorGui;
import tp.pr5.logica.Ficha;

public class BotonTablero extends JButton 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int fila;
	private int columna;
	private Ficha color;
	private ControladorGui myController;
	/**
	 * Constructor de la clase
	 * @param c columna asignada al botón
	 * @param f fila asignada al botón
	 * @param quien color asignado al botón
	 * @param wController controlador
	 */
	public BotonTablero(int c,int f,Ficha quien, ControladorGui wController)
	{
		myController=wController;
		color=quien;
		fila=f+1;
		columna=c+1;
		this.setToolTipText("[c,f] ="+columna+","+fila);
		this.setBackground(color.getColor());
		this.addActionListener
		(
				new ActionListener() 
									{
										@Override
										public void actionPerformed(ActionEvent e) 
										{
											myController.generaMovimientoJugador(columna, fila);
										}
									}
		);
	}
	/**
	 * Permite cambiar el color del botón
	 * @param color2 nuevo color
	 */
	public void cambiaBoton(Ficha color2) 
	{
		color=color2;
		this.setBackground(color.getColor());
	}
}
