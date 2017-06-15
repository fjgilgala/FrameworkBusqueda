package asignaciontrabajos.busqueda;

import java.util.ArrayList;
import java.util.List;

import asignaciontrabajos.AsignacionTrabajosMaquina;
import asignaciontrabajos.edd.adaptados.busqueda.EDDalgoritmosBusqueda;
import asignaciontrabajos.util.Intervalo;
import asignaciontrabajos.util.OperacionesAsignacionTrabajosMaquina;
import asignaciontrabajos.util.ValoresInstanciaAsignacionTrabajos;
import framework.Estado;
import framework.util.Metrica;

public class EstadoAsignacionTrabajosMaquina extends AsignacionTrabajosMaquina implements Cloneable, Estado {

	// estructuras propias de aplicar un algoritmo de búsqueda
	protected int profundidad;
	protected int h;

	public EstadoAsignacionTrabajosMaquina(List<Integer> sin_asignar, int[] sti, int profundidad, int coste,
			List<Intervalo> intervalos) {
		this.sti = sti;
		this.sin_asignar = sin_asignar;
		this.g = coste;
		this.profundidad = profundidad;
		this.intervalos = intervalos;
		this.h = calcularValorHeuristico();
	}

	/**
	 * Crea una copia de los atributos del estado
	 * 
	 * @param clon
	 */
	public EstadoAsignacionTrabajosMaquina(EstadoAsignacionTrabajosMaquina clon) {
		this.sti = clon.getSti().clone();
		this.sin_asignar = new ArrayList<Integer>(clon.getSin_asignar().size());
		for (Integer i : clon.getSin_asignar())
			sin_asignar.add(i);
		this.g = clon.g();
		this.profundidad = clon.profundidad;
		this.intervalos = new ArrayList<Intervalo>(clon.getIntervalos().size());
		for (Intervalo i : clon.getIntervalos())
			intervalos.add(i);
		this.h = clon.h();
	}

	@Override
	public EstadoAsignacionTrabajosMaquina clone() {
		return new EstadoAsignacionTrabajosMaquina(this);
	}

	public EstadoAsignacionTrabajosMaquina(AsignacionTrabajosMaquina solucion) {
		this.sti = solucion.getSti();
		this.sin_asignar = solucion.getSin_asignar();
		this.g = solucion.g();
		this.profundidad = sti.length - 1;
		this.intervalos = solucion.getIntervalos();
		this.h = calcularValorHeuristico();
	}

	public EstadoAsignacionTrabajosMaquina() {
		generaEstadoInicial();
	}

	protected int calcularValorHeuristico() {
		return ValoresInstanciaAsignacionTrabajos.heuristico.calcula(this);
	}

	public int f() {
		return g + h;
	}

	public int h() {
		return h;
	}

	public boolean esSolucion() {
		return sti.length == profundidad;
	}

	public List<Estado> expandir() {
		List<Estado> sucesores = new ArrayList<Estado>();
		for (Integer tarea : OperacionesAsignacionTrabajosMaquina.getSinAsignar(sin_asignar, sti))
			for (Intervalo intervalo : intervalos)
				if (intervalo.libre() && OperacionesAsignacionTrabajosMaquina.propaga(
						OperacionesAsignacionTrabajosMaquina.creaIntervalo(intervalo),
						ValoresInstanciaAsignacionTrabajos.duracion[tarea],
						OperacionesAsignacionTrabajosMaquina.copiaIntervalos(intervalos))) {
					Metrica.genera();
					sucesores.add(new EstadoAsignacionTrabajosMaquina(
							OperacionesAsignacionTrabajosMaquina.getCopiaSinAsignar(tarea, sin_asignar),
							OperacionesAsignacionTrabajosMaquina.copiaSti(tarea, intervalo.inicio, sti),
							profundidad + 1,
							g + OperacionesAsignacionTrabajosMaquina.estimacionG(intervalo.inicio, tarea),
							OperacionesAsignacionTrabajosMaquina.getIntervalos()));
					break;
				}
		return sucesores;
	}

	@Override
	public Estado solucionVoraz() {
		return new EstadoAsignacionTrabajosMaquina(new EDDalgoritmosBusqueda(this));
	}

	@Override
	public int compareTo(Estado nodo2) {
		if (f() > nodo2.f())
			return 1;
		else if (f() == nodo2.f()) {
			if (g > nodo2.g())
				return 1;
			else if (g == nodo2.g()) {
				if (equals(nodo2))
					return 0;
				return -1;
			}
			return -1;
		}
		return -1;
	}

	public int getProfundidad() {
		return profundidad;
	}

	@Override
	public int getExpandidosMaximo() {
		return (getSti().length - getProfundidad()) * 2;
	}
}
