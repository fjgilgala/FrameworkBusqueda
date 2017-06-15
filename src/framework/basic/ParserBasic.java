package framework.basic;

import framework.Estado;
import framework.Heuristico;
import framework.Parser;
import framework.util.HeuristicoActivo;

public abstract class ParserBasic implements Parser {

	@Override
	public Estado run(String fichero, Heuristico heuristico) {
		HeuristicoActivo.heuristico = heuristico;
		return run(fichero);
	}

	/**
	 * Método de interpretar el fichero y de generar el estado inicial
	 * 
	 * @param fichero
	 * @return Estado inicial
	 */
	public abstract Estado run(String fichero);

}
