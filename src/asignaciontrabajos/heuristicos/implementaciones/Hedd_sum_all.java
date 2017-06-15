package asignaciontrabajos.heuristicos.implementaciones;

import java.util.ArrayList;
import java.util.List;

import asignaciontrabajos.busqueda.EstadoAsignacionTrabajosMaquina;
import asignaciontrabajos.edd.adaptados.heuristicos.EDD_all;
import asignaciontrabajos.edd.adaptados.heuristicos.EDD_sum;
import asignaciontrabajos.heuristicos.HeuristicoPlanificacionTareas;
import asignaciontrabajos.util.ValoresInstanciaAsignacionTrabajos;

/**
 */
public class Hedd_sum_all extends HeuristicoPlanificacionTareas {

	@Override
	protected int valorHeuristico(EstadoAsignacionTrabajosMaquina estado) {
		List<Integer> duracionesDiferentes = new ArrayList<Integer>();
		List<Integer> valoresEDD = new ArrayList<Integer>();
		for (Integer tarea : estado.getSin_asignar())
			if (!duracionesDiferentes.contains(ValoresInstanciaAsignacionTrabajos.duracion[tarea]))
				duracionesDiferentes.add(ValoresInstanciaAsignacionTrabajos.duracion[tarea]);

		for (int i = 0; i < duracionesDiferentes.size(); i++)
			valoresEDD.add(new EDD_sum(estado, duracionesDiferentes.get(0), duracionesDiferentes.get(i)).g()
					+ new EDD_all(estado, duracionesDiferentes.get(i)).g());
		return valoresEDD.stream().mapToInt(i -> i).max().getAsInt();
	}
}