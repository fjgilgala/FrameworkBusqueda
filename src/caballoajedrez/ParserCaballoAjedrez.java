package caballoajedrez;

import java.io.File;
import java.util.Scanner;

import framework.Estado;
import framework.basic.ParserBasic;

public class ParserCaballoAjedrez extends ParserBasic {

	@Override
	public Estado run(String fichero) {
		Scanner kbd;
		int[][] tablero = null;
		try {
			kbd = new Scanner(new File(fichero));
			ValoresInstanciaCaballoAjedrez.tamañoTablero = Integer.parseInt(kbd.nextLine().split(":")[1]);
			ValoresInstanciaCaballoAjedrez.posicionInicialX = Integer.parseInt(kbd.nextLine().split(":")[1]);
			ValoresInstanciaCaballoAjedrez.posicionInicialY = Integer.parseInt(kbd.nextLine().split(":")[1]);

			tablero = new int[ValoresInstanciaCaballoAjedrez.tamañoTablero][ValoresInstanciaCaballoAjedrez.tamañoTablero];
			tablero[ValoresInstanciaCaballoAjedrez.posicionInicialX][ValoresInstanciaCaballoAjedrez.posicionInicialY] = 1;

			kbd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ValoresInstanciaCaballoAjedrez.tableroInicial = ValoresInstanciaCaballoAjedrez.clona(tablero);
		return new EstadoCaballoAjedrez(tablero);
	}
}
