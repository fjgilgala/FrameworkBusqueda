package laberinto;

import java.util.ArrayList;
import java.util.List;

import framework.Estado;
import framework.basic.EstadoBasic;
import framework.util.Metrica;

public class EstadoLaberinto extends EstadoBasic {
	private int dim = ValoresInstanciaLaberinto.tamañoTablero;
	public int[][] tablero;
	public int x; // posicion x en el tablero
	public int y; // posicion y en el tablero

	public EstadoLaberinto papi;

	public EstadoLaberinto(int[][] tablero) {
		this.tablero = tablero;
		profundidad = 0;
		dim = tablero.length + 1;
		x = ValoresInstanciaLaberinto.posicionInicialX;
		y = ValoresInstanciaLaberinto.posicionInicialY;
		calculaHeuristico();
	}

	public EstadoLaberinto(int[][] tablero, int profundidad, int x, int y, EstadoLaberinto papi) {
		this.tablero = tablero.clone();
		this.profundidad = profundidad;
		this.g = profundidad;
		this.x = x;
		this.y = y;
		this.tablero[x][y] = 2;
		this.papi = papi;
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

		// si puedo arriba
		if ((y - 1) >= 0 && tablero[x][y - 1] != 1) {
			Metrica.genera();
			resultado.add(new EstadoLaberinto(tablero, getProfundidad() + 1, x, y - 1, this));
		}
		// si puedo abajo
		if ((y + 1 < dim) && tablero[x][y + 1] != 1) {
			Metrica.genera();
			resultado.add(new EstadoLaberinto(tablero, getProfundidad() + 1, x, y + 1, this));
		}
		// si puedo a la derecha
		if ((x + 1 < dim) && tablero[x + 1][y] != 1) {
			Metrica.genera();
			resultado.add(new EstadoLaberinto(tablero, getProfundidad() + 1, x + 1, y, this));
		}
		// si puedo a la izquierda
		if ((x - 1) >= 0 && tablero[x - 1][y] != 1) {
			Metrica.genera();
			resultado.add(new EstadoLaberinto(tablero, getProfundidad() + 1, x - 1, y, this));
		}
		return resultado;
	}

	@Override
	public boolean esSolucion() {
		if (Math.abs(ValoresInstanciaLaberinto.posicionFinalX - x)
				+ Math.abs(ValoresInstanciaLaberinto.posicionFinalY - y) == 0) {
			componerTableroSolucion();
			return true;
		}
		return false;
	}

	private void componerTableroSolucion() {
		tablero = ValoresInstanciaLaberinto.tableroInicial.clone();
		EstadoLaberinto padre = this;
		while (padre != null) {
			tablero[padre.x][padre.y] = 2;
			padre = padre.papi;
		}
	}
}
