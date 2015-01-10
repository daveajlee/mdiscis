package de.davelee.mdiscis.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.time.LocalDate;

import mdiscis.data.DiscStore;
import mdiscis.data.Talk;

import org.junit.Test;

public class DiscStoreTest {
	
	@Test
	public void testMyDiscStore() {
		DiscStore myDiscStore = new DiscStore();
		assertEquals(myDiscStore.getNumDiscs(), 0);
		assertEquals(myDiscStore.addDisc(), 1);
		assertEquals(myDiscStore.addDisc(), 2);
		assertEquals(myDiscStore.getNumDiscs(), 2);
		Talk talk = new Talk();
		talk.setSubject("Subject");
		talk.setSpeaker("Speaker");
		talk.setTitle("Title");
		talk.setDate(LocalDate.of(2015, 1, 10));
		talk.setRecorded(true);
		assertTrue(myDiscStore.addTracks(1, 1, 10, talk));
		assertNotNull(myDiscStore.getDisc(2));
		assertNull(myDiscStore.getDisc(3));
		assertEquals(myDiscStore.getAllTracks(1).length, 10);
		assertEquals(myDiscStore.getNumTracks(1), 10);
		assertEquals(myDiscStore.getTrackInformation(1, 2), "3: 10/1/2015 - (Subject) - Title by Speaker");
		assertEquals(myDiscStore.getTrackNumber(1, 2), 3);
		assertTrue(myDiscStore.saveFile(new File(this.getClass().getResource("/test.mdi").getPath())));
		assertTrue(myDiscStore.removeTrack(1, 3));
		assertEquals(myDiscStore.getNumTracks(1), 9);
		myDiscStore.clearDisc(1);
		assertEquals(myDiscStore.getAllTracks(1).length, 0);
		myDiscStore.deleteDisc(2);
		assertEquals(myDiscStore.getNumDiscs(), 1);
		assertEquals(myDiscStore.getDiscNumber(0), 1);
		assertTrue(myDiscStore.loadFile(new File(this.getClass().getResource("/load.mdi").getPath())));
		assertEquals(myDiscStore.getNumTracks(1), 2);
	}

}
