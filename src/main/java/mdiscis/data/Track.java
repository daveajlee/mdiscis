package mdiscis.data;

import java.util.*;

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
     * @param subject a <code>String</code> with the subject.
     * @param speaker a <code>String</code> with the speaker.
     * @param talkTitle a <code>String</code> with the talk title.
     * @param date a <code>Calendar</code> with the date.
     * @param recorded a <code>boolean</code> which is true iff the track has been recorded to PC.
     */
    public Track (final String trackId, final String subject, final String speaker, final String talkTitle, final Calendar date, final boolean recorded){
        this.trackId = trackId;
        LOG.info("Track " + this.trackId + " has been recorded? " + recorded);
        talk = new Talk(subject,speaker,talkTitle,date, recorded);
    }

    /**
     * Get the track id.
     * @return a <code>String</code> with the track id.
     */
    public String getTrackId(){
        return trackId;
    }

    /**
     * Get the subject.
     * @return a <code>String</code> with the subject name.
     */
    public String getSubject() {
        return talk.getSubject();
    }

    /**
     * Get the speaker.
     * @return a <code>String</code> with the speaker.
     */
    public String getSpeaker ( ) {
        return talk.getSpeaker();
    }

    /**
     * Get the talk title.
     * @return a <code>String</code> with the talk title.
     */
    public String getTalkTitle ( ) {
        return talk.getTitle();
    }

    /**
     * Get the date.
     * @return a <code>Calendar</code> object with the date.
     */
    public Calendar getDate ( ) {
        return talk.getDate();
    }

    /**
     * Check whether this talk recorded on this track has been recorded.
     * @return a <code>boolean</code> which is true iff the talk has been recorded.
     */
    public boolean hasBeenRecorded ( ) {
        return talk.isRecorded();
    }

    /**
     * Get the date in format: dd/mm/yyyy.
     * @return a <code>String</code> with the date.
     */
    public String getShortDate ( ) {
        return talk.getShortDate();
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