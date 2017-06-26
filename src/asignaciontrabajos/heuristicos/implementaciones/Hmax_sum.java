package asignaciontrabajos.heuristicos.implementaciones;

import asignaciontrabajos.busqueda.EstadoAsignacionTrabajosMaquina;
import asignaciontrabajos.heuristicos.HeuristicoPlanificacionTareas;
import framework.util.Metrica;

public class Hmax_sum extends HeuristicoPlanificacionTareas {

	@Override
	protected int valorHeuristico(EstadoAsignacionTrabajosMaquina estado) {
		int h2 = new Hcapacidad_estricto().calcula(estado);
		if (h2 >= Metrica.cotaSuperior)
			return h2;
		else
			return Math.max(h2, new Hedd_sum_all().calcula(estado));
	}
}