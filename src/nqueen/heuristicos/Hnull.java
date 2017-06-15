package nqueen.heuristicos;

import framework.Estado;
import framework.Heuristico;
import nqueen.EstadoReinas;

public class Hnull implements Heuristico {

	@Override
	public int calcula(Estado nodo) {
		return valorHeuristico((EstadoReinas) nodo);
	}

	protected int valorHeuristico(EstadoReinas estado) {
		return 0;
	}
	
	@Override
	public String toString() {
		return this.getClass().getName().replaceAll("nqueen.heuristicos.", "");
	}
}