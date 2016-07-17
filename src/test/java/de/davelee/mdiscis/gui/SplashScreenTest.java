package de.davelee.mdiscis.gui;

import org.junit.Ignore;
import org.junit.Test;

public class SplashScreenTest {
	
	@Test
	@Ignore
	public void testSplashScreen ( ) {
		new SplashScreenMock(true);
		new SplashScreenMock(false);
	}

}
