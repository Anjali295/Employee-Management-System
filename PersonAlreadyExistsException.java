
public class PersonAlreadyExistsException extends Exception {
	public PersonAlreadyExistsException() {
		super("Person already exists!");
	}

	public PersonAlreadyExistsException(String errorMessage) {
		super(errorMessage);
	}

}

class PersonDoesNotExistsException extends Exception {
	public PersonDoesNotExistsException(String errorMessage) {
		super(errorMessage);

	}

	public PersonDoesNotExistsException() {
		super("Person does not exists");
	}
}
