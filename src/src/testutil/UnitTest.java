package testutil;

/* UnitTest.java
 * 
 * A minimal implementation of:
 * 
 * 	https://en.wikipedia.org/wiki/XUnit
 *
*/

abstract public class UnitTest {
	
	private int testCount;
	private int failureCount;
	private int exceptionCount;
	
	public UnitTest() {
		this.testCount = 0;
		this.failureCount = 0;
		this.exceptionCount = 0;
	}
		
	public void assertEquals(Object expected, Object actual) {
		++this.testCount;
		if (!assertEqualsSafely(expected, actual)) {
			++this.failureCount;
			prnf("FAILED (assertEquals) -- actual <%s> vs expected <%s>%n", actual, expected); 
		}
	}
	
	public void assertNotEquals(Object expected, Object actual) {
		++this.testCount;
		if (assertEqualsSafely(expected, actual)) {
			++this.failureCount;
			prnf("FAILED (assertNotEquals) -- actual <%s> vs expected <%s>%n", actual, expected); 
		}
	}	
	
	public void assertTrue(boolean condition) {
		assertEquals(true, condition);
	}
	
	public void fail(String msg) {
		//Record test as failed
		prnf("Failed -- " + msg);
		assertTrue(false);
	}	
	
	public static void header(String header) {
		prn("\nStarting test: " + header);
	}	

	public static void prn(Object o) {
		System.out.println(o);
	}
	
	public static void prnf(String format, Object... params) {
		System.out.printf(format, params);
	}	
	
	public static void separator() {
		prn("--------------------------------------------");
	}	
	
	public static void minorSeparator() {
		prn("-----------");
	}	
	
	public static void cr() {
		//carriage return new line
		prn("");
	}	
	
	private boolean assertEqualsSafely(Object a, Object b)  {
		//If only one is nil, then false, use XOR*/
		if (a==null ^ b==null)
			return false;
		//We now know that if either is null, they both are
		if (a==null)
			return true;
		//Finally a safe equals
		return a.equals(b);
	}	
	
	public void printSummary(String testeeType) {
		cr();
		prnf("Test Results for test -- %s%n", this.getClass().getSimpleName()); 
		int n, f, p, e;
		n = this.testCount;
		f = this.failureCount;
		e = this.exceptionCount;
		p = n - f - e;;
		if (n == 0) return;
		double percent = 100.0 * p / n;
		prn("\n----------- TEST SUMMARY -----------\n");
		prnf("Model (Testee) Type: %s%n", testeeType);		
		prnf("Test Class: %s%n", this.getClass().getSimpleName());		
		prnf("Test Count: %d%n", n);
		prnf("Passing Count: %d%n", p);		
		prnf("Failure Count: %d%n", f);
		prnf("Exception Count: %d%n", e);		
		prnf("Passing Percent: %.1f%%%n", percent);
		prn("");
		separator();
	}
	
	protected SubTest newSubTest(String subTestLabel, Evaluable testFunction) {
		return new SubTest(subTestLabel, testFunction); 
	}
	
	public void incrementCount() {
		this.testCount++;
	}
	
	public void incrementFailureCount() {
		this.failureCount++;
	}	
	
	public void incrementExceptionCount() {
		this.exceptionCount++;
	}	
	
	//----------------- Virtual Optional -----------------
	
	public void beforeEach() {
		//Called before each test
		//Optional override
	}
	
	public void afterEach() {
		//Called after each test
		//Optional override
	}	
	
}
