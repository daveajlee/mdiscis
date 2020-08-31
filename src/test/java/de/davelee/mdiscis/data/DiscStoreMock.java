package de.davelee.mdiscis.data;

import java.io.File;

import de.davelee.mdiscis.data.DiscStore;

public class DiscStoreMock extends DiscStore {
	
	public boolean saveFile(File file) {
		return file.getPath().contains("selected.mdi");
	}
	
	public boolean loadFile(File file) {
		return file.getPath().contains("selectedload.txt");
	}

}
