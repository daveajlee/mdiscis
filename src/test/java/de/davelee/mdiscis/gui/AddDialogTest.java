package de.davelee.mdiscis.gui;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import javax.swing.JFrame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.davelee.mdiscis.config.AddDialogConfig;
import de.davelee.mdiscis.data.DiscStore;
import de.davelee.mdiscis.data.Talk;
import de.davelee.mdiscis.gui.AddDialog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testApplicationContext.xml")
public class AddDialogTest {
	
	@Autowired
	private AddDialogConfig addDialogConfig;

	@Test
	public void testAddDialogSmallerConstructor() {
		DiscStore discStore = new DiscStore();
		discStore.addDisc();
		MDISCISGUIMock guiMock = new MDISCISGUIMock();
		guiMock.setAddDialogConfig(addDialogConfig);
		AddDialog addDialog1 = new AddDialogMock(new JFrame(), "Add Talk", discStore, guiMock);
		addDialog1.processOkButton();
		addDialog1.updateStatus(true);
		addDialog1.updateStatus(false);
		assertEquals(addDialog1.getDate("12/01/2015"), LocalDate.of(2015, 1, 12));
		AddDialog addDialog2 = new AddDialogMock(new JFrame(), "Edit Talk", discStore, guiMock);
		addDialog2.processOkButton();
		addDialog2.updateStatus(true);
		addDialog2.updateStatus(false);
		assertEquals(addDialog2.getDate("11/01/2015"), LocalDate.of(2015, 1, 11));
	}
	
	@Test
	public void testAddDialogLargerConstructor() {
		DiscStore discStore = new DiscStore();
		discStore.addDisc();
		Talk talk = new Talk();
		talk.setDate(LocalDate.of(2015, 1, 11));
		talk.setRecorded(true);
		talk.setSpeaker("Speaker");
		talk.setSubject("Subject");
		talk.setTitle("Title");
		MDISCISGUIMock guiMock = new MDISCISGUIMock();
		guiMock.setAddDialogConfig(addDialogConfig);
		AddDialog addDialog1 = new AddDialogMock(new JFrame(), "Add Talk", discStore, 1, 1, talk, guiMock);
		addDialog1.processOkButton();
		addDialog1.updateStatus(true);
		addDialog1.updateStatus(false);
		assertEquals(addDialog1.getDate("14/01/2015"), LocalDate.of(2015, 1, 14));
		AddDialog addDialog2 = new AddDialogMock(new JFrame(), "Edit Talk", discStore, 1, 1, talk, guiMock);
		addDialog2.processOkButton();
		addDialog2.updateStatus(true);
		addDialog2.updateStatus(false);
		assertEquals(addDialog2.getDate("13/01/2015"), LocalDate.of(2015, 1, 13));
	}
	
}
