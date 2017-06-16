package caballoajedrez;

import java.util.ArrayList;
import java.util.List;

import framework.Estado;
import framework.basic.EstadoBasic;
import framework.util.Metrica;

public class EstadoCaballoAjedrez extends EstadoBasic {
	public int dim = ValoresInstanciaCaballoAjedrez.tamañoTablero;
	public int[][] tablero;
	public int x; // posicion x en el tablero
	public int y; // posicion y en el tablero

	public EstadoCaballoAjedrez papi;

	public EstadoCaballoAjedrez(int[][] tablero) {
		this.tablero = tablero;
	}

	public EstadoCaballoAjedrez(int[][] tablero, int profundidad, int x, int y, EstadoCaballoAjedrez padre) {
		this.tablero = tablero.clone();
		this.profundidad = profundidad;
		this.g = profundidad;
		this.x = x;
		this.y = y;
		this.tablero[x][y] = 2;
		calculaHeuristico();
		this.papi = padre;
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

		if ((x + 1) < dim && (y + 2) < dim && tablero[x + 1][y + 2] == 0)
			resultado.add(new EstadoCaballoAjedrez(ValoresInstanciaCaballoAjedrez.clona(tablero), getProfundidad() + 1,
					x + 1, y + 2, this));
		if ((x + 2) < dim && (y + 1) < dim && tablero[x + 2][y + 1] == 0)
			resultado.add(new EstadoCaballoAjedrez(ValoresInstanciaCaballoAjedrez.clona(tablero), getProfundidad() + 1,
					x + 2, y + 1, this));
		if ((x + 2) < dim && (y - 1) >= 0 && tablero[x + 2][y - 1] == 0)
			resultado.add(new EstadoCaballoAjedrez(ValoresInstanciaCaballoAjedrez.clona(tablero), getProfundidad() + 1,
					x + 2, y - 1, this));
		if ((x + 1) < dim && (y - 2) >= 0 && tablero[x + 1][y - 2] == 0)
			resultado.add(new EstadoCaballoAjedrez(ValoresInstanciaCaballoAjedrez.clona(tablero), getProfundidad() + 1,
					x + 1, y - 2, this));
		if ((x - 1) >= 0 && (y - 2) >= 0 && tablero[x - 1][y - 2] == 0)
			resultado.add(new EstadoCaballoAjedrez(ValoresInstanciaCaballoAjedrez.clona(tablero), getProfundidad() + 1,
					x - 1, y - 2, this));
		if ((x - 2) >= 0 && (y - 1) >= 0 && tablero[x - 2][y - 1] == 0)
			resultado.add(new EstadoCaballoAjedrez(ValoresInstanciaCaballoAjedrez.clona(tablero), getProfundidad() + 1,
					x - 2, y - 1, this));
		if ((x - 2) >= 0 && (y + 1) < dim && tablero[x - 1][y + 1] == 0)
			resultado.add(new EstadoCaballoAjedrez(ValoresInstanciaCaballoAjedrez.clona(tablero), getProfundidad() + 1,
					x - 2, y + 1, this));
		if ((x - 1) >= 0 && (y + 2) < dim && tablero[x - 1][y + 2] == 0)
			resultado.add(new EstadoCaballoAjedrez(ValoresInstanciaCaballoAjedrez.clona(tablero), getProfundidad() + 1,
					x - 1, y + 2, this));

		for (int i = 0; i < resultado.size(); i++)
			Metrica.genera();

		return resultado;
	}

	@Override
	public boolean esSolucion() {
		if (profundidad == dim * dim) {
			componerTableroSolucion();
			return true;
		}
		return false;
	}

	private void componerTableroSolucion() {
		tablero = ValoresInstanciaCaballoAjedrez.tableroInicial.clone();
		EstadoCaballoAjedrez padre = this;
		int i = dim * dim;
		while (padre != null) {
			tablero[padre.x][padre.y] = i;
			i--;
			padre = padre.papi;
		}
	}
}
