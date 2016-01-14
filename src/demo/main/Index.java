package demo.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Index {
	public HashMap<String, List<String>> indexTable;
	public static final String INDEX_PATH = "index/indexes.txt";
	
	public Index() {
		if(!Files.exists(Paths.get(INDEX_PATH))){
			indexTable = new HashMap<>();
		} else {
			indexTable = this.loadIndexFromFile();
		}
	}

	public void setIndex(String term, String docID) {
		List<String> docs = this.indexTable.get(term);
		if(docs == null){
			docs = new ArrayList<String>();
		}
		docs.add(docID);
		this.indexTable.put(term, docs);
	}
	
	public void saveIndexToFile(){
		ObjectOutputStream s = null;
		try {
			FileOutputStream f = new FileOutputStream(new File(INDEX_PATH));
	        s = new ObjectOutputStream(f);
	        s.writeObject(indexTable);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public HashMap<String, List<String>> loadIndexFromFile(){
		HashMap<String, List<String>> indexTable = new HashMap<>();
		ObjectInputStream s = null;
		try {
			FileInputStream f = new FileInputStream(new File(INDEX_PATH));
		    s = new ObjectInputStream(f);
		    indexTable = (HashMap<String, List<String>>) s.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				s.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return indexTable;
	}
}
