package swingy.model.hero;


public class WrongClassException extends Exception {
	public WrongClassException() {
		super();
	}
	public WrongClassException( String msg) {
		super(msg);
	}
}