package de.davelee.mdiscis.gui;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

@Ignore
public class SplashScreenTest {
	
	@Test
	public void testSplashScreen ( ) {
		assertNotNull(new SplashScreenMock(true));
		assertNotNull(new SplashScreenMock(false));
	}

}
