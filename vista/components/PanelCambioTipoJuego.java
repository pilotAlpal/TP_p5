package tp.pr5.vista.components;

import tp.pr5.control.ControladorGui;
import tp.pr5.logica.TipoJuego;
import tp.pr5.vista.components.PanelArgsGravity;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;


public class PanelCambioTipoJuego extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JComboBox<TipoJuego> combo;
	private PanelArgsGravity panelGraArgs;
	private JButton botonCambiar;
	private ControladorGui myController;
	/**
	 * Constructor de la clase
	 * @param wC controlador swing
	 */
	public PanelCambioTipoJuego(ControladorGui wC,TipoJuego defauGameTipe)
	{
		myController=wC;
		this.setBorder(javax.swing.BorderFactory.createTitledBorder("Cambio tipo de juego"));
		panelGraArgs=new PanelArgsGravity();
		botonCambiar=new JButton(new ImageIcon("src/images/tick.png","Cambiar"));
		
		iniciaCombo(defauGameTipe);
		escucha(botonCambiar);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(combo);
		
		
		Dimension minSize = new Dimension(0, 100);
		Dimension prefSize = new Dimension(0, 125);
		Dimension maxSize = new Dimension(0, 125);
		this.add(new Box.Filler(minSize, prefSize, maxSize));
		this.add(panelGraArgs);

		minSize = new Dimension(0, 80);
		prefSize = new Dimension(0, 100);
		maxSize = new Dimension(0, 100);
		this.add(new Box.Filler(minSize, prefSize, maxSize));	
		this.add(botonCambiar);
	}
	/**
	 * Atiende el comboBox
	 * @param botonCambiar2
	 */
	private void escucha(JButton botonCambiar2) 
	{
		botonCambiar.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
					TipoJuego t=(TipoJuego) combo.getSelectedItem();
					if (t.isResizable())
					{
						int f=panelGraArgs.getRow();
						int c=panelGraArgs.getCol();
						myController.cambiaTipoJuego(t,f,c);
					}
					else
						myController.cambiaTipoJuego(t);
				
			}
		});
	}
	/**
	 * Inicializa el comboBox
	 */
	private void iniciaCombo(TipoJuego tJuego)
	{
		combo=new JComboBox<TipoJuego>(TipoJuego.values());
        combo.addItemListener(new ItemListener() 
        {
			@Override
			public void itemStateChanged(ItemEvent e) 
			{
				if (e.getSource()==combo) 
				{
					TipoJuego t=(TipoJuego) combo.getSelectedItem();
					if(t.isResizable())
					{
						panelGraArgs.setVisible(true);
						panelGraArgs.vaciaCampos();
					}
					else
					{
						panelGraArgs.setVisible(false);
					}
				}
				
				
			}
		});
        combo.setSelectedItem(tJuego);
	}
}