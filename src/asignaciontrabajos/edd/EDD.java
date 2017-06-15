package asignaciontrabajos.edd;

import asignaciontrabajos.AsignacionTrabajosMaquina;
import asignaciontrabajos.util.Intervalo;
import asignaciontrabajos.util.OperacionesAsignacionTrabajosMaquina;
import asignaciontrabajos.util.ValoresInstanciaAsignacionTrabajos;

/**
 * earliest due date first
 */
public abstract class EDD extends AsignacionTrabajosMaquina {

	protected int[] tareasOrdenadas;

	protected void algoritmo() {
		for (Integer tarea : tareasOrdenadas)
			for (Intervalo intervalo : intervalos)
				if (intervalo.libre() && OperacionesAsignacionTrabajosMaquina.propaga(
						OperacionesAsignacionTrabajosMaquina.creaIntervalo(intervalo), ValoresInstanciaAsignacionTrabajos.duracion[tarea],
						OperacionesAsignacionTrabajosMaquina.copiaIntervalos(intervalos))) {

					sin_asignar = OperacionesAsignacionTrabajosMaquina.getSinAsignar(tarea, sin_asignar);
					sti = OperacionesAsignacionTrabajosMaquina.getSti(tarea, intervalo.inicio, sti);
					g += OperacionesAsignacionTrabajosMaquina.estimacionG(intervalo.inicio, tarea);
					intervalos = OperacionesAsignacionTrabajosMaquina.getIntervalos();

					break;
				}
	}
}