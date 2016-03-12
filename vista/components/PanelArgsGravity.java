package tp.pr5.vista.components;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tp.pr5.control.FactoriaGravity;

public class PanelArgsGravity extends JPanel
{
	private JLabel labelFilas,labelColumnas;
	private JTextField texFieldFilas,texFieldColumnas;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor de la clase
	 * @param conti controlador swing
	 */
	public PanelArgsGravity()
	{
		labelFilas=new JLabel("Filas");
		labelColumnas=new JLabel("Columnas");
		texFieldColumnas=new JTextField();
		texFieldFilas=new JTextField();
		this.setLayout(new GridLayout(1, 4));
		this.add(labelColumnas);
		this.add(texFieldColumnas);
		this.add(labelFilas);
		this.add(texFieldFilas);
		this.setVisible(false);
	}
	//cambiar
	public int getRow()
	{
		String s= texFieldFilas.getText();
		int i=-1;
		try 
		{
			i=Integer.parseInt(s);
		} catch (NumberFormatException  e) 
		{
			int filas=FactoriaGravity.getDefaultRows();
			JOptionPane.showMessageDialog(this, "Número de filas incorrecto/vacío." +
					"se selecciona "+filas+" por defecto.", "Disculpe"
					, JOptionPane.INFORMATION_MESSAGE);
			return filas;
		} 
		return i;
	}
	public int getCol()
	{
		String s= texFieldColumnas.getText();
		int i=-1;
		try 
		{
		
			i=Integer.parseInt(s);
		} catch (NumberFormatException  e) 
		{
			int columnas=FactoriaGravity.getDefaultCols();
			JOptionPane.showMessageDialog(this, "Número de filas incorrecto/vacío." +
					"se selecciona "+columnas+" por defecto.", "Disculpe"
					, JOptionPane.INFORMATION_MESSAGE);
			return columnas;
		} 
		return i;
	}
	/**
	 * Vacía los campos para introducir el tamaño
	 */
	public void vaciaCampos()
	{
		texFieldColumnas.setText("");
		texFieldFilas.setText("");
	}
}
