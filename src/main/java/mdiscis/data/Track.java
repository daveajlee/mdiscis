package mdiscis.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents a track in the MDISCIS program.
 * @author Dave Lee
 */
public class Track {
	
    private String trackId;
    private Talk talk;
    
    private static final Logger LOG = LoggerFactory.getLogger(Track.class);
	
    /**
     * Create a new track.
     * @param trackId a <code>String</code> with the track ID.
     * @param talk a <code>Talk</code> object with the talk details.
     */
    public Track (final String trackId, final Talk talk){
        this.trackId = trackId;
        LOG.info("Track " + this.trackId + " has been recorded? " + talk.isRecorded());
        this.talk = talk;
    }

    /**
     * Get the track id.
     * @return a <code>String</code> with the track id.
     */
    public String getTrackId(){
        return trackId;
    }

    /**
     * Get the talk.
     * @return a <code>Talk</code> object with the talk details.
     */
    public Talk getTalk() {
    	return talk;
    }

    /**
     * Get the track info.
     * @return a <code>String</code> with the track info.
     */
    public String getTrackInfo ( ) {
        return trackId + ": " + talk.getTalkInformation();
    }

    /**
     * Return a string representation of this track including the associated talk.
     * @return a <code>String</code> with the string representation of this track.
     */
    public String toString(){
        return trackId + " " + talk.toString();
    }

}