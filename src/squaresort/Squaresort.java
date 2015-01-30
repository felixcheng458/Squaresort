package squaresort;

import java.util.Comparator;

public class Squaresort {
	
	public static void linearSort(int[] nums) {
		
		if (nums.length <= 1 || nums == null) return;
		
		int minindex, temp, outer;
		
		for (outer=0; outer<nums.length-1; outer++) {	
			minindex = outer;
			for (int inner=outer; inner<nums.length; inner++) {
				if (nums[inner] < nums[minindex]) minindex = inner;
				
				for (int iter2=outer; iter2<=inner; iter2++) assert nums[minindex] <= nums[iter2]; //loop invariant
			}
			
			temp = nums[outer];
			nums[outer] = nums[minindex];
			nums[minindex] = temp;
			
			for (int iter=0; iter<outer; iter++) assert nums[iter] <= nums[iter+1]; //loop invariant
		}
		
		assert outer == nums.length-1; //exit condition
		for (int iter=0; iter<nums.length-1; iter++) assert nums[iter] <= nums[iter+1]; //invariant + exit
	}
	
	public static void linearSort(Person[] people, Comparator<Person> comp) {
		
		if (people.length <= 1 || people == null) return;
		
		int minindex, outer;
		Person temp;
		for (outer=0; outer<people.length-1; outer++) {
			
			minindex = outer;
			for (int inner=outer; inner<people.length; inner++) {
				if (comp.compare(people[inner], people[minindex]) < 0) minindex = inner;
				
				for (int iter2=outer; iter2<=inner; iter2++) assert comp.compare(people[minindex], people[iter2]) <=0; //loop invariant
			}
			temp = people[outer];
			people[outer] = people[minindex];
			people[minindex] = temp;
			
			for (int iter=0; iter<=outer; iter++) assert comp.compare(people[iter], people[iter+1]) <= 0; //loop invariant
		}
		
		assert outer == people.length-1; //exit condition
		for (int iter=0; iter<people.length-1; iter++) assert comp.compare(people[iter], people[iter+1]) <= 0; //invariant + exit
	}
	
	public static void squaresort(Person[][] people, Comparator<Person> comp) {
		
		if (people.length == 0 || people == null) return;
		
		int minRowindex, minColindex, outerRow;
		int outerCol=-1;
		int rowSize = people.length;
		int colSize = people[0].length;
		
		Person temp;
		for (outerRow=0; outerRow<rowSize; outerRow++) {
			for (outerCol=0; outerCol<colSize; outerCol++) {
			
				minRowindex = outerRow;
				minColindex = outerCol;
				
				for (int innerRow=outerRow; innerRow<rowSize; innerRow++) {
					for (int innerCol=outerCol; innerCol<colSize; innerCol++) {
						if (comp.compare(people[innerRow][innerCol], people[minRowindex][minColindex]) < 0) {
							minRowindex = innerRow;
							minColindex = innerCol;
						}
						
						for (int i=outerRow; i<=innerRow; i++)
							for (int j=outerCol; j<=innerCol; j++) assert comp.compare(people[minRowindex][minColindex], people[i][j]) <= 0; // inner loop invariant
					}
				}
				temp = people[outerRow][outerCol];
				people[outerRow][outerCol] = people[minRowindex][minColindex];
				people[minRowindex][minColindex] = temp;
				
				for (int i=outerRow; i<rowSize; i++)
					for (int j=outerCol; j<colSize; j++) assert comp.compare(people[outerRow][outerCol], people[i][j]) <= 0; // outer loop invariant
			}
		}
		
		assert (outerRow == rowSize) && (outerCol == colSize); //exit condition
		for (int i=0; i<rowSize; i++) {
			for (int j=0; j<colSize; j++) {
				temp = people[i][j];
				for (int innerR=i; innerR<rowSize; innerR++)
					for (int innerC=j; innerC<colSize; innerC++)
						assert comp.compare(temp, people[innerR][innerC]) <= 0; // outer loop exit
			}
		}
	}
	
	/**
	 * helper function that checks whether an array of integers is sorted
	 * @param array
	 * @return true if sorted
	 */
	public static boolean ifArraySorted(int[] array) {
		
		if (array == null) return false;
		if (array.length <= 1) return true;
		
		for (int i=0; i<array.length-1; i++) {
			if (array[i] > array[i+1]) return false;
		}
		return true;
	}
	
	/**
	 * helper function that checks whether an array of Person is sorted by given Comparator
	 * @param array
	 * @param comp
	 * @return true if sorted
	 */
	public static boolean ifPersonSorted(Person[] array, Comparator<Person> comp) {
		
		if (array == null) return false;
		if (array.length <= 1) return true;
		
		for (int i=0; i<array.length-1; i++) {
			if (comp.compare(array[i], array[i+1]) > 0) return false;
		}
		return true;
	}
	
	/**
	 * helper function that checks whether an array of arrays of Person is sorted by given Comparator
	 * @param array
	 * @param comp
	 * @return true if sorted
	 */
	public static boolean ifPersonSquareSorted(Person[][] array, Comparator<Person> comp) {
		
		if (array == null) return false;
		if (array.length == 0) return true;
		
		for (int row=0; row<array.length; row++) {
			for (int col=0; col<array[0].length-1; col++) {
				if (comp.compare(array[row][col], array[row][col+1]) > 0) return false;
			}
		}
		
		for (int col=0; col<array[0].length; col++) {
			for (int row=0; row<array.length-1; row++) {
				if (comp.compare(array[row][col], array[row+1][col]) > 0) return false;
			}
		}
		
		return true;
	}
	
	private static void doTimings(int row, int col, Comparator<Person> comp) {
		
		Person[][] test = new Person[row][col];
		for (int i=0; i<row; i++)
			for (int j=0; j<col; j++) test[i][j] = new Person();
		
		System.gc();
		long startTime = System.nanoTime();
		squaresort(test, comp);
		long elapsedTime = System.nanoTime() - startTime;
		System.out.println("For given row = " + row + ", column = " + col + ", it took " + elapsedTime + " to run in nanosecs");
	}

	public static void main(String[] args) {
		for (int i=3; i<20; i=i+3)
			for (int j=4; j<40; j=j+5) doTimings(i, j, new NameComparator());
	}

}
