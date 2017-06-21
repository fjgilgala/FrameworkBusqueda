package sudoku;

import java.util.ArrayList;
import java.util.List;

import framework.Estado;
import framework.basic.EstadoBasic;
import framework.util.Metrica;

public class EstadoSudoku extends EstadoBasic {
	public int[][] tablero;
	public int posicion;
	private final int n = 9;

	public EstadoSudoku papi;

	public EstadoSudoku(int[][] tablero) {
		this.tablero = tablero;
		profundidad = 0;
		posicion = buscarCero(0);
		calculaHeuristico();
	}

	public EstadoSudoku(int[][] tablero, int posicion, int k, int profundidad, EstadoSudoku papi) {
		this.tablero = ValoresInstanciaSudoku.clona(tablero);
		this.profundidad = profundidad;
		this.g = profundidad;
		this.posicion = posicion;
		this.tablero[posicion / n][posicion % n] = k;
		this.papi = papi;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++)
				sb.append(" " + tablero[i][j]);
			sb.append("\n");
		}
		return sb.toString();
	}

	public List<Estado> expandir() {
		ArrayList<Estado> resultado = new ArrayList<Estado>();
		int posicionNueva = buscarCero(posicion);
		for (int k = 1; k <= n; k++) {
			if (fila(posicionNueva / n, k) && columna(posicionNueva % n, k)
					&& region(posicionNueva / n, posicionNueva % n, k)) {
				Metrica.genera();
				resultado.add(new EstadoSudoku(tablero, posicionNueva, k, getProfundidad() + 1, papi));
			}
		}
		return resultado;
	}

	int buscarCero(int posicion) {
		for (int i = posicion; i < n * n; i++)
			if (tablero[i / n][i % n] == 0)
				return i;
		return -1;
	}

	boolean fila(int x, int k) {
		boolean b = true;
		for (int i = 0; i < n; i++)
			if (tablero[x][i] == k)
				b = false;
		return b;
	}

	boolean columna(int y, int k) {
		boolean b = true;
		for (int i = 0; i < n; i++)
			if (tablero[i][y] == k)
				b = false;
		return b;
	}

	boolean region(int x, int y, int k) {
		boolean b = true;
		int bx = x - x % 3;
		int by = y - y % 3;
		for (int i = bx; i < bx + 3; i++)
			for (int j = by; j < by + 3; j++)
				if (tablero[i][j] == k)
					b = false;
		return b;
	}

	@Override
	public boolean esSolucion() {
		return (posicion == (n * n) - 1);
	}
}
