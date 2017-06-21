package laberinto.heuristicos;

import framework.Estado;
import framework.basic.Hbasic;
import laberinto.EstadoLaberinto;
import laberinto.ValoresInstanciaLaberinto;

public class Hmanhattan extends Hbasic {

	@Override
	public int calcula(Estado nodo) {
		return valorHeuristico((EstadoLaberinto) nodo);
	}

	protected int valorHeuristico(EstadoLaberinto estado) {
		return (Math.abs(ValoresInstanciaLaberinto.posicionFinalX - estado.x)
				+ Math.abs(ValoresInstanciaLaberinto.posicionFinalY - estado.y));
	}
}