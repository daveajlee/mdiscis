package de.davelee.mdiscis.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import de.davelee.mdiscis.data.DiscStore;
import de.davelee.mdiscis.data.Talk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.davelee.mdiscis.config.AddDialogConfig;

import java.time.LocalDate;
import java.util.*;

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

    /**
     * Drop-down list of discs available to choose from.
     */
    private JComboBox<Integer> discBox;
    /**
     * Spinner to choose the start track.
     */
    private JSpinner startTrackSpinner;
    /**
     * Spinner to choose the end track.
     */
    private JSpinner endTrackSpinner;
    /**
     * Text field for the subject.
     */
    private JTextField subjectField;
    /**
     * Text field for the speaker.
     */
    private JTextField speakerField;
    /**
     * Text field for the talk title.
     */
    private JTextField talkTitleField;
    /**
     * The spinner to choose the day of the month.
     */
    private JSpinner dayDateSpinner;
    /**
     * The spinner to choose the month.
     */
    private JSpinner monthDateSpinner;
    /**
     * The spinner to choose the year.
     */
    private JSpinner yearDateSpinner;

    /**
     * Checkbox to choose if track has already been recorded
     */
    private JCheckBox talkRecordedOption;

    /**
     * Storage object with all current discs.
     */
    private DiscStore discStore;
    /**
     * Class representing the GUI which is displayed to the user.
     */
    private MDISCISGUI gui;
    /**
     * Configuration class for the Add Dialog with translated texts.
     */
    private AddDialogConfig addDialogConfig;

    /**
     * Create a new add dialog to display on top of the parent frame.
     * @param parent a <code>JFrame</code> with the parent screen.
     * @param title a <code>String</code> with the title for the dialog.
     * @param discStore a <code>DiscStore</code> representing the current store.
     * @param gui a <code>MinidiscGUI</code> representing the parent window.
     */
    public AddDialog(JFrame parent, String title, final DiscStore discStore, final MDISCISGUI gui){
        this(parent, title, discStore, 0, 0, null, gui);
    }

    /**
     * Create a new edit dialog to display on top of the parent frame.
     * @param parent a <code>JFrame</code> with the parent screen.
     * @param title a <code>String</code> with the title for the dialog.
     * @param discStore a <code>DiscStore</code> representing the current store.
     * @param discNumber a <code>int</code> with the disc number.
     * @param trackNumber a <code>int</code> with the track number.
     * @param talk a <code>Talk</code> Object with the talk details.
     * @param gui a <code>MinidiscGUI</code> representing the parent window.
     */
    public AddDialog(JFrame parent, String title, final DiscStore discStore, final int discNumber, final int trackNumber, final Talk talk, final MDISCISGUI gui){
        super(parent,title,true);

        //Initialise variables.
        this.discStore = discStore;
        this.gui = gui;
        this.addDialogConfig = gui.getAddDialogConfig();

        DefaultComboBoxModel<Integer> discModel = new DefaultComboBoxModel<Integer>();
        if ( discNumber == 0 ) {
        	for ( int i = 0; i < this.discStore.getNumDiscs(); i++ ) {
                discModel.addElement(this.discStore.getDiscNumber(i));
            }
        } else {
        	discModel.addElement(discNumber);
        }
        discBox = new JComboBox<Integer>(discModel);
        if ( trackNumber == 0 ) {
        	startTrackSpinner = new JSpinner(new SpinnerNumberModel(1,1,50,1));
            endTrackSpinner = new JSpinner(new SpinnerNumberModel(10,1,50,1));
        } else {
        	startTrackSpinner = new JSpinner(new SpinnerNumberModel(trackNumber,trackNumber,trackNumber,0));
        	endTrackSpinner = new JSpinner(new SpinnerNumberModel(trackNumber,trackNumber,trackNumber,0));
        	endTrackSpinner.setVisible(false);
        }
        
        talkRecordedOption = new JCheckBox();
        if ( talk != null ) {
        	subjectField = new JTextField(talk.getSubject(), 30);
            speakerField = new JTextField(talk.getSpeaker(), 30);
            talkTitleField = new JTextField(talk.getTitle(),30);
        	//Initialise day, month and year to supplied date.
        	dayDateSpinner = new JSpinner(new SpinnerNumberModel(talk.getDate().getDayOfMonth(),1,31,1));
        	monthDateSpinner = new JSpinner(new SpinnerNumberModel(talk.getDate().getMonthValue(),1,12,1));
        	yearDateSpinner = new JSpinner(new SpinnerNumberModel(talk.getDate().getYear(),2003,2043,1));
        	talkRecordedOption.setSelected(talk.isRecorded());
        } else {
        	subjectField = new JTextField("", 30);
            speakerField = new JTextField("", 30);
            talkTitleField = new JTextField("",30);
        	//Initialise day, month and year to current date.
        	Calendar now = Calendar.getInstance();
            dayDateSpinner = new JSpinner(new SpinnerNumberModel(now.get(Calendar.DAY_OF_MONTH),1,31,1));
            monthDateSpinner = new JSpinner(new SpinnerNumberModel(now.get(Calendar.MONTH) + 1,1,12,1));
            yearDateSpinner = new JSpinner(new SpinnerNumberModel(now.get(Calendar.YEAR),2003,2043,1));
        }
        JSpinner.NumberEditor yearDisplayer = new JSpinner.NumberEditor(yearDateSpinner,"0000");
        yearDateSpinner.setEditor(yearDisplayer);
        
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

        labelPanel.add(new JLabel(addDialogConfig.getDiscLabelText()));
        fieldPanel.add(discBox);
        if ( addDialogConfig.getAddTalkText().equalsIgnoreCase(super.getTitle()) ) {
            labelPanel.add(new JLabel(addDialogConfig.getStartTrackText()));
            fieldPanel.add(startTrackSpinner);
            labelPanel.add(new JLabel(addDialogConfig.getEndTrackText()));
            fieldPanel.add(endTrackSpinner);
        } else {
            labelPanel.add(new JLabel(addDialogConfig.getTrackText()));
            fieldPanel.add(startTrackSpinner);
            labelPanel.add(new JLabel("")); 
            fieldPanel.add(new JLabel(""));
        }
        labelPanel.add(new JLabel(addDialogConfig.getSubjectText()));
        fieldPanel.add(subjectField);
        labelPanel.add(new JLabel(addDialogConfig.getSpeakerText()));
        fieldPanel.add(speakerField);
        labelPanel.add(new JLabel(addDialogConfig.getTalkTitleText()));
        fieldPanel.add(talkTitleField);
        labelPanel.add(new JLabel(addDialogConfig.getDateText()));
        //Construct a separate panel for date.
        JPanel datePanel = new JPanel();
        datePanel.add(dayDateSpinner); 
        datePanel.add(new JLabel(addDialogConfig.getDateSymbol()));
        datePanel.add(monthDateSpinner); 
        datePanel.add(new JLabel(addDialogConfig.getDateSymbol()));
        datePanel.add(yearDateSpinner);
        fieldPanel.add(datePanel);
        //Finally add recorded.
        labelPanel.add(new JLabel(addDialogConfig.getRecordedText()));
        fieldPanel.add(talkRecordedOption);
        
        c.add(labelPanel,BorderLayout.WEST);
        c.add(fieldPanel,BorderLayout.CENTER);

        JPanel okPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton okButton = new JButton(addDialogConfig.getOkButtonText());
        okPanel.add(okButton);
        this.getRootPane().setDefaultButton(okButton);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                processOkButton();
            }
        });

        JButton cancel = new JButton(addDialogConfig.getCancelButtonText());
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
    }
    
    /**
     * Display the front screen to the user.
     */
    public void displayDialog ( ) {
        this.pack ();
        this.setVisible (true);
        this.setSize ( new Dimension(550,500) );
    }
    
    /**
     * Perform the desired operations as soon as the OK button is pressed by the user.
     */
    public void processOkButton ( ) {
    	//Check if the end track is before the start track - if it is show error message and do nothing.
        if ( Integer.parseInt(endTrackSpinner.getValue().toString()) < Integer.parseInt(startTrackSpinner.getValue().toString())) {
            JOptionPane.showMessageDialog(AddDialog.this, addDialogConfig.getEndTrackErrorMessage(), addDialogConfig.getEndTrackErrorTitle(), JOptionPane.ERROR_MESSAGE);
            return;
        }
        //Before we add it - delete the old one if it is edit!
        if ( addDialogConfig.getEditTalkText().equalsIgnoreCase(AddDialog.this.getTitle()) ) {
            discStore.removeTrack(Integer.parseInt(discBox.getSelectedItem().toString()), Integer.parseInt(startTrackSpinner.getValue().toString()));
        }
        boolean added = false;
        try {
            int discNum = Integer.parseInt(discBox.getSelectedItem().toString());
            int startTrack = Integer.parseInt(startTrackSpinner.getValue().toString());
            int endTrack = Integer.parseInt(endTrackSpinner.getValue().toString());
            String subject = subjectField.getText();
            String speaker = speakerField.getText();
            String talkTitle = talkTitleField.getText();
            LocalDate date = getDate(dayDateSpinner.getValue().toString() + addDialogConfig.getDateSymbol() + monthDateSpinner.getValue().toString() + addDialogConfig.getDateSymbol() + yearDateSpinner.getValue().toString());
            boolean recorded = talkRecordedOption.isSelected();
            Talk talk = new Talk();
            talk.setSubject(subject);
            talk.setSpeaker(speaker);
            talk.setTitle(talkTitle);
            talk.setDate(date);
            talk.setRecorded(recorded);
            added = discStore.addTracks(discNum, startTrack, endTrack, talk);
        } catch ( Exception ex ) { 
        	LOG.error("Parsing error whilst adding tracks", ex);
        }
        updateStatus(added);
        gui.refreshDisplay();
        dispose();
    }
    
    /**
     * Update the status bar with an appropriate text depending on whether the supplied boolean is true or false.
     * @param added a <code>boolean</code> which is true iff the track was added successfully.
     */
    public void updateStatus ( final boolean added ) {
    	if ( addDialogConfig.getEditTalkText().equalsIgnoreCase(AddDialog.this.getTitle()) && added ) {
        	gui.updateStatus(addDialogConfig.getTrackLabel() + discBox.getSelectedItem().toString() + addDialogConfig.getOfDiscLabel() + startTrackSpinner.getValue().toString() + addDialogConfig.getEditSuccessText());
        } else if ( addDialogConfig.getEditTalkText().equalsIgnoreCase(AddDialog.this.getTitle()) && !added ) {
        	gui.updateStatus(addDialogConfig.getTrackLabel() + discBox.getSelectedItem().toString() + addDialogConfig.getOfDiscLabel() + startTrackSpinner.getValue().toString() + addDialogConfig.getEditErrorText());
        } else if ( added ) {
        	gui.updateStatus(addDialogConfig.getAddSuccessText() + "\n");
        } else {
        	gui.updateStatus(addDialogConfig.getAddErrorText() + "\n");
        }
    }

    /**
     * Convert date entered by the user into a <code>Calendar</code> object.
     * @param date a <code>String</code> in the form dd/mm/yyyy.
     * @return a <code>LocalDate</code> representing the selected date.
     */
    public LocalDate getDate ( final String date ) {
        String[] dateSplit = date.split(addDialogConfig.getDateSymbol());
        int day = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int year = Integer.parseInt(dateSplit[2]);
        return LocalDate.of(year,month,day);
    }

}
