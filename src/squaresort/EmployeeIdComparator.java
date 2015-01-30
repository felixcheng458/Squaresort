package squaresort;

import java.util.Comparator;

public class EmployeeIdComparator implements Comparator<Person> {

	@Override
	public int compare(Person p1, Person p2) {
		return p1.employeeId - p2.employeeId;
	}

}
