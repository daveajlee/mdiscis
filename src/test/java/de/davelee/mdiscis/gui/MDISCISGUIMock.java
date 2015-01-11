package de.davelee.mdiscis.gui;

import mdiscis.data.DiscStore;
import mdiscis.gui.HelpScreen;
import mdiscis.gui.MDISCISGUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MDISCISGUIMock extends MDISCISGUI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5783619907189868550L;
	private static final Logger LOG = LoggerFactory.getLogger(HelpScreen.class);
	
	public MDISCISGUIMock() {
		super();
	}
	
	public MDISCISGUIMock(DiscStore discStore) {
		super(discStore);
	}
	
	public void displayScreen() {
		LOG.info("Screen not displayed for test purposes");
	}

}
