package de.davelee.mdiscis.config;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by davelee on 11.08.16.
 */
public class MenuConfigTest {

    @Test
    public void testGettersAndSetters ( ) {
        MenuConfig menuConfig = new MenuConfig();
        menuConfig.setAboutText("About text");
        assertEquals("About text", menuConfig.getAboutText());
        menuConfig.setContentsText("Contents text");
        assertEquals("Contents text", menuConfig.getContentsText());
        menuConfig.setExitText("Exit text");
        assertEquals("Exit text", menuConfig.getExitText());
        menuConfig.setFileText("File text");
        assertEquals("File text", menuConfig.getFileText());
        menuConfig.setHelpText("Help text");
        assertEquals("Help text", menuConfig.getHelpText());
        menuConfig.setLoadText("Load text");
        assertEquals("Load text", menuConfig.getLoadText());
        menuConfig.setNewText("New text");
        assertEquals("New text", menuConfig.getNewText());
        menuConfig.setSaveText("Save text");
        assertEquals("Save text", menuConfig.getSaveText());
    }

}
