package squaresort;

import java.util.Comparator;

public class NameComparator implements Comparator<Person> {

	@Override
	public int compare(Person p1, Person p2) {
		if (p1.surname.compareTo(p2.surname) == 0) return p1.givenName.compareTo(p2.givenName);
		else return p1.surname.compareTo(p2.surname);
	}

}
