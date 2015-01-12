package de.davelee.mdiscis.gui;

import javax.swing.JOptionPane;

import mdiscis.data.DiscStore;
import mdiscis.data.Talk;
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
	
	public int showConfirmDialog(final String message, final String title, final int optionType, final int messageType ) {
    	if ( message.contains("disc 1")) {
    		return JOptionPane.YES_OPTION;
    	}
    	return JOptionPane.NO_OPTION;
    }
	
	public void showEditDialog(final String title, final int discNumber, final int trackNumber, final Talk talk) {
    	LOG.info("Edit Dialog not displayed for test purposes");
    }
	
	public void loadDisplayScreen(final DiscStore discStore) {
		LOG.info("Load Display Screen not displayed for test purposes");
	}

}
