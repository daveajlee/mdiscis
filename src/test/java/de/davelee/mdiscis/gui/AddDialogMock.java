package de.davelee.mdiscis.gui;

import javax.swing.JFrame;

import mdiscis.data.DiscStore;
import mdiscis.data.Talk;
import mdiscis.gui.AddDialog;
import mdiscis.gui.MDISCISGUI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
