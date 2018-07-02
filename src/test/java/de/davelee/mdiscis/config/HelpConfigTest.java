package de.davelee.mdiscis.config;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by davelee on 12.08.16.
 */
public class HelpConfigTest {

    @Test
    public void testGettersAndSetters ( ) {
        HelpConfig helpConfig = new HelpConfig();
        helpConfig.setAddDiscOptionPage("addDiscOptionPage");
        assertEquals("addDiscOptionPage", helpConfig.getAddDiscOptionPage());
        helpConfig.setAddDiscOptionText("addDiscOptionText");
        assertEquals("addDiscOptionText", helpConfig.getAddDiscOptionText());
        helpConfig.setAddTrackOptionPage("addTrackOptionPage");
        assertEquals("addTrackOptionPage", helpConfig.getAddTrackOptionPage());
        helpConfig.setAddTrackOptionText("addTrackOptionText");
        assertEquals("addTrackOptionText", helpConfig.getAddTrackOptionText());
        helpConfig.setClearDiscOptionPage("clearDiscOptionPage");
        assertEquals("clearDiscOptionPage", helpConfig.getClearDiscOptionPage());
        helpConfig.setClearDiscOptionText("clearDiscOptionText");
        assertEquals("clearDiscOptionText", helpConfig.getClearDiscOptionText());
        helpConfig.setDeleteDiscOptionPage("deleteDiscOptionPage");
        assertEquals("deleteDiscOptionPage", helpConfig.getDeleteDiscOptionPage());
        helpConfig.setDeleteDiscOptionText("deleteDiscOptionText");
        assertEquals("deleteDiscOptionText", helpConfig.getDeleteDiscOptionText());
        helpConfig.setDeleteTrackOptionPage("deleteTrackOptionPage");
        assertEquals("deleteTrackOptionPage", helpConfig.getDeleteTrackOptionPage());
        helpConfig.setDeleteTrackOptionText("deleteTrackOptionText");
        assertEquals("deleteTrackOptionText", helpConfig.getDeleteTrackOptionText());
        helpConfig.setEditTrackOptionPage("editTrackOptionPage");
        assertEquals("editTrackOptionPage", helpConfig.getEditTrackOptionPage());
        helpConfig.setEditTrackOptionText("editTrackOptionText");
        assertEquals("editTrackOptionText", helpConfig.getEditTrackOptionText());
        helpConfig.setHelpScreenTitle("helpScreenTitle");
        assertEquals("helpScreenTitle", helpConfig.getHelpScreenTitle());
        helpConfig.setLoadDiscStoreOptionPage("loadDiscStoreOptionPage");
        assertEquals("loadDiscStoreOptionPage", helpConfig.getLoadDiscStoreOptionPage());
        helpConfig.setLoadDiscStoreOptionText("loadDiscStoreOptionText");
        assertEquals("loadDiscStoreOptionText", helpConfig.getLoadDiscStoreOptionText());
        helpConfig.setMainScreenOptionPage("mainScreenOptionPage");
        assertEquals("mainScreenOptionPage", helpConfig.getMainScreenOptionPage());
        helpConfig.setMainScreenOptionText("mainScreenOptionText");
        assertEquals("mainScreenOptionText", helpConfig.getMainScreenOptionText());
        helpConfig.setNewDiscStoreOptionPage("newDiscStoreOptionPage");
        assertEquals("newDiscStoreOptionPage", helpConfig.getNewDiscStoreOptionPage());
        helpConfig.setNewDiscStoreOptionText("newDiscStoreOptionText");
        assertEquals("newDiscStoreOptionText", helpConfig.getNewDiscStoreOptionText());
        helpConfig.setSaveDiscStoreOptionPage("saveDiscStoreOptionPage");
        assertEquals("saveDiscStoreOptionPage", helpConfig.getSaveDiscStoreOptionPage());
        helpConfig.setSaveDiscStoreOptionText("saveDiscStoreOptionText");
        assertEquals("saveDiscStoreOptionText", helpConfig.getSaveDiscStoreOptionText());
        helpConfig.setSearchLabelText("searchLabelText");
        assertEquals("searchLabelText", helpConfig.getSearchLabelText());
        helpConfig.setTopicsLabelText("topicsLabelText");
        assertEquals("topicsLabelText", helpConfig.getTopicsLabelText());
        helpConfig.setWelcomeOptionPage("welcomeOptionPage");
        assertEquals("welcomeOptionPage", helpConfig.getWelcomeOptionPage());
        helpConfig.setWelcomeOptionText("welcomeOptionText");
        assertEquals("welcomeOptionText", helpConfig.getWelcomeOptionText());
    }

}
