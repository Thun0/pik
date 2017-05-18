import hello.Greeting;
import static org.junit.Assert.*;
import org.junit.Test;

public class GreetingTest {
	@Test
	public void testGetId() {
		System.out.println("Test: testGetId()");
		long sample_long = 4589342;
		Greeting classUnderTest = new Greeting(sample_long, "sample string");
		assertTrue("testGetId should return '"+sample_long+"'", classUnderTest.getId() == sample_long);
	}

	@Test
	public void testGetContent() {
		System.out.println("Test: testGetContent()");
		String sample_string = "sample string";
		Greeting classUnderTest = new Greeting(458684, sample_string);
		assertTrue("testGetContent should return '"+sample_string+"'", classUnderTest.getContent().equals(sample_string));
	}

}
