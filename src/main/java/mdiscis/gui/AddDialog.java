package mdiscis.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import mdiscis.data.*;

/**
 * The add dialog displays an add window to the user in the MDISCIS program.
 * @author Dave Lee
 */
public class AddDialog extends JDialog {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8397731490351720561L;
	
	private static final Logger LOG = LoggerFactory.getLogger(DiscStore.class);
	
	private JComboBox<Integer> theDiscBox;
    private JSpinner theStartTrackSpinner;
    private JSpinner theEndTrackSpinner;
    private JTextField theSubjectField;
    private JTextField theSpeakerField;
    private JTextField theTalkTitleField;
    private JSpinner theDayDateSpinner;
    private JSpinner theMonthDateSpinner;
    private JSpinner theYearDateSpinner;

    private JCheckBox theTalkRecordedOption;

    private DiscStore theDiscStore;
    private MDISCISGUI theGUI;

    /**
     * Create a new add dialog to display on top of the parent frame.
     * @param parent a <code>JFrame</code> with the parent screen.
     * @param title a <code>String</code> with the title for the dialog.
     * @param discStore a <code>DiscStore</code> representing the current store.
     * @param gui a <code>MinidiscGUI</code> representing the parent window.
     */
    public AddDialog(JFrame parent, String title, DiscStore discStore, MDISCISGUI gui){
        super(parent,title,true);

        //Initialise variables.
        theDiscStore = discStore;
        theGUI = gui;
        
        DefaultComboBoxModel<Integer> discModel = new DefaultComboBoxModel<Integer>();
        for ( int i = 0; i < theDiscStore.getNumDiscs(); i++ ) {
            discModel.addElement(theDiscStore.getDiscNumber(i));
        }
        theDiscBox = new JComboBox<Integer>(discModel);
        theStartTrackSpinner = new JSpinner(new SpinnerNumberModel(1,1,50,1));
        theEndTrackSpinner = new JSpinner(new SpinnerNumberModel(10,1,50,1));
        theSubjectField = new JTextField("", 30);
        theSpeakerField = new JTextField("", 30);
        theTalkTitleField = new JTextField("",30);
        //Initialise day, month and year to current date.
        Calendar now = Calendar.getInstance();
        theDayDateSpinner = new JSpinner(new SpinnerNumberModel(now.get(Calendar.DAY_OF_MONTH),1,31,1));
        theMonthDateSpinner = new JSpinner(new SpinnerNumberModel(now.get(Calendar.MONTH) + 1,1,12,1));
        theYearDateSpinner = new JSpinner(new SpinnerNumberModel(now.get(Calendar.YEAR),2003,2023,1));
        JSpinner.NumberEditor yearDisplayer = new JSpinner.NumberEditor(theYearDateSpinner,"0000");
        theYearDateSpinner.setEditor(yearDisplayer);

        theTalkRecordedOption = new JCheckBox();

        //Create interface.
        createInterface();
    }

    /**
     * Create a new edit dialog to display on top of the parent frame.
     * @param parent a <code>JFrame</code> with the parent screen.
     * @param title a <code>String</code> with the title for the dialog.
     * @param discStore a <code>DiscStore</code> representing the current store.
     * @param discNumber a <code>int</code> with the disc number.
     * @param trackNumber a <code>String</code> with the track number.
     * @param subject a <code>String</code> with the subject.
     * @param speaker a <code>String</code> with the speaker.
     * @param talkTitle a <code>String</code> with the talk title.
     * @param date a <code>Calendar</code> with the date.
     * @param isRecorded a <code>boolean</code> which is true iff the talk has been recorded.
     * @param gui a <code>MinidiscGUI</code> representing the parent window.
     */
    public AddDialog(JFrame parent, String title, DiscStore discStore, int discNumber, String trackNumber, String subject, String speaker, String talkTitle, Calendar date, boolean isRecorded, MDISCISGUI gui){
        super(parent,title,true);

        //Initialise variables.
        theDiscStore = discStore;
        theGUI = gui;

        DefaultComboBoxModel<Integer> discModel = new DefaultComboBoxModel<Integer>();
        discModel.addElement(discNumber);
        theDiscBox = new JComboBox<Integer>(discModel);
        int trackInt = Integer.parseInt(trackNumber);
        theStartTrackSpinner = new JSpinner(new SpinnerNumberModel(trackInt,trackInt,trackInt,0));
        theEndTrackSpinner = new JSpinner(new SpinnerNumberModel(trackInt,trackInt,trackInt,0));
        theEndTrackSpinner.setVisible(false);
        theSubjectField = new JTextField(subject, 30);
        theSpeakerField = new JTextField(speaker, 30);
        theTalkTitleField = new JTextField(talkTitle,30);
        //Initialise day, month and year to supplied date.
        theDayDateSpinner = new JSpinner(new SpinnerNumberModel(date.get(Calendar.DAY_OF_MONTH),1,31,1));
        theMonthDateSpinner = new JSpinner(new SpinnerNumberModel(date.get(Calendar.MONTH) + 1,1,12,1));
        theYearDateSpinner = new JSpinner(new SpinnerNumberModel(date.get(Calendar.YEAR),2003,2023,1));
        JSpinner.NumberEditor yearDisplayer = new JSpinner.NumberEditor(theYearDateSpinner,"0000");
        theYearDateSpinner.setEditor(yearDisplayer);

        theTalkRecordedOption = new JCheckBox();
        if ( isRecorded ) {
            theTalkRecordedOption.setSelected(true);
        }

        //Create interface.
        createInterface();
    }

    /**
     * Method to create the interface.
     */
    public void createInterface ( ) {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Container c = this.getContentPane();
        c.setLayout(new BorderLayout(5, 5));

        JPanel labelPanel = new JPanel(new GridLayout(9,2,5,5));
        JPanel fieldPanel = new JPanel(new GridLayout(9,2,5,5));

        labelPanel.add(new JLabel("Disc"));
        fieldPanel.add(theDiscBox);
        if ( "Add Talk".equalsIgnoreCase(super.getTitle()) ) {
            labelPanel.add(new JLabel("Start Track"));
            fieldPanel.add(theStartTrackSpinner);
            labelPanel.add(new JLabel("End Track"));
            fieldPanel.add(theEndTrackSpinner);
        } else {
            labelPanel.add(new JLabel("Track"));
            fieldPanel.add(theStartTrackSpinner);
            labelPanel.add(new JLabel("")); 
            fieldPanel.add(new JLabel(""));
        }
        labelPanel.add(new JLabel("Subject"));
        fieldPanel.add(theSubjectField);
        labelPanel.add(new JLabel("Speaker"));
        fieldPanel.add(theSpeakerField);
        labelPanel.add(new JLabel("Talk Title"));
        fieldPanel.add(theTalkTitleField);
        labelPanel.add(new JLabel("Date"));
        //Construct a separate panel for date.
        JPanel datePanel = new JPanel();
        datePanel.add(theDayDateSpinner); 
        datePanel.add(new JLabel("/"));
        datePanel.add(theMonthDateSpinner); 
        datePanel.add(new JLabel("/"));
        datePanel.add(theYearDateSpinner);
        fieldPanel.add(datePanel);
        //Finally add recorded.
        labelPanel.add(new JLabel("Recorded?"));
        fieldPanel.add(theTalkRecordedOption);
        
        c.add(labelPanel,BorderLayout.WEST);
        c.add(fieldPanel,BorderLayout.CENTER);

        JPanel okPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton okButton = new JButton("OK");
        okPanel.add(okButton);
        this.getRootPane().setDefaultButton(okButton);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                //Check if the end track is before the start track - if it is show error message and do nothing.
                if ( Integer.parseInt(theEndTrackSpinner.getValue().toString()) < Integer.parseInt(theStartTrackSpinner.getValue().toString())) {
                    JOptionPane.showMessageDialog(AddDialog.this, "End Track value was less than the Start Track value which is not possible. Please choose another End Track value!", "ERROR: End Track is before Start Track", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //Before we add it - delete the old one if it is edit!
                if ( "Edit Talk".equalsIgnoreCase(AddDialog.this.getTitle()) ) {
                    theDiscStore.removeTrack(Integer.parseInt(theDiscBox.getSelectedItem().toString()), theStartTrackSpinner.getValue().toString());
                }
                boolean added = false;
                try {
                    int discNum = Integer.parseInt(theDiscBox.getSelectedItem().toString());
                    int startTrack = Integer.parseInt(theStartTrackSpinner.getValue().toString());
                    int endTrack = Integer.parseInt(theEndTrackSpinner.getValue().toString());
                    String subject = theSubjectField.getText();
                    String speaker = theSpeakerField.getText();
                    String talkTitle = theTalkTitleField.getText();
                    Calendar date = getDate(theDayDateSpinner.getValue().toString() + "/" + theMonthDateSpinner.getValue().toString() + "/" + theYearDateSpinner.getValue().toString());
                    boolean recorded = false;
                    if ( theTalkRecordedOption.isSelected() ) { 
                    	recorded = true; 
                    }
                    added = theDiscStore.addTracks(discNum, startTrack, endTrack, subject, speaker, talkTitle, date, recorded);
                } catch ( Exception ex ) { 
                	LOG.error("Parsing error whilst adding tracks", ex);
                }
                if (added) {
                    if ( "Edit Talk".equalsIgnoreCase(AddDialog.this.getTitle()) ) {
                        theGUI.updateStatus("Track " + theDiscBox.getSelectedItem().toString() + " of disc " + theStartTrackSpinner.getValue().toString() + " has been edited successfully!");
                    } else {
                        theGUI.updateStatus("Added successfully.\n");
                    }
                    theGUI.refreshDisplay();
                } else {
                    if ( "Edit Talk".equalsIgnoreCase(AddDialog.this.getTitle()) ) {
                        theGUI.updateStatus("Track " + theDiscBox.getSelectedItem().toString() + " of disc " + theStartTrackSpinner.getValue().toString() + " could not be edited!");
                    } else {
                        theGUI.updateStatus("Not added - Maybe a duplicate track id was used?\n");
                    }
                }
                dispose();
            }
        });

        JButton cancel = new JButton("Cancel");
        okPanel.add(cancel);
        cancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        c.add(okPanel, BorderLayout.SOUTH);

        //Position the screen at the center of the screen.
        Toolkit tools = Toolkit.getDefaultToolkit();
        Dimension screenDim = tools.getScreenSize();
        Dimension displayDim = new Dimension(550,500);
        this.setLocation ( (int) (screenDim.width/2)-(displayDim.width/2), (int) (screenDim.height/2)-(displayDim.height/2));

        //Display the front screen to the user.
        this.pack ();
        this.setVisible (true);
        this.setSize ( new Dimension(550,500) );
    }

    /**
     * Convert date entered by the user into a <code>Calendar</code> object.
     * @param date a <code>String</code> in the form dd/mm/yyyy.
     * @return a <code>Calendar</code> representing the selected date.
     */
    public Calendar getDate ( String date ) {
        String[] dateSplit = date.split("/");
        int day = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1])-1;
        int year = Integer.parseInt(dateSplit[2]);
        return new GregorianCalendar(year,month,day);
    }

}
