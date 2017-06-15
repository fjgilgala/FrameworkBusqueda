package framework.util;

import framework.ABIERTA;
import framework.Estado;

public class Metrica {

	public static double inicio, fin, finCotaSuperior;
	public static int expandidos, generados, simetrias, cotaSuperior, cotaInferiorFinal, cotaInferiorInicial;

	public static Estado solucion;
	public static boolean OutTime = false;
	public static boolean OutMemory = false;

	public static String instancia;

	public static void expande() {
		expandidos++;
	}

	public static void genera() {
		generados++;
	}

	public static void simetriaDetectada() {
		simetrias++;
	}

	public static void reinicia() {
		inicio = System.currentTimeMillis();
		expandidos = generados = 0;
		cotaSuperior = Integer.MAX_VALUE;
		cotaInferiorFinal = cotaInferiorInicial = Integer.MIN_VALUE;
		solucion = null;
		OutMemory = OutTime = false;
		simetrias = 0;
	}

	public static void para() {
		fin = System.currentTimeMillis();
	}

	public static double tiempo() {
		return (fin - inicio) / 1000;
	}

	public static double tiempoEncontrarCotaSuperior() {
		return (finCotaSuperior - inicio) / 1000;
	}

	/**
	 * LB inicial
	 */
	public static void renuevaCotaInferiorInicial(int nueva) {
		cotaInferiorInicial = nueva;
	}

	/**
	 * LB final
	 */
	public static void renuevaCotaInferiorFinal(ABIERTA ABIERTA) {
		if (solucion != null)
			if (solucion.g() > cotaInferiorFinal)
				cotaInferiorFinal = solucion.g();
		if (!ABIERTA.vacia())
			cotaInferiorFinal = ABIERTA.estimacionMejor();
	}

	/**
	 * VB
	 */
	public static void renuevaCotaSuperior(Estado nueva) {
		if (nueva != null)
			if (nueva.f() < cotaSuperior) {
				cotaSuperior = nueva.f();
				solucion = nueva;
				finCotaSuperior = System.currentTimeMillis();
			}
	}

	public static void renuevaCotaSuperiorFinal(Estado nueva) {
		cotaSuperior = nueva.f();
	}

	public static Estado getSolucion() {
		return solucion;
	}

}