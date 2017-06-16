package laberinto.heuristicos;

import framework.Estado;
import framework.Heuristico;
import laberinto.EstadoLaberinto;

public class HNull implements Heuristico {

	@Override
	public int calcula(Estado nodo) {
		return valorHeuristico((EstadoLaberinto) nodo);
	}

	protected int valorHeuristico(EstadoLaberinto estado) {
		return 0;
	}

	@Override
	public String toString() {
		return this.getClass().getName().replaceAll("nqueen.heuristicos.", "");
	}
}