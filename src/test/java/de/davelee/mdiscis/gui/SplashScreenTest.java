package de.davelee.mdiscis.gui;

import org.junit.Test;

public class SplashScreenTest {
	
	@Test
	public void testSplashScreen ( ) {
		new SplashScreenMock(true);
		new SplashScreenMock(false);
	}

}
