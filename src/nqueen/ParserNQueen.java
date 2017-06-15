package nqueen;

import java.io.File;
import java.util.Scanner;

import framework.Estado;
import framework.basic.ParserBasic;

public class ParserNQueen extends ParserBasic {

	@Override
	public Estado run(String fichero) {
		Scanner kbd;
		int n = 1;
		try {
			kbd = new Scanner(new File(fichero));
			String value = kbd.nextLine().split(":")[1];
			n = Integer.parseInt(value);
			kbd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new EstadoReinas(n);
	}
}