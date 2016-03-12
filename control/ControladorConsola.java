package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Partida;

public class ControladorConsola extends Controller 
{
	private Scanner entrada;
	private Jugador jugBlanco,jugNegro;
	/**
	 * Constructor de la clase
	 * @param fTj factoria a la que se juega inicialmente
	 * @param modelo partida inicial
	 * @param teclado entrada de consola
	 */
	public ControladorConsola(FactoriaTipoJuego fTj, Partida modelo,
			Scanner teclado) 
	{
		entrada=teclado;
		miModelo=modelo;
		miFactoria=fTj;
		actualizaJugadoresHumanos();
	}
	/**
	 * metodo principal de la aplicacion
	 */
	public void run() 
	{
		boolean stop=false;String comando="nada";
		while((!stop)&&(!comando.equalsIgnoreCase("salir")))
		{
			comando=entrada.nextLine();
			if(!comando.equalsIgnoreCase("salir"))
				parsea(comando);
			if(miModelo.isTerminada())
				stop=true;
		}
		
	}
	/**
	 * Llama al metodo encargado de parsear la entrada del usuario(en funcion de 
	 * la longitud de dicha entrada)
	 * @param comando introducido por el usuario
	 */
	private void parsea(String comando) 
	{
		while(comando.equalsIgnoreCase(""))
			comando=entrada.nextLine();
		String[] vec=comando.split(" +");
		if(vec.length>4)
			System.err.println("No te entiendo.");
		else if (vec.length==1)
			parseaComando(vec[0]);
		else if (vec.length==2)
			parseaComando(vec);
		else if (vec.length==3)
			parseaJugador(vec);
		else if (vec.length==4) parseaNuevoGr(vec);
	}
	/**
	 * Parsea comandos de cuatro palabras
	 * @param vec vector con el comando introducido por el usuario
	 */
	private void parseaNuevoGr(String[] vec) 
	{
		if(vec[0].equalsIgnoreCase("jugar"))
		{
			if(vec[1].equalsIgnoreCase("gr"))
			{
				try 
				{
					int a=Integer.parseInt(vec[2]);
					int b=Integer.parseInt(vec[3]);
					miFactoria=new FactoriaGravity(a,b);
					miModelo.reset(miFactoria.creaReglas());
					actualizaJugadoresHumanos();
				}
				catch (Exception e) 
				{
					System.err.println("Filas y columnas enteras");
				}
			}
			else System.err.println("No te entiendo");
		}
		else System.err.println("No te entiendo");

	}
	/**
	 * Establece un nuevo jugador humano para ambos jugadores
	 */
	private void actualizaJugadoresHumanos() 
	{
			jugNegro=miFactoria.creaJugadorHumanoConsola(entrada);
			jugBlanco=miFactoria.creaJugadorHumanoConsola(entrada);
	}
	/**
	 * Parsea comandos de tres palabras
	 * @param vec vector con el comando introducido por el usuario
	 */
	private void parseaJugador(String[] vec) 
	{
		if (vec[0].equalsIgnoreCase("jugador"))
		{
			if(vec[1].equalsIgnoreCase("blancas"))
			{
				if (vec[2].equalsIgnoreCase("humano"))
				{
					jugBlanco=miFactoria.creaJugadorHumanoConsola(entrada);
				}
				else if(vec[2].equalsIgnoreCase("aleatorio"))
				{
					jugBlanco=miFactoria.creaJugadorAleatorio();
				}
				else System.err.println("No te entiendo");
			}
			else if(vec[1].equalsIgnoreCase("negras"))
			{
				if (vec[2].equalsIgnoreCase("humano"))
				{
					jugNegro=miFactoria.creaJugadorHumanoConsola(entrada);
				}
				else if(vec[2].equalsIgnoreCase("aleatorio"))
				{
					jugNegro=miFactoria.creaJugadorAleatorio();
				}
				else System.err.println("No te entiendo");
			}
			else System.err.println("No te entiendo");
		}
		else System.err.println("No te entiendo");
	}
	/**
	 * 
	 * @return El jugador al que le toca poner
	 */
	private Jugador getJugador()
	{
		if (miModelo.getTurno().equals(Ficha.BLANCA))
			return jugBlanco;
		else return jugNegro;
	}
	/**
	 * Parsea comandos de una palabra
	 * @param comando introducido por el usuario
	 */
	private void parseaComando(String comando) 
	{
		if(comando.equalsIgnoreCase("ayuda"))
		{
			System.out.println("Los comandos disponibles son:");
			System.out.println();
			System.out.println("PONER: utilízalo para poner la siguiente ficha.");
			System.out.println("DESHACER: deshace el último movimiento hecho en la partida.");
			System.out.println("REINICIAR: reinicia la partida.");
			System.out.println("JUGAR [c4|co|gr|rv] [tamX tamY]: cambia el tipo de juego.");
			System.out.println("JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.");
			System.out.println("SALIR: termina la aplicaci�n.");
			System.out.println("AYUDA: muestra esta ayuda.");
			System.out.println();
		}
		else if (comando.equalsIgnoreCase("deshacer"))
		{
			boolean deshecho=miModelo.undo();
			if(!deshecho)
				System.err.println("Imposible deshacer.");
		}
		else if(comando.equalsIgnoreCase("reiniciar"))
		{	
			miModelo.reset(miFactoria.creaReglas());
			actualizaJugadoresHumanos();
		}
			
		else if (comando.equalsIgnoreCase("poner"))
		{
			Movimiento jugada=miModelo.ponga(getJugador());
			miModelo.ejecutaMovimiento(jugada);
			
		}
		else if(comando.equalsIgnoreCase("salir"))
		{
			System.exit(0);
		}
		else if( (!comando.equalsIgnoreCase("salir")))
			System.err.println("No te entiendo.");
		
	}
	/**
	 * Parsea comandos de dos palabras
	 * @param vec vector con el comando introducido por el usuario
	 */
	private void parseaComando(String[] vec) 
	{
		if(vec[0].equalsIgnoreCase("jugar"))
		{
			if(vec[1].equalsIgnoreCase("c4"))
			{
				miFactoria=new FactoriaConecta4();
				miModelo.reset(miFactoria.creaReglas());
				actualizaJugadoresHumanos();
			}	
			else if(vec[1].equalsIgnoreCase("co"))
			{
				miFactoria=new FactoriaComplica();
				miModelo.reset(miFactoria.creaReglas());
				actualizaJugadoresHumanos();
			}
			else if(vec[1].equalsIgnoreCase("gr"))
			{
				miFactoria=new FactoriaGravity();
				miModelo.reset(miFactoria.creaReglas());
				actualizaJugadoresHumanos();
			}
			else if(vec[1].equalsIgnoreCase("rv"))
			{
				miFactoria=new FactoriaReversi();
				miModelo.reset(miFactoria.creaReglas());
				actualizaJugadoresHumanos();
			}
			else System.err.println("No te entiendo");
		}
		else System.err.println("No te entiendo");
	}
}
