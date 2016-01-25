package demo.main;

import java.util.ArrayList;
import java.util.List;

public class NGramTokenizer {
	final int NGRAM = 2;
	public List<String> terms;

	public NGramTokenizer() {
		terms = new ArrayList<>();
	}

	public List<String> getTerms(String string) {
		terms.add(string.toLowerCase());
//		if (!string.equals(string.toLowerCase())) {
//			terms.add(string.toLowerCase());
//		}
		if (string.length() == 1) {
			return terms;
		}
		for (int i = 0; i <= string.length() - NGRAM; i++) {
			int currentGRAM = NGRAM + i;
			for (int j = 0; j + currentGRAM - 1 < string.length(); j++) {
				String term = string.substring(j, j + currentGRAM);
				terms.add(term.toLowerCase());
			}
		}
		return terms;
	}
}
