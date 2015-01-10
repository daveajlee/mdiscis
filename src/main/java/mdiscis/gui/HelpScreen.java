package mdiscis.gui;

//Import java awt packages.
import java.awt.*;
import java.awt.event.*;
//Import java io packages.
import java.io.*;
import java.util.HashMap;
import java.util.Map;


//Import java swing packages.
import javax.swing.*;
import javax.swing.event.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HelpScreen.java is the screen to display the help screen for MDISCIS.
 * @author Dave Lee
 * @version 1.0
 */
public class HelpScreen extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8280654427837134758L;
	
	private static final Logger LOG = LoggerFactory.getLogger(HelpScreen.class);
	
	private JLabel theSearchLabel;
    private JTextField theSearchField;
    private JLabel theTopicsLabel;
    private JList<String> theTopicsList;
    private DefaultListModel<String> theTopicsModel;
    private JEditorPane theDisplayPane;
    
    private Map<String, String> contentUrls;
    
    /**
     * Default constructor for HelpScreen which creates the help screen interface and displays it to the user.
     */
    public HelpScreen (  ) {
        
        //Set image icon.
        Image img = Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/mdiscislogo.png"));
        setIconImage(img);
        
        //Close this window if the user hits exit.
        this.addWindowListener ( new WindowAdapter() {
            public void windowClosing ( WindowEvent e ) {
                dispose();
            }
        });
        
        initialiseContent();
        
        //Initialise GUI with title and close attributes.
        this.setTitle ( "MDISCIS Help" );
        this.setResizable (false);
        this.setDefaultCloseOperation (DISPOSE_ON_CLOSE);
        
        //Get a container to add things to.
        Container c = this.getContentPane();
        
        //Create a panel to display components.
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout( new BoxLayout ( dialogPanel, BoxLayout.PAGE_AXIS ) );
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 10))); //Spacer.
        
        //Create grid layout - 2 to 1.
        JPanel helpPanel = new JPanel();
        helpPanel.setLayout(new BoxLayout( helpPanel, BoxLayout.LINE_AXIS ) );
        helpPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        //Create left hand panel.
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout( new BoxLayout ( leftPanel, BoxLayout.PAGE_AXIS ) );
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10))); //Spacer.
        
        //Add search label.
        JPanel searchLabelPanel = new JPanel();
        theSearchLabel = new JLabel("Search for Help...");
        theSearchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        searchLabelPanel.add(theSearchLabel);
        leftPanel.add(searchLabelPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        //Add search field.
        theSearchField = new JTextField();
        theSearchField.setColumns(40);
        theSearchField.addKeyListener( new KeyAdapter() {
            public void keyReleased ( KeyEvent e ) {
                updateList(theSearchField.getText());
            }
        });
        leftPanel.add(theSearchField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); //Spacer.
        
        //Add search label.
        JPanel topicLabelPanel = new JPanel();
        theTopicsLabel = new JLabel("Choose a Help Topic...");
        theTopicsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topicLabelPanel.add(theTopicsLabel);
        leftPanel.add(topicLabelPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        //Add topics list.
        JPanel topicListPanel = new JPanel(new BorderLayout());
        theTopicsModel = new DefaultListModel<String>();
        theTopicsModel.addElement("Welcome"); 
        theTopicsModel.addElement("Main Screen");
        theTopicsModel.addElement("New Disc Store"); 
        theTopicsModel.addElement("Load Disc Store");
        theTopicsModel.addElement("Save Disc Store"); 
        theTopicsModel.addElement("Add Disc");
        theTopicsModel.addElement("Clear Disc"); 
        theTopicsModel.addElement("Delete Disc");
        theTopicsModel.addElement("Add Track(s)"); 
        theTopicsModel.addElement("Edit Track");
        theTopicsModel.addElement("Delete Track");

        theTopicsList = new JList<String>(theTopicsModel);
        theTopicsList.setVisibleRowCount(10);
        theTopicsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //Default.
        theTopicsList.setSelectedIndex(0);
        //Action Listener for when a particular help topic is selected.
        theTopicsList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged ( ListSelectionEvent lse ) {
                //Get selected item.
                String selectedItem;
                try {
                    selectedItem = theTopicsList.getSelectedValue().toString();
                } catch ( NullPointerException npe ) {
                	LOG.warn("No topics in list ", npe);
                    if ( theTopicsList.getModel().getSize() > 0 ) {
                        selectedItem = theTopicsList.getModel().getElementAt(0).toString();
                        theTopicsList.setSelectedValue(selectedItem, true);
                    } else {
                        selectedItem = "";
                    }
                }
                //If loading content fails, then stack trace and dispose.
                loadContent(selectedItem);
            }
        });
        JScrollPane topicsPane = new JScrollPane(theTopicsList);
        topicListPanel.add(topicsPane, BorderLayout.CENTER);
        leftPanel.add(topicListPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10))); //Spacer.
        leftPanel.setMaximumSize(new Dimension(450,400));
        
        //Add left panel to help panel.
        helpPanel.add(leftPanel);
        helpPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        //Create right pane.
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout( new BoxLayout ( rightPanel, BoxLayout.PAGE_AXIS ) );
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10))); //Spacer
        //Add editor pane.
        try {
            theDisplayPane = new JEditorPane(HelpScreen.class.getResource("/intro.html"));
            theDisplayPane.setSize(new Dimension(650,500));
            theDisplayPane.setMaximumSize(new Dimension(650,500));
        } catch (IOException e) {
        	LOG.error("Error displaying file", e);
            dispose();
        }
        JScrollPane displayScroll = new JScrollPane(theDisplayPane);
        displayScroll.setMaximumSize(new Dimension(650,390));
        rightPanel.add(displayScroll);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 10))); //Spacer.
        rightPanel.setMaximumSize(new Dimension(650,390));
        helpPanel.add(rightPanel);
        helpPanel.setMaximumSize(new Dimension(650,390));
        //Add help panel to dialog panel.
        dialogPanel.add(helpPanel);
        dialogPanel.setMaximumSize(new Dimension(450,390));
        
         //Add the panel to the container.
        c.add ( dialogPanel );
        
        //Display the dialog box to the user.
        this.setVisible (true);
        this.setSize ( new Dimension(700,450) );
        
        // Set the window's bounds, centering the window
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - this.getWidth()) / 2;
        int y = (screen.height - this.getHeight()) / 2;
        setBounds(x, y, this.getWidth(), this.getHeight());
    }
    
    public void initialiseContent ( ) {
    	contentUrls = new HashMap<String, String>();
    	contentUrls.put("Welcome", "/intro.html");
    	contentUrls.put("Main Screen", "/mainscreen.html");
    	contentUrls.put("New Disc Store", "/newdiscstore.html");
    	contentUrls.put("Load Disc Store", "/loaddiscstore.html");
    	contentUrls.put("Save Disc Store", "/savediscstore.html");
    	contentUrls.put("Add Disc", "/adddisc.html");
    	contentUrls.put("Clear Disc", "/cleardisc.html");
    	contentUrls.put("Delete Disc", "/deletedisc.html");
    	contentUrls.put("Add Track(s)", "/addtracks.html");
    	contentUrls.put("Edit Track", "/edittrack.html");
    	contentUrls.put("Delete Track", "/deletetrack.html");
    }
    
    public void loadContent(final String selectedItem) {
    	try {
    		theDisplayPane.setPage(HelpScreen.class.getResource(contentUrls.get(selectedItem)));
        } catch ( IOException e ) {
            LOG.error("IO Exception whilst processing list", e);
            dispose();
        }
    }
    
    /**
     * This method updates the topic lists according to the search text entered by the user.
     * @param text a <code>String</code> containing the text entered by the user in the search text box.
     */
    public void updateList ( String text ) {
        //Create temp model.
        DefaultListModel<String> tempModel = new DefaultListModel<String>();
        //If text is blank then set tempModel to fullModel.
        if ( "".equalsIgnoreCase(text) ) {
            tempModel = theTopicsModel;
        } else {
        	//Otherwise, add those which have this prefix.
            for ( int i = 0; i < theTopicsModel.size(); i++ ) {
                if ( includeString(text, theTopicsModel.get(i).toString()) ) {
                    tempModel.addElement(theTopicsModel.get(i).toString());
                }
            }
        }
        //Set the list to the temp model.
        theTopicsList.setModel(tempModel);
        theTopicsList.setSelectedIndex(0);
    }
    
    /**
     * This method determines whether a string should be included (strToCheck) against another String (strCheckAgainst).
     * Specifically e.g. statsres (in model) should be included if user's text was stats.
     * @param strToCheck a <code>String</code> containing the user's text.
     * @param strCheckAgainst a <code>String</code> containing the text in model.
     * @return a <code>boolean</code> which is true if and only if text in model should be included based on user's text.
     */
    public boolean includeString ( String strToCheck, String strCheckAgainst ) {
        for ( int i = 0; i < strToCheck.length(); i++ ) {
            if ( !strToCheck.substring(i, i+1).equalsIgnoreCase(strCheckAgainst.substring(i, i+1)) ) {
                return false;
            }
        }
        return true;
    }
    
}
