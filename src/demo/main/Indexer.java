package demo.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class Indexer {
	public static final String RESOURCE_PATH = "src/demo/resource";

	public static void startIndex(boolean NGRAM_ENABLE) throws IOException {
		Index index = new Index();
		File dir = new File(RESOURCE_PATH);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File document : directoryListing) {
				System.out.println("Indexing for: " + document.getAbsolutePath());
				BufferedReader br = new BufferedReader(new FileReader(document));
				String line = null;
				while ((line = br.readLine()) != null) {
					StringTokenizer st = new StringTokenizer(line);
					while (st.hasMoreElements()) {
						String word = (String) st.nextElement();
						if (NGRAM_ENABLE) {
							NGramTokenizer nGramTokenizer = new NGramTokenizer();
							List<String> terms = nGramTokenizer.getTerms(word);
							for (String term : terms) {
								index.setIndex(term, document.getName());
							}
						} else {
							index.setIndex(word, document.getName());
						}
					}
				}
			}
		}
		index.saveIndexToFile();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("===Full Text Search Console Demo===\n");
		System.out.print("Enable for NGRAM (y/n)? ");
		try {
			String ans = bReader.readLine();
			startIndex(ans.toUpperCase().equals("Y") ? true : false);

			System.out.println("==========================");
			System.out.println("All documents has been indexed successfully!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			main(args);
		} finally {
			if (bReader != null) {
				try {
					bReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
