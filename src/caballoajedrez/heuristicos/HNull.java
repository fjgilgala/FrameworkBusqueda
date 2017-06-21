package caballoajedrez.heuristicos;

import caballoajedrez.EstadoCaballoAjedrez;
import framework.Estado;
import framework.Heuristico;

public class Hnull implements Heuristico {

	@Override
	public int calcula(Estado nodo) {
		return valorHeuristico((EstadoCaballoAjedrez) nodo);
	}

	protected int valorHeuristico(EstadoCaballoAjedrez estado) {
		return 0;
	}

	@Override
	public String toString() {
		return this.getClass().getName().replaceAll("caballoajedrez.heuristicos.", "");
	}
}