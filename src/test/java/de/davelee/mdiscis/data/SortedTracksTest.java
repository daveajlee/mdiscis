package de.davelee.mdiscis.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import de.davelee.mdiscis.data.SortedTracks;
import de.davelee.mdiscis.data.Track;

public class SortedTracksTest {
	
	@Test
	public void testSortedTracks() {
		Track track1 = new Track();
		track1.setTrackId(1);
		Track track2 = new Track();
		track2.setTrackId(2);
		SortedTracks sortedTracks = new SortedTracks();
		assertEquals(sortedTracks.compare(track1, track2), -1);
	}

}
