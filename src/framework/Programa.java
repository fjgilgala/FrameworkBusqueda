package framework;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import framework.algoritmosbusqueda.AlgoritmoBusqueda;
import framework.conf.Configuracion;
import framework.util.Writer;

/**
 * Punto de entrada al programa. Se encarga de leer e interpretar el archivo
 * conf y de hacer todas las inicializaciones necesarias.
 * 
 * @author fran
 *
 */
public class Programa {

	public static void main(String[] args) {
		loadProperties();
		Writer.genera(cargarInstancias());
	}

	/**
	 * Se encarga de leer el archivo de propiedades
	 */
	private static void loadProperties() {
		Scanner kbd;
		try {
			kbd = new Scanner(new File("conf"));

			Configuracion.problema = kbd.nextLine().split(":")[1];
			String paquete_heuristicos = kbd.nextLine().split(":")[1];
			String parser = kbd.nextLine().split(":")[1];

			Configuracion.parser = (Parser) Class.forName(parser).newInstance();

			Configuracion.algoritmo = (AlgoritmoBusqueda) Class.forName(
					"framework.algoritmosbusqueda.implementaciones." + kbd.nextLine().split(":")[1].replaceAll(" ", ""))
					.newInstance();

			Configuracion.abierta = (ABIERTA) Class
					.forName("framework.abierta.implementaciones." + kbd.nextLine().split(":")[1].replaceAll(" ", ""))
					.newInstance();

			String[] h = kbd.nextLine().split(":")[1].replaceAll(" ", "").split(",");
			Configuracion.heuristicos = new ArrayList<Heuristico>();
			for (int i = 0; i < h.length; i++)
				Configuracion.heuristicos
						.add((Heuristico) Class.forName(paquete_heuristicos + "." + h[i]).newInstance());

			Configuracion.profundidadMaxima = Integer.parseInt(kbd.nextLine().split(":")[1].replaceAll(" ", ""));
			Configuracion.frecuenciaLookAHead = Integer.parseInt(kbd.nextLine().split(":")[1].replaceAll(" ", ""));
			Configuracion.timeout = Integer.parseInt(kbd.nextLine().split(":")[1].replaceAll(" ", ""));
			Configuracion.trazable = Boolean.parseBoolean(kbd.nextLine().split(":")[1].replaceAll(" ", ""));

			kbd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String sDirectorio = "instancias/";

	private static List<String> cargarInstancias() {
		List<String> nombres = new ArrayList<String>();
		File directorio = new File(sDirectorio);

		if (directorio.isDirectory()) {
			File[] ficheros = directorio.listFiles();
			for (File fichero : ficheros) {
				nombres.add(sDirectorio + fichero.getName());
			}
		}
		return nombres;
	}

}
