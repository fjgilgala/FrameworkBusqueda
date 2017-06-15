package asignaciontrabajos.heuristicos.implementaciones;

import java.util.List;

import asignaciontrabajos.busqueda.EstadoAsignacionTrabajosMaquina;
import asignaciontrabajos.heuristicos.HeuristicoPlanificacionTareas;
import asignaciontrabajos.util.Intervalo;
import asignaciontrabajos.util.OperacionesAsignacionTrabajosMaquina;

/**
 * Calcula la suma de tardiness de las tareas no planificadas si se asignan
 * todas en el primer intervalo con capacidad disponible
 * 
 * Relaja capacidad
 * 
 */
public class Hcapacidad_relajado extends HeuristicoPlanificacionTareas {

	@Override
	protected int valorHeuristico(EstadoAsignacionTrabajosMaquina estado) {
		int sti = buscaInicioIntervaloLibre(estado.getIntervalos());
		int total = 0;
		for (Integer tarea : estado.getSin_asignar()) 
			total += OperacionesAsignacionTrabajosMaquina.estimacionG(sti, tarea);
		return total;
	}

	/*
	 * Siempre encontrará un intervalo, aunque sea el último.
	 */
	private int buscaInicioIntervaloLibre(List<Intervalo> intervalos) {
		for (Intervalo intervalo : intervalos)
			if (intervalo.libre())
				return intervalo.inicio;
		return -1;
	}
}