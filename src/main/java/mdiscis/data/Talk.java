package mdiscis.data;

import java.util.*;

/**
 * This class represents a talk in the MDISCIS program.
 * @author Dave Lee
 */
public class Talk {
	
    private String subject;
    private String speaker;
    private String title;
    private Calendar date;
    private boolean recorded;
	
    /**
     * Default constructor - create a blank talk.
     */ 
    public Talk() {
    	subject = "";
    	speaker = "";
    	title = "";
    	date = null;
    	recorded = false;
    }

    /**
     * Construct a new Talk object.
     * @param subject a <code>String</code> with the subject name.
     * @param speaker a <code>String</code> with the speaker.
     * @param title a <code>String</code> with the title.
     * @param date a <code>Calendar</code> with the date.
     * @param recorded a <code>boolean</code> which is true iff the track has been recorded to PC.
     */
    public Talk(final String subject, final String speaker, final String title, final Calendar date, final boolean recorded) {
    	this.subject = subject;
    	this.speaker = speaker;
    	this.title = title;
    	this.date = date;
    	this.recorded = recorded;
    }

    /**
     * Get the subject name.
     * @return a <code>String</code> with the subject name.
     */
    public String getSubject ( ) {
        return subject;
    }

    /**
     * Get the speaker.
     * @return a <code>String</code> with the speaker.
     */
    public String getSpeaker ( ) {
        return speaker;
    }

    /**
     * Get the title.
     * @return a <code>String</code> with the title.
     */
    public String getTitle ( ) {
        return title;
    }

    /**
     * Get the date.
     * @return a <code>Calendar</code> object with the date.
     */
    public Calendar getDate ( ) {
        return date;
    }

    /**
     * Check whether this talk has been recorded.
     * @return a <code>boolean</code> which is true iff the talk has been recorded.
     */
    public boolean isRecorded ( ) {
        return recorded;
    }

    /**
     * Set record to a revised boolean value.
     * @param recorded a <code>boolean</code> which is either true or false indicating whether or not the talk has been recorded.
     */
    public void setRecorded(final boolean recorded){
        this.recorded = recorded;
    }

    /**
     * Get talk information in the form date: talk title by speaker.
     * @return a <code>String</code> with the talk information.
     */
    public String getTalkInformation ( ) {
        return getShortDate() + " - " + "(" + subject + ") - " + title + " by " + speaker;
    }

    /**
     * Get the date in format: dd/mm/yyyy.
     * @return a <code>String</code> with the date.
     */
    public String getShortDate ( ) {
        return date.get(Calendar.DAY_OF_MONTH) + "/" + (date.get(Calendar.MONTH)+1) + "/" + date.get(Calendar.YEAR);
    }

    /**
     * Return a String representation of this talk.
     * @return a <code>String</code> with a string representation of this talk.
     */
    public String toString() {
        return subject + " " + speaker + " " + title + " " + date.toString() + " " + recorded;
    }
        
}