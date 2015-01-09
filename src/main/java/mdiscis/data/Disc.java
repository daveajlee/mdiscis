package mdiscis.data;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents a disc in the MinidiscSystem program.
 * @author Dave Lee
 */
public class Disc {

    private List<Track> theTracks;
    private int theDiscNumber;
    
    private static final Logger LOG = LoggerFactory.getLogger(Disc.class);

    /**
     * Create a new disc - it is blank initially.
     * @param discNumber a <code>int</code> with the disc number.
     */
    public Disc ( int discNumber ) {
	theTracks = new LinkedList<Track>();
        theDiscNumber = discNumber;
    }

    /**
     * Add a track to this disc.
     * @param trackId a <code>String</code> with track id.
     * @param subject a <code>String</code> with the subject.
     * @param speaker a <code>String</code> with the speaker.
     * @param talkTitle a <code>String</code> with the talk title.
     * @param date a <code>Calendar</code> with the date.
     * @param recorded a <code>boolean</code> which is true iff the track has been recorded to PC.
     */
    public boolean addTrack (String trackId, String subject, String speaker, String talkTitle, Calendar date, boolean recorded) {
	//Check for duplicate tracks.
        for ( int i = 0; i < theTracks.size(); i++ ) {
            if ( theTracks.get(i).getTrackId().equalsIgnoreCase(trackId) ) {
                LOG.info("Duplicate number!!!");
                return false;
            }
        }
        //If not, add!!!!
        if ( theTracks.add(new Track(trackId, subject, speaker, talkTitle, date, recorded)) ) {
            Collections.sort(theTracks, new SortedTracks());
            return true;
        }
        return false;
    }

    /**
     * Add multiple tracks to this disc.
     * @param startTrack a <code>int</code> with the start track.
     * @param endTrack a <code>int</code> with the end track.
     * @param subject a <code>String</code> with the subject.
     * @param speaker a <code>String</code> with the speaker.
     * @param talkTitle a <code>String</code> with the talk title.
     * @param date a <code>Calendar</code> with the date.
     * @param recorded a <code>boolean</code> which is true iff the track has been recorded to PC.
     * @return a <code>boolean</code> which is true iff the tracks are added successfully.
     */
    public boolean addTracks (int startTrack, int endTrack, String subject, String speaker, String talkTitle, Calendar date, boolean recorded) {
        int diff = endTrack - startTrack + 1;
        int intTrack = startTrack;
        for ( int i = 0; i < diff; i++ ) {
            String track = this.formatTrackNo(intTrack);
            if ( !addTrack(track,subject,speaker,talkTitle,date, recorded) ) {
                return false;
            }
            intTrack++;
        }
        return true;
    }

    /**
     * Get all of the tracks for this disc as a String array.
     * @return a <code>String</code> array with all tracks.
     */
    public String[] getAllTracks () {
        String allTracks[] = new String[theTracks.size()];
	for ( int i = 0; i < theTracks.size(); i++ ) {
            allTracks[i] = theTracks.get(i).toString();
	} 
	java.util.Arrays.sort(allTracks);
	return allTracks;	
    }

    /**
     * Get the track at the specified position in the list.
     * @param pos a <code>int</code> with the specified position.
     * @return a <code>Track</code> object.
     */
    public Track getTrack ( int pos ) {
        return theTracks.get(pos);
    }
	
    /**
     * Clear the discs.
     */
    public void clearDisc() {
	theTracks = new LinkedList<Track>();
    }

    /**
     * Get the number of tracks on this disc.
     * @return a <code>int</code> with the number of tracks.
     */
    public int getNumTracks() {
	return theTracks.size();
    }

    /**
     * Get the track information for a particular position in the track list.
     * @param pos a <code>int</code> with the position.
     * @return a <code>String</code> with the track information.
     */
    public String getTrackInformation ( int pos ) {
        return theTracks.get(pos).getTrackInfo();
    }

    /**
     * Get the disc number.
     * @return a <code>int</code> with the disc number.
     */
    public int getDiscNumber() {
        return theDiscNumber;
    }

    /**
     * Format track number as a String i.e. with leading 0 if appropriate.
     * @param num a <code>int</code> with the track number.
     * @return a <code>String</code> with the formatted track number.
     */
    private String formatTrackNo(int num) {
        String track = "";
        if ( num > 0 && num < 10 ) {
            track = "" + num;
            track = "0" + track;
        } else {
            track = "" + num;
        }
        return track;
    }

    /**
     * Get the track object which has the same id as the supplied id.
     * @param trackId a <code>String</code> with the track id.
     * @return a <code>Track</code> object representing the track with this id.
     */
    public Track getTrackByNumber ( String trackId ) {
        for ( int i = 0; i < theTracks.size(); i++ ) {
            if ( theTracks.get(i).getTrackId().equalsIgnoreCase(trackId) ) {
                return theTracks.get(i);
            }
        }
        return null;
    }

    /**
     * Remove the track with the specified track number.
     * @param trackNumber a <code>String</code> with the trackId.
     * @return a <code>boolean</code> which is true iff the track was deleted successfully.
     */
    public boolean removeTrack ( String trackNumber ) {
        String trackNo = formatTrackNo(Integer.parseInt(trackNumber));
        for ( int i = 0; i < theTracks.size(); i++ ) {
            if ( theTracks.get(i).getTrackId().equalsIgnoreCase(trackNo) ) {
                theTracks.remove(theTracks.get(i));
                return true;
            }
        }
        return false;
    }

}