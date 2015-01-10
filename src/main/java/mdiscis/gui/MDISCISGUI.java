package mdiscis.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mdiscis.data.*;

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
	
	private DiscStore theDiscStore;
    private JLabel theStatusBar;
    private JLabel theDiscLabel;
    private DefaultComboBoxModel<Integer> theDiscModel;
    private JComboBox<Integer> theDiscBox;
    private JButton theClearDiscButton;
    private JButton theAddDiscButton;
    private JButton theDeleteDiscButton;
    private JPanel theDialogPanel;
    private JPanel theContentsPanel;
    private JButton[] theEditTrackButtons;
    private JButton[] theDeleteTrackButtons;
    private JButton thePreviousTracksButton;
    private JButton theNextTracksButton;
    private JButton theAddTracksButton;

    //Change here number of tracks to display on screen.
    private static final int NUM_DISPLAY_TRACKS = 10;
    
    private static final String TRACK = "Track ";
    private static final String DISC = "Disc ";
    private static final String FONT_NAME = "Arial";

    //This is the page of tracks that we are showing.
    private int thePage = 1;

    //This is needed to control action listener.
    private int theTrackNum = 0;

    /**
     * Create and display the user interface to the user.
     */
    public MDISCISGUI ( DiscStore discStore ) {
        theDiscStore = discStore;
        createInterface();
    }
    
    /**
     * Create and display the user interface to the user.
     */
    public MDISCISGUI ( ) {
        theDiscStore = new DiscStore();
        createInterface();
    }

    /**
     * Private method to create the interface.
     */
    private void createInterface ( ) {
        this.setTitle("MDISCIS - MiniDISC Indexing Software");
        this.setResizable(false);
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        //Call the Exit method in the UserInterface class if the user hits exit.
        this.addWindowListener ( new WindowAdapter() {
            public void windowClosing ( WindowEvent e ) {
                exit();
            }
        });

        //Set image icon.
        Image img = Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/mdiscislogo.png"));
        setIconImage(img);

        Container c = getContentPane();
        theDialogPanel = new JPanel(new BorderLayout());

        //Create centre panel.
        JPanel centrePanel = new JPanel(new BorderLayout());
        centrePanel.setBackground(Color.WHITE);

        //Now add discControlPanel to centrePanel.
        theDialogPanel.add(createDiscControlPanel(), BorderLayout.NORTH);

        //Finally, drawContentsPanel and add to dialogPanel.
        theContentsPanel = drawContentsPanel();
        theDialogPanel.add(theContentsPanel, BorderLayout.CENTER);

        //Create south panel.
        JPanel southPanel = new JPanel(new BorderLayout());
        //Now create previous and next tracks button and add tracks button - these should be greyed out as necessary.
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        //Create previous track button.
        thePreviousTracksButton = new JButton("< Previous Track(s)");
        thePreviousTracksButton.setEnabled(thePage != 1); 
        thePreviousTracksButton.addActionListener( new ActionListener() {
            public void actionPerformed ( ActionEvent e ) {
                thePage -= 1;
                theContentsPanel = drawContentsPanel();
                theDialogPanel.remove(theDialogPanel.getComponent(1));
                theDialogPanel.add(theContentsPanel, 1);
                theDialogPanel.revalidate();
                theDialogPanel.repaint();
                thePreviousTracksButton.setEnabled(thePage != 1); 
                theNextTracksButton.setEnabled(theDiscStore.getDisc((Integer) theDiscBox.getSelectedItem()).getNumTracks() > (thePage*NUM_DISPLAY_TRACKS ) ); 
            }
        });
        buttonPanel.add(thePreviousTracksButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5,0)));
        //Create next track button.
        theNextTracksButton = new JButton("Next Track(s) >");
        theNextTracksButton.setEnabled(theDiscStore.getNumDiscs() > 0 && theDiscStore.getDisc((Integer) theDiscBox.getSelectedItem()).getNumTracks() > (thePage*NUM_DISPLAY_TRACKS ));
        theNextTracksButton.addActionListener( new ActionListener() {
            public void actionPerformed ( ActionEvent e ) {
                thePage += 1;
                theContentsPanel = drawContentsPanel();
                theDialogPanel.remove(theDialogPanel.getComponent(1));
                theDialogPanel.add(theContentsPanel, 1);
                theDialogPanel.revalidate();
                theDialogPanel.repaint();
                thePreviousTracksButton.setEnabled(thePage != 1);  
                theNextTracksButton.setEnabled(theDiscStore.getDisc((Integer) theDiscBox.getSelectedItem()).getNumTracks() > (thePage*NUM_DISPLAY_TRACKS )); 
            }
        });
        buttonPanel.add(theNextTracksButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(20,0)));
        //Create add track button.
        theAddTracksButton = new JButton("Add Track(s)");
        theAddTracksButton.setEnabled(theDiscStore.getNumDiscs() > 0);
        theAddTracksButton.addActionListener( new ActionListener() {
            public void actionPerformed ( ActionEvent e ) {
                addTracks();
            }
        });
        buttonPanel.add(theAddTracksButton);
        //Add button panel to south panel.
        southPanel.add(buttonPanel, BorderLayout.NORTH);
        //Add statusBar to south panel.
        theStatusBar = new JLabel("Ready");
        southPanel.add(theStatusBar, BorderLayout.SOUTH);
        theDialogPanel.add(southPanel,BorderLayout.SOUTH);

        //Add dialog panel to c.
        c.add(theDialogPanel, BorderLayout.CENTER);

        initialiseMenu();

        //Position the screen at the center of the screen.
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension screenDim = tools.getScreenSize();
        Dimension displayDim = new Dimension(750,600);
        this.setLocation ( (int) (screenDim.width/2)-(displayDim.width/2), (int) (screenDim.height/2)-(displayDim.height/2));

        //Display the front screen to the user.
        this.pack ();
        this.setVisible (true);
        this.setSize ( new Dimension(750,600) );
    }
    
    public JPanel createDiscControlPanel ( ) {
    	//Create disc control panel.
        JPanel discControlPanel = new JPanel(new GridBagLayout());
        theDiscLabel = new JLabel("Disc: ");
        theDiscLabel.setFont(new Font(FONT_NAME, Font.BOLD, 14));
        theDiscModel = new DefaultComboBoxModel<Integer>();
        for ( int i = 0; i < theDiscStore.getNumDiscs(); i++ ) {
            theDiscModel.addElement(theDiscStore.getDiscNumber(i));
        }
        theDiscBox = new JComboBox<Integer>(theDiscModel);
        theDiscBox.setFont(new Font(FONT_NAME, Font.PLAIN, 14));
        theDiscBox.addItemListener( new ItemListener() {
            public void itemStateChanged ( ItemEvent e ) {
                thePage = 1;
                theContentsPanel = drawContentsPanel();
                theDialogPanel.remove(theDialogPanel.getComponent(1));
                theDialogPanel.add(theContentsPanel, 1);
                theDialogPanel.revalidate();
                theDialogPanel.repaint();
                theNextTracksButton.setEnabled(theDiscStore.getNumDiscs() > 0 && theDiscStore.getDisc((Integer) theDiscBox.getSelectedItem()).getNumTracks() > (thePage*NUM_DISPLAY_TRACKS )); 
            }
        });
        //Add label and box + then spacer.
        discControlPanel.add(theDiscLabel);
        discControlPanel.add(theDiscBox);
        discControlPanel.add(Box.createRigidArea(new Dimension(10,0)));
        //Now add a clear disc button.
        theClearDiscButton = new JButton("Clear Disc"); 
        theClearDiscButton.setEnabled(theDiscStore.getNumDiscs() > 0); 
        theClearDiscButton.addActionListener(new ActionListener() {
            public void actionPerformed ( ActionEvent e ) {
                clearDisc();
            }
        });
        discControlPanel.add(theClearDiscButton);
        discControlPanel.add(Box.createRigidArea(new Dimension(5,0)));
        //Now create the delete disc button.
        theDeleteDiscButton = new JButton("Delete Disc");
        theDeleteDiscButton.setEnabled(theDiscStore.getNumDiscs() > 0); 
        theDeleteDiscButton.addActionListener(new ActionListener() {
            public void actionPerformed ( ActionEvent e ) {
                deleteDisc();
            }
        });
        discControlPanel.add(theDeleteDiscButton);
        discControlPanel.add(Box.createRigidArea(new Dimension(25,0)));
        //Now create add another disc button.
        theAddDiscButton = new JButton("Add Another Disc");
        theAddDiscButton.addActionListener(new ActionListener() {
            public void actionPerformed ( ActionEvent e ) {
                addAnotherDisc();
            }
        });
        discControlPanel.add(theAddDiscButton);
        discControlPanel.add(Box.createRigidArea(new Dimension(0,10)));
        return discControlPanel;
    }
    
    public void initialiseMenu ( ) {
    	JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem menuItem;

        menuItem = new JMenuItem("New");
        menuItem.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MDISCISGUI.this.newFile();
            }
        });
        fileMenu.add(menuItem);

        menuItem = new JMenuItem("Load");
        menuItem.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MDISCISGUI.this.loadFile();
            }
        });
        fileMenu.add(menuItem);

        fileMenu.addSeparator();

        menuItem = new JMenuItem("Save");
        menuItem.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MDISCISGUI.this.saveFile();
            }
        });
        fileMenu.add(menuItem);

        fileMenu.addSeparator();

        menuItem = new JMenuItem("Exit");
        menuItem.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exit();
            }
        });
        fileMenu.add(menuItem);

        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);

        menuItem = new JMenuItem("Contents");
        menuItem.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new HelpScreen();
            }
        });
        helpMenu.add(menuItem);

        menuItem = new JMenuItem("About");
        menuItem.addActionListener (new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SplashScreen(true);
            }
        });
        helpMenu.add(menuItem);
    }
    
    public void exit() {
    	int result = JOptionPane.showOptionDialog(MDISCISGUI.this, "Are you sure you wish to exit MDISCIS?", "Please Confirm Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Yes", "No" }, "No");
        if ( result == JOptionPane.YES_OPTION ) {
            System.exit(0);
        }
    }

    /**
     * This method draws the contents panel as requested.
     */
    public JPanel drawContentsPanel ( ) {
        JPanel contentsPanel = new JPanel(new GridLayout(NUM_DISPLAY_TRACKS,1,5,5));
        contentsPanel.setBorder(BorderFactory.createEtchedBorder());
        contentsPanel.setBackground(Color.WHITE);
        //Initialise button arrays.
        theEditTrackButtons = new JButton[NUM_DISPLAY_TRACKS];
        theDeleteTrackButtons = new JButton[NUM_DISPLAY_TRACKS];
        //Do some test data for the moment.
        for ( int i = 0; i < NUM_DISPLAY_TRACKS; i++ ) {
            //Create a panel.
            JPanel thisPanel = new JPanel();
            thisPanel.setLayout(new BoxLayout(thisPanel, BoxLayout.LINE_AXIS));
            thisPanel.setBackground(Color.WHITE);
            //Check if we have a track or if we just have a blank space.
            theTrackNum = ((thePage-1)*NUM_DISPLAY_TRACKS) + i;
            if ( theDiscStore.getNumDiscs() > 0 && theTrackNum < theDiscStore.getDisc((Integer) theDiscBox.getSelectedItem()).getNumTracks() ) {
                //Create a label which will hold the track info.
                JLabel trackLabel = new JLabel(TRACK + theDiscStore.getTrackInformation((Integer) theDiscBox.getSelectedItem(), theTrackNum));
                trackLabel.setFont(new Font(FONT_NAME, Font.ITALIC, 12));
                thisPanel.add(trackLabel);
                thisPanel.add(Box.createRigidArea(new Dimension(20, 0)));
                //Create an edit button.
                theEditTrackButtons[i] = new JButton("Edit");
                theEditTrackButtons[i].addActionListener( new ActionListener() {
                    private String theTrackNumber = theDiscStore.getTrackNumber((Integer) theDiscBox.getSelectedItem(), theTrackNum);
                    public void actionPerformed ( ActionEvent e ) {
                        editTrack((Integer) theDiscBox.getSelectedItem(), theTrackNumber);
                    }
                });
                thisPanel.add(theEditTrackButtons[i]);
                thisPanel.add(Box.createRigidArea(new Dimension(10, 0)));
                //Create a delete button.
                theDeleteTrackButtons[i] = new JButton("Delete");
                theDeleteTrackButtons[i].addActionListener( new ActionListener() {
                    private String theTrackNumber = theDiscStore.getTrackNumber((Integer) theDiscBox.getSelectedItem(), theTrackNum);
                    public void actionPerformed ( ActionEvent e ) {
                        performDeleteTrack(theTrackNumber);
                    }
                });
                thisPanel.add(theDeleteTrackButtons[i]);
            }
            //Finally add this panel to the contents panel.
            contentsPanel.add(thisPanel);
        }
        //Return contents panel.
        return contentsPanel;
    }
    
    public void performDeleteTrack ( final String trackNumber ) {
    	int confirm = JOptionPane.showConfirmDialog(MDISCISGUI.this, "Are you sure that you wish to delete track " + trackNumber + " from disc " + (Integer) theDiscBox.getSelectedItem() + "?", "Please confirm delete operation!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if ( confirm == JOptionPane.YES_OPTION ) {
            if ( theDiscStore.removeTrack((Integer) theDiscBox.getSelectedItem(), trackNumber) ) {
                updateStatus(TRACK + trackNumber + " was removed successfully! ");
                refreshDisplay();
            } else {
                updateStatus(TRACK + trackNumber + " could not be removed!");
            }
        }
    }

    /**
     * Update status bar with the provided text.
     * @param message a <code>String</code> with the message to display in the status bar.
     */
    public void updateStatus ( String message ) {
        theStatusBar.setText(message);
    }

    /**
     * Method to refresh the display.
     */
    public void refreshDisplay ( ) {
        theContentsPanel = drawContentsPanel();
        theDialogPanel.remove(theDialogPanel.getComponent(1));
        theDialogPanel.add(theContentsPanel, 1);
        theDialogPanel.revalidate();
        theDialogPanel.repaint();
    }

    /**
     * Private method to add another disc.
     */
    private void addAnotherDisc() {
        int nextDiscNumber = theDiscStore.addDisc();
        theDiscModel.addElement(nextDiscNumber);
        theStatusBar.setText(DISC + nextDiscNumber + " has been successfully added!");
        if ( theDiscStore.getNumDiscs() == 1 ) {
            theClearDiscButton.setEnabled(true);
            theDeleteDiscButton.setEnabled(true);
            theAddTracksButton.setEnabled(true);
        }
    }

    /**
     * Private method to clear discs.
     */
    private void clearDisc() {
        int discNumber = (Integer) theDiscBox.getSelectedItem();
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure that you wish to delete the index of disc " + discNumber + "?", "Please confirm clear operation!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if ( confirm == JOptionPane.YES_OPTION ) {
            theDiscStore.clearDisc(discNumber);
            theStatusBar.setText(DISC + discNumber + " has been successfully cleared!");
        }
    }

    /**
     * Private method to delete disc.
     */
    private void deleteDisc() {
        int discNumber = (Integer) theDiscBox.getSelectedItem();
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure that you wish to delete disc " + discNumber + "?", "Please confirm delete operation!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if ( confirm == JOptionPane.YES_OPTION ) {
            theDiscStore.deleteDisc(discNumber);
            theDiscModel.removeElement(discNumber);
            theStatusBar.setText(DISC + discNumber + " has been successfully deleted!");
        }
        if ( theDiscStore.getNumDiscs() == 0 ) {
            theClearDiscButton.setEnabled(false);
            theDeleteDiscButton.setEnabled(false);
            theAddTracksButton.setEnabled(false);
        }
    }

    /**
     * Private method to add tracks.
     */
    private void addTracks() {
        //Construct add dialog and it will do the rest!
        new AddDialog(this, "Add Talk", theDiscStore, this);
    }

    /**
     * Private method to edit track.
     * @param discNumber a <code>int</code> with the disc number.
     * @param trackNumber a <code>String</code> with the track number.
     */
    private void editTrack(int discNumber, String trackNumber) {
        //Get the track to be edited.
        Track myTrack = theDiscStore.getDisc(discNumber).getTrackByNumber(trackNumber);
        //Check that it is not null.
        if ( myTrack == null ) {
            theStatusBar.setText("ERROR: Track could not be edited!");
            return;
        }
        //Construct edit dialog.
        new AddDialog(this, "Edit Talk", theDiscStore, discNumber, trackNumber, myTrack.getTalk(), this);
    }

    /**
     * Save file.
     */
    private void saveFile ( ) {
        //Create file dialog box.
        JFileChooser fileDialog = new JFileChooser();
        fileDialog.setDialogTitle("Save Disc Store");
        //Only display files with mdi extension.
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Minidisc Index Files", "mdi");
        fileDialog.setFileFilter(filter);
        //Display file dialog.
        int returnVal = fileDialog.showSaveDialog(this);
        //Check if user submitted file.
        if ( returnVal == JFileChooser.APPROVE_OPTION ) {
            if ( theDiscStore.saveFile(fileDialog.getSelectedFile()) ) {
                String fileName = fileDialog.getSelectedFile().getPath();
                if ( !fileName.endsWith(".mdi") ) { 
                	fileName += ".mdi"; 
                }
                theStatusBar.setText("The current disc indices have been successfully saved to " + fileName);
            } else {
                theStatusBar.setText("ERROR: The file could not be saved. Please try again later.");
            }
        }
    }

    /**
     * Load file.
     */
    private void loadFile ( ) {
        //Check that the user wants to lose data.
        int result = JOptionPane.showOptionDialog(MDISCISGUI.this, "Are you sure you want to load a store without saving the current store?", "Please Confirm Load Operation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Yes", "No" }, "No");
        if ( result == JOptionPane.YES_OPTION ) {
            //Create file dialog box.
            JFileChooser fileDialog = new JFileChooser();
            fileDialog.setDialogTitle("Load Disc Store");
            //Only display files with mdi extension.
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Mindisc Index Files", "mdi");
            fileDialog.setFileFilter(filter);
            //Display file dialog.
            int returnVal = fileDialog.showOpenDialog(this);
            //Check if user submitted file and print coming soon.
            boolean validFile = true;
            if ( returnVal == JFileChooser.APPROVE_OPTION) {
                if ( theDiscStore.loadFile(fileDialog.getSelectedFile()) ) {
                    MDISCISGUI gui = new MDISCISGUI(theDiscStore);
                    gui.theStatusBar.setText("File loaded successfully!");
                    dispose();
                    return;
                }
                validFile = false;
            }
            if ( !validFile ) {
                JOptionPane.showMessageDialog(this,"The selected file is not compatible with this version of MDISCIS.\nPlease either choose another file or create a new store.", "ERROR: Saved File Could Not Be Loaded", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Create a new disc store.
     */
    private void newFile ( ) {
        //Check that the user wants to lose data.
        int result = JOptionPane.showOptionDialog(MDISCISGUI.this, "Are you sure you want to create a new store without saving the current store?", "Please Confirm New Operation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[] { "Yes", "No" }, "No");
        if ( result == JOptionPane.YES_OPTION ) {
            MDISCISGUI gui = new MDISCISGUI(theDiscStore);
            gui.theStatusBar.setText("Created a new disc store successfully!");
            dispose();
        }
    }
    
    /**
     * Main method to run the MDISCIS program.
     * @param args a <code>String</code> array of arguments which are not presently used.
     */
    public static void main(String[] args) {
        try {
            //Use Nimbus Look and Feel!!!!
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
            //Show splash screen.
            SplashScreen ss = new SplashScreen(false);
            Thread.sleep(1500);
            ss.dispose();
        } catch ( Exception e ) {
        	LOG.warn("Thread was interrupted!", e);
        }
        //Create the gui.
        new MDISCISGUI();
    }
            
}