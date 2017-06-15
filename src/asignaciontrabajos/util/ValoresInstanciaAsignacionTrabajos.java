package asignaciontrabajos.util;

import java.util.ArrayList;

import framework.basic.ValoresInstanciaBasic;

/**
 * Clase ValoresInstancia encargada de representar el perfil inicial de la
 * máquina así como las tareas con sus tiempos de procesamiento y sus due dates.
 * También la función heuristica empleada para resolver la instancia.
 * 
 * @author fran
 *
 */
public class ValoresInstanciaAsignacionTrabajos extends ValoresInstanciaBasic{
	/** Due dates de las tareas */
	public static int[] fechaVencimiento;
	/** Tiempos de procesamiento de las tareas */
	public static int[] duracion;

	/** Perfil inicial de la máquina */
	public static ArrayList<Intervalo> perfilMaquina = new ArrayList<Intervalo>();
}