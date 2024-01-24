package rst;

import static org.junit.Assert.*;

import org.junit.Test;

public class EscapeRoomTest {

	private EscapeRoom escapeRoom = new EscapeRoom();
	
	// Method: attemptUnlock(int lockNum, int actualLockNum, String code):String
	@Test
	public void testLock2Correct() {
		assertEquals("Correct!", escapeRoom.attemptUnlock(2, 2, "6"));
	}
	
	@Test
	public void testLock2IncorrectLock() {
		assertEquals("Incorrect! Please try again. (-$" + EscapeRoom.INCORRECT_FEE + ")", escapeRoom.attemptUnlock(1, 2, "6"));
	}
	
	@Test
	public void testLock2IncorrectCode() {
		assertEquals("Incorrect! Please try again. (-$" + EscapeRoom.INCORRECT_FEE + ")", escapeRoom.attemptUnlock(2, 2, "7"));
	}
	
	@Test
	public void testLock2IncorrectLockAndCode() {
		assertEquals("Incorrect! Please try again. (-$" + EscapeRoom.INCORRECT_FEE + ")", escapeRoom.attemptUnlock(5, 2, "16"));
	}
	
	@Test
	public void testLock1Correct() {
		assertEquals("Correct!", escapeRoom.attemptUnlock(1, 1, "16"));
	}
	
	@Test
	public void testLock1IncorrectLock() {
		assertEquals("Incorrect! Please try again. (-$" + EscapeRoom.INCORRECT_FEE + ")", escapeRoom.attemptUnlock(2, 1, "16"));
	}
	
	@Test
	public void testLock1IncorrectCode() {
		assertEquals("Incorrect! Please try again. (-$" + EscapeRoom.INCORRECT_FEE + ")", escapeRoom.attemptUnlock(1, 1, "15"));
	}
	
	@Test
	public void testLock1IncorrectLockAndCode() {
		assertEquals("Incorrect! Please try again. (-$" + EscapeRoom.INCORRECT_FEE + ")", escapeRoom.attemptUnlock(3, 1, "10210"));
	}
	
	@Test
	public void testLock3Correct() {
		assertEquals("Correct!", escapeRoom.attemptUnlock(3, 3, "10210"));
	}
	
	@Test
	public void testLock3IncorrectLock() {
		assertEquals("Incorrect! Please try again. (-$" + EscapeRoom.INCORRECT_FEE + ")", escapeRoom.attemptUnlock(4, 3, "10210"));
	}
	
	@Test
	public void testLock3IncorrectCode() {
		assertEquals("Incorrect! Please try again. (-$" + EscapeRoom.INCORRECT_FEE + ")", escapeRoom.attemptUnlock(3, 3, "10201"));
	}
	
	@Test
	public void testLock3IncorrectLockAndCode() {
		assertEquals("Incorrect! Please try again. (-$" + EscapeRoom.INCORRECT_FEE + ")", escapeRoom.attemptUnlock(1, 3, "4793232323"));
	}
	
	@Test
	public void testLock5Correct() {
		assertEquals("Correct!", escapeRoom.attemptUnlock(5, 5, "2610"));
	}
	
	@Test
	public void testLock5IncorrectLock() {
		assertEquals("Incorrect! Please try again. (-$" + EscapeRoom.INCORRECT_FEE + ")", escapeRoom.attemptUnlock(4, 5, "2610"));
	}
	
	@Test
	public void testLock5IncorrectCode() {
		assertEquals("Incorrect! Please try again. (-$" + EscapeRoom.INCORRECT_FEE + ")", escapeRoom.attemptUnlock(5, 5, "26100"));
	}
	
	@Test
	public void testLock5IncorrectLockAndCode() {
		assertEquals("Incorrect! Please try again. (-$" + EscapeRoom.INCORRECT_FEE + ")", escapeRoom.attemptUnlock(6, 5, "2"));
	}
	
	@Test
	public void testLock4Correct() {
		assertEquals("Correct!", escapeRoom.attemptUnlock(4, 4, "312"));
	}
	
	@Test
	public void testLock4IncorrectLock() {
		assertEquals("Incorrect! Please try again. (-$" + EscapeRoom.INCORRECT_FEE + ")", escapeRoom.attemptUnlock(1, 4, "312"));
	}
	
	@Test
	public void testLock4IncorrectCode() {
		assertEquals("Incorrect! Please try again. (-$" + EscapeRoom.INCORRECT_FEE + ")", escapeRoom.attemptUnlock(4, 4, "333333333"));
	}
	
	@Test
	public void testLock4IncorrectLockAndCode() {
		assertEquals("Incorrect! Please try again. (-$" + EscapeRoom.INCORRECT_FEE + ")", escapeRoom.attemptUnlock(1, 4, "12"));
	}
	
	// Method: validateWord(String word):boolean
	@Test
	public void testValidateWordNumber() {
		assertEquals(false, escapeRoom.validateWord("655425432"));
	}
	
	@Test
	public void testValidateWordLong() {
		assertEquals(true, escapeRoom.validateWord("CruiseOasisOfTheSeashfehfeiheihroqeohrqeo"));
	}
	
	@Test
	public void testValidateWordShort() {
		assertEquals(true, escapeRoom.validateWord("m"));
	}
	
	@Test
	public void testValidateWordSymbols() {
		assertEquals(false, escapeRoom.validateWord("&(@&$(@&#"));
	}
	
	@Test
	public void testValidateWordSymbolsLetters() {
		assertEquals(false, escapeRoom.validateWord("&(@&$(@rehriehr&#"));
	}
	
	@Test
	public void testValidateWordPhrase() {
		assertEquals(false, escapeRoom.validateWord("Cruising Is awesome"));
	}

}