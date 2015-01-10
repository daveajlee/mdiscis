package mdiscis.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class represents a talk in the MDISCIS program.
 * @author Dave Lee
 */
public class Talk {
	
    private String subject;
    private String speaker;
    private String title;
    private LocalDate date;
    private boolean recorded;
	
    /**
     * Constructor - create a blank talk.
     */ 
    public Talk() {
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
     * @return a <code>LocalDate</code> object with the date.
     */
    public LocalDate getDate ( ) {
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
     * Set subject to a new subject value.
     * @param subject a <code>String</code> with the new subject.
     */
    public void setSubject(final String subject) {
		this.subject = subject;
	}

    /**
     * Set speaker to a new speaker value.
     * @param speaker a <code>String</code> with the new speaker.
     */
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	/**
     * Set title to a new title value.
     * @param title a <code>String</code> with the new title.
     */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
     * Set date to a new date value.
     * @param date a <code>LocalDate</code> with the new date.
     */
	public void setDate(LocalDate date) {
		this.date = date;
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
        return date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
    }

    /**
     * Return a String representation of this talk.
     * @return a <code>String</code> with a string representation of this talk.
     */
    public String toString() {
        return subject + " " + speaker + " " + title + " " + date.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + recorded;
    }
        
}