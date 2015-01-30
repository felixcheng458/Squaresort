package squaresort;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class EmployeeIdComparatorTest {

	@Test
	public void testCompare() {
		Person person1 = new Person("Alex","Smith",20);
		Person person2 = new Person("Keegan","Corney",12);
		Person person3 = new Person("Armanda","Corner",20);
		Person person4 = new Person("Effie","Zhou",40);
		Comparator<Person> comp = new EmployeeIdComparator();
		assertTrue(comp.compare(person1, person2) < 0);
		assertTrue(comp.compare(person2, person3) < 0);
		assertTrue(comp.compare(person3, person4) < 0);
		assertTrue(comp.compare(person3, person1) > 0);
		assertTrue(comp.compare(person2, person2) == 0);
	}

}
