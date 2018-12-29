package hw2;
//I pledge my honor that I have abided by the Stevens Honor System. -cli50
public class Complexity {
	/**
	 * method that has a time complexity of n * n
	 * @param n is the input integer
	 */
	public void method1(int n) {
		int counter = 0;
		for (int i=0; i<n; i++) {
			for (int j=0;j<n; j++) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}
	/**
	 * method that has a time complexity of n * n * n
	 * @param n is the input integer
	 */
	public void method4(int n) {
		int counter = 0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				for (int x=0; x<n; x++) {
					System.out.println("Operation " + counter);
					counter++;
				}
			}
		}
	}
	/**
	 * method that has a time complexity of log n
	 * @param n is the input integer
	 */
	public void method2(int n) {
		int counter = 0;
		for (int i=1; i<n; i*=2) {
			System.out.println("Operation " + counter);
			counter++;
		}
	}
	
	/**
	 * method that has time complexity of n(log n)
	 * @param n is the input integer
	 */
	public void method3(int n) {
		int counter = 0;
		for (int i=0; i<n; i++) {
			for (int j=1; j<n; j*=2) {
				System.out.println("Operation " + counter);
				counter++;
			}
		}
	}
	
	/**
	 * method that has a time complexity of log(log n)
	 * @param n is the input integer
	 */
	public void method5(int n) {
		int counter = 0;
		for (int i=2; i<n; i*=i) {
			System.out.println("Operation " + counter);
			counter++;
		}
	}
	/**
	 *method that has a time complexity of 2**n
	 * @param n is the input integer
	 * @return 2**n
	 */
	public int method6(int n) {
		int counter = 1;
		if(n == 0) {
			return counter;
		}
		counter++;
		if(n == 1) {
			return counter;
		}
		else {
			return method6(n-2) * method6(n-1);
		}
	}

}

