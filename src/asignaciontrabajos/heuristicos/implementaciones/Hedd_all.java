package asignaciontrabajos.heuristicos.implementaciones;

import java.util.ArrayList;
import java.util.List;

import asignaciontrabajos.busqueda.EstadoAsignacionTrabajosMaquina;
import asignaciontrabajos.edd.adaptados.heuristicos.EDD_all;
import asignaciontrabajos.heuristicos.HeuristicoPlanificacionTareas;
import asignaciontrabajos.util.ValoresInstanciaAsignacionTrabajos;

/**
 * 
 * Para cada uno de los tiempos de procesamiento distintos entre las tareas no
 * planificadas se considera una instancia del problema donde las tareas que
 * originalmente tienen una duración mayor o igual toman el tiempo de
 * procesamiento considerado, luego esta instancia se resuelve con EDD.
 * 
 * Finalmente se selecciona como valor heurístico el valor mayor de las
 * instancias resueltas con EDD.
 * 
 */
public class Hedd_all extends HeuristicoPlanificacionTareas {

	@Override
	protected int valorHeuristico(EstadoAsignacionTrabajosMaquina estado) {
		List<Integer> duracionesDiferentes = new ArrayList<Integer>();
		List<Integer> valoresEDD = new ArrayList<Integer>();
		for (Integer tarea : estado.getSin_asignar())
			if (!duracionesDiferentes.contains(ValoresInstanciaAsignacionTrabajos.duracion[tarea]))
				duracionesDiferentes.add(ValoresInstanciaAsignacionTrabajos.duracion[tarea]);
		for (Integer duracion : duracionesDiferentes)
			valoresEDD.add(new EDD_all(estado, duracion).g());
		return valoresEDD.stream().mapToInt(i -> i).max().getAsInt();
	}
}