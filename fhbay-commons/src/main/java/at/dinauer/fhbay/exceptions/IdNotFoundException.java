package at.dinauer.fhbay.exceptions;

import java.io.Serializable;

public class IdNotFoundException extends Exception implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public IdNotFoundException(Serializable id, String entity) {
		super(String.format("Id <%s> for entity <%s> not found.", id, entity));
	}
}
