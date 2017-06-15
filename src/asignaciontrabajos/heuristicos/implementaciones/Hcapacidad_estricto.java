package asignaciontrabajos.heuristicos.implementaciones;

import java.util.List;

import asignaciontrabajos.busqueda.EstadoAsignacionTrabajosMaquina;
import asignaciontrabajos.heuristicos.HeuristicoPlanificacionTareas;
import asignaciontrabajos.util.Intervalo;
import asignaciontrabajos.util.OperacionesAsignacionTrabajosMaquina;
import asignaciontrabajos.util.ValoresInstanciaAsignacionTrabajos;

/**
 * Calcula la suma de tardiness de las tareas no planificadas si se asignan
 * todas en el primer intervalo con capacidad disponible donde dicha tarea entre
 * 
 * Relaja capacidad
 * 
 */
public class Hcapacidad_estricto extends HeuristicoPlanificacionTareas {

	@Override
	protected int valorHeuristico(EstadoAsignacionTrabajosMaquina estado) {
		int total = 0;
		for (Integer tarea : estado.getSin_asignar())
			total += OperacionesAsignacionTrabajosMaquina
					.estimacionG(buscaInicioIntervaloLibreDondeEntra(estado.getIntervalos(), tarea), tarea);
		return total;
	}

	/*
	 * Siempre encontrará un intervalo, aunque sea el último.
	 */
	protected int buscaInicioIntervaloLibreDondeEntra(List<Intervalo> intervalos, int tarea) {
		for (Intervalo intervalo : intervalos)
			if (intervalo.libre() && entro(intervalo, ValoresInstanciaAsignacionTrabajos.duracion[tarea], intervalos))
				return intervalo.inicio;
		return -1;
	}

	protected boolean entro(Intervalo intervalo, int duracion, List<Intervalo> intervalos) {
		int acumulada = intervalo.lon();
		Intervalo aux = intervalo;
		while (acumulada < duracion) {
			aux = OperacionesAsignacionTrabajosMaquina.buscaIntervaloSiguiente(aux.fin, intervalos);
			if (aux == null) // no se deberia de dar nunca
				return false;
			else if (!aux.libre())
				return false;
			else
				acumulada += aux.lon();
		}
		return true;
	}
}