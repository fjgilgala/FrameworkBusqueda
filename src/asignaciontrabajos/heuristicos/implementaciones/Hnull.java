package asignaciontrabajos.heuristicos.implementaciones;

import asignaciontrabajos.busqueda.EstadoAsignacionTrabajosMaquina;
import asignaciontrabajos.heuristicos.HeuristicoPlanificacionTareas;

public class Hnull extends HeuristicoPlanificacionTareas {

	@Override
	protected int valorHeuristico(EstadoAsignacionTrabajosMaquina estado) {
		return 0;
	}
}