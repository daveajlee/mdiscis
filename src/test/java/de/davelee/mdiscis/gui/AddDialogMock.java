package de.davelee.mdiscis.gui;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.davelee.mdiscis.data.DiscStore;
import de.davelee.mdiscis.data.Talk;
import de.davelee.mdiscis.gui.AddDialog;
import de.davelee.mdiscis.gui.MDISCISGUI;

public class AddDialogMock extends AddDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3906493538783395728L;
	private static final Logger LOG = LoggerFactory.getLogger(AddDialogMock.class);
	
	public AddDialogMock(JFrame parent, String title, DiscStore discStore, MDISCISGUI gui){
		super(parent, title, discStore, gui);
	}
	
	public AddDialogMock(JFrame parent, String title, DiscStore discStore, int discNumber, int trackNumber, Talk talk, MDISCISGUI gui){
		super(parent, title, discStore, discNumber, trackNumber, talk, gui);
	}
	
	public void displayScreen() {
		LOG.info("Screen not displayed for test purposes");
	}

}
