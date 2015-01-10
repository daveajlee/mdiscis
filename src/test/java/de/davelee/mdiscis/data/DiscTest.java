package de.davelee.mdiscis.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import mdiscis.data.Disc;
import mdiscis.data.Talk;

import org.junit.Test;

public class DiscTest {
	
	@Test
	public void testMyDisc() {
		Disc myDisc = new Disc(1);
		assertEquals(myDisc.getDiscNumber(), 1);
		assertEquals(myDisc.getNumTracks(), 0);
		Talk myTalk = new Talk();
		myTalk.setDate(LocalDate.of(2015, 1, 10));
		myTalk.setRecorded(true);
		myTalk.setSpeaker("Speaker");
		myTalk.setSubject("Subject");
		myTalk.setTitle("Title");
		assertTrue(myDisc.addTrack(1, myTalk));
		assertFalse(myDisc.addTrack(1, myTalk));
		assertFalse(myDisc.addTracks(1, 10, myTalk));
		assertTrue(myDisc.addTracks(2, 10, myTalk));
		assertEquals(myDisc.getAllTracks().length, 10);
		assertEquals(myDisc.getTrack(0).getTrackId(), 1);
		assertEquals(myDisc.getTrackInformation(0), "1: 10/1/2015 - (Subject) - Title by Speaker");
		assertEquals(myDisc.getTrackByNumber(2).getTrackId(), 2);
		assertTrue(myDisc.removeTrack(2));
		assertFalse(myDisc.removeTrack(2));
		assertNull(myDisc.getTrackByNumber(2));
		myDisc.clearDisc();
		assertEquals(myDisc.getNumTracks(), 0);
	}

}
