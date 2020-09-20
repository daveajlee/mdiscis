package de.davelee.mdiscis.gui;

//Import the Java GUI packages.
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.davelee.mdiscis.config.GUIConfig;

/**
 * Splash screen for the MDISCIS program.
 * @author Dave Lee.
 */
public class SplashScreen extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1199055213432005930L;
	
	private static final String FONT_NAME = "Arial";
	private static final String MOUSE_MESSAGE = "Mouse entered - no action";

	private ImageDisplay logoDisplay;
	
	private static final Logger LOG = LoggerFactory.getLogger(SplashScreen.class);
    
    /**
     * Create a new splash screen.
     * @param isAboutScreen a <code>boolean</code> which is true iff this is the about screen rather than splash screen at beginning.
     * @param guiConfig a <code>GUIConfig</code> object representing the localisation text for the GUI.
     */
    public SplashScreen ( final boolean isAboutScreen, final GUIConfig guiConfig ) {
        
        //Initialise GUI with resizable, title and decorate methods.
        this.setTitle (guiConfig.getTitle());
        this.setResizable (true);
        this.setUndecorated(true);
        
        //Set image icon.
        Image img = Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/images/mdiscis-logo-icon.png"));
        setIconImage(img);
        
        //Get a container to add things to.
        Container c = this.getContentPane();
        
        //Construct centre panel with box layout to display all components.
        JPanel centrePanel = new JPanel();
        centrePanel.setLayout ( new BoxLayout ( centrePanel, BoxLayout.PAGE_AXIS ) );
        centrePanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black,1), BorderFactory.createEmptyBorder(5,5,5,5)));
        centrePanel.add(Box.createRigidArea(new Dimension(0,10))); //Spacer.
        centrePanel.setBackground(Color.WHITE);
        
        //Construct logo panel to add to the centre panel.
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(Color.WHITE);
        logoDisplay = new ImageDisplay("images/mdiscis-logo.png", 0, 0);
        logoDisplay.setSize(794,493);
        logoDisplay.setBackground(Color.WHITE);
        logoPanel.add(logoDisplay);
        centrePanel.add(logoPanel);
        
        //Construct loading panel to add to the centre panel.
        JPanel loadingPanel = new JPanel();
        loadingPanel.setBackground(Color.WHITE);
        JLabel loadingLabel = new JLabel(guiConfig.getLoadingText());
        if ( isAboutScreen ) { 
        	loadingLabel = new JLabel(guiConfig.getVersionText()); 
        }
        loadingLabel.setFont(new Font(FONT_NAME, Font.ITALIC, 15));
        loadingPanel.add(loadingLabel);
        centrePanel.add(loadingPanel);
        
        //Construct copyright panel to add to the centre panel.
        JPanel copyrightPanel = new JPanel();
        copyrightPanel.setBackground(Color.WHITE);
        JLabel copyrightLabel = new JLabel(guiConfig.getCopyrightText());
        copyrightLabel.setFont(new Font(FONT_NAME, Font.PLAIN, 10));
        copyrightPanel.add(copyrightLabel);
        centrePanel.add(copyrightPanel);
        
        c.add(centrePanel, BorderLayout.CENTER);
        
        //Mouse listeners if this is the about screen.
        if ( isAboutScreen ) {
            this.getContentPane().addMouseListener (createMouseListener());
            logoDisplay.addMouseListener (createMouseListener());
        }
        
        //Position the screen at the center of the screen.
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension screenDim = tools.getScreenSize();
        Dimension displayDim = getPreferredSize();
        this.setLocation ( (int) (screenDim.width/2)-(displayDim.width/2), (int) (screenDim.height/2)-(displayDim.height/2));

    }
    
    /**
     * Display the front screen to the user.
     */
    public void displayScreen() {
        this.pack ();
        this.setVisible (true);
        this.setSize ( getPreferredSize() );
    }
    
    /**
     * Create a mouse listener which closes this screen when clicked.
     * @return a <code>MouseListener</code> object representing the created mouse listener.
     */
    public MouseListener createMouseListener() {
    	return new MouseListener () {
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
            public void mousePressed(MouseEvent e) {
                //Nothing happens when mouse pressed.
            }
            public void mouseReleased(MouseEvent e) {
                //Nothing happens when mouse released.
            }
            public void mouseEntered(MouseEvent e) {
                //Nothing happens when mouse entered.
            }
            public void mouseExited(MouseEvent e) {
                //Nothing happens when mouse exited.
            }
        };
    }
    
}
