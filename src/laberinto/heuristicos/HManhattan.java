package laberinto.heuristicos;

import framework.Estado;
import framework.Heuristico;
import laberinto.EstadoLaberinto;
import laberinto.ValoresInstanciaLaberinto;

public class HManhattan implements Heuristico {

	@Override
	public int calcula(Estado nodo) {
		return valorHeuristico((EstadoLaberinto) nodo);
	}

	protected int valorHeuristico(EstadoLaberinto estado) {
		return (Math.abs(ValoresInstanciaLaberinto.posicionFinalX - estado.x)
				+ Math.abs(ValoresInstanciaLaberinto.posicionFinalY - estado.y));
	}

	@Override
	public String toString() {
		return this.getClass().getName().replaceAll("nqueen.heuristicos.", "");
	}
}