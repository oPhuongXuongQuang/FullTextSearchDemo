package demo.main;

import java.util.ArrayList;
import java.util.List;

public class NGramTokenizer {
	final int NGRAM = 2; 
	public List<String> terms;
	
	public NGramTokenizer(){
		terms = new ArrayList<>();
	}
	
	public List<String> getTerms(String string){
		terms.add(string);
		if(string.length() == 1){
			return terms;
		}
		for (int i = 0; i + NGRAM - 1 < string.length(); i++) {
			String term = string.substring(i, i + NGRAM);
			terms.add(term);
		}
		return terms;
	}
}
