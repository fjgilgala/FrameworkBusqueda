package caballoajedrez;

import java.util.ArrayList;
import java.util.List;

import framework.Estado;
import framework.basic.EstadoBasic;
import framework.util.Metrica;

public class EstadoAjedrezCaballo extends EstadoBasic {
	public int dim;
	public int[][] tablero;
	public int x; // posicion x en el tablero
	public int y; // posicion y en el tablero

	public EstadoAjedrezCaballo(int[][] tablero) {
		this.tablero = tablero;
	}

	public EstadoAjedrezCaballo(int[][] tablero, int profundidad, int x, int y) {
		this.tablero = tablero.clone();
		this.profundidad = profundidad;
		this.g = profundidad;
		this.x = x;
		this.y = y;
		this.tablero[x][y] = 2;
		calculaHeuristico();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero.length; j++)
				sb.append(tablero[i][j]);
			sb.append("\n");
		}
		return sb.toString();
	}

	public List<Estado> expandir() {
		ArrayList<Estado> resultado = new ArrayList<Estado>();
		for (int i = 0; i < ValoresInstanciaCaballoAjedrez.movimientoX.length; i++) {
			try {
				resultado.add(new EstadoAjedrezCaballo(tablero, getProfundidad() + 1,
						ValoresInstanciaCaballoAjedrez.movimientoX[i], ValoresInstanciaCaballoAjedrez.movimientoY[i]));
				Metrica.genera();
			} catch (Exception e) {
			}
		}
		return resultado;
	}

	@Override
	public void generaEstadoInicial() {
	}

	@Override
	public boolean esSolucion() {
		return profundidad == dim * dim + 1;
	}
}
