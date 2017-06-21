package framework.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import framework.ABIERTA;
import framework.Estado;
import framework.Heuristico;
import framework.algoritmosbusqueda.AlgoritmoBusqueda;
import framework.conf.Configuracion;

public class Writer {

	private static String nombreInforme;
	private static String informe;

	private static BufferedWriter bw;

	private static String cuadroTiempo, cuadroEstadosRepetidos, cuadroExpandidos, cuadroGenerados, cuadroCotaInferior,
			cuadroSolucion, cuadroEncontrarSolucion;

	private static List<Heuristico> heuristicos = Configuracion.heuristicos;
	private static AlgoritmoBusqueda algoritmo = Configuracion.algoritmo;
	private static ABIERTA abierta = Configuracion.abierta;

	public static void genera(List<String> instancias) {
		long inicio = System.currentTimeMillis();
		String fecha = getFecha();

		try {

			nombreInforme = Configuracion.problema + " " + algoritmo + " " + abierta + " " + getFecha() + ".csv";
			informe = "Resolucion del problema " + Configuracion.problema + " con la configuracion:\n";
			informe += "Algoritmo;" + algoritmo + "\n";
			informe += "ABIERTA;" + abierta + "\n";

			File archivo = new File(nombreInforme);
			bw = new BufferedWriter(new FileWriter(archivo));
			bw.write(informe);

			cuadroExpandidos = cuadroGenerados = cuadroTiempo = cuadroCotaInferior = cuadroSolucion = cuadroEncontrarSolucion = " ";
			for (Heuristico h : heuristicos) {
				cuadroExpandidos += ";" + algoritmo + " " + h;
				cuadroEstadosRepetidos += ";" + algoritmo + " " + h;
				cuadroGenerados += ";" + algoritmo + " " + h;
				cuadroTiempo += ";" + algoritmo + " " + h;
				cuadroCotaInferior += ";" + algoritmo + " " + h;
				cuadroSolucion += ";" + algoritmo + " " + h;
				cuadroEncontrarSolucion += ";" + algoritmo + " " + h;
			}

			for (String instancia : instancias)
				ejecutaInstancia(instancia, heuristicos);

			bw.write("\n\n\n" + "Nodos expandidos\n" + cuadroExpandidos + "\n");
			bw.write("\n\n" + "Nodos generados\n" + cuadroGenerados + "\n");
			bw.write("\n\n" + "Estados Repetidos\n" + cuadroEstadosRepetidos + "\n");
			bw.write("\n\n" + "Tiempo\n" + cuadroTiempo + "\n");
			bw.write("\n\n" + "Tiempo en encontrar la solucion\n" + cuadroEncontrarSolucion + "\n");
			bw.write("\n\n" + "Cota inferior\n" + cuadroCotaInferior + "\n");
			bw.write("\n\n" + "Solucion\n" + cuadroSolucion + "\n");

		} catch (IOException e1) {
		} finally {
			try {
				bw.write("\n\nInforme empezado a las " + fecha + " y finalizado a las " + getFecha()
						+ " generado en aproximadamente " + ((System.currentTimeMillis() - inicio) / 1000 / 60)
						+ " minutos\n\n");
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static void ejecutaInstancia(String instancia, List<Heuristico> heuristicos) throws IOException {
		if (Configuracion.trazable)
			System.out.println(
					"Ejecutando:" + instancia.replaceFirst(Configuracion.sDirectorio, "").replaceFirst(".txt", ""));
		cuadroTiempo += "\n" + instancia.replaceFirst(Configuracion.sDirectorio, "").replaceFirst(".txt", "");
		cuadroExpandidos += "\n" + instancia.replaceFirst(Configuracion.sDirectorio, "").replaceFirst(".txt", "");
		cuadroGenerados += "\n" + instancia.replaceFirst(Configuracion.sDirectorio, "").replaceFirst(".txt", "");
		cuadroCotaInferior += "\n" + instancia.replaceFirst(Configuracion.sDirectorio, "").replaceFirst(".txt", "");
		cuadroSolucion += "\n" + instancia.replaceFirst(Configuracion.sDirectorio, "").replaceFirst(".txt", "");
		cuadroEncontrarSolucion += "\n"
				+ instancia.replaceFirst(Configuracion.sDirectorio, "").replaceFirst(".txt", "");
		cuadroEstadosRepetidos += "\n" + instancia.replaceFirst(Configuracion.sDirectorio, "").replaceFirst(".txt", "");
		bw.write(instancia.replaceFirst(Configuracion.sDirectorio, "").replaceFirst(".txt", "") + "\n"
				+ ";coste;tiempo;generados;expandidos;estados repetidos;cota inferior final;cota inferior inicial;resultado\n");
		for (Heuristico h : heuristicos)
			interpretaResultados(instancia, h);
	}

	private static void interpretaResultados(String instancia, Heuristico h) throws IOException {
		String resultados = "";
		Estado estado = EjecutorInstancia.ejecuta(instancia, algoritmo, abierta, h);
		Metrica.solucion = estado;

		if (estado != null) {
			resultados = HeuristicoActivo.heuristico + ";" + estado.g() + ";" + Metrica.tiempo() + ";"
					+ Metrica.generados + ";" + Metrica.expandidos + ";" + Metrica.simetrias + ";"
					+ Metrica.cotaInferiorFinal + ";" + Metrica.cotaInferiorInicial + ";" + estado.resultado();
			if (Metrica.tiempoEncontrarCotaSuperior() > 0)
				resultados += " en " + Metrica.tiempoEncontrarCotaSuperior() + " segundos";
			else
				resultados += " en " + Metrica.tiempo() + " segundos";
		} else
			resultados = HeuristicoActivo.heuristico + ";NO;Excede;" + Metrica.generados + ";" + Metrica.expandidos
					+ Metrica.simetrias + ";" + ";" + Metrica.cotaInferiorFinal + ";" + Metrica.cotaInferiorInicial;

		if (!Metrica.OutMemory && !Metrica.OutTime)
			resultados += "\n";
		else if (Metrica.OutTime) {
			if (estado != null)
				resultados = HeuristicoActivo.heuristico + ";" + estado.g() + ";Excede;" + Metrica.generados + ";"
						+ Metrica.expandidos + ";" + Metrica.simetrias + ";" + Metrica.cotaInferiorFinal + ";"
						+ Metrica.cotaInferiorInicial + ";" + estado.resultado() + ";"
						+ "Instancia no se ejecuto del todo, excedia el timeout (" + Configuracion.timeout
						+ ") Se muestra una solucion no optima dada por la cota superior encontrada en "
						+ Metrica.tiempoEncontrarCotaSuperior() + " segundos\n";
			else
				resultados += ";" + "Instancia no se ejecuto del todo, excedia el timeout (" + Configuracion.timeout
						+ ") No se muestra ninguna solucion con " + algoritmo + " \n";
		} else if (Metrica.OutMemory) {
			if (estado != null)
				resultados += ";" + "Instancia no se ejecuto del todo, desbordaba la memoria (En " + Metrica.tiempo()
						+ " segundos) Se muestra una solucion no optima dada por la cota superior encontrada en "
						+ Metrica.tiempoEncontrarCotaSuperior() + " segundos\n";
			else
				resultados += ";" + "Instancia no se ejecuto del todo, desbordaba la memoria (En " + Metrica.tiempo()
						+ " segundos) No se muestra ninguna solucion con " + algoritmo + " \n";
		}

		if (Metrica.OutTime)
			cuadroTiempo += ";Excede";
		else if (Metrica.OutMemory)
			cuadroTiempo += ";Memoria Desbordada (" + Metrica.tiempo() + ")";
		else
			cuadroTiempo += ";" + Metrica.tiempo();
		cuadroExpandidos += ";" + Metrica.expandidos;
		cuadroGenerados += ";" + Metrica.generados;
		cuadroCotaInferior += ";" + Metrica.cotaInferiorFinal;
		cuadroEstadosRepetidos += ";" + Metrica.simetrias;
		if (estado != null) {
			cuadroSolucion += ";" + Metrica.solucion.g();
			if (Metrica.tiempoEncontrarCotaSuperior() > 0)
				cuadroEncontrarSolucion += ";" + Metrica.tiempoEncontrarCotaSuperior();
			else
				cuadroEncontrarSolucion += ";" + Metrica.tiempo();
		} else {
			cuadroSolucion += ";NO";
			cuadroEncontrarSolucion += ";NO";
		}
		bw.write(resultados);
	}

	private static String getFecha() {
		Date fechaActual = new Date();
		DateFormat formatoHora = new SimpleDateFormat("HH-mm-ss");
		DateFormat formatoFecha = new SimpleDateFormat("dd-MM-yyyy");
		return formatoHora.format(fechaActual) + " " + formatoFecha.format(fechaActual);
	}
}
