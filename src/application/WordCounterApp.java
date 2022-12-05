package application;

import java.io.File;
import java.util.Scanner;

import model.*;


public class WordCounterApp {



	

  public static void main(String[] args) {
	  System.out.print("Eneter the name of the desired file: ");

	    // res/argfoo.txt
	    Scanner in = new Scanner(System.in);
	    final String DB_PATH = in.nextLine();
	    System.out.print("Enter the size of the table: ");
	    int size = in.nextInt();
	    final File database = new File(DB_PATH);
	    Scanner wordReader = new Scanner(database);
	    WordCounter wc = new WordCounter(size);

	    while (wordReader.hasNextLine()) {
	      String lineOfWords = wordReader.nextLine();
	      System.out.println(lineOfWords);
	      String[] wordArray = lineOfWords.toLowerCase().split(" ");
	      for (int index = 0; index < wordArray.length; index++) {
	        // System.out.println(wordArray[index]);
	        HashElement word = new HashElement(wordArray[index]);
	        wc.put(word);
	      }
    // TODO Auto-generated method stub
    System.out.println("hi");
    WordCounter wc = new WordCounter(70);
    wc.put(new HashElement ("Chris" , 0));
    wc.printTable();
    
    

  }


}
