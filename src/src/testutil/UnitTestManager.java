package testutil;

import java.util.ArrayList;
import java.util.List;
import static testutil.TestTool.prn;

public class UnitTestManager<T extends UnitTest> {

	private String testeeType;
	private T unitTest;
	private List<SubTest> subTests = new ArrayList<>();	

	public UnitTestManager(String testeeType, T testee, List<SubTest> subTests) {
		this.testeeType = testeeType;
		this.unitTest = testee;
		this.subTests = subTests;
	}

	public T getUnitTest() {
		return unitTest;
	}

	public List<SubTest> getSubTests() {
		return subTests;
	}

	public TestRunner testRunner() {
		return new TestRunner(this.unitTest, this.subTests);
	}
	
	public void runAll() {
		prn("\n----------- TEST LOG -----------");		
		this.testRunner().runAll();
		this.unitTest.printSummary(this.testeeType);
	}

}
