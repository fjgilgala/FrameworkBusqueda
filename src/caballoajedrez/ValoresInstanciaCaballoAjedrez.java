package caballoajedrez;

import framework.basic.ValoresInstanciaBasic;

public class ValoresInstanciaCaballoAjedrez extends ValoresInstanciaBasic {
	public static int tamañoTablero, posicionInicialX, posicionInicialY;
	public static int[] movimientoX = { 1, 2, 2, 1 - 1, -2, -2, -1 };
	public static int[] movimientoY = { 2, 1, -1, -2, -2, -1, 1, 2 };
}