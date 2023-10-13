package Examen;
public class MyException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyException(String string) {
		super();
		}
	
		public MyException(boolean mensaje) throws MyException {
			// TODO Auto-generated constructor stub
			if (!mensaje) {
				throw new MyException("El fichero se encuentra vacio");
				}
		}
}


