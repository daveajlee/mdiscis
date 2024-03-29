package de.davelee.mdiscis.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.*;

import de.davelee.mdiscis.data.DiscStore;
import de.davelee.mdiscis.data.Talk;
import de.davelee.mdiscis.data.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.davelee.mdiscis.config.AddDialogConfig;
import de.davelee.mdiscis.config.GUIConfig;
import de.davelee.mdiscis.config.HelpConfig;
import de.davelee.mdiscis.config.MenuConfig;

/**
 * This class creates and displays the user interface for the MDISCIS program.
 * @author Dave Lee
 */
public class MDISCISGUI extends JFrame {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -1685964927592081737L;
	
	private static final Logger LOG = LoggerFactory.getLogger(MDISCISGUI.class);

    /**
     * Storage object of all discs available
     */
    private DiscStore discStore;
    /**
     * Status bar to show comments to the user
     */
    private JLabel statusBar;
    /**
     * Model containing list of discs available
     */
    private DefaultComboBoxModel<Integer> discModel;
    /**
     * Drop-down list to choose a disc.
     */
    private JComboBox<Integer> discBox;
    /**
     * Button to clear a disc.
     */
    private JButton clearDiscButton;
    /**
     * Button to delete a disc.
     */
    private JButton deleteDiscButton;
    /**
     * Panel to show dialogs.
     */
    private JPanel dialogPanel;
    /**
     * Panel to show content.
     */
    private JPanel contentsPanel;
    /**
     * Button to display previous tracks.
     */
    private JButton previousTracksButton;
    /**
     * Button to display next tracks.
     */
    private JButton nextTracksButton;
    /**
     * Button to add tracks.
     */
    private JButton addTracksButton;

    private static final String FONT_NAME = "Arial";

    /**
     * This is the page of tracks that we are showing.
     */
    private int page = 1;

    /**
     * Config object for general GUI.
     */
    private GUIConfig guiConfig;
    /**
     * Config object for menu.
     */
    private MenuConfig menuConfig;
    /**
     * Config object for help.
     */
    private HelpConfig helpConfig;
    /**
     * Configuration with translated texts for Add Dialog.
     */
    private AddDialogConfig addDialogConfig;

    /**
     * Create and display the user interface to the user.
     * @param discStore a <code>DiscStore</code> representing the current discs to display as part of the interface.
     * @param guiConfig a <code>GUIConfig</code> representing the localisation texts for the GUI.
     * @param menuConfig a <code>MenuConfig</code> representing the localisation texts for the Menu.
     * @param helpConfig a <code>HelpConfig</code> representing the localisation texts for the Help screen.
     * @param addDialogConfig a <code>AddDialogConfig</code> representing the localisation texts for the Add Dialog.
     */
    public MDISCISGUI ( final DiscStore discStore, final GUIConfig guiConfig, final MenuConfig menuConfig, final HelpConfig helpConfig, final AddDialogConfig addDialogConfig ) {
        this.discStore = discStore;
        this.guiConfig = guiConfig;
        this.menuConfig = menuConfig;
        this.helpConfig = helpConfig;
        this.addDialogConfig = addDialogConfig;
        createInterface();
    }
    
    /**
     * Create and display the user interface to the user.
     * @param guiConfig a <code>GUIConfig</code> representing the localisation texts for the GUI.
     * @param menuConfig a <code>MenuConfig</code> representing the localisation texts for the Menu.
     * @param helpConfig a <code>HelpConfig</code> representing the localisation texts for the Help screen.
     * @param addDialogConfig a <code>AddDialogConfig</code> representing the localisation texts for the Add Dialog.
     */
    public MDISCISGUI ( final GUIConfig guiConfig, final MenuConfig menuConfig, final HelpConfig helpConfig, final AddDialogConfig addDialogConfig ) {
        this(new DiscStore(), guiConfig, menuConfig, helpConfig, addDialogConfig);
    }

    /**
     * Private method to create the interface.
     */
    private void createInterface ( ) {
        this.setTitle(guiConfig.getTitle());
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        //Call the Exit method in the UserInterface class if the user hits exit.
        this.addWindowListener ( new WindowAdapter() {
            public void windowClosing ( WindowEvent e ) {
                exit();
            }
        });

        //Set image icon.
        Image img = Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/images/mdiscis-logo-icon.png"));
        setIconImage(img);

        Container c = getContentPane();
        dialogPanel = new JPanel(new BorderLayout());

        //Create centre panel.
        JPanel centrePanel = new JPanel(new BorderLayout());
        centrePanel.setBackground(Color.WHITE);

        //Now add discControlPanel to centrePanel.
        dialogPanel.add(createDiscControlPanel(), BorderLayout.NORTH);

        //Finally, drawContentsPanel and add to dialogPanel.
        contentsPanel = drawContentsPanel();
        dialogPanel.add(contentsPanel, BorderLayout.CENTER);

        //Create south panel.
        JPanel southPanel = new JPanel(new BorderLayout());
        //Now create previous and next tracks button and add tracks button - these should be greyed out as necessary.
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        //Create previous track button.
        previousTracksButton = new JButton(guiConfig.getPreviousTrackButtonText());
        previousTracksButton.setEnabled(page != 1); 
        previousTracksButton.addActionListener(e -> processPreviousTrackButton((Integer) discBox.getSelectedItem()));
        buttonPanel.add(previousTracksButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5,0)));
        //Create next track button.
        nextTracksButton = new JButton(guiConfig.getNextTrackButtonText());
        nextTracksButton.setEnabled(discStore.getNumDiscs() > 0 && discStore.getDisc((Integer) discBox.getSelectedItem()).getNumTracks() > (page*guiConfig.getNumDisplayTracks() ));
        nextTracksButton.addActionListener(e -> {
            page+=1;
            processNextTrackButton(page, (Integer) discBox.getSelectedItem());
        });
        buttonPanel.add(nextTracksButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20,0)));
        //Create add track button.
        addTracksButton = new JButton(guiConfig.getAddTrackButtonText());
        addTracksButton.setEnabled(discStore.getNumDiscs() > 0);
        addTracksButton.addActionListener(e -> showAddDialog(guiConfig.getAddTalkText()));
        buttonPanel.add(addTracksButton);
        //Add button panel to south panel.
        southPanel.add(buttonPanel, BorderLayout.NORTH);
        //Add statusBar to south panel.
        statusBar = new JLabel(guiConfig.getStatusReadyText());
        southPanel.add(statusBar, BorderLayout.SOUTH);
        dialogPanel.add(southPanel,BorderLayout.SOUTH);

        //Add dialog panel to c.
        c.add(dialogPanel, BorderLayout.CENTER);

        initialiseMenu();

        //Position the screen at the center of the screen.
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension screenDim = tools.getScreenSize();
        Dimension displayDim = new Dimension(750,600);
        this.setLocation (  (screenDim.width/2)-(displayDim.width/2), (screenDim.height/2)-(displayDim.height/2));

    }
    
    /**
     * Process the previous track button for the selected disc.
     * @param selectedDisc a <code>int</code> with the disc currently selected in the interface.
     */
    public void processPreviousTrackButton ( int selectedDisc) {
    	page -= 1;
        contentsPanel = drawContentsPanel();
        dialogPanel.remove(dialogPanel.getComponent(1));
        dialogPanel.add(contentsPanel, 1);
        dialogPanel.revalidate();
        dialogPanel.repaint();
        previousTracksButton.setEnabled(page != 1); 
        if (discStore.getDisc(selectedDisc)!=null) {
        	nextTracksButton.setEnabled(discStore.getDisc(selectedDisc).getNumTracks() > (page*guiConfig.getNumDisplayTracks() ) ); 
        }
    }
    
    /**
     * Process the next track button for this page and the selected disc.
     * @param page a <code>int</code> with the page number for the selected disc which is currently selected in the user interface.
     * @param selectedDisc a <code>int</code> with the disc currently selected in the interface.
     */
    public void processNextTrackButton ( final int page, final int selectedDisc ) {
    	this.page = page;
        contentsPanel = drawContentsPanel();
        dialogPanel.remove(dialogPanel.getComponent(1));
        dialogPanel.add(contentsPanel, 1);
        dialogPanel.revalidate();
        dialogPanel.repaint();
        previousTracksButton.setEnabled(this.page != 1);
        if (discStore.getDisc(selectedDisc)!=null) {
        	nextTracksButton.setEnabled(discStore.getDisc(selectedDisc).getNumTracks() > (this.page*guiConfig.getNumDisplayTracks() )); 
        }
    }
    
    /**
     * Display the front screen to the user.
     */
    public void displayScreen ( ) {
        this.pack ();
        this.setVisible (true);
        this.setSize ( new Dimension(750,600) );
    }
    
    /**
     * Create the disc control panel with buttons to choose discs etc.
     * @return a <code>JPanel</code> representing the generated user interface panel.
     */
    public JPanel createDiscControlPanel ( ) {
        JPanel discControlPanel = new JPanel(new GridBagLayout());
        JLabel discLabel = new JLabel(guiConfig.getDiscLabelText());
        discLabel.setFont(new Font(FONT_NAME, Font.BOLD, 14));
        discModel = new DefaultComboBoxModel<>();
        for ( int i = 0; i < discStore.getNumDiscs(); i++ ) {
            discModel.addElement(discStore.getDiscNumber(i));
        }
        discBox = new JComboBox<>(discModel);
        discBox.setFont(new Font(FONT_NAME, Font.PLAIN, 14));
        discBox.addItemListener(e -> {
            if ( discBox.getSelectedItem() != null ) {
                processNextTrackButton(1, (Integer) discBox.getSelectedItem());
            }
        });
        //Add label and box + then spacer.
        discControlPanel.add(discLabel);
        discControlPanel.add(discBox);
        discControlPanel.add(Box.createRigidArea(new Dimension(10,0)));
        //Now add a clear disc button.
        clearDiscButton = new JButton(guiConfig.getClearDiscButtonText()); 
        clearDiscButton.setEnabled(discStore.getNumDiscs() > 0); 
        clearDiscButton.addActionListener(e -> clearDisc((Integer) discBox.getSelectedItem()));
        discControlPanel.add(clearDiscButton);
        discControlPanel.add(Box.createRigidArea(new Dimension(5,0)));
        //Now create the delete disc button.
        deleteDiscButton = new JButton(guiConfig.getDeleteDiscButtonText());
        deleteDiscButton.setEnabled(discStore.getNumDiscs() > 0); 
        deleteDiscButton.addActionListener(e -> deleteDisc((Integer) discBox.getSelectedItem()));
        discControlPanel.add(deleteDiscButton);
        discControlPanel.add(Box.createRigidArea(new Dimension(25,0)));
        //Now create add another disc button.
        JButton addDiscButton = new JButton(guiConfig.getAddDiscButtonText());
        addDiscButton.addActionListener(e -> addAnotherDisc());
        discControlPanel.add(addDiscButton);
        discControlPanel.add(Box.createRigidArea(new Dimension(0,10)));
        return discControlPanel;
    }
    
    /**
     * Initialise the menu bar displayed at the top of the screen.
     */
    public void initialiseMenu ( ) {
    	JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu(menuConfig.getFileText());
        menuBar.add(fileMenu);
        JMenuItem menuItem;

        menuItem = new JMenuItem(menuConfig.getNewText());
        menuItem.addActionListener (e -> MDISCISGUI.this.newFile(JOptionPane.showOptionDialog(MDISCISGUI.this, guiConfig.getNewDialogMessage(), guiConfig.getNewDialogTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { guiConfig.getYesOptionText(), guiConfig.getNoOptionText() }, guiConfig.getNoOptionText())));
        fileMenu.add(menuItem);

        menuItem = new JMenuItem(menuConfig.getLoadText());
        menuItem.addActionListener (e -> {
            int result = showOptionDialog(guiConfig.getLoadDialogMessage(), guiConfig.getLoadDialogTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if ( result == JOptionPane.YES_OPTION ) {
                JFileChooser fileChooser = MDISCISGUI.this.showOpenFileDialog();
                MDISCISGUI.this.loadFile(fileChooser.showOpenDialog(MDISCISGUI.this), fileChooser.getSelectedFile());
            }
        });
        fileMenu.add(menuItem);

        fileMenu.addSeparator();

        menuItem = new JMenuItem(menuConfig.getSaveText());
        menuItem.addActionListener (e -> {
            JFileChooser fileChooser = MDISCISGUI.this.showSaveFileDialog();
            MDISCISGUI.this.saveFile(fileChooser.showSaveDialog(MDISCISGUI.this), fileChooser.getSelectedFile());
        });
        fileMenu.add(menuItem);

        fileMenu.addSeparator();

        menuItem = new JMenuItem(menuConfig.getExitText());
        menuItem.addActionListener (e -> exit());
        fileMenu.add(menuItem);

        JMenu helpMenu = new JMenu(menuConfig.getHelpText());
        menuBar.add(helpMenu);

        menuItem = new JMenuItem(menuConfig.getContentsText());
        menuItem.addActionListener (e -> {
            HelpScreen screen = new HelpScreen(helpConfig);
            screen.displayScreen();
        });
        helpMenu.add(menuItem);

        menuItem = new JMenuItem(menuConfig.getAboutText());
        menuItem.addActionListener (e -> {
            SplashScreen screen = new SplashScreen(true, guiConfig);
            screen.displayScreen();
        });
        helpMenu.add(menuItem);
    }
    
    /**
     * Display a dialog to the user asking if they wish to exit MDISCIS.
     * If yes, then exit the program.
     */
    public void exit() {
        ImageIcon imageIcon = new ImageIcon(MDISCISGUI.class.getResource("/images/mdiscis-logo-icon.png"));
    	int result = JOptionPane.showOptionDialog(MDISCISGUI.this, guiConfig.getExitDialogMessage(), guiConfig.getExitDialogTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, imageIcon, new String[] { guiConfig.getYesOptionText(), guiConfig.getNoOptionText() }, guiConfig.getNoOptionText());
        if ( result == JOptionPane.YES_OPTION ) {
            System.exit(0);
        }
    }

    /**
     * This method draws the contents panel as requested.
     * @return a <code>JPanel</code> with the drawn contents panel.
     */
    public JPanel drawContentsPanel ( ) {
        JPanel myContentsPanel = new JPanel(new GridLayout(guiConfig.getNumDisplayTracks(),1,5,5));
        myContentsPanel.setBorder(BorderFactory.createEtchedBorder());
        myContentsPanel.setBackground(Color.WHITE);
        //Initialise button arrays.
        JButton[] editTrackButtons = new JButton[guiConfig.getNumDisplayTracks()];
        JButton[] deleteTrackButtons = new JButton[guiConfig.getNumDisplayTracks()];
        //Enable the buttons if tracks is greater than 10.
        if ( discBox.getSelectedItem() != null && nextTracksButton != null && discStore.getDisc((Integer) discBox.getSelectedItem()).getNumTracks() > 10 ) {
            nextTracksButton.setEnabled(true);
        }
        //Do some test data for the moment.
        for ( int i = 0; i < guiConfig.getNumDisplayTracks(); i++ ) {
            //Create a panel.
            JPanel thisPanel = new JPanel();
            thisPanel.setLayout(new BoxLayout(thisPanel, BoxLayout.LINE_AXIS));
            thisPanel.setBackground(Color.WHITE);
            //Check if we have a track or if we just have a blank space.
            int trackNum = ((page-1)*guiConfig.getNumDisplayTracks()) + i;
            if ( discStore.getNumDiscs() > 0 && trackNum < discStore.getDisc((Integer) discBox.getSelectedItem()).getNumTracks() ) {
                //Create a label which will hold the track info.
                JLabel trackLabel = new JLabel(guiConfig.getTrackText() + discStore.getTrackInformation((Integer) discBox.getSelectedItem(), trackNum));
                trackLabel.setFont(new Font(FONT_NAME, Font.ITALIC, 12));
                thisPanel.add(trackLabel);
                thisPanel.add(Box.createRigidArea(new Dimension(20, 0)));
                //Create an edit button.
                editTrackButtons[i] = new JButton(guiConfig.getEditButtonText());
                editTrackButtons[i].addActionListener( new ActionListener() {
                    private int theTrackNumber = discStore.getTrackNumber((Integer) discBox.getSelectedItem(), trackNum);
                    public void actionPerformed ( ActionEvent e ) {
                        editTrack((Integer) discBox.getSelectedItem(), theTrackNumber);
                    }
                });
                thisPanel.add(editTrackButtons[i]);
                thisPanel.add(Box.createRigidArea(new Dimension(10, 0)));
                //Create a delete button.
                deleteTrackButtons[i] = new JButton(guiConfig.getDeleteButtonText());
                deleteTrackButtons[i].addActionListener( new ActionListener() {
                    private int theTrackNumber = discStore.getTrackNumber((Integer) discBox.getSelectedItem(), trackNum);
                    public void actionPerformed ( ActionEvent e ) {
                        performDeleteTrack(theTrackNumber);
                    }
                });
                thisPanel.add(deleteTrackButtons[i]);
            }
            //Finally add this panel to the contents panel.
            myContentsPanel.add(thisPanel);
        }
        //Return contents panel.
        return myContentsPanel;
    }
    
    /**
     * Delete the selected track after asking the user if they really want to delete the track.
     * @param trackNumber a <code>int</code> with the track number that the user wishes to delete.
     */
    public void performDeleteTrack ( final int trackNumber ) {
    	int confirm = JOptionPane.showConfirmDialog(MDISCISGUI.this, guiConfig.getDiscLabelText() + (Integer) discBox.getSelectedItem() + " " + guiConfig.getTrackLabelText() + trackNumber + guiConfig.getDeleteTrackDialogMessage(), guiConfig.getDeleteDialogTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if ( confirm == JOptionPane.YES_OPTION ) {
            if ( discStore.removeTrack((Integer) discBox.getSelectedItem(), trackNumber) ) {
                updateStatus(guiConfig.getTrackText() + trackNumber + guiConfig.getDeleteSuccessText());
                refreshDisplay();
            } else {
                updateStatus(guiConfig.getTrackText() + trackNumber + guiConfig.getDeleteErrorText());
            }
        }
    }

    /**
     * Update status bar with the provided text.
     * @param message a <code>String</code> with the message to display in the status bar.
     */
    public void updateStatus ( String message ) {
        statusBar.setText(message);
    }

    /**
     * Method to refresh the display.
     */
    public void refreshDisplay ( ) {
        contentsPanel = drawContentsPanel();
        dialogPanel.remove(dialogPanel.getComponent(1));
        dialogPanel.add(contentsPanel, 1);
        dialogPanel.revalidate();
        dialogPanel.repaint();
    }

    /**
     * Public method to add another disc.
     */
    public void addAnotherDisc() {
        int nextDiscNumber = discStore.addDisc();
        discModel.addElement(nextDiscNumber);
        statusBar.setText(guiConfig.getDiscText() + nextDiscNumber + guiConfig.getAddedSuccessText());
        if ( discStore.getNumDiscs() == 1 ) {
            clearDiscButton.setEnabled(true);
            deleteDiscButton.setEnabled(true);
            addTracksButton.setEnabled(true);
        }
    }

    /**
     * Private method to clear discs.
     * @param discNumber a <code>int</code> with the number of the disc to clear.
     */
    public void clearDisc(int discNumber) {
        int confirm = showConfirmDialog(guiConfig.getDiscText() + discNumber + guiConfig.getClearDialogMessage(), guiConfig.getClearDialogTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if ( confirm == JOptionPane.YES_OPTION ) {
            discStore.clearDisc(discNumber);
            //Refresh screen.
            MDISCISGUI gui = new MDISCISGUI(discStore, guiConfig, menuConfig, helpConfig, addDialogConfig);
            gui.statusBar.setText(guiConfig.getDiscText() + discNumber + guiConfig.getClearSuccessText());
            gui.displayScreen();
            dispose();
        }
    }
    
    /**
     * Display a confirmation panel according to the specified options.
     * @param message a <code>String</code> with the message to display to the user.
     * @param title a <code>String</code> with the dialog title.
     * @param optionType a <code>int</code> representing which options to display to the user - basis: JOptionPane.
     * @param messageType a <code>int</code> representing the type of message e.g. INFORMATION_MESSAGE, ERROR_MESSAGE etc.
     * @return a <code>int</code> with the result of the confirmation dialog e.g. YES_OPTION, NO_OPTION.
     */
    public int showConfirmDialog(final String message, final String title, final int optionType, final int messageType ) {
    	return JOptionPane.showConfirmDialog(this, message, title, optionType, messageType);
    }

    /**
     * Private method to delete disc.
     * @param discNumber a <code>int</code> with the number of the disc to delete.
     */
    public void deleteDisc(int discNumber) {
        int confirm = showConfirmDialog(guiConfig.getDiscText() + discNumber + guiConfig.getDeleteDiscDialogMessage(), guiConfig.getDeleteDialogTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if ( confirm == JOptionPane.YES_OPTION ) {
            discStore.deleteDisc(discNumber);
            discModel.removeElement(discNumber);
        }
        if ( discStore.getNumDiscs() == 0 ) {
            clearDiscButton.setEnabled(false);
            deleteDiscButton.setEnabled(false);
            addTracksButton.setEnabled(false);
        }
        //Refresh screen.
        MDISCISGUI gui = new MDISCISGUI(discStore, guiConfig, menuConfig, helpConfig, addDialogConfig);
        gui.statusBar.setText(guiConfig.getDiscText() + discNumber + guiConfig.getDeleteSuccessText());
        gui.displayScreen();
        dispose();
    }

    /**
     * Private method to display add dialog.
     */
    private void showAddDialog(final String title) {
        //Construct add dialog and it will do the rest!
        AddDialog dialog = new AddDialog(this, title, discStore, this);
        dialog.displayDialog();
    }

    /**
     * Private method to edit track.
     * @param discNumber a <code>int</code> with the disc number.
     * @param trackNumber a <code>int</code> with the track number.
     */
    public void editTrack(int discNumber, int trackNumber) {
        //Get the track to be edited.
        Track myTrack = discStore.getDisc(discNumber).getTrackByNumber(trackNumber);
        //Check that it is not null.
        if ( myTrack == null ) {
            statusBar.setText(guiConfig.getEditErrorText());
            return;
        }
        showEditDialog(guiConfig.getEditLabelText(), discNumber, trackNumber, myTrack.getTalk());
    }
    
    /**
     * Show an edit dialog based on the specified parameters.
     * @param title a <code>String</code> with the title of the track.
     * @param discNumber a <code>int</code> with the disc number.
     * @param trackNumber a <code>int</code> with the track number.
     * @param talk a <code>Talk</code> object representing the details of the talk currently saved to this track.
     */
    public void showEditDialog(final String title, final int discNumber, final int trackNumber, final Talk talk) {
    	//Construct edit dialog.
        AddDialog dialog = new AddDialog(this, title, discStore, discNumber, trackNumber, talk, this);
        dialog.displayDialog();
    }

    /**
     * Save file.
     * @return a <code>JFileChooser</code> object to display a dialog to the user allowing them to choose a file to save the contents to.
     */
    public JFileChooser showSaveFileDialog ( ) {
        //Create file dialog box.
        JFileChooser fileDialog = new JFileChooser();
        fileDialog.setDialogTitle(guiConfig.getSaveFileDialogTitle());
        //Only display files with mdi extension.
        FileNameExtensionFilter filter = new FileNameExtensionFilter(guiConfig.getFileExtensionName(), guiConfig.getFileExtension());
        fileDialog.setFileFilter(filter);
        //Display file dialog.
        return fileDialog;
    }
    
    /**
     * Load file.
     * @return a <code>JFileChooser</code> object to display a dialog to the user allowing them to choose a file to load the contents from.
     */
    public JFileChooser showOpenFileDialog ( ) {
    	//Create file dialog box.
        JFileChooser fileDialog = new JFileChooser();
        fileDialog.setDialogTitle(guiConfig.getLoadFileDialogTitle());
        //Only display files with mdi extension.
        FileNameExtensionFilter filter = new FileNameExtensionFilter(guiConfig.getFileExtensionName(), guiConfig.getFileExtension());
        fileDialog.setFileFilter(filter);
        //Display file dialog.
        return fileDialog;
    }
    
    /**
     * Save the file.
     * @param returnVal a <code>int</code> based on the result of the dialog box e.g. APPROVE_OPTION.
     * @param selectedFile a <code>File</code> object representing the selected file to save the contents to.
     * @return a <code>boolean</code> which is true iff the file was saved successfully.
     */
    public boolean saveFile ( final int returnVal, final File selectedFile ) {
        //Check if user submitted file.
        if ( returnVal == JFileChooser.APPROVE_OPTION ) {
            if ( discStore.saveFile(selectedFile) ) {
                String fileName = selectedFile.getPath();
                if ( !fileName.endsWith("." + guiConfig.getFileExtension()) ) { 
                	fileName += "." + guiConfig.getFileExtension(); 
                }
                statusBar.setText(guiConfig.getSaveFileSuccessText() + fileName);
                return true;
            } else {
                statusBar.setText(guiConfig.getSaveFileErrorText());
                return false;
            }
        }
        return false;
    }

    /**
     * Show an option dialog based on the specified parameters.
     * @param message a <code>String</code> with the message to display to the user.
     * @param title a <code>String</code> with the dialog title.
     * @param options a <code>int</code> representing which options to display to the user - basis: JOptionPane.
     * @param messageType a <code>int</code> representing the type of message e.g. INFORMATION_MESSAGE, ERROR_MESSAGE etc.
     * @return a <code>int</code> with the result of the confirmation dialog e.g. YES_OPTION, NO_OPTION.
     */
    public int showOptionDialog ( final String message, final String title, final int options, final int messageType ) {
    	return JOptionPane.showOptionDialog(MDISCISGUI.this, message, title, options, messageType, null, new String[] { guiConfig.getYesOptionText(), guiConfig.getNoOptionText() }, guiConfig.getNoOptionText());
    }
    
    /**
     * Load the display screen based on the supplied disc store.
     * @param discStore a <code>DiscStore</code> representing the discs to display in the user interface.
     */
    public void loadDisplayScreen( final DiscStore discStore ) {
    	MDISCISGUI gui = new MDISCISGUI(discStore, guiConfig, menuConfig, helpConfig, addDialogConfig);
        gui.displayScreen();
        gui.statusBar.setText(guiConfig.getLoadFileSuccessText());
        dispose();
    }
    
    /**
     * Load file.
     * @param returnVal a <code>int</code> which represents the user response to the JFileChooser Dialog.
     * @param selectedFile a <code>File</code> object representing the file to load the data from.
     * @return a <code>boolean</code> which is true iff the file was loaded successfully.
     */
    public boolean loadFile ( final int returnVal, final File selectedFile ) {
    	//Check if user submitted file.
        boolean validFile = true;
        if ( returnVal == JFileChooser.APPROVE_OPTION) {
        	if ( discStore.loadFile(selectedFile) ) {
        		loadDisplayScreen(discStore);
                return true;
            }
            validFile = false;
        }
        if ( !validFile ) {
        	this.statusBar.setText(guiConfig.getLoadFileErrorText());
        	return false;
        }
        return false;
    }

    /**
     * Create a new disc store.
     * @param result a <code>int</code> which contains the result of a JOptionPane.
     */
    public void newFile ( final int result ) {
        //Check that the user wants to lose data.
        if ( result == JOptionPane.YES_OPTION ) {
            discStore = new DiscStore();
            MDISCISGUI gui = new MDISCISGUI(discStore, guiConfig, menuConfig, helpConfig, addDialogConfig);
            gui.displayScreen();
            gui.statusBar.setText(guiConfig.getNewSuccessText());
            dispose();
        }
    }
    
    /**
     * Retrieve the configuration for the add dialog.
     * @return a <code>AddDialogConfig</code> representing the configuration for the add dialog.
     */
    public AddDialogConfig getAddDialogConfig() {
		return addDialogConfig;
	}

    /**
     * Set the configuration for the add dialog.
     * @param addDialogConfig a <code>AddDialogConfig</code> representing the configuration for the add dialog.
     */
	public void setAddDialogConfig(final AddDialogConfig addDialogConfig) {
		this.addDialogConfig = addDialogConfig;
	}

	/**
     * Main method to run the MDISCIS program.
     * @param args a <code>String</code> array of arguments which are not presently used.
     */
    public static void main(String[] args) {
    	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        GUIConfig guiConfig = context.getBean(GUIConfig.class);
        MenuConfig menuConfig = context.getBean(MenuConfig.class);
        HelpConfig helpConfig = context.getBean(HelpConfig.class);
        AddDialogConfig addDialogConfig = context.getBean(AddDialogConfig.class);
        try {
            //Show splash screen.
            SplashScreen ss = new SplashScreen(false, guiConfig);
            ss.displayScreen();
            Thread.sleep(1500);
            ss.dispose();
        } catch ( Exception e ) {
        	LOG.warn("Thread was interrupted!", e);
        }
        //Create the gui.
        MDISCISGUI gui = new MDISCISGUI(guiConfig, menuConfig, helpConfig, addDialogConfig);
        gui.displayScreen();
        context.close();
    }

}