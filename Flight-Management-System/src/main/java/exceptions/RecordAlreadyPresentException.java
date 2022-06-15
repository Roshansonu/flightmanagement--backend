package exceptions;

public class RecordAlreadyPresentException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecordAlreadyPresentException(String s) {
		super(s);
	}
}
