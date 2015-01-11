package de.davelee.mdiscis.gui;

import org.junit.Test;

import mdiscis.data.DiscStore;
import mdiscis.gui.MDISCISGUI;

public class MDISCISGUITest {
	
	@Test
	public void testMDISCISGUIShortConstructor() {
		MDISCISGUI gui = new MDISCISGUIMock();
		gui.processPreviousTrackButton(1);
		gui.processNextTrackButton(1, 1);
		gui.createDiscControlPanel();
		gui.initialiseMenu();
		gui.updateStatus("Test");
	}
	
	@Test
	public void testMDISCISGUILongConstructor() {
		DiscStore discStore = new DiscStore();
		discStore.addDisc();
		MDISCISGUI gui = new MDISCISGUIMock();
		gui.processPreviousTrackButton(1);
		gui.processNextTrackButton(1, 1);
		gui.createDiscControlPanel();
		gui.initialiseMenu();
		gui.updateStatus("Test");
	}

}
