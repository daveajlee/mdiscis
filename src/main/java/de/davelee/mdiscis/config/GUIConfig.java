package de.davelee.mdiscis.config;

/**
 * Configuration class for the GUI for the MDISCIS program.
 * @author Dave Lee.
 */
public class GUIConfig {
	
	private String title;
	private String versionText;
	private String loadingText;
	private String copyrightText;
	private int numDisplayTracks;
	private String trackText;
	private String discText;
	private String previousTrackButtonText;
	private String nextTrackButtonText;
	private String addTrackButtonText;
	private String addTalkText;
	private String statusReadyText;
	private String clearDiscButtonText;
	private String deleteDiscButtonText;
	private String addDiscButtonText;
	private String loadDialogMessage;
	private String loadDialogTitle;
	private String discLabelText;
	private String trackLabelText;
	private String exitDialogMessage;
	private String exitDialogTitle;
	private String yesOptionText;
	private String noOptionText;
	private String editButtonText;
	private String deleteButtonText;
	private String deleteTrackDialogMessage;
	private String deleteDiscDialogMessage;
	private String deleteDialogTitle;
	private String deleteSuccessText;
	private String deleteErrorText;
	private String addedSuccessText;
	private String clearDialogMessage;
	private String clearDialogTitle;
	private String clearSuccessText;
	private String editLabelText;
	private String editErrorText;
	private String saveFileDialogTitle;
	private String loadFileDialogTitle;
	private String fileExtension;
	private String fileExtensionName;
	private String saveFileSuccessText;
	private String saveFileErrorText;
	private String loadFileSuccessText;
	private String loadFileErrorText;
	private String newDialogMessage;
	private String newDialogTitle;
	private String newSuccessText;

	/**
	 * Retrieve the localisation text for title.
	 * @return a <code>String</code> containing the localisation text for title.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Set the localisation text for title.
	 * @param title a <code>String</code> containing the localisation text for title.
	 */
	public void setTitle(final String title) {
		this.title = title;
	}

	/**
	 * Retrieve the localisation text for version.
	 * @return a <code>String</code> containing the localisation text for version.
	 */
	public String getVersionText() {
		return versionText;
	}

	/**
	 * Set the localisation text for version.
	 * @param versionText a <code>String</code> containing the localisation text for version.
	 */
	public void setVersionText(final String versionText) {
		this.versionText = versionText;
	}

	/**
	 * Retrieve the localisation text for loading.
	 * @return a <code>String</code> containing the localisation text for loading.
	 */
	public String getLoadingText() {
		return loadingText;
	}

	/**
	 * Set the localisation text for loading.
	 * @param loadingText a <code>String</code> containing the localisation text for loading.
	 */
	public void setLoadingText(final String loadingText) {
		this.loadingText = loadingText;
	}

	/**
	 * Retrieve the localisation text for copyright.
	 * @return a <code>String</code> containing the localisation text for copyright.
	 */
	public String getCopyrightText() {
		return copyrightText;
	}

	/**
	 * Set the localisation text for copyright.
	 * @param copyrightText a <code>String</code> containing the localisation text for copyright.
	 */
	public void setCopyrightText(final String copyrightText) {
		this.copyrightText = copyrightText;
	}

	/**
	 * Retrieve the localisation text for number of display tracks.
	 * @return a <code>String</code> containing the localisation text for number of display tracks.
	 */
	public int getNumDisplayTracks() {
		return numDisplayTracks;
	}

	/**
	 * Set the localisation text for number of display tracks.
	 * @param numDisplayTracks a <code>String</code> containing the localisation text for number of display tracks.
	 */
	public void setNumDisplayTracks(final int numDisplayTracks) {
		this.numDisplayTracks = numDisplayTracks;
	}

	/**
	 * Retrieve the localisation text for track.
	 * @return a <code>String</code> containing the localisation text for track.
	 */
	public String getTrackText() {
		return trackText;
	}

	/**
	 * Set the localisation text for track.
	 * @param trackText a <code>String</code> containing the localisation text for track.
	 */
	public void setTrackText(final String trackText) {
		this.trackText = trackText;
	}

	/**
	 * Retrieve the localisation text for disc.
	 * @return a <code>String</code> containing the localisation text for disc.
	 */
	public String getDiscText() {
		return discText;
	}

	/**
	 * Set the localisation text for disc.
	 * @param discText a <code>String</code> containing the localisation text for disc.
	 */
	public void setDiscText(final String discText) {
		this.discText = discText;
	}

	/**
	 * Retrieve the localisation text for previous track button.
	 * @return a <code>String</code> containing the localisation text for previous track button.
	 */
	public String getPreviousTrackButtonText() {
		return previousTrackButtonText;
	}

	/**
	 * Set the localisation text for previous track button.
	 * @param previousTrackButtonText a <code>String</code> containing the localisation text for previous track button.
	 */
	public void setPreviousTrackButtonText(final String previousTrackButtonText) {
		this.previousTrackButtonText = previousTrackButtonText;
	}

	/**
	 * Retrieve the localisation text for next track button.
	 * @return a <code>String</code> containing the localisation text for next track button.
	 */
	public String getNextTrackButtonText() {
		return nextTrackButtonText;
	}

	/**
	 * Set the localisation text for next track button.
	 * @param nextTrackButtonText a <code>String</code> containing the localisation text for next track button.
	 */
	public void setNextTrackButtonText(final String nextTrackButtonText) {
		this.nextTrackButtonText = nextTrackButtonText;
	}

	/**
	 * Retrieve the localisation text for add track button.
	 * @return a <code>String</code> containing the localisation text for add track button.
	 */
	public String getAddTrackButtonText() {
		return addTrackButtonText;
	}

	/**
	 * Set the localisation text for add track button.
	 * @param addTrackButtonText a <code>String</code> containing the localisation text for add track button.
	 */
	public void setAddTrackButtonText(final String addTrackButtonText) {
		this.addTrackButtonText = addTrackButtonText;
	}

	/**
	 * Retrieve the localisation text for add talk.
	 * @return a <code>String</code> containing the localisation text for add talk.
	 */
	public String getAddTalkText() {
		return addTalkText;
	}

	/**
	 * Set the localisation text for add talk.
	 * @param addTalkText a <code>String</code> containing the localisation text for add talk.
	 */
	public void setAddTalkText(final String addTalkText) {
		this.addTalkText = addTalkText;
	}

	/**
	 * Retrieve the localisation text for status ready.
	 * @return a <code>String</code> containing the localisation text for status ready.
	 */
	public String getStatusReadyText() {
		return statusReadyText;
	}

	/**
	 * Set the localisation text for status ready.
	 * @param statusReadyText a <code>String</code> containing the localisation text for status ready.
	 */
	public void setStatusReadyText(final String statusReadyText) {
		this.statusReadyText = statusReadyText;
	}

	/**
	 * Retrieve the localisation text for clear disc button.
	 * @return a <code>String</code> containing the localisation text for clear disc button.
	 */
	public String getClearDiscButtonText() {
		return clearDiscButtonText;
	}

	/**
	 * Set the localisation text for clear disc button.
	 * @param clearDiscButtonText a <code>String</code> containing the localisation text for clear disc button.
	 */
	public void setClearDiscButtonText(final String clearDiscButtonText) {
		this.clearDiscButtonText = clearDiscButtonText;
	}

	/**
	 * Retrieve the localisation text for delete disc button.
	 * @return a <code>String</code> containing the localisation text for delete disc button.
	 */
	public String getDeleteDiscButtonText() {
		return deleteDiscButtonText;
	}

	/**
	 * Set the localisation text for delete disc button.
	 * @param deleteDiscButtonText a <code>String</code> containing the localisation text for delete disc button.
	 */
	public void setDeleteDiscButtonText(final String deleteDiscButtonText) {
		this.deleteDiscButtonText = deleteDiscButtonText;
	}

	/**
	 * Retrieve the localisation text for add disc button.
	 * @return a <code>String</code> containing the localisation text for add disc button.
	 */
	public String getAddDiscButtonText() {
		return addDiscButtonText;
	}

	/**
	 * Set the localisation text for add disc button.
	 * @param addDiscButtonText a <code>String</code> containing the localisation text for add disc button.
	 */
	public void setAddDiscButtonText(final String addDiscButtonText) {
		this.addDiscButtonText = addDiscButtonText;
	}

	/**
	 * Retrieve the localisation text for load dialog message.
	 * @return a <code>String</code> containing the localisation text for load dialog message.
	 */
	public String getLoadDialogMessage() {
		return loadDialogMessage;
	}

	/**
	 * Set the localisation text for load dialog message.
	 * @param loadDialogMessage a <code>String</code> containing the localisation text for load dialog message.
	 */
	public void setLoadDialogMessage(final String loadDialogMessage) {
		this.loadDialogMessage = loadDialogMessage;
	}

	/**
	 * Retrieve the localisation text for load dialog title.
	 * @return a <code>String</code> containing the localisation text for load dialog title.
	 */
	public String getLoadDialogTitle() {
		return loadDialogTitle;
	}

	/**
	 * Set the localisation text for load dialog title.
	 * @param loadDialogTitle a <code>String</code> containing the localisation text for load dialog title.
	 */
	public void setLoadDialogTitle(final String loadDialogTitle) {
		this.loadDialogTitle = loadDialogTitle;
	}

	/**
	 * Retrieve the localisation text for disc label.
	 * @return a <code>String</code> containing the localisation text for disc label.
	 */
	public String getDiscLabelText() {
		return discLabelText;
	}

	/**
	 * Set the localisation text for disc label.
	 * @param discLabelText a <code>String</code> containing the localisation text for disc label.
	 */
	public void setDiscLabelText(final String discLabelText) {
		this.discLabelText = discLabelText;
	}
	
	/**
	 * Retrieve the localisation text for track label.
	 * @return a <code>String</code> containing the localisation text for track label.
	 */
	public String getTrackLabelText() {
		return trackLabelText;
	}

	/**
	 * Set the localisation text for track label.
	 * @param trackLabelText a <code>String</code> containing the localisation text for track label.
	 */
	public void setTrackLabelText(final String trackLabelText) {
		this.trackLabelText = trackLabelText;
	}

	/**
	 * Retrieve the localisation text for exit dialog message.
	 * @return a <code>String</code> containing the localisation text for exit dialog message.
	 */
	public String getExitDialogMessage() {
		return exitDialogMessage;
	}

	/**
	 * Set the localisation text for exit dialog message.
	 * @param exitDialogMessage a <code>String</code> containing the localisation text for exit dialog message.
	 */
	public void setExitDialogMessage(final String exitDialogMessage) {
		this.exitDialogMessage = exitDialogMessage;
	}

	/**
	 * Retrieve the localisation text for exit dialog title.
	 * @return a <code>String</code> containing the localisation text for exit dialog title.
	 */
	public String getExitDialogTitle() {
		return exitDialogTitle;
	}

	/**
	 * Set the localisation text for exit dialog title.
	 * @param exitDialogTitle a <code>String</code> containing the localisation text for exit dialog title.
	 */
	public void setExitDialogTitle(final String exitDialogTitle) {
		this.exitDialogTitle = exitDialogTitle;
	}

	/**
	 * Retrieve the localisation text for yes option.
	 * @return a <code>String</code> containing the localisation text for yes option.
	 */
	public String getYesOptionText() {
		return yesOptionText;
	}

	/**
	 * Set the localisation text for yes option.
	 * @param yesOptionText a <code>String</code> containing the localisation text for yes option.
	 */
	public void setYesOptionText(final String yesOptionText) {
		this.yesOptionText = yesOptionText;
	}

	/**
	 * Retrieve the localisation text for no option.
	 * @return a <code>String</code> containing the localisation text for no option.
	 */
	public String getNoOptionText() {
		return noOptionText;
	}

	/**
	 * Set the localisation text for no option.
	 * @param noOptionText a <code>String</code> containing the localisation text for no option.
	 */
	public void setNoOptionText(final String noOptionText) {
		this.noOptionText = noOptionText;
	}

	/**
	 * Retrieve the localisation text for edit button.
	 * @return a <code>String</code> containing the localisation text for edit button.
	 */
	public String getEditButtonText() {
		return editButtonText;
	}

	/**
	 * Set the localisation text for edit button.
	 * @param editButtonText a <code>String</code> containing the localisation text for edit button.
	 */
	public void setEditButtonText(final String editButtonText) {
		this.editButtonText = editButtonText;
	}

	/**
	 * Retrieve the localisation text for delete button.
	 * @return a <code>String</code> containing the localisation text for delete button.
	 */
	public String getDeleteButtonText() {
		return deleteButtonText;
	}

	/**
	 * Set the localisation text for delete button.
	 * @param deleteButtonText a <code>String</code> containing the localisation text for delete button.
	 */
	public void setDeleteButtonText(final String deleteButtonText) {
		this.deleteButtonText = deleteButtonText;
	}

	/**
	 * Retrieve the localisation text for the message in delete track dialog.
	 * @return a <code>String</code> containing the localisation text for the message in delete track dialog.
	 */
	public String getDeleteTrackDialogMessage() {
		return deleteTrackDialogMessage;
	}

	/**
	 * Set the localisation text for the message in delete track dialog.
	 * @param deleteTrackDialogMessage a <code>String</code> containing the localisation text for the message in delete track dialog.
	 */
	public void setDeleteTrackDialogMessage(final String deleteTrackDialogMessage) {
		this.deleteTrackDialogMessage = deleteTrackDialogMessage;
	}
	
	/**
	 * Retrieve the localisation text for the message in delete disc dialog.
	 * @return a <code>String</code> containing the localisation text for the message in delete disc dialog.
	 */
	public String getDeleteDiscDialogMessage() {
		return deleteDiscDialogMessage;
	}

	/**
	 * Set the localisation text for the message in delete disc dialog.
	 * @param deleteDiscDialogMessage a <code>String</code> containing the localisation text for the message in delete disc dialog.
	 */
	public void setDeleteDiscDialogMessage(final String deleteDiscDialogMessage) {
		this.deleteDiscDialogMessage = deleteDiscDialogMessage;
	}

	/**
	 * Retrieve the localisation text for the title in delete dialogs.
	 * @return a <code>String</code> containing the localisation text for the title in delete dialogs.
	 */
	public String getDeleteDialogTitle() {
		return deleteDialogTitle;
	}

	/**
	 * Set the localisation text for the title in delete dialogs.
	 * @param deleteDialogTitle a <code>String</code> containing the localisation text for the title in delete dialogs.
	 */
	public void setDeleteDialogTitle(final String deleteDialogTitle) {
		this.deleteDialogTitle = deleteDialogTitle;
	}

	/**
	 * Retrieve the localisation text for the success of the delete operation.
	 * @return a <code>String</code> containing the localisation text for the sucess of the delete operation.
	 */
	public String getDeleteSuccessText() {
		return deleteSuccessText;
	}

	/**
	 * Set the localisation text for the success of the delete operation.
	 * @param deleteSuccessText a <code>String</code> containing the localisation text for the success of the delete operation.
	 */
	public void setDeleteSuccessText(final String deleteSuccessText) {
		this.deleteSuccessText = deleteSuccessText;
	}

	/**
	 * Retrieve the localisation text for an error caused by the delete operation.
	 * @return a <code>String</code> containing the localisation text for an error caused by the delete operation.
	 */
	public String getDeleteErrorText() {
		return deleteErrorText;
	}

	/**
	 * Set the localisation text for an error caused by the delete operation.
	 * @param deleteErrorText a <code>String</code> containing the localisation text for an error caused by the delete operation.
	 */
	public void setDeleteErrorText(final String deleteErrorText) {
		this.deleteErrorText = deleteErrorText;
	}

	/**
	 * Retrieve the localisation text for the success of the add operation.
	 * @return a <code>String</code> containing the localisation text for the success of the add operation.
	 */
	public String getAddedSuccessText() {
		return addedSuccessText;
	}

	/**
	 * Set the localisation text for the success of the add operation.
	 * @param addedSuccessText a <code>String</code> containing the localisation text for the success of the add operation.
	 */
	public void setAddedSuccessText(final String addedSuccessText) {
		this.addedSuccessText = addedSuccessText;
	}

	/**
	 * Retrieve the localisation text for the message of the clear dialog.
	 * @return a <code>String</code> containing the localisation text for the message of the clear dialog.
	 */
	public String getClearDialogMessage() {
		return clearDialogMessage;
	}

	/**
	 * Set the localisation text for the message of the clear dialog.
	 * @param clearDialogMessage a <code>String</code> containing the localisation text for the message of the clear dialog.
	 */
	public void setClearDialogMessage(final String clearDialogMessage) {
		this.clearDialogMessage = clearDialogMessage;
	}

	/**
	 * Retrieve the localisation text for the title of the clear dialog.
	 * @return a <code>String</code> containing the localisation text for the title of the clear dialog.
	 */
	public String getClearDialogTitle() {
		return clearDialogTitle;
	}

	/**
	 * Set the localisation text for the title of the clear dialog.
	 * @param clearDialogTitle a <code>String</code> containing the localisation text for the title of the clear dialog.
	 */
	public void setClearDialogTitle(final String clearDialogTitle) {
		this.clearDialogTitle = clearDialogTitle;
	}

	/**
	 * Retrieve the localisation text for success of the clear operation.
	 * @return a <code>String</code> containing the localisation text for success of the clear operation.
	 */
	public String getClearSuccessText() {
		return clearSuccessText;
	}

	/**
	 * Set the localisation text for success of the clear operation.
	 * @param clearSuccessText a <code>String</code> containing the localisation text for success of the clear operation.
	 */
	public void setClearSuccessText(final String clearSuccessText) {
		this.clearSuccessText = clearSuccessText;
	}

	/**
	 * Retrieve the localisation text for the edit label.
	 * @return a <code>String</code> containing the localisation text for the edit label.
	 */
	public String getEditLabelText() {
		return editLabelText;
	}

	/**
	 * Set the localisation text for the edit label.
	 * @param editLabelText a <code>String</code> containing the localisation text for the edit label.
	 */
	public void setEditLabelText(final String editLabelText) {
		this.editLabelText = editLabelText;
	}

	/**
	 * Retrieve the localisation text for an error caused by the edit operation.
	 * @return a <code>String</code> containing the localisation text for an error caused by the edit operation.
	 */
	public String getEditErrorText() {
		return editErrorText;
	}

	/**
	 * Set the localisation text for an error caused by the edit operation.
	 * @param editErrorText a <code>String</code> containing the localisation text for an error caused by the edit operation.
	 */
	public void setEditErrorText(final String editErrorText) {
		this.editErrorText = editErrorText;
	}

	/**
	 * Retrieve the localisation text for the title in the save file dialog.
	 * @return a <code>String</code> containing the localisation text for the title in the save file dialog.
	 */
	public String getSaveFileDialogTitle() {
		return saveFileDialogTitle;
	}

	/**
	 * Set the localisation text for the title in the save file dialog.
	 * @param saveFileDialogTitle a <code>String</code> containing the localisation text for the title in the save file dialog.
	 */
	public void setSaveFileDialogTitle(final String saveFileDialogTitle) {
		this.saveFileDialogTitle = saveFileDialogTitle;
	}

	/**
	 * Retrieve the localisation text for the title in the load file dialog.
	 * @return a <code>String</code> containing the localisation text for the title in the load file dialog.
	 */
	public String getLoadFileDialogTitle() {
		return loadFileDialogTitle;
	}

	/**
	 * Set the localisation text for the title in the load file dialog.
	 * @param loadFileDialogTitle a <code>String</code> containing the localisation text for the title in the load file dialog.
	 */
	public void setLoadFileDialogTitle(final String loadFileDialogTitle) {
		this.loadFileDialogTitle = loadFileDialogTitle;
	}

	/**
	 * Retrieve the localisation text for the file extension.
	 * @return a <code>String</code> containing the localisation text for the file extension.
	 */
	public String getFileExtension() {
		return fileExtension;
	}

	/**
	 * Set the localisation text for the file extension.
	 * @param fileExtension a <code>String</code> containing the localisation text for the file extension.
	 */
	public void setFileExtension(final String fileExtension) {
		this.fileExtension = fileExtension;
	}

	/**
	 * Retrieve the localisation text for the file extension name.
	 * @return a <code>String</code> containing the localisation text for the file extension name.
	 */
	public String getFileExtensionName() {
		return fileExtensionName;
	}

	/**
	 * Set the localisation text for the file extension name.
	 * @param fileExtensionName a <code>String</code> containing the localisation text for the file extension name.
	 */
	public void setFileExtensionName(final String fileExtensionName) {
		this.fileExtensionName = fileExtensionName;
	}

	/**
	 * Retrieve the localisation text for success of the save file operation.
	 * @return a <code>String</code> containing the localisation text for success of the save file operation.
	 */
	public String getSaveFileSuccessText() {
		return saveFileSuccessText;
	}

	/**
	 * Set the localisation text for success of the save file operation.
	 * @param saveFileSuccessText a <code>String</code> containing the localisation text for success of the save file operation.
	 */
	public void setSaveFileSuccessText(final String saveFileSuccessText) {
		this.saveFileSuccessText = saveFileSuccessText;
	}

	/**
	 * Retrieve the localisation text for an error caused by the save file operation.
	 * @return a <code>String</code> containing the localisation text for an error caused by the save file operation.
	 */
	public String getSaveFileErrorText() {
		return saveFileErrorText;
	}

	/**
	 * Set the localisation text for an error caused by the save file operation.
	 * @param saveFileErrorText a <code>String</code> containing the localisation text for an error caused by the save file operation.
	 */
	public void setSaveFileErrorText(final String saveFileErrorText) {
		this.saveFileErrorText = saveFileErrorText;
	}

	/**
	 * Retrieve the localisation text for success of the load file operation.
	 * @return a <code>String</code> containing the localisation text for success of the load file operation.
	 */
	public String getLoadFileSuccessText() {
		return loadFileSuccessText;
	}

	/**
	 * Set the localisation text for success of the load file operation.
	 * @param loadFileSuccessText a <code>String</code> containing the localisation text for success of the load file operation.
	 */
	public void setLoadFileSuccessText(final String loadFileSuccessText) {
		this.loadFileSuccessText = loadFileSuccessText;
	}

	/**
	 * Retrieve the localisation text for an error caused by the load file operation.
	 * @return a <code>String</code> containing the localisation text for an error caused by the load file operation.
	 */
	public String getLoadFileErrorText() {
		return loadFileErrorText;
	}

	/**
	 * Set the localisation text for an error caused by the load file operation.
	 * @param loadFileErrorText a <code>String</code> containing the localisation text for an error caused by the load file operation.
	 */
	public void setLoadFileErrorText(final String loadFileErrorText) {
		this.loadFileErrorText = loadFileErrorText;
	}

	/**
	 * Retrieve the localisation text for the message in the new dialog.
	 * @return a <code>String</code> containing the localisation text for the message in the new dialog.
	 */
	public String getNewDialogMessage() {
		return newDialogMessage;
	}

	/**
	 * Set the localisation text for the message in the new dialog.
	 * @param newDialogMessage a <code>String</code> containing the localisation text for the message in the new dialog.
	 */
	public void setNewDialogMessage(final String newDialogMessage) {
		this.newDialogMessage = newDialogMessage;
	}

	/**
	 * Retrieve the localisation text for the title in the new dialog.
	 * @return a <code>String</code> containing the localisation text for the title in the new dialog.
	 */
	public String getNewDialogTitle() {
		return newDialogTitle;
	}

	/**
	 * Set the localisation text for the title in the new dialog.
	 * @param newDialogTitle a <code>String</code> containing the localisation text for the title in the new dialog.
	 */
	public void setNewDialogTitle(final String newDialogTitle) {
		this.newDialogTitle = newDialogTitle;
	}

	/**
	 * Retrieve the localisation text for success for the new operation.
	 * @return a <code>String</code> containing the localisation text for success for the new operation.
	 */
	public String getNewSuccessText() {
		return newSuccessText;
	}

	/**
	 * Set the localisation text for success for the new operation.
	 * @param newSuccessText a <code>String</code> containing the localisation text for success for the new operation.
	 */
	public void setNewSuccessText(final String newSuccessText) {
		this.newSuccessText = newSuccessText;
	}
	
}
