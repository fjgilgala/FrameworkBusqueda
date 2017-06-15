package asignaciontrabajos.edd.adaptados.heuristicos;

import java.util.ArrayList;

import asignaciontrabajos.busqueda.EstadoAsignacionTrabajosMaquina;
import asignaciontrabajos.edd.EDD;
import asignaciontrabajos.util.Intervalo;
import asignaciontrabajos.util.ValoresInstanciaAsignacionTrabajos;

/**
 * earliest due date first adaptado para calcular el valor heuristico
 */
abstract class EDDadaptadoHeuristicos extends EDD {

	protected int tiempoProcesamiento;

	public EDDadaptadoHeuristicos(EstadoAsignacionTrabajosMaquina estado) {
		this.sin_asignar = new ArrayList<Integer>(estado.getSin_asignar().size());
		for (Integer i : estado.getSin_asignar())
			sin_asignar.add(i);
		this.sti = estado.getSti().clone();
		this.intervalos = new ArrayList<Intervalo>(estado.getIntervalos().size());
		for (Intervalo i : estado.getIntervalos())
			intervalos.add(i);
	}

	protected int estimacionG(int sti, int tarea) {
		return Math.max(0, tiempoProcesamiento - (ValoresInstanciaAsignacionTrabajos.fechaVencimiento[tarea] - sti));
	}
}