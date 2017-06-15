package asignaciontrabajos.heuristicos.implementaciones;

import asignaciontrabajos.busqueda.EstadoAsignacionTrabajosMaquina;
import asignaciontrabajos.edd.adaptados.heuristicos.EDD_all;
import asignaciontrabajos.heuristicos.HeuristicoPlanificacionTareas;

/**
 * Explotando la idea de los heurísticos anteriores se calcula el valor de la
 * media aritmetica de los tiempos de procesamiento entre las tareas no
 * planificadas.
 * 
 * La idea de esta heurístico es contrastar la mejora de los nodos expandidos
 * que hace h4 en contraposición con el tiempo de procesamiento que requiere
 * calcular tantas veces EDD como tiempos de procesamiento distintos entre las
 * tareas no planificadas.
 * 
 * A continuación, se selecciona el tiempo de procesamiento que mejor se ajuste
 * al valor promedio.
 * 
 * Finalmente se aplica EDD sobre la instancia considerando que todas las tareas
 * sin asignar tienen como tiempo de procesamiento el tiempo de procesamiento de
 * la tarea sin asignar que mejor se ajuste al valor promedio.
 * 
 */
public class Hedd_promedio_A extends HeuristicoPlanificacionTareas {

	@Override
	protected int valorHeuristico(EstadoAsignacionTrabajosMaquina estado) {
		return new EDD_all(estado,
				calculaValorPromediante(mediaAritmetica(estado.getSin_asignar()), estado.getSin_asignar())).g();
	}
}