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
	
	private static final Logger LOG = LoggerFactory.getLogger(SplashScreen.class);
	
    private JLabel titleLabel;
    private JLabel loadingLabel;
    private JLabel copyrightLabel;
    
    private JPanel logoPanel;
    
    /**
     * Create a new splash screen.
     * @param isAboutScreen a <code>boolean</code> which is true iff this is the about screen rather than splash screen at beginning.
     */
    public SplashScreen ( final boolean isAboutScreen, final GUIConfig guiConfig ) {
        
        //Initialise GUI with resizable, title and decorate methods.
        this.setTitle (guiConfig.getTitle());
        this.setResizable (true);
        this.setUndecorated(true);
        
        //Set image icon.
        Image img = Toolkit.getDefaultToolkit().getImage(SplashScreen.class.getResource("/images/mdiscislogo.png"));
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
        logoPanel = new JPanel();
        logoPanel.setBackground(Color.WHITE);
        ImageIcon logoImage = new ImageIcon(SplashScreen.class.getResource("/images/mdiscislogo.png"));
        JLabel logoLabel = new JLabel("", logoImage, JLabel.CENTER);
        logoPanel.add(logoLabel);
        centrePanel.add(logoPanel);
        
        //Construct title panel to add to the centre panel.
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.WHITE);
        titleLabel = new JLabel(guiConfig.getTitle());
        titleLabel.setFont(new Font(FONT_NAME, Font.BOLD, 25));
        titlePanel.add(titleLabel);
        centrePanel.add(titlePanel);
        
        //Construct loading panel to add to the centre panel.
        JPanel loadingPanel = new JPanel();
        loadingPanel.setBackground(Color.WHITE);
        if ( isAboutScreen ) { 
        	loadingLabel = new JLabel(guiConfig.getVersionText()); 
        } else { 
        	loadingLabel = new JLabel(guiConfig.getLoadingText()); 
        }
        loadingLabel.setFont(new Font(FONT_NAME, Font.ITALIC, 15));
        loadingPanel.add(loadingLabel);
        centrePanel.add(loadingPanel);
        
        //Construct copyright panel to add to the centre panel.
        JPanel copyrightPanel = new JPanel();
        copyrightPanel.setBackground(Color.WHITE);
        copyrightLabel = new JLabel(guiConfig.getCopyrightText());
        copyrightLabel.setFont(new Font(FONT_NAME, Font.PLAIN, 10));
        copyrightPanel.add(copyrightLabel);
        centrePanel.add(copyrightPanel);
        
        c.add(centrePanel, BorderLayout.CENTER);
        
        //Mouse listeners if this is the about screen.
        if ( isAboutScreen ) {
            this.getContentPane().addMouseListener (createMouseListener());
            logoPanel.addMouseListener (createMouseListener());
        }
        
        //Position the screen at the center of the screen.
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension screenDim = tools.getScreenSize();
        Dimension displayDim = getPreferredSize();
        this.setLocation ( (int) (screenDim.width/2)-(displayDim.width/2), (int) (screenDim.height/2)-(displayDim.height/2));

    }
    
    public void displayScreen() {
    	//Display the front screen to the user.
        this.pack ();
        this.setVisible (true);
        this.setSize ( getPreferredSize() );
    }
    
    public MouseListener createMouseListener() {
    	return new MouseListener () {
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
            public void mousePressed(MouseEvent e) {
            	LOG.info(MOUSE_MESSAGE);
            }
            public void mouseReleased(MouseEvent e) {
            	LOG.info(MOUSE_MESSAGE);
            }
            public void mouseEntered(MouseEvent e) {
            	LOG.info(MOUSE_MESSAGE);
            }
            public void mouseExited(MouseEvent e) {
            	LOG.info(MOUSE_MESSAGE);
            }
        };
    }
    
}
