package de.davelee.mdiscis.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import de.davelee.mdiscis.config.GUIConfig;
import de.davelee.mdiscis.config.HelpConfig;
import de.davelee.mdiscis.config.MenuConfig;
import de.davelee.mdiscis.data.*;

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
	
	private DiscStore discStore;
    private JLabel statusBar;
    private JLabel discLabel;
    private DefaultComboBoxModel<Integer> discModel;
    private JComboBox<Integer> discBox;
    private JButton clearDiscButton;
    private JButton addDiscButton;
    private JButton deleteDiscButton;
    private JPanel dialogPanel;
    private JPanel contentsPanel;
    private JButton[] editTrackButtons;
    private JButton[] deleteTrackButtons;
    private JButton previousTracksButton;
    private JButton nextTracksButton;
    private JButton addTracksButton;

    private static final String FONT_NAME = "Arial";

    //This is the page of tracks that we are showing.
    private int page = 1;

    //This is needed to control action listener.
    private int trackNum = 0;
    
    //Config objects.
    private GUIConfig guiConfig;
    private MenuConfig menuConfig;
    private HelpConfig helpConfig;

    /**
     * Create and display the user interface to the user.
     */
    public MDISCISGUI ( final DiscStore discStore, final GUIConfig guiConfig, final MenuConfig menuConfig, final HelpConfig helpConfig ) {
        this.discStore = discStore;
        this.guiConfig = guiConfig;
        this.menuConfig = menuConfig;
        this.helpConfig = helpConfig;
        createInterface();
    }
    
    /**
     * Create and display the user interface to the user.
     */
    public MDISCISGUI ( final GUIConfig guiConfig, final MenuConfig menuConfig, final HelpConfig helpConfig ) {
        discStore = new DiscStore();
        this.guiConfig = guiConfig;
        this.menuConfig = menuConfig;
        this.helpConfig = helpConfig;
        createInterface();
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
        Image img = Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/images/mdiscislogo.png"));
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
        previousTracksButton.addActionListener( new ActionListener() {
            public void actionPerformed ( ActionEvent e ) {
                processPreviousTrackButton((Integer) discBox.getSelectedItem());
            }
        });
        buttonPanel.add(previousTracksButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5,0)));
        //Create next track button.
        nextTracksButton = new JButton(guiConfig.getNextTrackButtonText());
        nextTracksButton.setEnabled(discStore.getNumDiscs() > 0 && discStore.getDisc((Integer) discBox.getSelectedItem()).getNumTracks() > (page*guiConfig.getNumDisplayTracks() ));
        nextTracksButton.addActionListener( new ActionListener() {
            public void actionPerformed ( ActionEvent e ) {
            	page+=1;
                processNextTrackButton(page, (Integer) discBox.getSelectedItem());
            }
        });
        buttonPanel.add(nextTracksButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20,0)));
        //Create add track button.
        addTracksButton = new JButton(guiConfig.getAddTrackButtonText());
        addTracksButton.setEnabled(discStore.getNumDiscs() > 0);
        addTracksButton.addActionListener( new ActionListener() {
            public void actionPerformed ( ActionEvent e ) {
                showAddDialog(guiConfig.getAddTalkText());
            }
        });
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
        this.setLocation ( (int) (screenDim.width/2)-(displayDim.width/2), (int) (screenDim.height/2)-(displayDim.height/2));

    }
    
    public void processPreviousTrackButton ( int selectedItem) {
    	page -= 1;
        contentsPanel = drawContentsPanel();
        dialogPanel.remove(dialogPanel.getComponent(1));
        dialogPanel.add(contentsPanel, 1);
        dialogPanel.revalidate();
        dialogPanel.repaint();
        previousTracksButton.setEnabled(page != 1); 
        if (discStore.getDisc(selectedItem)!=null) {
        	nextTracksButton.setEnabled(discStore.getDisc(selectedItem).getNumTracks() > (page*guiConfig.getNumDisplayTracks() ) ); 
        }
    }
    
    public void processNextTrackButton ( final int page, final int selectedItem ) {
    	this.page = page;
        contentsPanel = drawContentsPanel();
        dialogPanel.remove(dialogPanel.getComponent(1));
        dialogPanel.add(contentsPanel, 1);
        dialogPanel.revalidate();
        dialogPanel.repaint();
        previousTracksButton.setEnabled(this.page != 1);
        if (discStore.getDisc(selectedItem)!=null) {
        	nextTracksButton.setEnabled(discStore.getDisc(selectedItem).getNumTracks() > (this.page*guiConfig.getNumDisplayTracks() )); 
        }
    }
    
    public void displayScreen ( ) {
    	//Display the front screen to the user.
        this.pack ();
        this.setVisible (true);
        this.setSize ( new Dimension(750,600) );
    }
    
    public JPanel createDiscControlPanel ( ) {
    	//Create disc control panel.
        JPanel discControlPanel = new JPanel(new GridBagLayout());
        discLabel = new JLabel(guiConfig.getDiscLabelText());
        discLabel.setFont(new Font(FONT_NAME, Font.BOLD, 14));
        discModel = new DefaultComboBoxModel<Integer>();
        for ( int i = 0; i < discStore.getNumDiscs(); i++ ) {
            discModel.addElement(discStore.getDiscNumber(i));
        }
        discBox = new JComboBox<Integer>(discModel);
        discBox.setFont(new Font(FONT_NAME, Font.PLAIN, 14));
        discBox.addItemListener( new ItemListener() {
            public void itemStateChanged ( ItemEvent e ) {
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
        clearDiscButton.addActionListener(new ActionListener() {
            public void actionPerformed ( ActionEvent e ) {
                clearDisc((Integer) discBox.getSelectedItem());
            }
        });
        discControlPanel.add(clearDiscButton);
        discControlPanel.add(Box.createRigidArea(new Dimension(5,0)));
        //Now create the delete disc button.
        deleteDiscButton = new JButton(guiConfig.getDeleteDiscButtonText());
        deleteDiscButton.setEnabled(discStore.getNumDiscs() > 0); 
        deleteDiscButton.addActionListener(new ActionListener() {
            public void actionPerformed ( ActionEvent e ) {
                deleteDisc((Integer) discBox.getSelectedItem());
            }
        });
        discControlPanel.add(deleteDiscButton);
        discControlPanel.add(Box.createRigidArea(new Dimension(25,0)));
        //Now create add another disc button.
        addDiscButton = new JButton(guiConfig.getAddDiscButtonText());
        addDiscButton.addActionListener(new ActionListener() {
            public void actionPerformed ( ActionEvent e ) {
                addAnotherDisc();
            }
        });
        discControlPanel.add(addDiscButton);
        discControlPanel.add(Box.createRigidArea(new Dimension(0,10)));
        return discControlPanel;
    }
    
    public void initialiseMenu ( ) {
    	JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu(menuConfig.getFileText());
        menuBar.add(fileMenu);
        JMenuItem menuItem;

        menuItem = new JMenuItem(menuConfig.getNewText());
        menuItem.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MDISCISGUI.this.newFile();
            }
        });
        fileMenu.add(menuItem);

        menuItem = new JMenuItem(menuConfig.getLoadText());
        menuItem.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int result = showOptionDialog(guiConfig.getLoadDialogMessage(), guiConfig.getLoadDialogTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if ( result == JOptionPane.YES_OPTION ) {
                	JFileChooser fileChooser = MDISCISGUI.this.showOpenFileDialog();
                	MDISCISGUI.this.loadFile(fileChooser.showOpenDialog(MDISCISGUI.this), fileChooser.getSelectedFile());
                }
            }
        });
        fileMenu.add(menuItem);

        fileMenu.addSeparator();

        menuItem = new JMenuItem(menuConfig.getSaveText());
        menuItem.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JFileChooser fileChooser = MDISCISGUI.this.showSaveFileDialog();
                MDISCISGUI.this.saveFile(fileChooser.showSaveDialog(MDISCISGUI.this), fileChooser.getSelectedFile());
            }
        });
        fileMenu.add(menuItem);

        fileMenu.addSeparator();

        menuItem = new JMenuItem(menuConfig.getExitText());
        menuItem.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        fileMenu.add(menuItem);

        JMenu helpMenu = new JMenu(menuConfig.getHelpText());
        menuBar.add(helpMenu);

        menuItem = new JMenuItem(menuConfig.getContentsText());
        menuItem.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                HelpScreen screen = new HelpScreen(helpConfig);
                screen.displayScreen();
            }
        });
        helpMenu.add(menuItem);

        menuItem = new JMenuItem(menuConfig.getAboutText());
        menuItem.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SplashScreen screen = new SplashScreen(true, guiConfig);
                screen.displayScreen();
            }
        });
        helpMenu.add(menuItem);
    }
    
    public void exit() {
    	int result = JOptionPane.showOptionDialog(MDISCISGUI.this, guiConfig.getExitDialogMessage(), guiConfig.getExitDialogTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { guiConfig.getYesOptionText(), guiConfig.getNoOptionText() }, guiConfig.getNoOptionText());
        if ( result == JOptionPane.YES_OPTION ) {
            System.exit(0);
        }
    }

    /**
     * This method draws the contents panel as requested.
     */
    public JPanel drawContentsPanel ( ) {
        JPanel contentsPanel = new JPanel(new GridLayout(guiConfig.getNumDisplayTracks(),1,5,5));
        contentsPanel.setBorder(BorderFactory.createEtchedBorder());
        contentsPanel.setBackground(Color.WHITE);
        //Initialise button arrays.
        editTrackButtons = new JButton[guiConfig.getNumDisplayTracks()];
        deleteTrackButtons = new JButton[guiConfig.getNumDisplayTracks()];
        //Do some test data for the moment.
        for ( int i = 0; i < guiConfig.getNumDisplayTracks(); i++ ) {
            //Create a panel.
            JPanel thisPanel = new JPanel();
            thisPanel.setLayout(new BoxLayout(thisPanel, BoxLayout.LINE_AXIS));
            thisPanel.setBackground(Color.WHITE);
            //Check if we have a track or if we just have a blank space.
            trackNum = ((page-1)*guiConfig.getNumDisplayTracks()) + i;
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
            contentsPanel.add(thisPanel);
        }
        //Return contents panel.
        return contentsPanel;
    }
    
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
     */
    public void clearDisc(int discNumber) {
        int confirm = showConfirmDialog(guiConfig.getDiscText() + discNumber + guiConfig.getClearDialogMessage(), guiConfig.getClearDialogTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if ( confirm == JOptionPane.YES_OPTION ) {
            discStore.clearDisc(discNumber);
            statusBar.setText(guiConfig.getDiscText() + discNumber + guiConfig.getClearSuccessText());
        }
    }
    
    public int showConfirmDialog(final String message, final String title, final int optionType, final int messageType ) {
    	return JOptionPane.showConfirmDialog(this, message, title, optionType, messageType);
    }

    /**
     * Private method to delete disc.
     */
    public void deleteDisc(int discNumber) {
        int confirm = showConfirmDialog(guiConfig.getDiscText() + discNumber + guiConfig.getDeleteDiscDialogMessage(), guiConfig.getDeleteDialogTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if ( confirm == JOptionPane.YES_OPTION ) {
            discStore.deleteDisc(discNumber);
            discModel.removeElement(discNumber);
            statusBar.setText(guiConfig.getDiscText() + discNumber + guiConfig.getDeleteSuccessText());
        }
        if ( discStore.getNumDiscs() == 0 ) {
            clearDiscButton.setEnabled(false);
            deleteDiscButton.setEnabled(false);
            addTracksButton.setEnabled(false);
        }
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
    
    public void showEditDialog(final String title, final int discNumber, final int trackNumber, final Talk talk) {
    	//Construct edit dialog.
        AddDialog dialog = new AddDialog(this, title, discStore, discNumber, trackNumber, talk, this);
        dialog.displayDialog();
    }

    /**
     * Save file.
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
    
    public void saveFile ( final int returnVal, final File selectedFile ) {
        //Check if user submitted file.
        if ( returnVal == JFileChooser.APPROVE_OPTION ) {
            if ( discStore.saveFile(selectedFile) ) {
                String fileName = selectedFile.getPath();
                if ( !fileName.endsWith("." + guiConfig.getFileExtension()) ) { 
                	fileName += "." + guiConfig.getFileExtension(); 
                }
                statusBar.setText(guiConfig.getSaveFileSuccessText() + fileName);
            } else {
                statusBar.setText(guiConfig.getSaveFileErrorText());
            }
        }
    }

    public int showOptionDialog ( final String message, final String title, final int options, final int messageType ) {
    	return JOptionPane.showOptionDialog(MDISCISGUI.this, message, title, options, messageType, null, new String[] { guiConfig.getYesOptionText(), guiConfig.getNoOptionText() }, guiConfig.getNoOptionText());
    }
    
    public void loadDisplayScreen( final DiscStore discStore ) {
    	MDISCISGUI gui = new MDISCISGUI(discStore, guiConfig, menuConfig, helpConfig);
        gui.displayScreen();
        gui.statusBar.setText(guiConfig.getLoadFileSuccessText());
        dispose();
    }
    
    /**
     * Load file.
     */
    public void loadFile ( final int returnVal, final File selectedFile ) {
    	//Check if user submitted file and print coming soon.
        boolean validFile = true;
        if ( returnVal == JFileChooser.APPROVE_OPTION) {
        	if ( discStore.loadFile(selectedFile) ) {
        		loadDisplayScreen(discStore);
                return;
            }
            validFile = false;
        }
        if ( !validFile ) {
        	this.statusBar.setText(guiConfig.getLoadFileErrorText());
        }
    }

    /**
     * Create a new disc store.
     */
    private void newFile ( ) {
        //Check that the user wants to lose data.
        int result = JOptionPane.showOptionDialog(MDISCISGUI.this, guiConfig.getNewDialogMessage(), guiConfig.getNewDialogTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Yes", "No" }, "No");
        if ( result == JOptionPane.YES_OPTION ) {
            MDISCISGUI gui = new MDISCISGUI(discStore, guiConfig, menuConfig, helpConfig);
            gui.displayScreen();
            gui.statusBar.setText(guiConfig.getNewSuccessText());
            dispose();
        }
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
        try {
            //Use Nimbus Look and Feel!!!!
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //Show splash screen.
            SplashScreen ss = new SplashScreen(false, guiConfig);
            ss.displayScreen();
            Thread.sleep(1500);
            ss.dispose();
        } catch ( Exception e ) {
        	LOG.warn("Thread was interrupted!", e);
        }
        //Create the gui.
        MDISCISGUI gui = new MDISCISGUI(guiConfig, menuConfig, helpConfig);
        gui.displayScreen();
        context.close();
    }
            
}