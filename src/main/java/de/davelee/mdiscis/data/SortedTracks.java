package de.davelee.mdiscis.data;

import java.util.*;

/**
 * This class sorts tracks into numerical order in the MDISCIS program.
 * @author Dave Lee
 */
public class SortedTracks implements Comparator<Track> {

    /**
     * Compare two track objects.
     * @param t1 a <code>Track</code> object.
     * @param t2 a <code>Track</code> object.
     * @return a <code>int</code> so that if t1's id comes before t2's id - it is ordered first.
     */
    public int compare ( Track t1, Track t2 ) {
        return new Integer(t1.getTrackId()).compareTo(new Integer(t2.getTrackId()));
    }

}
