package demo.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Searcher {
	
	public Searcher(){
		
	}
	
	public List<String> tokenSearchString(String searchvalue){
		List<String> tokens = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(searchvalue);
		while (st.hasMoreElements()) {
			String string = (String) st.nextElement();
			tokens.add(string);
		}
		return tokens;
	}
	
	public Set<String> booleanOperator(List<Set<String>> listDocs){
		Set<String> result = new HashSet<>();
		if(listDocs.size() == 1){
			return listDocs.get(0);
		}
		for (int i = 0; i < listDocs.size() - 1; i++) {
			Set<String> set1 = listDocs.get(i);
			Set<String> set2 = listDocs.get(i + 1);
			set1.addAll(set2);
			result = set1;
		}
		return result;
	}
	
	public String search(String searchvalue){
		List<String> tokens = tokenSearchString(searchvalue);
		Index index = new Index();
		List<Set<String>> listDocs = new ArrayList<>();
		for (String token : tokens) {
			List<String> docs = index.indexTable.get(token);
			if(docs != null){
				Set<String> set = new HashSet<>(docs);
				listDocs.add(set);
			}
		}
		Set<String> result = booleanOperator(listDocs);
		return result.toString();
	}
}
