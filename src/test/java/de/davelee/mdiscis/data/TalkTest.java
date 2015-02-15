package de.davelee.mdiscis.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

import de.davelee.mdiscis.data.Talk;

public class TalkTest {
	
	@Test
	public void testBlankTalk() {
		Talk talk = new Talk();
		assertNull(talk.getDate());
		assertNull(talk.getSpeaker());
		assertNull(talk.getSubject());
		assertNull(talk.getTitle());
		assertFalse(talk.isRecorded());
	}
	
	@Test
	public void testTalkData() {
		Talk talk = new Talk();
		talk.setSubject("Subject");
		talk.setSpeaker("Speaker");
		talk.setTitle("Title");
		talk.setDate(LocalDate.of(2015, 1, 10));
		talk.setRecorded(true);
		assertEquals(talk.getSubject(), "Subject");
		assertEquals(talk.getSpeaker(), "Speaker");
		assertEquals(talk.getTitle(), "Title");
		assertEquals(talk.getDate(), LocalDate.of(2015, 1, 10));
		assertTrue(talk.isRecorded());
		assertEquals(talk.getShortDate(), "10/1/2015");
		assertEquals(talk.getTalkInformation(), "10/1/2015 - (Subject) - Title by Speaker");
		assertEquals(talk.toString(), "Subject Speaker Title 2015-01-10 true");
	}

}
