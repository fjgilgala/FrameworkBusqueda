package laberinto;

import framework.basic.ValoresInstanciaBasic;

public class ValoresInstanciaLaberinto extends ValoresInstanciaBasic {
	public static int tamañoTablero, posicionInicialX, posicionInicialY, posicionFinalX, posicionFinalY;
	public static int[][] tableroInicial;

	public static int[][] clona(int[][] tablero) {
		int[][] copia = new int[tablero.length][tablero.length];
		for (int i = 0; i < tablero.length; i++)
			for (int j = 0; j < tablero.length; j++)
				copia[i][j] = tablero[i][j];
		return copia;
	}

}