package de.davelee.mdiscis.gui;

import static org.junit.Assert.assertFalse;


import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.davelee.mdiscis.config.HelpConfig;
import de.davelee.mdiscis.gui.HelpScreen;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
@Ignore
public class HelpScreenTest {
	
	@Autowired
	private HelpConfig helpConfig;
	
	@Test
	public void testHelpScreen ( ) {
		HelpScreen screen = new HelpScreenMock(helpConfig);
		screen.loadContent("Add Disc");
		screen.updateList("add");
		assertFalse(screen.includeString("add", "ade"));
		assertTrue(screen.includeString("ad", "add"));
	}

}
