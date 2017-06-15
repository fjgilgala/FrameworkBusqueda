package asignaciontrabajos.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import asignaciontrabajos.busqueda.EstadoAsignacionTrabajosMaquina;
import asignaciontrabajos.util.Intervalo;
import asignaciontrabajos.util.ValoresInstanciaAsignacionTrabajos;
import framework.Estado;
import framework.basic.ParserBasic;

public class ParserAsignacionTrabajosMaquina extends ParserBasic {

	@Override
	public Estado run(String fichero) {
		Scanner kbd;
		try {
			kbd = new Scanner(new File(fichero));
			boolean encontreUltimoIntervalo = false;
			int inicio, fin, capacidad, indice = 0;

			kbd.next(); // NOP:
			ValoresInstanciaAsignacionTrabajos.duracion = new int[Integer.parseInt(kbd.next())];
			ValoresInstanciaAsignacionTrabajos.fechaVencimiento = new int[ValoresInstanciaAsignacionTrabajos.duracion.length];
			kbd.next(); // NINT:
			ValoresInstanciaAsignacionTrabajos.perfilMaquina = new ArrayList<>(Integer.parseInt(kbd.next()));

			while (kbd.hasNext()) {
				if (!encontreUltimoIntervalo) { // intervalos
					inicio = Integer.parseInt(kbd.next());
					fin = Integer.parseInt(kbd.next());
					capacidad = Integer.parseInt(kbd.next());
					ValoresInstanciaAsignacionTrabajos.perfilMaquina.add(new Intervalo(capacidad, inicio, fin));
					if (fin == 300000)
						encontreUltimoIntervalo = true;
				} else { // tareas
					indice = Integer.parseInt(kbd.next());
					ValoresInstanciaAsignacionTrabajos.duracion[indice - 1] = Integer.parseInt(kbd.next());
					ValoresInstanciaAsignacionTrabajos.fechaVencimiento[indice - 1] = Integer.parseInt(kbd.next());
				}
			}
			kbd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new EstadoAsignacionTrabajosMaquina();
	}
}
