package tp.pr5.vista.components;


import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import tp.pr5.control.ControladorGui;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;
import tp.pr5.vista.components.PanelDerecho;
import tp.pr5.vista.components.PanelIzquierdo;

public class VistaGUI extends JFrame 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel derecho;
	private PanelIzquierdo izquierdo;
	/**
	 * Constructor de la clase
	 * @param wC controlador ventana
	 * @param tableroInicial 
	 * @param turnoInicial
	 */
	public  VistaGUI(ControladorGui wC, TableroInmutable tableroInicial,Ficha turnoInicial,TipoJuego tJ) 
	{
		izquierdo=new PanelIzquierdo(wC,tableroInicial,turnoInicial);
		wC.addPartidaObserver(izquierdo);
		derecho=new PanelDerecho(wC,izquierdo.getBotonAleatorio(),tJ);
		this.setLayout(new GridLayout(1,2));
		this.add(izquierdo);
		this.add(derecho);
		
	}

			
}
