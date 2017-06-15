package laberinto;

import java.io.File;
import java.util.Scanner;

import framework.Estado;
import framework.basic.ParserBasic;

public class ParserLaberinto extends ParserBasic {

	@Override
	public Estado run(String fichero) {
		Scanner kbd;
		int[][] tablero = null;
		try {
			kbd = new Scanner(new File(fichero));
			ValoresInstanciaLaberinto.tamañoTablero = Integer.parseInt(kbd.nextLine().split(":")[1]);

			tablero = new int[ValoresInstanciaLaberinto.tamañoTablero][ValoresInstanciaLaberinto.tamañoTablero];
			int nP = 0;
			while (kbd.hasNext()) {
				int x = nP / ValoresInstanciaLaberinto.tamañoTablero;
				int y = nP % ValoresInstanciaLaberinto.tamañoTablero;
				int l = (int) kbd.nextInt();
				tablero[x][y] = l;
				nP++;
				if (l == 2) {
					ValoresInstanciaLaberinto.posicionInicialX = x;
					ValoresInstanciaLaberinto.posicionInicialY = y;
				}
				if (l == 3) {
					ValoresInstanciaLaberinto.posicionFinalX = x;
					ValoresInstanciaLaberinto.posicionFinalY = y;
				}
			}
			kbd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ValoresInstanciaLaberinto.tableroInicial = new int[tablero.length][tablero.length];
		for (int i = 0; i < tablero.length; i++)
			for (int j = 0; j < tablero.length; j++)
				ValoresInstanciaLaberinto.tableroInicial[i][j] = tablero[i][j];
		return new EstadoLaberinto(tablero);
	}
}
