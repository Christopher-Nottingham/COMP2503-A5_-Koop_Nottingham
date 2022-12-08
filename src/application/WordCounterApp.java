package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.System.Logger;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.*;

/**
 * Assignment 5 main class
 * 
 * @author Ethan Koop and Christopher Nottingham
 *
 */
public class WordCounterApp {

	/**
	 * Main entry point into this application Takes in a file provided by the user
	 * Takes the size provided by the user Provides most common word and total count
	 * of all words in the file
	 * 
	 * @param args
	 * @throws FileNotFoundException in case file is not found
	 */
	public static void main(String[] args) throws FileNotFoundException {

		System.out.print("Enter the name of the desired file: ");

		Scanner in = new Scanner(System.in);

		String DB_PATH = in.nextLine();
		File database = new File(DB_PATH);

		while (!database.exists()) {
			System.out.println("File does not exist....");
			System.out.print("Enter the name of the desired file: ");
			DB_PATH = in.nextLine();
			database = new File(DB_PATH);
		}

		System.out.print("Enter the size of the table: ");

		while (!in.hasNextInt()) {
			System.out.println("Worng input.....please enter an integer value");
			System.out.print("Enter the size of the table: ");
			in.next();

		}

		int size = in.nextInt();

		Scanner wordReader = new Scanner(database);
		WordCounter wc = new WordCounter(size);

		while (wordReader.hasNextLine()) {
			String lineOfWords = wordReader.nextLine();
			String[] wordArray = lineOfWords.toLowerCase().trim().split(" ");
			for (int index = 0; index < wordArray.length; index++) {
				HashElement word = new HashElement(wordArray[index]);
				wc.put(word);
			}
			wc.printTable();
			wc.printDistinctWords();
			wc.printMostComonWord();
			wc.printTotalNumberOfWords();

		}

	}
}
