package de.davelee.mdiscis.data;

/**
 * This class represents a track in the MDISCIS program.
 * @author Dave Lee
 */
public class Track {
	
    private int trackId;
    private Talk talk;
    
    /**
     * Default constructor.
     */
    public Track() { 	
    }

    /**
     * Get the track id.
     * @return an <code>int</code> with the track id.
     */
    public int getTrackId(){
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
     * Set the track id.
     * @param trackId a <code>int</code> with the track id.
     */
    public void setTrackId(int trackId) {
		this.trackId = trackId;
	}

    /**
     * Set the talk.
     * @param talk a <code>Talk</code> with the talk information.
     */
	public void setTalk(Talk talk) {
		this.talk = talk;
	}

	/**
     * Return a string representation of this track including the associated talk.
     * @return a <code>String</code> with the string representation of this track.
     */
    public String toString(){
        return trackId + " " + talk.toString();
    }

}