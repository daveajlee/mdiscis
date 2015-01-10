package de.davelee.mdiscis.gui;

import mdiscis.gui.SplashScreen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SplashScreenMock extends SplashScreen {
	
	public SplashScreenMock ( final boolean isAboutScreen ) {
		super(isAboutScreen);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8790394480124503803L;
	private static final Logger LOG = LoggerFactory.getLogger(SplashScreen.class);
	
	public void displayScreen() {
		LOG.info("Screen not displayed for test purposes");
	}

}
