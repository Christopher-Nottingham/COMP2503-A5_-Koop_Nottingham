package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.System.Logger;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.*;

/**
 * Assignment 5 main class
 * @author Ethan Koop and Christopher Nottingham
 *
 */
public class WordCounterApp {

	/**
	 * Main entry point into this application
	 * Takes in a file provided by the user
	 * Takes the size provided by the user
	 * Provides most common word and total count of all words in the file
	 * @param args
	 * @throws FileNotFoundException in case file is not found
	 */
	public static void main(String[] args){
		


		System.out.print("Enter the name of the desired file: ");

		Scanner in = new Scanner(System.in);
		
		final String DB_PATH = in.nextLine();
		
		System.out.print("Enter the size of the table: ");

		
		
		int size = in.nextInt();
			
		
		
		try {
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
				wc.printTable();
				wc.printDistinctWords();
				wc.printMostComonWord();
				wc.printTotalNumberOfWords();

			}
		} catch (FileNotFoundException e) {
			System.out.println("Invalid file, please try again.");
		}
		
		
		
		
		
		
		
		
			
		
		
		
		
		
		
		
		
		
		
		
		

		
	}
}
