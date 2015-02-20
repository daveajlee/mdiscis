package de.davelee.mdiscis.gui;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.davelee.mdiscis.config.GUIConfig;
import de.davelee.mdiscis.config.HelpConfig;
import de.davelee.mdiscis.config.MenuConfig;
import de.davelee.mdiscis.data.DiscStore;
import de.davelee.mdiscis.data.Talk;
import de.davelee.mdiscis.gui.HelpScreen;
import de.davelee.mdiscis.gui.MDISCISGUI;

public class MDISCISGUIMock extends MDISCISGUI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5783619907189868550L;
	private static final Logger LOG = LoggerFactory.getLogger(HelpScreen.class);
	
	public MDISCISGUIMock() {
		super(new GUIConfig(), new MenuConfig(), new HelpConfig());
	}
	
	public MDISCISGUIMock(DiscStore discStore) {
		super(discStore, new GUIConfig(), new MenuConfig(), new HelpConfig());
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
