package laberinto.heuristicos;

import framework.Estado;
import framework.basic.Hbasic;
import laberinto.EstadoLaberinto;

public class Hnull extends Hbasic {

	@Override
	public int calcula(Estado nodo) {
		return valorHeuristico((EstadoLaberinto) nodo);
	}

	protected int valorHeuristico(EstadoLaberinto estado) {
		return 0;
	}
}