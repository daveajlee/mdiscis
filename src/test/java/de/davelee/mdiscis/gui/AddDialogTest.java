package de.davelee.mdiscis.gui;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import javax.swing.JFrame;

import mdiscis.data.DiscStore;
import mdiscis.data.Talk;
import mdiscis.gui.AddDialog;

import org.junit.Test;

public class AddDialogTest {

	@Test
	public void testAddDialogSmallerConstructor() {
		DiscStore discStore = new DiscStore();
		discStore.addDisc();
		AddDialog addDialog1 = new AddDialogMock(new JFrame(), "Add Talk", discStore, new MDISCISGUIMock());
		addDialog1.processOkButton();
		addDialog1.updateStatus(true);
		addDialog1.updateStatus(false);
		assertEquals(addDialog1.getDate("12/01/2015"), LocalDate.of(2015, 1, 12));
		AddDialog addDialog2 = new AddDialogMock(new JFrame(), "Edit Talk", discStore, new MDISCISGUIMock());
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
		AddDialog addDialog1 = new AddDialogMock(new JFrame(), "Add Talk", discStore, 1, 1, talk, new MDISCISGUIMock());
		addDialog1.processOkButton();
		addDialog1.updateStatus(true);
		addDialog1.updateStatus(false);
		assertEquals(addDialog1.getDate("14/01/2015"), LocalDate.of(2015, 1, 14));
		AddDialog addDialog2 = new AddDialogMock(new JFrame(), "Edit Talk", discStore, 1, 1, talk, new MDISCISGUIMock());
		addDialog2.processOkButton();
		addDialog2.updateStatus(true);
		addDialog2.updateStatus(false);
		assertEquals(addDialog2.getDate("13/01/2015"), LocalDate.of(2015, 1, 13));
	}
	
}
