package dual_lab.bus_stop;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import duallab.busstop.App;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
		extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public AppTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(AppTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		String[] args = new String[] { "src\\test\\resources\\input.txt" };
		App.main(args);

		int linesCount = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("src\\test\\resources\\output.txt"))) {
			for (String line; (line = br.readLine()) != null;) {
				linesCount++;
				if (linesCount == 5) {
					// check empty line between sections
					assertTrue(line.isEmpty());
				}
			}
		} catch (FileNotFoundException exception) {
			// exceptionRule.expect(FileNotFoundException.class);
		} catch (IOException exception) {
			//
		}
		// check rows count
		assertEquals(linesCount, 8);
	}
}
