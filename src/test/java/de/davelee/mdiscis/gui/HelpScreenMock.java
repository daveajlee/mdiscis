package de.davelee.mdiscis.gui;

import mdiscis.gui.HelpScreen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelpScreenMock extends HelpScreen {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1654222281860078459L;
	private static final Logger LOG = LoggerFactory.getLogger(HelpScreen.class);
	
	public void displayScreen() {
		LOG.info("Screen not displayed for test purposes");
	}

}
