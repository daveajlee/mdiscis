package mdiscis.data;

import java.util.*;

import javax.xml.parsers.*;
import javax.xml.xpath.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.xml.sax.*;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;

/**
 * This class stores the discs in the Minidisc program.
 * @author Dave Lee
 */
public class DiscStore {
	
	private static final Logger LOG = LoggerFactory.getLogger(DiscStore.class);

    private List<Disc> theDiscs;
    
    /**
     * Default constructor - initialise variables.
     */
    public DiscStore ( ) {
        theDiscs = new LinkedList<Disc>();
    }
    
    /**
     * Add a disc to the store - return the disc number assigned to it.
     * @return a <code>int</code> with the disc number that was added.
     */
    public int addDisc ( ) {
        int discNo;
    	//Find the next disc number to use.
        if ( !theDiscs.isEmpty() ) {
        	discNo = theDiscs.get(theDiscs.size()-1).getDiscNumber() + 1;
        } else {
        	discNo = 1;
        }
        //Add disc.
        theDiscs.add(new Disc(discNo));
        //Return disc number.
        return discNo;
    }
    
    /**
     * Add tracks to a disc.
     * @param discNumber a <code>int</code> with the disc number.
     * @param startTrack a <code>int</code> with the start track.
     * @param endTrack a <code>int</code> with the end track.
     * @param talk a <code>Talk</code> with the talk details.
     * @return a <code>boolean</code> which is true iff the tracks are added successfully.
     */
    public boolean addTracks (int discNumber, int startTrack, int endTrack, Talk talk) {
        return getDisc(discNumber).addTracks(startTrack, endTrack, talk);
    }
    
    /**
     * Get the disc object with the supplied disc number.
     * @param discNumber a <code>int</code> with the disc number.
     * @return a <code>Disc</code> object.
     */
    public Disc getDisc ( int discNumber ) {
        for ( int i = 0; i < theDiscs.size(); i++ ) {
            if ( theDiscs.get(i).getDiscNumber() == discNumber ) {
                return theDiscs.get(i);
            }
        }
        //Null if not found.
        return null; 
    }

    /**
     * Get all tracks for a particular disc.
     * @param discNumber a <code>int</code> with the disc number.
     * @return a <code>String</code> array.
     */
    public String[] getAllTracks ( int discNumber ) {
        return getDisc(discNumber).getAllTracks();
    }

    /**
     * Clear all tracks from a particular disc.
     * @param discNumber a <code>int</code> with the disc number.
     */
    public void clearDisc ( int discNumber ) {
        getDisc(discNumber).clearDisc();
    }

    /**
     * Delete this disc.
     * @param discNumber a <code>int</code> with the disc number.
     */
    public void deleteDisc ( int discNumber ) {
        theDiscs.remove(getDisc(discNumber));
    }

    /**
     * Get the number of discs in this store.
     * @return a <code>int</code> with the number of discs.
     */
    public int getNumDiscs ( ) {
        return theDiscs.size();
    }

    /**
     * Get the disc number at the supplied position in the list.
     * @param pos a <code>int</code> with the position in the list.
     * @return a <code>int</code> with the disc number.
     */
    public int getDiscNumber ( int pos ) {
        return theDiscs.get(pos).getDiscNumber();
    }

    /**
     * Get the number of tracks on a particular disc.
     * @param discNumber a <code>int</code> with the disc number.
     * @return a <code>int</code> with the number of tracks.
     */
    public int getNumTracks ( int discNumber ) {
        return getDisc(discNumber).getNumTracks();
    }

    /**
     * Get the track information for a particular position for a particular disc.
     * @param discNumber a <code>int</code> with the disc number.
     * @param pos a <code>int</code> with the position.
     * @return a <code>String</code> with the track information.
     */
    public String getTrackInformation ( int discNumber, int pos ) {
        return getDisc(discNumber).getTrackInformation(pos);
    }

    /**
     * Get the track number for a particular position for a particular disc.
     * @param discNumber a <code>int</code> with the disc number.
     * @param pos a <code>int</code> with the position.
     * @return a <code>int</code> with the track number.
     */
    public String getTrackNumber ( int discNumber, int pos ) {
        return getDisc(discNumber).getTrack(pos).getTrackId();
    }

    /**
     * Remove the track with the specified track number.
     * @param discNumber a <code>int</code> with the disc number.
     * @param trackNumber a <code>String</code> with the track number.
     * @return a <code>boolean</code> which is true iff the track has been deleted successfully.
     */
    public boolean removeTrack ( int discNumber, String trackNumber ) {
        return getDisc(discNumber).removeTrack(trackNumber);
    }

    /**
     * Load the specified file.
     * @param file a <code>File</code> to load.
     * @return a <code>boolean</code> which is true iff the file was loaded successfully.
     */
    public boolean loadFile ( File file ) {
        //Load XML document using XML classes. This is the important variable - the DOM!
        Document document; 
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(file);
        } catch (SAXException sxe) {
        	//Exception handling from http://java.sun.com/webservices/jaxp/dist/1.1/docs/tutorial/dom/1_read.html
            // Error generated during parsing
            Exception  x = sxe;
            if (sxe.getException() != null) {
                x = sxe.getException();
            }
            LOG.error("Error during parsing", x);
            return false;
        } catch (ParserConfigurationException pce) {
            // Parser with specified options can't be built
        	LOG.error("Parser with specified options can't be built", pce);
            return false;
        } catch (IOException ioe) {
            // I/O error
        	LOG.error("IO Error", ioe);
            return false;
        }
        //Run helper method to process XML document.
        return createStoreFromFile(document);
    }

    /**
     * Save the indices as a file.
     * @param f a <code>File</code> with the name of the file to save the indices to.
     * @return a <code>boolean</code> which is true iff the simulation has been saved successfully.
     */
    public boolean saveFile ( final File f ) {
    	File fileCopy = f;
        //Check it has correct extension!
        if ( !fileCopy.getName().endsWith(".mdi") ) { 
        	fileCopy = new File(f.getName() + ".mdi"); 
        }
        //Create DOM instance.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            //Create document builder.
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Create new DOM document.
            Document document = builder.newDocument();
            createXMLDocument(document);
            //Save file.
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(f));
            transformer.transform(source, result);
            return true;
        } catch ( Exception e ) {
        	LOG.error("Error saving file: ", e);
            return false;
        }
    }

    /**
     * Create an XML document for saving to file.
     * @param doc a <code>Document</code> object.
     * @return a <code>Document</code> object which has been modified.
     */
    public Document createXMLDocument ( Document doc ) {
        //Create root element.
        Element discStore = doc.createElement("discstore");
        doc.appendChild(discStore);
        //Create disc elements.
        for ( int i = 0; i < theDiscs.size(); i++ ) {
            //Store the disc to improve efficiency.
            Disc myDisc = theDiscs.get(i);
            //First of all, create the disc element.
            Element disc = doc.createElement("disc");
            //Set number as attribute.
            disc.setAttribute("number", "" + myDisc.getDiscNumber());
            //Now go through all of the tracks and add them to this disc.
            for ( int j = 0; j < myDisc.getNumTracks(); j++ ) {
                //Again store track to improve efficiency.
                Track myTrack = myDisc.getTrack(j);
                //Create element for this track.
                Element track = doc.createElement("track");
                //Now set attributes for this track.
                track.setAttribute("id", myTrack.getTrackId());
                track.setAttribute("subject", myTrack.getTalk().getSubject());
                track.setAttribute("speaker", myTrack.getTalk().getSpeaker());
                track.setAttribute("talkTitle", myTrack.getTalk().getTitle());
                track.setAttribute("recorded", "" + myTrack.getTalk().isRecorded());
                track.setAttribute("date", myTrack.getTalk().getShortDate());
                //Append this track to its parent disc element.
                disc.appendChild(track);
            }
            //Add this disc to the doc.
            discStore.appendChild(disc);
        }
        //Finally, return document.
        return doc;
    }

    /**
     * Create discStore from file - for load.
     * @param document a <code>Document</code> object.
     * @return a <code>boolean</code> which is true iff the discStore was created successfully.
     */
    private boolean createStoreFromFile ( Document document ) {
        //Wipe all discs first of all.
        theDiscs = new LinkedList<Disc>();
        try {
            XPath xpath = XPathFactory.newInstance().newXPath();
            //Create the discs.
            NodeList discNode = (NodeList) xpath.evaluate("//disc", document.getDocumentElement(), XPathConstants.NODESET);
            for ( int i = 0; i < discNode.getLength(); i++ ) {
                //Get the disc element.
                Element discElement = (Element) discNode.item(i);
                //Create the disc.
                Disc myDisc = new Disc(Integer.parseInt(discElement.getAttribute("number")));
                //Now go through adding tracks as appropriate.
                NodeList trackNodes = discElement.getElementsByTagName("track");
                for ( int j = 0; j < trackNodes.getLength(); j++ ) {
                    //Add each track to the disc object.
                    Element trackElement = (Element) trackNodes.item(j);
                    //Create calendar element.
                    String[] shortDate = trackElement.getAttribute("date").split("/");
                    Calendar cal = new GregorianCalendar(Integer.parseInt(shortDate[2]), Integer.parseInt(shortDate[1])-1, Integer.parseInt(shortDate[0]));
                    //Then call add track method.
                    boolean wasRecorded = "true".equalsIgnoreCase(trackElement.getAttribute("recorded"));
                    Talk talk = new Talk(trackElement.getAttribute("subject"), trackElement.getAttribute("speaker"), trackElement.getAttribute("talkTitle"), cal, wasRecorded);
                    myDisc.addTrack(trackElement.getAttribute("id"), talk);
                }
                //Add disc to list.
                theDiscs.add(myDisc);
            }
        } catch (Exception e) {
            LOG.error("Error Creating Store from File", e);
            return false;
        }
        return true;
    }

}
