package testutil;

/*
 * TestRunner
 * A micro unit test runner
 */

import static testutil.TestTool.prn;

import java.util.List;

public class TestRunner {

	private UnitTest unitTest;
	private List<SubTest> subTests;

	public TestRunner(UnitTest test, List<SubTest> subTests) {
		this.unitTest = test;
		this.subTests = subTests;		
	}

	public void runAll() {
		for (SubTest eachSubTest : this.subTests) {
			this.unitTest.beforeEach();
			try {
				eachSubTest.run();
			} catch (Exception ex) {
				this.unitTest.incrementCount();
				this.unitTest.incrementExceptionCount();				
				printException(ex, this.unitTest, "");
			}
			this.unitTest.afterEach();
		}
	}

	private static void printException(Exception outerException, Object target,
			String message1) {
		Throwable cause = getRootException(outerException);
		StackTraceElement[] stackTrace = cause.getStackTrace();
		StackTraceElement stackElem = stackTrace[0];
		String javaFilename = stackElem.getFileName();
		int lineNumber = stackElem.getLineNumber();
		String description = cause.getMessage();
		prn("EXCEPTION");
		if (!message1.isBlank())
			prn(message1);
		prn("For Class: " + target.getClass().getSimpleName());
		prn("Top-level Exception: " + outerException.getClass().getSimpleName());
		prn("Cause Exception: " + cause.getClass().getSimpleName());
		prn("Exception Occurred In: " + javaFilename);
		prn("At Line Number: " + lineNumber);
		prn("Reason: " + description);
	}
	
	private static Throwable getRootException(Throwable ex) {
		 Throwable root = ex;
		 while (root.getCause() != null)
			 root = root.getCause();
		 return root;
	}

}
