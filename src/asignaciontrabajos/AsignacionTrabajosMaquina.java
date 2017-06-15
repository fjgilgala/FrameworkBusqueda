package asignaciontrabajos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import asignaciontrabajos.util.Intervalo;
import asignaciontrabajos.util.OperacionesAsignacionTrabajosMaquina;
import asignaciontrabajos.util.ValoresInstanciaAsignacionTrabajos;
import framework.Problema;
import framework.util.Metrica;

public abstract class AsignacionTrabajosMaquina implements Problema {

	protected List<Integer> sin_asignar; // representan las tareas sin asignar
	protected int[] sti; // guarda los sti de cada tarea
	protected List<Intervalo> intervalos; // perfil de máquina
	protected int g; // coste

	public void generaEstadoInicial() {
		g = 0;
		sti = new int[ValoresInstanciaAsignacionTrabajos.duracion.length];
		sin_asignar = new ArrayList<Integer>(sti.length);
		for (int i = 0; i < sti.length; i++) { // no esta ninguno asignado
			sti[i] = -1;
			sin_asignar.add(i);
		}
		intervalos = new ArrayList<Intervalo>(ValoresInstanciaAsignacionTrabajos.perfilMaquina.size());
		for (int i = 0; i < ValoresInstanciaAsignacionTrabajos.perfilMaquina.size(); i++)
			intervalos.add(i, ValoresInstanciaAsignacionTrabajos.perfilMaquina.get(i));
	}

	public List<Integer> getSin_asignar() {
		return sin_asignar;
	}

	public int[] getSti() {
		return sti;
	}

	public String resultado() {
		return Arrays.toString(getSti());
	}

	public List<Intervalo> getIntervalos() {
		return intervalos;
	}

	public int g() {
		return g;
	}

	public void resultados() {
		OperacionesAsignacionTrabajosMaquina.sacaIntervalos();
	}

	@Override
	public String toString() {
		return "coste: " + g + " sti:" + Arrays.toString(sti);
	}

	@Override
	public boolean equals(Object obj) {
		AsignacionTrabajosMaquina aux = (AsignacionTrabajosMaquina) obj;
		for (int i = 0; i < sti.length; i++) {
			if (sti[i] != aux.getSti()[i])
				return false;
		}
		Metrica.simetriaDetectada();
		return true;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(sti);
	}
}