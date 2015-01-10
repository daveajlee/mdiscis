package de.davelee.mdiscis.data;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import mdiscis.data.Talk;
import mdiscis.data.Track;

import org.junit.Test;

public class TrackTest {
	
	@Test
	public void testMyTrack() {
		Talk myTalk = new Talk();
		myTalk.setDate(LocalDate.of(2015, 1, 10));
		myTalk.setRecorded(true);
		myTalk.setSpeaker("Speaker");
		myTalk.setSubject("Subject");
		myTalk.setTitle("Title");
		Track myTrack = new Track();
		myTrack.setTrackId(1);
		myTrack.setTalk(myTalk);
		assertEquals(myTrack.getTrackId(),1);
		assertEquals(myTrack.getTalk().getSpeaker(),"Speaker");
		assertEquals(myTrack.getTrackInfo(), "1: 10/1/2015 - (Subject) - Title by Speaker");
		assertEquals(myTrack.toString(), "1 Subject Speaker Title 2015-01-10 true");
	}

}
