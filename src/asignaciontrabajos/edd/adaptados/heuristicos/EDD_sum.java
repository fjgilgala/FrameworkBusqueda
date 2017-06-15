package asignaciontrabajos.edd.adaptados.heuristicos;

import asignaciontrabajos.busqueda.EstadoAsignacionTrabajosMaquina;
import asignaciontrabajos.util.Intervalo;
import asignaciontrabajos.util.OperacionesAsignacionTrabajosMaquina;
import asignaciontrabajos.util.OrdenarTareas;
import asignaciontrabajos.util.ValoresInstanciaAsignacionTrabajos;

/**
 * earliest due date first adaptado al heuristico 6
 */
public class EDD_sum extends EDDadaptadoHeuristicos {

	private int minimo, maximo;

	public EDD_sum(EstadoAsignacionTrabajosMaquina estado, int minimo, int maximo, int tiempoProcesamiento) {
		super(estado);
		tareasOrdenadas = OrdenarTareas.ordenaTodasLasTareasPorDueDate();
		this.tiempoProcesamiento = tiempoProcesamiento;
		this.minimo = minimo;
		this.maximo = maximo;
		algoritmo();
	}

	public EDD_sum(EstadoAsignacionTrabajosMaquina estado, int minimo, int maximo) {
		super(estado);
		tareasOrdenadas = OrdenarTareas.ordenaTodasLasTareasPorDueDate();
		this.tiempoProcesamiento = minimo;
		this.minimo = minimo;
		this.maximo = maximo;
		algoritmo();
	}

	protected void algoritmo() {
		for (Integer tarea : tareasOrdenadas)
			if (sin_asignar.contains(tarea) && minimo <= ValoresInstanciaAsignacionTrabajos.duracion[tarea]
					&& ValoresInstanciaAsignacionTrabajos.duracion[tarea] < maximo)

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