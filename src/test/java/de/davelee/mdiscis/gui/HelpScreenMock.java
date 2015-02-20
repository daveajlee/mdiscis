package de.davelee.mdiscis.gui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.davelee.mdiscis.config.HelpConfig;
import de.davelee.mdiscis.gui.HelpScreen;

public class HelpScreenMock extends HelpScreen {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1654222281860078459L;
	private static final Logger LOG = LoggerFactory.getLogger(HelpScreen.class);
	
	public HelpScreenMock ( final HelpConfig helpConfig ) {
		super(helpConfig);
	}
	
	public void displayScreen() {
		LOG.info("Screen not displayed for test purposes");
	}

}
