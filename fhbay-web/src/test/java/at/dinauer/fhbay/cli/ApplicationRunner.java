package at.dinauer.fhbay.cli;
import static java.lang.String.*;

public class ApplicationRunner {
	public void addCustomer(String firstName, String lastName, String userName, String password) {
		CLI.executeCommand(format("add user -firstName=%s -lastName=%s -userName=%s -password=%s", firstName, lastName, userName, password));
	}
}
