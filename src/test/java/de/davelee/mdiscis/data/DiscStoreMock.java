package de.davelee.mdiscis.data;

import java.io.File;

import de.davelee.mdiscis.data.DiscStore;

public class DiscStoreMock extends DiscStore {
	
	public boolean saveFile(File file) {
		if ( file.getPath().contains("selected.mdi")) {
			return true;
		}
		return false;
	}
	
	public boolean loadFile(File file) {
		if ( file.getPath().contains("selectedload.txt")) {
			return true;
		}
		return false;
	}

}
