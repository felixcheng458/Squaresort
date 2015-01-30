package squaresort;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class NameComparatorTest {

	@Test
	public void testCompare() {
		Person person1 = new Person("Alex","Smith",20);
		Person person2 = new Person("Keegan","Corney",12);
		Person person3 = new Person("Armanda","Corner",20);
		Person person4 = new Person("Kobe","Bryant",24);
		Person person5 = new Person("Lebron","James",1);
		Person person6 = new Person("Kevin","James",5);
		Person person7 = new Person("Alex","Smith",22);
		Person person8 = new Person("Alez","Smith",22);
		Comparator<Person> comp = new NameComparator();
		assertTrue(comp.compare(person1, person2) > 0);
		assertTrue(comp.compare(person4, person3) < 0);
		assertTrue(comp.compare(person1, person7) == 0);
		assertTrue(comp.compare(person5, person6) > 0);
		assertTrue(comp.compare(person7, person8) < 0);
	}

}
