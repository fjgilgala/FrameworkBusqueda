package asignaciontrabajos.edd.adaptados.heuristicos;

import asignaciontrabajos.busqueda.EstadoAsignacionTrabajosMaquina;
import asignaciontrabajos.util.Intervalo;
import asignaciontrabajos.util.OperacionesAsignacionTrabajosMaquina;
import asignaciontrabajos.util.OrdenarTareas;
import asignaciontrabajos.util.ValoresInstanciaAsignacionTrabajos;

/**
 * earliest due date first adaptado al heuristico 4
 */
public class EDD_all extends EDDadaptadoHeuristicos {

	public EDD_all(EstadoAsignacionTrabajosMaquina estado, int tiempoProcesamiento) {
		super(estado);
		tareasOrdenadas = OrdenarTareas.ordenaTodasLasTareasPorDueDate();
		this.tiempoProcesamiento = tiempoProcesamiento;
		algoritmo();
	}

	protected void algoritmo() {
		for (Integer tarea : tareasOrdenadas)
			if (sin_asignar.contains(tarea) && tiempoProcesamiento <= ValoresInstanciaAsignacionTrabajos.duracion[tarea])
				for (Intervalo intervalo : intervalos)
					if (intervalo.libre() && OperacionesAsignacionTrabajosMaquina.propaga(
							OperacionesAsignacionTrabajosMaquina.creaIntervalo(intervalo), tiempoProcesamiento,
							OperacionesAsignacionTrabajosMaquina.copiaIntervalos(intervalos))) {

						sin_asignar = OperacionesAsignacionTrabajosMaquina.getSinAsignar(tarea, sin_asignar);
						sti = OperacionesAsignacionTrabajosMaquina.getSti(tarea, intervalo.inicio, sti);
						g += estimacionG(intervalo.inicio, tarea);
						intervalos = OperacionesAsignacionTrabajosMaquina.getIntervalos();

						break;
					}
	}
}