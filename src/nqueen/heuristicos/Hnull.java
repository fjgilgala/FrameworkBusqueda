package nqueen.heuristicos;

import framework.Estado;
import framework.basic.Hbasic;
import nqueen.EstadoReinas;

public class Hnull extends Hbasic {

	@Override
	public int calcula(Estado nodo) {
		return valorHeuristico((EstadoReinas) nodo);
	}

	protected int valorHeuristico(EstadoReinas estado) {
		return 0;
	}
}