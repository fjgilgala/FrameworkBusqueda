package framework.util;

import java.util.Calendar;

import framework.ABIERTA;
import framework.Estado;
import framework.Heuristico;
import framework.algoritmosbusqueda.AlgoritmoBusqueda;
import framework.conf.Configuracion;

public class EjecutorInstancia {

	public static Estado ejecuta(String instancia, AlgoritmoBusqueda algoritmo, ABIERTA abierta,
			Heuristico heuristico) {

		if (Configuracion.trazable) {
			Calendar cal1 = Calendar.getInstance();
			System.out.println("-> Ejecutando " + instancia.replaceFirst("instancias/", "").replaceFirst(".txt", "")
					+ " con " + algoritmo + " y " + heuristico + " ["
					+ (cal1.get(Calendar.HOUR) + ":" + cal1.get(Calendar.MINUTE) + ":" + cal1.get(Calendar.SECOND))
					+ "]");
		}

		Estado estado = algoritmo.apply(abierta, Configuracion.parser.run(instancia, heuristico), heuristico);

		if (Configuracion.trazable)
			sacaTraza(algoritmo, heuristico, estado);

		return estado;
	}

	public static void sacaTraza(AlgoritmoBusqueda algoritmo, Heuristico h, Estado estado) {
		System.out.println("-> Resultados con " + algoritmo + " y " + h);
		if (estado != null) {
			if (Metrica.tiempoEncontrarCotaSuperior() > 0)
				System.out.println("--> Solucion:" + estado.resultado() + " en " + Metrica.tiempoEncontrarCotaSuperior()
						+ " segundos");
			else
				System.out.println("--> Solucion:\n" + estado.resultado() + " en " + Metrica.tiempo() + " segundos");
			System.out.println("--> Coste solucion:" + estado.g());
			System.out.println("--> Tiempo:" + Metrica.tiempo());
			System.out.println("--> Coste cota inferior final:" + Metrica.cotaInferiorFinal);
			System.out.println("--> Coste cota inferior inicial:" + Metrica.cotaInferiorInicial);
		} else
			System.out.println("--> Solución no encontrada");

		System.out.println("--> Nodos expandidos:" + Metrica.expandidos);
		System.out.println("--> Nodos generados:" + Metrica.generados);
		System.out.println("--> Simetrias:" + Metrica.simetrias);

		if (Metrica.OutTime) {
			if (estado != null)
				System.out
						.println("****La instancia no se ejecutó del todo, excedia el timeout (" + Configuracion.timeout
								+ ") Se muestra una solucion no optima dada por la cota superior encontrada en "
								+ Metrica.tiempoEncontrarCotaSuperior() + " segundos****");
			else
				System.out.println("****La instancia no se ejecutó del todo, excedia el timeout ("
						+ Configuracion.timeout + ") No se muestra ninguna una solucion con " + algoritmo + " ****");
		}
		if (Metrica.OutMemory) {
			if (estado != null)
				System.out.println(
						"****La instancia no se ejecutó del todo, desbordaba la memoria (En " + Metrica.tiempo()
								+ " segundos) Se muestra una solucion no optima dada por la cota superior encontrada en "
								+ Metrica.tiempoEncontrarCotaSuperior() + " segundos****");
			else
				System.out.println(
						"****La instancia no se ejecutó del todo, desbordaba la memoria (En " + Metrica.tiempo()
								+ " segundos) No se muestra ninguna una solucion con " + algoritmo + " ****");
		}
	}
}