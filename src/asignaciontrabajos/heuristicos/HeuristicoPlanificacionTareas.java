package asignaciontrabajos.heuristicos;

import java.util.ArrayList;
import java.util.List;

import asignaciontrabajos.AsignacionTrabajosMaquina;
import asignaciontrabajos.busqueda.EstadoAsignacionTrabajosMaquina;
import asignaciontrabajos.util.OrdenarTareas;
import asignaciontrabajos.util.ValoresInstanciaAsignacionTrabajos;
import framework.Estado;
import framework.Heuristico;

public abstract class HeuristicoPlanificacionTareas implements Heuristico {

	@Override
	public int calcula(Estado estado) {
		if (!((AsignacionTrabajosMaquina) estado).getSin_asignar().isEmpty())
			return valorHeuristico((EstadoAsignacionTrabajosMaquina) estado);
		else
			return 0;
	}

	protected abstract int valorHeuristico(EstadoAsignacionTrabajosMaquina estado);

	protected double mediaAritmetica(List<Integer> sin_asignar) {
		double media = 0;
		for (Integer i : sin_asignar)
			media += ValoresInstanciaAsignacionTrabajos.duracion[i];
		return media / sin_asignar.size();
	}

	@Override
	public String toString() {
		return this.getClass().getName().replaceAll("asignaciontrabajos.heuristicos.implementaciones.", "");
	}

	protected double mediaGeometrica(List<Integer> sin_asignar) {
		double media = 1;
		for (Integer i : sin_asignar)
			media *= ValoresInstanciaAsignacionTrabajos.duracion[i];
		return Math.pow(media, 1.0 / sin_asignar.size());
	}

	protected double mediana(List<Integer> sin_asignar) {
		List<Integer> noPlanificados = new ArrayList<Integer>(sin_asignar.size());
		int[] tareasOrdenadas = OrdenarTareas.ordenaTodasLasTareasPorTiempoProcesamiento();
		for (Integer tarea : tareasOrdenadas)
			if (sin_asignar.contains(tarea))
				noPlanificados.add(tarea);
		if (noPlanificados.size() % 2 == 0) {
			return (ValoresInstanciaAsignacionTrabajos.duracion[noPlanificados.get(noPlanificados.size() / 2)]
					+ ValoresInstanciaAsignacionTrabajos.duracion[noPlanificados.get((noPlanificados.size() / 2) - 1)]) / 2;
		} else
			return ValoresInstanciaAsignacionTrabajos.duracion[noPlanificados.get(noPlanificados.size() / 2)];
	}

	/**
	 * Calcula el valor que se les asignará al tiempo de procesamiento que será
	 * el valor más proximo al valor promedio
	 */
	protected int calculaValorPromediante(double media, List<Integer> sin_asignar) {
		int tiempoProcesamiento = 0;
		double valor = Integer.MAX_VALUE;
		for (Integer tarea : sin_asignar)
			// busca el valor más proximo al valor promedio
			if (Math.abs(media - ValoresInstanciaAsignacionTrabajos.duracion[tarea]) <= valor) {
				valor = Math.abs(media - ValoresInstanciaAsignacionTrabajos.duracion[tarea]);
				tiempoProcesamiento = ValoresInstanciaAsignacionTrabajos.duracion[tarea];
			}
		return tiempoProcesamiento;
	}

}