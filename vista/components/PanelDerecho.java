package tp.pr5.vista.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

//import tp.pr5.vista.components.PanelGesJugs;
import tp.pr5.control.ControladorGui;
import tp.pr5.logica.TipoJuego;
import tp.pr5.vista.components.PanelCambioTipoJuego;
import tp.pr5.vista.components.PanelPartida;

public class PanelDerecho extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelCambio,panelGestion;
	private PanelPartida panelPartida;
	private JButton botonSalir;
	private ControladorGui myController;

	/**
	 * Constructor de la clase
	 * @param wC controlador
	 * @param botonAleatorio (de otro panel)que deshabilitará cuando proceda
	 */
	public PanelDerecho(ControladorGui wC,JButton botonAleatorio,TipoJuego tJ)
	{
		myController=wC;
		panelCambio=new PanelCambioTipoJuego(wC,tJ);
		panelPartida=new PanelPartida(wC);
		panelGestion=new PanelGesJugs(wC,panelPartida.getBotonDeshacer(),botonAleatorio);
		botonSalir=new JButton(new ImageIcon("src/images/exit.png","Salir"));//customizar
		escucha(botonSalir);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(panelPartida);
		
		this.add(panelGestion);
		
		this.add(panelCambio);		
		
		this.add(botonSalir);
	}
	/**
	 * Atiende al botón de salir
	 * @param botonSalir2
	 */
	private void escucha(JButton botonSalir2) 
	{
		botonSalir2.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				myController.shutDown(0);
			}
		});
		
	}

}
