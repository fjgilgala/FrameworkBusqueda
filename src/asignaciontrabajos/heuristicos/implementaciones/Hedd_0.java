package asignaciontrabajos.heuristicos.implementaciones;

import asignaciontrabajos.busqueda.EstadoAsignacionTrabajosMaquina;
import asignaciontrabajos.edd.adaptados.heuristicos.EDD_0;
import asignaciontrabajos.heuristicos.HeuristicoPlanificacionTareas;

/**
 * Calcula el valor aplicando EDD considerando que todas las tareas tienen el
 * tiempo de procesamiento de la tarea no planificada con menor tiempo de
 * procesamiento
 */
public class Hedd_0 extends HeuristicoPlanificacionTareas {

	@Override
	protected int valorHeuristico(EstadoAsignacionTrabajosMaquina estado) {
		return new EDD_0(estado).g();
	}
}