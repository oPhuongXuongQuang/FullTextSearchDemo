package demo.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Searcher {

	public Searcher() {

	}

	public String search(String searchvalue) {
		List<String> tokens = tokenSearchString(searchvalue);
		Index index = new Index();
		List<List<Result>> listDocs = new ArrayList<>();
		for (String token : tokens) {
			List<Result> results = index.indexTable.get(token);
			if (results != null) {
				listDocs.add(results);
			}
		}
		
		List<Result> result = booleanOperator(listDocs);
		// Sort the result
		Collections.sort(result, new Comparator<Result>() {
			public int compare(Result r1, Result r2) {
				int i1 = r1.rank;
				int i2 = r2.rank;
				return (i1 > i2 ? -1 : (i1 == i2 ? 0 : 1));
			}
		});
		Set<String> setResult = new LinkedHashSet<>(convertToListDoc(result));
		return setResult.toString();
	}
	
	public List<String> convertToListDoc(List<Result> results) {
		List<String> docs = new ArrayList<>();
		for (Result result : results) {
			docs.add(result.docName);
		}
		return docs;
	}

	public List<String> tokenSearchString(String searchvalue) {
		List<String> tokens = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(searchvalue);
		while (st.hasMoreElements()) {
			String string = (String) st.nextElement();
			tokens.add(string);
		}
		return tokens;
	}

	public List<Result> booleanOperator(List<List<Result>> listDocs) {
		List<Result> result = new ArrayList<>();
		if (listDocs.size() == 1) {
			return listDocs.get(0);
		}
		for (int i = 0; i < listDocs.size() - 1; i++) {
			List<Result> set1 = listDocs.get(i);
			List<Result> set2 = listDocs.get(i + 1);
			set1.addAll(set2);

			result.addAll(set1);
		}
		return result;
	}

}
