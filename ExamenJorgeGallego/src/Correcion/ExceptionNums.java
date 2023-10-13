package Correcion;

import java.io.BufferedReader;
import java.io.IOException;

public class ExceptionNums extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExceptionNums(String mensaje) {
		super(mensaje);
	}

	public ExceptionNums(BufferedReader br) throws ExceptionNums {
		String linea;
		try {
			if ((linea = br.readLine()) == null) {
				throw new ExceptionNums("El archivo está vacío de contenido");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ExceptionNums e) {
			e.printStackTrace();
		}
	}
}

