package de.davelee.mdiscis.gui;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SplashScreenTest {
	
	@Test
	public void testSplashScreen ( ) {
		assertNotNull(new SplashScreenMock(true));
		assertNotNull(new SplashScreenMock(false));
	}

}
