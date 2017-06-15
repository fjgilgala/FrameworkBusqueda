package asignaciontrabajos.edd.adaptados.busqueda;

import java.util.ArrayList;

import asignaciontrabajos.busqueda.EstadoAsignacionTrabajosMaquina;
import asignaciontrabajos.edd.EDD;
import asignaciontrabajos.util.Intervalo;
import asignaciontrabajos.util.OrdenarTareas;

/**
 * Earliest Due Date first adaptado para calcular soluciones a partir de un
 * estado
 */
public class EDDalgoritmosBusqueda extends EDD {

	public EDDalgoritmosBusqueda(EstadoAsignacionTrabajosMaquina estado) {
		this.sin_asignar = new ArrayList<Integer>(estado.getSin_asignar().size());
		for (Integer i : estado.getSin_asignar())
			sin_asignar.add(i);
		this.sti = estado.getSti().clone();
		this.intervalos = new ArrayList<Intervalo>(estado.getIntervalos().size());
		for (Intervalo i : estado.getIntervalos())
			intervalos.add(i);
		tareasOrdenadas = OrdenarTareas.ordenaTodasLasTareasPorDueDate();
		algoritmo();
	}
}