package tp.pr5.logica;
/**
 * Clase que representa una pila gen�rica
 * @author lucas
 *
 */
public class OurStack <T>
{
	private static int TAMANO=10;
	private T[]apilado;
	private int cabeza;
	
	/**
	 * Constructora vacía
	 */
	public OurStack()
	{
		cabeza=-1;
		apilado= (T[])new Object[TAMANO];
	}
	/**
	 *Apila un nuevo elemento.
	 *Si no cabe,dobla la capacidad de la pila y después lo apila. 
	 * @param m  Elemento a apilar
	 */
	public void apila(T m)
	{
		if(cabeza==TAMANO-1)
			dobla();
		cabeza++;
		apilado[cabeza]=m;
	}
	/**
	 * Informa si la pila esta vacía.
	 * @return True si está vacía,false si no.
	 */
	public boolean isVacia()
	{
		return cabeza==-1;
	}
	/**
	 * Permite desapilar el ultimo elemento de la pila
	 * @return true si se ha podido desapilar,false si la pila esta vac�a.
	 */
	public boolean desapila()
	{
		if (isVacia()) return false;
		cabeza--;
		return true;
	}
	/**
	 * Devuelve el ultimo elemento apilado y lo desapila.
	 * 
	 * @return el ultimo elemento apilado,null si la pila esta vacía.
	 */
	public T top()
	{
		if (isVacia())
			return null;
		T m=apilado[cabeza];
		cabeza--;
		return m;
	}
	/**
	 * dobla la capacidad de la pila
	 */
	private void dobla()
	{
		int nuevoTam=2*TAMANO;
		T[] nuevosMovimientos=(T[])new Object[nuevoTam];
		for(int i=0;i<TAMANO;i++)
			nuevosMovimientos[i]=apilado[i];
		TAMANO=nuevoTam;
		apilado=nuevosMovimientos;
	}
}
