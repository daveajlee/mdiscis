package de.davelee.mdiscis.config;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by davelee on 12.08.16.
 */
public class GUIConfigTest {

    @Test
    public void testGettersAndSetters ( ) {
        GUIConfig guiConfig = new GUIConfig();
        guiConfig.setAddDiscButtonText("addDiscButtonText");
        assertEquals("addDiscButtonText", guiConfig.getAddDiscButtonText());
        guiConfig.setAddedSuccessText("addedSuccessText");
        assertEquals("addedSuccessText", guiConfig.getAddedSuccessText());
        guiConfig.setAddTalkText("addTalkText");
        assertEquals("addTalkText", guiConfig.getAddTalkText());
        guiConfig.setAddTrackButtonText("addTrackButtonText");
        assertEquals("addTrackButtonText", guiConfig.getAddTrackButtonText());
        guiConfig.setClearDialogMessage("clearDialogMessage");
        assertEquals("clearDialogMessage", guiConfig.getClearDialogMessage());
        guiConfig.setClearDialogTitle("clearDialogTitle");
        assertEquals("clearDialogTitle", guiConfig.getClearDialogTitle());
        guiConfig.setClearDiscButtonText("clearDiscButtonText");
        assertEquals("clearDiscButtonText", guiConfig.getClearDiscButtonText());
        guiConfig.setClearSuccessText("clearSuccessText");
        assertEquals("clearSuccessText", guiConfig.getClearSuccessText());
        guiConfig.setCopyrightText("copyrightText");
        assertEquals("copyrightText", guiConfig.getCopyrightText());
        guiConfig.setDeleteButtonText("deleteButtonText");
        assertEquals("deleteButtonText", guiConfig.getDeleteButtonText());
        guiConfig.setDeleteDialogTitle("deleteDialogText");
        assertEquals("deleteDialogText", guiConfig.getDeleteDialogTitle());
        guiConfig.setDeleteDiscButtonText("deleteDiscButtonText");
        assertEquals("deleteDiscButtonText", guiConfig.getDeleteDiscButtonText());
        guiConfig.setDeleteDiscDialogMessage("deleteDiscDialogMessage");
        assertEquals("deleteDiscDialogMessage", guiConfig.getDeleteDiscDialogMessage());
        guiConfig.setDeleteErrorText("deleteErrorText");
        assertEquals("deleteErrorText", guiConfig.getDeleteErrorText());
        guiConfig.setDeleteSuccessText("deleteSuccessText");
        assertEquals("deleteSuccessText", guiConfig.getDeleteSuccessText());
        guiConfig.setDeleteTrackDialogMessage("deleteTrackDialogMessage");
        assertEquals("deleteTrackDialogMessage", guiConfig.getDeleteTrackDialogMessage());
        guiConfig.setDiscLabelText("discLabelText");
        assertEquals("discLabelText", guiConfig.getDiscLabelText());
        guiConfig.setDiscText("discText");
        assertEquals("discText", guiConfig.getDiscText());
        guiConfig.setEditButtonText("editButtonText");
        assertEquals("editButtonText", guiConfig.getEditButtonText());
        guiConfig.setEditErrorText("editErrorText");
        assertEquals("editErrorText", guiConfig.getEditErrorText());
        guiConfig.setEditLabelText("editLabelText");
        assertEquals("editLabelText", guiConfig.getEditLabelText());
        guiConfig.setExitDialogMessage("exitDialogMessage");
        assertEquals("exitDialogMessage", guiConfig.getExitDialogMessage());
        guiConfig.setExitDialogTitle("exitDialogTitle");
        assertEquals("exitDialogTitle", guiConfig.getExitDialogTitle());
        guiConfig.setFileExtension("fileExtension");
        assertEquals("fileExtension", guiConfig.getFileExtension());
        guiConfig.setFileExtensionName("fileExtensionName");
        assertEquals("fileExtensionName", guiConfig.getFileExtensionName());
        guiConfig.setLoadDialogMessage("loadDialogMessage");
        assertEquals("loadDialogMessage", guiConfig.getLoadDialogMessage());
        guiConfig.setLoadDialogTitle("loadDialogTitle");
        assertEquals("loadDialogTitle", guiConfig.getLoadDialogTitle());
        guiConfig.setLoadFileDialogTitle("loadFileDialogTitle");
        assertEquals("loadFileDialogTitle", guiConfig.getLoadFileDialogTitle());
        guiConfig.setLoadFileErrorText("loadFileErrorText");
        assertEquals("loadFileErrorText", guiConfig.getLoadFileErrorText());
        guiConfig.setLoadFileSuccessText("loadFileSuccessText");
        assertEquals("loadFileSuccessText", guiConfig.getLoadFileSuccessText());
        guiConfig.setLoadingText("loadingText");
        assertEquals("loadingText", guiConfig.getLoadingText());
        guiConfig.setNewDialogMessage("newDialogMessage");
        assertEquals("newDialogMessage", guiConfig.getNewDialogMessage());
        guiConfig.setNewDialogTitle("newDialogTitle");
        assertEquals("newDialogTitle", guiConfig.getNewDialogTitle());
        guiConfig.setNewSuccessText("newSuccessText");
        assertEquals("newSuccessText", guiConfig.getNewSuccessText());
        guiConfig.setNextTrackButtonText("nextTrackButtonText");
        assertEquals("nextTrackButtonText", guiConfig.getNextTrackButtonText());
        guiConfig.setNoOptionText("noOptionText");
        assertEquals("noOptionText", guiConfig.getNoOptionText());
        guiConfig.setNumDisplayTracks(1);
        assertEquals(1, guiConfig.getNumDisplayTracks());
        guiConfig.setPreviousTrackButtonText("previousTrackButtonText");
        assertEquals("previousTrackButtonText", guiConfig.getPreviousTrackButtonText());
        guiConfig.setSaveFileDialogTitle("saveFileDialogTitle");
        assertEquals("saveFileDialogTitle", guiConfig.getSaveFileDialogTitle());
        guiConfig.setSaveFileErrorText("saveFileErrorText");
        assertEquals("saveFileErrorText", guiConfig.getSaveFileErrorText());
        guiConfig.setSaveFileSuccessText("saveFileSuccessText");
        assertEquals("saveFileSuccessText", guiConfig.getSaveFileSuccessText());
        guiConfig.setStatusReadyText("statusReadyText");
        assertEquals("statusReadyText", guiConfig.getStatusReadyText());
        guiConfig.setTitle("title");
        assertEquals("title", guiConfig.getTitle());
        guiConfig.setTrackLabelText("trackLabelText");
        assertEquals("trackLabelText", guiConfig.getTrackLabelText());
        guiConfig.setTrackText("trackText");
        assertEquals("trackText", guiConfig.getTrackText());
        guiConfig.setVersionText("versionText");
        assertEquals("versionText", guiConfig.getVersionText());
        guiConfig.setYesOptionText("yesOptionText");
        assertEquals("yesOptionText", guiConfig.getYesOptionText());
    }

}
