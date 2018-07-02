package de.davelee.mdiscis.config;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by davelee on 11.08.16.
 */
public class AddDialogConfigTest {

    @Test
    public void testGettersAndSetters ( ) {
        AddDialogConfig addDialogConfig = new AddDialogConfig();
        addDialogConfig.setAddErrorText("This is my add error text");
        assertEquals("This is my add error text", addDialogConfig.getAddErrorText());
        addDialogConfig.setAddSuccessText("This is my add success text");
        assertEquals("This is my add success text", addDialogConfig.getAddSuccessText());
        addDialogConfig.setAddTalkText("This is my add talk text");
        assertEquals("This is my add talk text", addDialogConfig.getAddTalkText());
        addDialogConfig.setCancelButtonText("This is my cancel button text");
        assertEquals("This is my cancel button text", addDialogConfig.getCancelButtonText());
        addDialogConfig.setDateSymbol("This is my date symbol");
        assertEquals("This is my date symbol", addDialogConfig.getDateSymbol());
        addDialogConfig.setDateText("This is my date text");
        assertEquals("This is my date text", addDialogConfig.getDateText());
        addDialogConfig.setDiscLabelText("This is my disc label text");
        assertEquals("This is my disc label text", addDialogConfig.getDiscLabelText());
        addDialogConfig.setEditErrorText("This is my edit error text");
        assertEquals("This is my edit error text", addDialogConfig.getEditErrorText());
        addDialogConfig.setEditSuccessText("This is my edit success text");
        assertEquals("This is my edit success text", addDialogConfig.getEditSuccessText());
        addDialogConfig.setEditTalkText("This is my edit talk text");
        assertEquals("This is my edit talk text", addDialogConfig.getEditTalkText());
        addDialogConfig.setEndTrackErrorMessage("This is my end track error message text");
        assertEquals("This is my end track error message text", addDialogConfig.getEndTrackErrorMessage());
        addDialogConfig.setEndTrackErrorTitle("This is my end track error title text");
        assertEquals("This is my end track error title text", addDialogConfig.getEndTrackErrorTitle());
        addDialogConfig.setEndTrackText("This is my end track text");
        assertEquals("This is my end track text", addDialogConfig.getEndTrackText());
        addDialogConfig.setOfDiscLabel("This is my of disc label text");
        assertEquals("This is my of disc label text", addDialogConfig.getOfDiscLabel());
        addDialogConfig.setOkButtonText("This is my ok button text");
        assertEquals("This is my ok button text", addDialogConfig.getOkButtonText());
        addDialogConfig.setRecordedText("This is my recorded text");
        assertEquals("This is my recorded text", addDialogConfig.getRecordedText());
        addDialogConfig.setSpeakerText("This is my speaker text");
        assertEquals("This is my speaker text", addDialogConfig.getSpeakerText());
        addDialogConfig.setSubjectText("This is my subject text");
        assertEquals("This is my subject text", addDialogConfig.getSubjectText());
        addDialogConfig.setStartTrackText("This is my start track text");
        assertEquals("This is my start track text", addDialogConfig.getStartTrackText());
        addDialogConfig.setTalkTitleText("This is my talk title text");
        assertEquals("This is my talk title text", addDialogConfig.getTalkTitleText());
        addDialogConfig.setTrackLabel("This is my track text");
        assertEquals("This is my track text", addDialogConfig.getTrackLabel());
        addDialogConfig.setTrackText("This is my track text");
        assertEquals("This is my track text", addDialogConfig.getTrackText());
    }

}
