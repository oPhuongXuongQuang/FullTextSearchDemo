package demo.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Searcher {
	
	public Searcher(){
		
	}
	
	public String search(String searchvalue){
		List<String> tokens = tokenSearchString(searchvalue);
		Index index = new Index();
		List<Set<Result>> listDocs = new ArrayList<>();
		for (String token : tokens) {
			List<Result> docs = index.indexTable.get(token);
			if(docs != null){
				Set<Result> set = new HashSet<>(docs);
				listDocs.add(set);
			}
		}
		Set<Result> result = booleanOperator(listDocs);
		
		ArrayList<Result> resultList = new ArrayList<>(result);
		
		// Sort the result
		Collections.sort(resultList, new Comparator<Result>() {
		    public int compare(Result r1, Result r2) {
		        int i1 = r1.rank;
		        int i2 = r2.rank;
		        return (i1 > i2 ? -1 : (i1 == i2 ? 0 : 1));
		    }
		});
		
		StringList stringList = new StringList(resultList);
		return stringList.toString();
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
	
	public Set<Result> booleanOperator(List<Set<Result>> listDocs){
		Set<Result> result = new HashSet<>();
		if(listDocs.size() == 1){
			return listDocs.get(0);
		}
		for (int i = 0; i < listDocs.size() - 1; i++) {
			Set<Result> set1 = listDocs.get(i);
			Set<Result> set2 = listDocs.get(i + 1);
			set1.addAll(set2);
			result = set1;
		}
		return result;
	}
	
	class StringList extends ArrayList<Result>{
		ArrayList<Result> resultList;
		
		public StringList() {
			// TODO Auto-generated constructor stub
		}
		
		public StringList(ArrayList<Result> resultList) {
			this.resultList = resultList;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			Iterator<Result> it = this.resultList.iterator();
	        if (! it.hasNext())
	            return "[]";
	        
			StringBuilder sb = new StringBuilder();
	        sb.append('[');
	        for (;;) {
	        	Result r = it.next();
	            sb.append(r.docName);
	            if (! it.hasNext())
	                return sb.append(']').toString();
	            sb.append(',').append(' ');
	        }
		}
	}
}
