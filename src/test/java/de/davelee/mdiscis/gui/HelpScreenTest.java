package de.davelee.mdiscis.gui;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import mdiscis.gui.HelpScreen;

public class HelpScreenTest {
	
	@Test
	public void testHelpScreen ( ) {
		HelpScreen screen = new HelpScreenMock();
		screen.loadContent("Add Disc");
		screen.updateList("add");
		assertFalse(screen.includeString("add", "ade"));
		assertTrue(screen.includeString("ad", "add"));
	}

}
