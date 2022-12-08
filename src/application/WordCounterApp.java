package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.*;

public class WordCounterApp {

	public static void main(String[] args) throws FileNotFoundException {

		System.out.print("Enter the name of the desired file: ");

		Scanner in = new Scanner(System.in);
		final String DB_PATH = in.nextLine();
		System.out.print("Enter the size of the table: ");

		int size = in.nextInt();

		final File database = new File(DB_PATH);
		Scanner wordReader = new Scanner(database);
		WordCounter wc = new WordCounter(size);

		while (wordReader.hasNextLine()) {
			String lineOfWords = wordReader.nextLine();
			String[] wordArray = lineOfWords.toLowerCase().trim().split(" ");
			for (int index = 0; index < wordArray.length; index++) {
				HashElement word = new HashElement(wordArray[index]);
				wc.put(word);
			}

		}

		wc.printTable();
		wc.printDistinctWords();
		wc.printMostComonWord();
		wc.printTotalNumberOfWords();
	}
}
