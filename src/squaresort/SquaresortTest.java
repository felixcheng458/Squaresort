package squaresort;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

public class SquaresortTest {
	
	Person person1, person2, person3, person4, person5, person6, person7, person8, person9, person10;
	
	@Before
	public void setUp() throws Exception {
		person1 = new Person("Alex","Smith",20);
		person2 = new Person("Keegan","Corney",12);
		person3 = new Person("Armanda","Corner",20);
		person4 = new Person("Kobe","Bryant",24);
		person5 = new Person("Lebron","James",1);
		person6 = new Person("Kevin","James",5);
		person7 = new Person("Alex","Smith",22);
		person8 = new Person("Alez","Smith",22);
		person9 = new Person("Bratt","Zeth",13);
	}
	
	@Test
	public void testIfArraySorted() {
		int[] test = new int[]{5,2,4,-1};
		assertFalse(Squaresort.ifArraySorted(test));
		test = new int[]{11};
		assertTrue(Squaresort.ifArraySorted(test));
		test = new int[]{-10,4,6,20,45,60,122,403};
		assertTrue(Squaresort.ifArraySorted(test));
		test = new int[]{3,40,53,1,66,89,90};
		assertFalse(Squaresort.ifArraySorted(test));
		test = new int[]{1,4,6,11,33,40,2};
		assertFalse(Squaresort.ifArraySorted(test));
	}
	
	@Test
	public void testIfPersonSorted() {
		Person[] test = new Person[]{person1};
		assertTrue(Squaresort.ifPersonSorted(test, new PayGradeComparator()));
		test = new Person[]{person1, person2, person3, person8};
		assertFalse(Squaresort.ifPersonSorted(test, new PayGradeComparator()));
		assertFalse(Squaresort.ifPersonSorted(test, new NameComparator()));
		test = new Person[]{person2, person3, person4, person5};
		assertTrue(Squaresort.ifPersonSorted(test, new EmployeeIdComparator()));
		test = new Person[]{person4, person3, person2, person6, person5, person1, person8};
		assertTrue(Squaresort.ifPersonSorted(test, new NameComparator()));
		test = new Person[]{person5, person2, person1, person8};
		assertTrue(Squaresort.ifPersonSorted(test, new PayGradeComparator()));
		test = new Person[]{person5, person2, person1, person8, person6};
		assertFalse(Squaresort.ifPersonSorted(test, new PayGradeComparator()));
	}
	
	@Test
	public void testIfPersonSquareSorted() {
		Person[][] test = new Person[4][2];
		test[0][0] = person5; test[0][1] = person6; test[1][0] = person2; test[1][1] = person1;
		test[2][0] = person3; test[2][1] = person7; test[3][0] = person8; test[3][1] = person4;
		assertTrue(Squaresort.ifPersonSquareSorted(test, new PayGradeComparator()));
		test[1][1] = person5;
		assertFalse(Squaresort.ifPersonSquareSorted(test, new PayGradeComparator()));
		test = new Person[3][3];
		test[0][0] = person1; test[0][1] = person2; test[0][2] = person3; test[1][0] = person4;
		test[1][1] = person5; test[1][2] = person6; test[2][0] = person7; test[2][1] = person8; test[2][2] = person9;
		assertTrue(Squaresort.ifPersonSquareSorted(test, new EmployeeIdComparator()));
		test[1][2] = person1;
		assertFalse(Squaresort.ifPersonSquareSorted(test, new EmployeeIdComparator()));
		test = new Person[2][4];
		test[0][0] = person4; test[0][1] = person3; test[0][2] = person2; test[0][3] = person6;
		test[1][0] = person5; test[1][1] = person1; test[1][2] = person8; test[1][3] = person9;
		assertTrue(Squaresort.ifPersonSquareSorted(test, new NameComparator()));
		test[1][1] = person3;
		assertFalse(Squaresort.ifPersonSquareSorted(test, new NameComparator()));
	}
	
	@Test
	public void testLinearSortIntArray() {
		int[] test = new int[]{20,-2,30,1,32,45,5,6,7,7};
		Squaresort.linearSort(test);
		assertTrue(Squaresort.ifArraySorted(test));
		test = new int[]{1,10,4,4,12,-30,5,3,93,11,22,4,-10};
		Squaresort.linearSort(test);
		assertTrue(Squaresort.ifArraySorted(test));
		test = new int[]{1,2,3,4,5,6,7,10,11,44,55,-100};
		Squaresort.linearSort(test);
		assertTrue(Squaresort.ifArraySorted(test));
	}

	@Test
	public void testLinearSortPersonArrayComparatorOfPerson() {
		Person[] test = new Person[10];
		for (int i=0; i<test.length; i++) test[i] = new Person();
		Squaresort.linearSort(test, new NameComparator());
		assertTrue(Squaresort.ifPersonSorted(test, new NameComparator()));
		Squaresort.linearSort(test, new EmployeeIdComparator());
		assertTrue(Squaresort.ifPersonSorted(test, new EmployeeIdComparator()));
		Squaresort.linearSort(test, new PayGradeComparator());
		assertTrue(Squaresort.ifPersonSorted(test, new PayGradeComparator()));
	}

	@Test
	public void testSquaresort() {
		Person[][] test = new Person[7][5];
		for (int i=0; i<test.length; i++)
			for (int j=0; j<test[0].length; j++) test[i][j] = new Person();
		Squaresort.squaresort(test, new NameComparator());
		assertTrue(Squaresort.ifPersonSquareSorted(test, new NameComparator()));
		Squaresort.squaresort(test, new EmployeeIdComparator());
		assertTrue(Squaresort.ifPersonSquareSorted(test, new EmployeeIdComparator()));
		Squaresort.squaresort(test, new PayGradeComparator());
		assertTrue(Squaresort.ifPersonSquareSorted(test, new PayGradeComparator()));
		test = new Person[3][8];
		for (int i=0; i<test.length; i++)
			for (int j=0; j<test[0].length; j++) test[i][j] = new Person();
		Squaresort.squaresort(test, new NameComparator());
		assertTrue(Squaresort.ifPersonSquareSorted(test, new NameComparator()));
		Squaresort.squaresort(test, new EmployeeIdComparator());
		assertTrue(Squaresort.ifPersonSquareSorted(test, new EmployeeIdComparator()));
		Squaresort.squaresort(test, new PayGradeComparator());
		assertTrue(Squaresort.ifPersonSquareSorted(test, new PayGradeComparator()));
	}

}
