package sudoku;

import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

import framework.Estado;
import framework.basic.ParserBasic;

public class ParserSudoku extends ParserBasic {

	@Override
	public Estado run(String fichero) {
		Scanner kbd;
		int[][] tablero = new int[9][9];
		String linea = "";
		StringTokenizer palabras;
		try {
			kbd = new Scanner(new File(fichero));
			while (kbd.hasNext()) {
				for (int x = 0; x < 9; x++) {
					linea = kbd.nextLine();
					palabras = new StringTokenizer(linea);
					for (int y = 0; y < 9; y++)
						tablero[x][y] = Integer.parseInt(palabras.nextToken());
				}
			}
			kbd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ValoresInstanciaSudoku.tableroInicial = ValoresInstanciaSudoku.clona(tablero);
		return new EstadoSudoku(tablero);
	}
}
