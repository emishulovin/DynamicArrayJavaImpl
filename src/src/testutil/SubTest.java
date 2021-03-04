package testutil;

/*
 * SubTest
 * 
 * I am one sub test on a given test class.
 * 
 * E.g. on
 * 	RectangleTest
 * I may by
 * 	testComputeArea
 * 
 */

public class SubTest {

	private Evaluable testFunction; //Simply evaluate this evaluable to perform the test
	private String testLabel;

	public SubTest(String testLabel, Evaluable testFunction) {
		this.testLabel = testLabel;
		this.testFunction = testFunction;
	}

	public Evaluable getTestFunction() {
		return testFunction;
	}

	public String getTestLabel() {
		return testLabel;
	}

	public void run() {
		System.out.println("\nStarting -- " + this.testLabel);
		this.testFunction.evaluate();
	}

}
