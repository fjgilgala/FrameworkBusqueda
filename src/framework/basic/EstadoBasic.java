package framework.basic;

import java.util.List;

import framework.Estado;
import framework.util.HeuristicoActivo;

public abstract class EstadoBasic implements Estado {

	public abstract List<Estado> expandir();

	public abstract boolean esSolucion();

	protected int profundidad;
	protected int h;
	protected int g;

	@Override
	public int f() {
		return g + h;
	}

	@Override
	public int h() {
		return h;
	}

	public void calculaHeuristico() {
		h = HeuristicoActivo.heuristico.calcula(this);
	}

	@Override
	public int compareTo(Estado nodo2) {
		if (f() > nodo2.f())
			return 1;
		else if (f() == nodo2.f())
			return 0;
		return -1;
	}

	@Override
	public String resultado() {
		return toString();
	}

	@Override
	public Estado clone() {
		return null;
	}

	@Override
	public Estado solucionVoraz() {
		return null;
	}

	@Override
	public void generaEstadoInicial() {
	}

	@Override
	public int getExpandidosMaximo() {
		return profundidad * 2;
	}

	@Override
	public int g() {
		return g;
	}

	public int getProfundidad() {
		return profundidad;
	}
}
