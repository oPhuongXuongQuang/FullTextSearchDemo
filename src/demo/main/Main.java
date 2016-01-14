package demo.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader bReader = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.println("===Full Text Search Console Demo===\n");
		System.out.print("What are you searching for? Enter text: ");
		try {
			String value = bReader.readLine();
			Searcher searcher = new Searcher();
			String result = searcher.search(value);
			System.out.println("\nYour search result: " + result);
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
