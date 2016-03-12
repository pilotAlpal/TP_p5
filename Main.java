package tp.pr5;
import java.util.Scanner;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;



import tp.pr5.control.ControladorConsola;
import tp.pr5.control.ControladorGui;
import tp.pr5.control.FactoriaComplica;
import tp.pr5.control.FactoriaConecta4;
import tp.pr5.control.FactoriaGravity;
import tp.pr5.control.FactoriaReversi;
import tp.pr5.control.FactoriaTipoJuego;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Partida;
import tp.pr5.logica.ReglasJuego;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.TipoJuego;
import tp.pr5.vista.VistaConsola;
import tp.pr5.vista.components.VistaGUI;

public class Main 
{

	/**
	 * @param args
	 */
	private static void principalConsola(FactoriaTipoJuego f)
	{
		Scanner teclado=new Scanner(System.in);
		FactoriaTipoJuego fTj=f;
		ReglasJuego reglasIniciales=fTj.creaReglas();
		Partida modelo=new Partida(reglasIniciales);
		ControladorConsola consoController=new ControladorConsola(fTj,modelo,teclado);
		TableroInmutable tabInicial=reglasIniciales.iniciaTablero();
		Ficha jugadorInicial=reglasIniciales.jugadorInicial();
		VistaConsola vista=new VistaConsola(consoController, tabInicial, jugadorInicial);
		consoController.addPartidaObserver(vista);
		consoController.run();
	}
	
	private static void principalVentana(FactoriaTipoJuego f)
	{
		FactoriaTipoJuego fTj=f;
		ReglasJuego reglasIniciales=fTj.creaReglas();
		TableroInmutable tableroInicial =reglasIniciales.iniciaTablero();
		Ficha turnoInicial=reglasIniciales.jugadorInicial();
		Partida modelo=new Partida(reglasIniciales);
		ControladorGui myController=new ControladorGui(modelo, fTj);
		VistaGUI vista =new VistaGUI(myController,tableroInicial,turnoInicial,TipoJuego.Reversi);
		vista.setBounds(300,100,800,600);
	    vista.setVisible(true);
	}
	public static void main(String[] args)
	{
		TipoJuego j=TipoJuego.Reversi;
		FactoriaTipoJuego fTj=new FactoriaReversi();
		Options options = new Options();
        
		options.addOption("g","game",true,"Tipo de juego (c4, co, gr,rv). Por defecto, c4.");
        options.addOption("h", "help", false, "Muestra esta ayuda");
        options.addOption("u","ui",true," Tipo de interfaz (console, window). Por defecto, console.");
        options.addOption("x","tamX",true,"Número de columnas del tablero (sólo paraGravity). " +
        		"Por defecto, 10.");
        options.addOption("y","tamY",true,"Número de filas del tablero (sólo para " +
        		"Gravity). Por defecto, 10.");
		CommandLineParser parser = new BasicParser( );
	    try
	    {  
	    	CommandLine cmd = parser.parse( options, args );
	    	 if(cmd.hasOption("h"))
	    	 {
	    		 System.out.println("usage: tp.pr3.Main [-g <game>] [-h] [-x <columnNumber>] [-y <rowNumber>]");
	    		 System.out.println(" -g,--game <game>           Tipo de juego (c4, co, gr,rv). Por defecto, c4.");
	    		 System.out.println(" -h,--help                  Muestra esta ayuda.");
	    		 System.out.println("-u,--ui <tipo>              Tipo de interfaz (console, window). Por defecto, console");
	    		 System.out.println(" -x,--tamX <columnNumber>   Nï¿½mero de columnas del tablero (sï¿½lo para"); 
	    		 System.out.println("                            Gravity). Por defecto, 10.");
	    		 System.out.println(" -y,--tamY <rowNumber>      Nï¿½mero de filas del tablero (sï¿½lo para" );
	    		 System.out.println("                            Gravity). Por defecto, 10.");
	    	 }
	    	 else
	    	 {
	    		 if (cmd.hasOption("g"))
		    	 {
					 String s[]=cmd.getArgs();
	    			 if(!(s.length==0))
	    			 {
	    				 argumentosNoEntendidos(s);
	    			 }
	    			 if(cmd.getOptionValue("g").equalsIgnoreCase("c4"))
	    			 {
	    				 fTj=new FactoriaConecta4();
		    			 j=TipoJuego.Conecta4;
	    			 }
	    			 else if(cmd.getOptionValue("g").equalsIgnoreCase("co"))
	    			 {
	    				 fTj=new FactoriaComplica();
		    			 j=TipoJuego.Complica;
	    			 }
	    			 else if(cmd.getOptionValue("g").equalsIgnoreCase("rv"))
	    			 {
	    				 fTj=new FactoriaReversi();
		    			 j=TipoJuego.Reversi;
	    			 }
	    			 else if(cmd.getOptionValue("g").equalsIgnoreCase("gr"))
	    			 {
	    				 fTj=new FactoriaGravity();
		    			 j=TipoJuego.Gravity;
	    			 }
	    			 else
		    		 {
		    			 System.err.println("Uso incorrecto: Juego "+cmd.getOptionValue("g")+" incorrecto.");
			    		 System.err.println("Use -h|--help para mï¿½s detalles.");
			    		 System.exit(1);
		    		 }
		    	 }
		    	 if (cmd.hasOption("x"))
		    	 {
		    		 if (j.equals(TipoJuego.Gravity))
		    		 {
		    			 if (cmd.hasOption("y"))
		    	    	 {
		    	    		try 
		    	    		{
								int col=Integer.parseInt(cmd.getOptionValue("x"));
								int fil=Integer.parseInt(cmd.getOptionValue("y"));
								fTj=new FactoriaGravity(col, fil);
							} 
		    	    		catch (Exception e) 
		    	    		{
								
							} 
		    	    	 }
		    			 else
			    		 {
			    			 System.err.println("Uso incorrecto.");
				    		 System.err.println("Use -h|--help para más detalles.");
			    		 } 
		    		 }
		    		 else
		    		 {
		    			 System.err.println("Uso incorrecto.");
			    		 System.err.println("Use -h|--help para más detalles.");
		    		 }
		    	 }
		    	 if (cmd.hasOption("u"))
		    	 {
		    		 if(cmd.getOptionValue("u").equalsIgnoreCase("console"))
		    			 principalConsola(fTj);
		    		 else if(cmd.getOptionValue("u").equalsIgnoreCase("window"))
		    			 principalVentana(fTj);
		    		 else 
		    		 {
		    			 System.err.println("Uso incorrecto.");
			    		 System.err.println("Use -h|--help para más detalles.");
		    		 }
		    	 }
		    	 else principalConsola(new FactoriaConecta4());
	    	 }	    	 
	    	 
	    
	    }
	    catch(ParseException ex)
	    {
	    	System.err.print("Uso incorrecto: ");
	    	System.err.println(ex.getMessage());
	    	System.err.println("Use -h|--help para más detalles.");
	    	System.exit(1);
	    }
	}

	private static void argumentosNoEntendidos(String[] s) 
	{
		System.err.print("Uso incorrecto: Argumentos no entendidos:");
		for(int i=0;i<s.length;i++)
			System.err.print(" "+s[i]);
		System.err.println();
		System.err.print("Use -h|--help para más detalles.");
		System.exit(1);
		
	}

}
