package demo.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Index {
	public HashMap<String, List<String>> indexTable;
	public static final String INDEX_PATH = "../indexes.properties";
	
	public Index() {
		if(!Files.exists(Paths.get(INDEX_PATH))){
			indexTable = new HashMap<>();
		} else {
			indexTable = this.loadIndexFromFile();
		}
	}

	public void setIndex(String term, String docID) {
		List<String> docs = this.indexTable.get(term);
		docs.add(docID);
		this.indexTable.put(term, docs);
	}
	
	public void saveIndexToFile(){
		Properties properties = new Properties();

		properties.putAll(indexTable);
		try {
			properties.store(new FileOutputStream(INDEX_PATH), null);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HashMap<String, List<String>> loadIndexFromFile(){
		Properties properties = new Properties();
		HashMap<String, List<String>> indexTable = new HashMap<>();
		try {
			properties.load(new FileInputStream(INDEX_PATH));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (String key : properties.stringPropertyNames()) {
			indexTable.put(key, (List<String>)properties.get(key));
		}
		return indexTable;
	}
}
