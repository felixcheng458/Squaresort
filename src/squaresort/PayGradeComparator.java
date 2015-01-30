package squaresort;

import java.util.Comparator;

public class PayGradeComparator implements Comparator<Person> {

	@Override
	public int compare(Person p1, Person p2) {
		return p1.payGrade - p2.payGrade;
	}

}
