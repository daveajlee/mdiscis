package de.davelee.mdiscis.gui;

import java.io.File;
import java.time.LocalDate;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.Ignore;
import org.junit.Test;

import de.davelee.mdiscis.data.DiscStore;
import de.davelee.mdiscis.data.DiscStoreMock;
import de.davelee.mdiscis.data.Talk;
import de.davelee.mdiscis.gui.MDISCISGUI;

public class MDISCISGUITest {
	
	@Test
	@Ignore
	public void testMDISCISGUIShortConstructor() {
		MDISCISGUI gui = new MDISCISGUIMock();
		gui.processPreviousTrackButton(1);
		gui.processPreviousTrackButton(10);
		gui.processNextTrackButton(1, 1);
		gui.processNextTrackButton(1, 50);
		gui.processNextTrackButton(2, 50);
		gui.addAnotherDisc();
		gui.createDiscControlPanel();
		gui.initialiseMenu();
		gui.addAnotherDisc();
		gui.processPreviousTrackButton(1);
		gui.clearDisc(1);
		gui.clearDisc(3);
		gui.deleteDisc(1);
		gui.deleteDisc(2);
		gui.deleteDisc(3);
		gui.updateStatus("Test");
		gui.newFile(JOptionPane.YES_OPTION);
		gui.newFile(JOptionPane.NO_OPTION);
	}
	
	@Test
	@Ignore
	public void testMDISCISGUILongConstructor() {
		DiscStore discStore = new DiscStore();
		discStore.addDisc();
		discStore.getDisc(1).addTracks(1, 50, new Talk());
		MDISCISGUI gui = new MDISCISGUIMock();
		gui.processPreviousTrackButton(1);
		gui.processPreviousTrackButton(13);
		gui.processNextTrackButton(1, 1);
		gui.processNextTrackButton(1, 50);
		gui.processNextTrackButton(2, 50);
		gui.createDiscControlPanel();
		gui.initialiseMenu();
		gui.updateStatus("Test");
	}
	
	@Test
	@Ignore
	public void testEditTrack() {
		DiscStore discStore = new DiscStore();
		discStore.addDisc();
		Talk talk = new Talk();
		talk.setDate(LocalDate.of(2015, 1, 12));
		talk.setRecorded(true);
		talk.setSpeaker("Speaker");
		talk.setSubject("Subject");
		talk.setTitle("Title");
		discStore.addTracks(1, 1, 10, talk);
		MDISCISGUI gui = new MDISCISGUIMock(discStore);
		gui.editTrack(1, 1);
		gui.editTrack(1, 11);
	}
	
	@Test
	@Ignore
	public void testSaveFile() {
		DiscStore discStore = new DiscStoreMock();
		MDISCISGUI gui = new MDISCISGUIMock(discStore);
		gui.saveFile(JOptionPane.NO_OPTION, new File("selected.txt"));
		gui.saveFile(JOptionPane.YES_OPTION, new File("selected.mdi"));
		gui.saveFile(JOptionPane.YES_OPTION, new File("selected.txt"));
	}
	
	@Test
	@Ignore
	public void testLoadFile() {
		DiscStore discStore = new DiscStoreMock();
		MDISCISGUI gui = new MDISCISGUIMock(discStore);
		gui.loadFile(JFileChooser.APPROVE_OPTION, new File("selectedload.txt"));
		gui.loadFile(JFileChooser.APPROVE_OPTION, new File("selectedload2.txt"));
		gui.loadFile(JFileChooser.CANCEL_OPTION, new File("selectedload2.txt"));
	}

}
