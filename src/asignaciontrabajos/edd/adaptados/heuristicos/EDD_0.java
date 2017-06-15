package asignaciontrabajos.edd.adaptados.heuristicos;

import asignaciontrabajos.busqueda.EstadoAsignacionTrabajosMaquina;
import asignaciontrabajos.util.Intervalo;
import asignaciontrabajos.util.OperacionesAsignacionTrabajosMaquina;
import asignaciontrabajos.util.OrdenarTareas;
import asignaciontrabajos.util.ValoresInstanciaAsignacionTrabajos;

/**
 * earliest due date first adaptado al heuristico 3
 */
public class EDD_0 extends EDDadaptadoHeuristicos {

	public EDD_0(EstadoAsignacionTrabajosMaquina estado) {
		super(estado);
		tareasOrdenadas = OrdenarTareas.ordenaTodasLasTareasPorDueDate();
		tiempoProcesamiento = Integer.MAX_VALUE;
		for (Integer i : sin_asignar)
			if (ValoresInstanciaAsignacionTrabajos.duracion[i] < tiempoProcesamiento)
				tiempoProcesamiento = ValoresInstanciaAsignacionTrabajos.duracion[i];
		algoritmo();
	}

	protected void algoritmo() {
		for (Integer tarea : tareasOrdenadas)
			if (sin_asignar.contains(tarea))
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