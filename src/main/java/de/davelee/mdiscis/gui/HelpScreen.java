package de.davelee.mdiscis.gui;

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

import de.davelee.mdiscis.config.HelpConfig;

/**
 * HelpScreen.java is the screen to display the help screen for MDISCIS.
 * @author Dave Lee
 */
public class HelpScreen extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8280654427837134758L;
	
	private static final Logger LOG = LoggerFactory.getLogger(HelpScreen.class);

    private JTextField searchField;
    private JList<String> topicsList;
    private DefaultListModel<String> topicsModel;
    private JEditorPane displayPane;
    
    private Map<String, String> contentUrls;
    private HelpConfig helpConfig;
    
    /**
     * Default constructor for HelpScreen which creates the help screen interface and displays it to the user.
     * @param helpConfig a <code>HelpConfig</code> object representing the current localisation for help messages.
     */
    public HelpScreen ( final HelpConfig helpConfig ) {
    	
    	this.helpConfig = helpConfig;
        
        //Set image icon.
        Image img = Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/images/mdiscislogo.png"));
        setIconImage(img);
        
        //Close this window if the user hits exit.
        this.addWindowListener ( new WindowAdapter() {
            public void windowClosing ( WindowEvent e ) {
                dispose();
            }
        });
        
        initialiseContent();
        
        //Initialise GUI with title and close attributes.
        this.setTitle ( helpConfig.getHelpScreenTitle() );
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
        JLabel searchLabel = new JLabel(helpConfig.getSearchLabelText());
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        searchLabelPanel.add(searchLabel);
        leftPanel.add(searchLabelPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        //Add search field.
        searchField = new JTextField();
        searchField.setColumns(40);
        searchField.addKeyListener( new KeyAdapter() {
            public void keyReleased ( KeyEvent e ) {
                updateList(searchField.getText());
            }
        });
        leftPanel.add(searchField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20))); //Spacer.
        
        //Add search label.
        JPanel topicLabelPanel = new JPanel();
        JLabel topicsLabel = new JLabel(helpConfig.getTopicsLabelText());
        topicsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        topicLabelPanel.add(topicsLabel);
        leftPanel.add(topicLabelPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        //Add topics list.
        JPanel topicListPanel = new JPanel(new BorderLayout());
        topicsModel = new DefaultListModel<String>();
        topicsModel.addElement(helpConfig.getWelcomeOptionText()); 
        topicsModel.addElement(helpConfig.getMainScreenOptionText());
        topicsModel.addElement(helpConfig.getNewDiscStoreOptionText()); 
        topicsModel.addElement(helpConfig.getLoadDiscStoreOptionText());
        topicsModel.addElement(helpConfig.getSaveDiscStoreOptionText()); 
        topicsModel.addElement(helpConfig.getAddDiscOptionText());
        topicsModel.addElement(helpConfig.getClearDiscOptionText()); 
        topicsModel.addElement(helpConfig.getDeleteDiscOptionText());
        topicsModel.addElement(helpConfig.getAddTrackOptionText()); 
        topicsModel.addElement(helpConfig.getEditTrackOptionText());
        topicsModel.addElement(helpConfig.getDeleteTrackOptionText());

        topicsList = new JList<String>(topicsModel);
        topicsList.setVisibleRowCount(10);
        topicsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //Default.
        topicsList.setSelectedIndex(0);
        //Action Listener for when a particular help topic is selected.
        topicsList.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged ( ListSelectionEvent lse ) {
                //Get selected item.
                String selectedItem;
                try {
                    selectedItem = topicsList.getSelectedValue().toString();
                } catch ( NullPointerException npe ) {
                	LOG.warn("No topics in list ", npe);
                    if ( topicsList.getModel().getSize() > 0 ) {
                        selectedItem = topicsList.getModel().getElementAt(0).toString();
                        topicsList.setSelectedValue(selectedItem, true);
                    } else {
                        selectedItem = "";
                    }
                }
                //If loading content fails, then stack trace and dispose.
                loadContent(selectedItem);
            }
        });
        JScrollPane topicsPane = new JScrollPane(topicsList);
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
            displayPane = new JEditorPane(HelpScreen.class.getResource(helpConfig.getWelcomeOptionPage()));
            displayPane.setSize(new Dimension(650,500));
            displayPane.setMaximumSize(new Dimension(650,500));
        } catch (IOException e) {
        	LOG.error("Error displaying file", e);
            dispose();
        }
        JScrollPane displayScroll = new JScrollPane(displayPane);
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
        
        // Set the window's bounds, centering the window
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - this.getWidth()) / 2;
        int y = (screen.height - this.getHeight()) / 2;
        setBounds(x, y, this.getWidth(), this.getHeight());
    }
    
    /**
     * Display the screen to the user.
     */
    public void displayScreen ( ) {
    	//Display the dialog box to the user.
        this.setVisible (true);
        this.setSize ( new Dimension(700,450) );
    }
    
    /**
     * Initialise the options and html pages for this help screen.
     * This method is usually called by the constructor.
     */
    public void initialiseContent ( ) {
    	contentUrls = new HashMap<String, String>();
    	contentUrls.put(helpConfig.getWelcomeOptionText(), helpConfig.getWelcomeOptionPage());
    	contentUrls.put(helpConfig.getMainScreenOptionText(), helpConfig.getMainScreenOptionPage());
    	contentUrls.put(helpConfig.getNewDiscStoreOptionText(), helpConfig.getNewDiscStoreOptionPage());
    	contentUrls.put(helpConfig.getLoadDiscStoreOptionText(), helpConfig.getLoadDiscStoreOptionPage());
    	contentUrls.put(helpConfig.getSaveDiscStoreOptionText(), helpConfig.getSaveDiscStoreOptionPage());
    	contentUrls.put(helpConfig.getAddDiscOptionText(), helpConfig.getAddDiscOptionPage());
    	contentUrls.put(helpConfig.getClearDiscOptionText(), helpConfig.getClearDiscOptionPage());
    	contentUrls.put(helpConfig.getDeleteDiscOptionText(), helpConfig.getDeleteDiscOptionPage());
    	contentUrls.put(helpConfig.getAddTrackOptionText(), helpConfig.getAddTrackOptionPage());
    	contentUrls.put(helpConfig.getEditTrackOptionText(), helpConfig.getEditTrackOptionPage());
    	contentUrls.put(helpConfig.getDeleteTrackOptionText(), helpConfig.getDeleteTrackOptionPage());
    }
    
    /**
     * Load the html page associated with the selected text into the html content pane.
     * @param selectedItem a <code>String</code> with the selected text.
     */
    public void loadContent(final String selectedItem) {
    	try {
    		displayPane.setPage(HelpScreen.class.getResource(contentUrls.get(selectedItem)));
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
            tempModel = topicsModel;
        } else {
        	//Otherwise, add those which have this prefix.
            for ( int i = 0; i < topicsModel.size(); i++ ) {
                if ( includeString(text, topicsModel.get(i).toString()) ) {
                    tempModel.addElement(topicsModel.get(i).toString());
                }
            }
        }
        //Set the list to the temp model.
        topicsList.setModel(tempModel);
        topicsList.setSelectedIndex(0);
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
