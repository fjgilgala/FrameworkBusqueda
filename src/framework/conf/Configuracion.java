package framework.conf;

import java.util.List;

import framework.ABIERTA;
import framework.Heuristico;
import framework.Parser;
import framework.algoritmosbusqueda.AlgoritmoBusqueda;

/**
 * Clase Configuracion encargada de asignar los valores que utilizará el
 * programa
 */
public class Configuracion {
	public static String problema;
	public static AlgoritmoBusqueda algoritmo;
	public static ABIERTA abierta;
	public static List<Heuristico> heuristicos;
	public static int profundidadMaxima;
	public static int frecuenciaLookAHead;
	public static int timeout;
	public static boolean trazable;
	public static Parser parser;
}