package model;

import java.util.Arrays;

import model.HashElement;
import model.HashInterface;

public class WordCounter implements HashInterface<HashElement> {
	private int counter = 0;
	private int size;
	private HashElement[] hashtable;
	private String hashIndexToString = null;

	public WordCounter(int size) {
		this.size = size;
		hashtable = new HashElement[size];
	}

	/**
	 * @author Christopher Nottingham 
	 * Solves the hashcode from the assignment by
	 * getting the ASCII value of each character and adding it for each
	 * word, then takes the total ASCII value of the word and returns the
	 * modular of the total by the size of the table.
	 * @param key to retrieve hashcode from
	 */
	@Override
	public int gethashCode(HashElement key) {
		int asciiSum = 0;
		char charKey[] = key.getWord().toString().toLowerCase().toCharArray();
		int s = charKey.length;
		int a = 2;

		for (int counter = 0; counter < charKey.length; counter++) {
			int letter = charKey[counter];
			asciiSum = asciiSum + (letter * (a ^ (charKey.length - (counter + 1))));
		}
		return asciiSum % size;
	}

	/**
	 * @author Christopher Nottingham 
	 * Puts the word into the hashtable using
	 * quadratic probing
	 * @param key to be put into the table
	 */
	@Override
	public void put(HashElement key) {
		int hashCode = gethashCode(key);
		putQuad(hashCode, key);
	}

	/**
	 * @author Ethan Koop 
	 * Finds the given key, removes the key and replaces it's
	 * value in the table as "null" This leaves room for another item to be
	 * put.
	 */
	@Override
	public HashElement remove(HashElement key) {

		int toRemove = gethashCode(key);

		for (int i = 0; i < size; i++) {
			int temp = gethashCode(key);

			temp = (toRemove + (i * i) % size);

			if (hashtable[temp] == key) {
				hashtable[temp] = null;
				return key;
			}
		}

		return null;
	}

	/**
	 * @author Christopher Nottingham 
	 * Resets the hashtable
	 */
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		for (int i = 0; i < size; i++) {
			hashtable[i] = null;

		}
	}

	/**
	 * @author Christoper Nottingham 
	 * Prints the table.
	 */
	@Override
	public void printTable() {
		// TODO Auto-generated method stub
		for (int index = 0; index < size; index++) {
			if (hashtable[index] == null) {

			} else {

				System.out.println(hashtable[index]);
			}

		}
	}

	/**
	 * @author Christopher Nottingham
	 * Method for finding where to put the hash element in the event of a collision
	 * @param index to be probed
	 * @return the probed index
	 */
	public int quadraticProb(int index) {

		String word = hashtable[index].getWord();

		int lengthOfWord = hashtable[index].getWord().length();
		int probeIndex = 0;

		int hashcode = index;

		for (int j = 0; j < size; j++) {

			int k = j + 1;

			probeIndex = (hashcode + (k * k)) % size;

			if (hashtable[probeIndex] == null) {

				return probeIndex;

			}

		}

		return -1;
	}

	/**
	 * Puts the item using quadratic probing
	 * 
	 * @param index  to be put
	 * @param theArg to be put
	 */
	public void putQuad(int index, HashElement theArg) {

		if (theArg == null) {
			counter--;
		}
		String theArgToString = theArg.getWord();
		if (hashtable[index] != null) {

			hashIndexToString = hashtable[index].getWord().toString();
			//hashtable[index].setCount(hashtable[index].getCount());
			
		}

		if (hashtable[index] == null) {
			counter++;
			hashtable[index] = theArg;
			hashtable[index].setCount(hashtable[index].getCount());
		}

		else if (hashIndexToString.compareTo(theArgToString) == 0) {
			counter++;
			hashtable[index].setCount(hashtable[index].getCount());
		} else {

		}

	}

	/**
	 * A void returning method that counts the number of distinct words in the file
	 */
	public void printDistinctWords() {
		int counter = 0;
		for (int i = 0; i < hashtable.length; i++) {
			if (hashtable[i] != null) {
				counter++;
			}

		}
		System.out.println("\nTotal number of distinct words are: " + counter);
	}

	/**
	 * A void returning method that counts the most common word in the file
	 */
	public void printMostComonWord() {

		HashElement theMostCommonWord = new HashElement(null);

		for (int i = 0; i < hashtable.length; i++) {
			{

				if (hashtable[i] == null) {

				} else if (hashtable[i].getCount() > theMostCommonWord.getCount()) {
					theMostCommonWord = hashtable[i];
				} else {

				}

			}

		}

		System.out.println("The most common word is " + "'" + theMostCommonWord.getWord() + "'" + " and occurs "
				+ theMostCommonWord.getCount() + " times");
	}

	/**
	 * A void returning method that counts the total number of words in the file
	 */
	public void printTotalNumberOfWords() {
		System.out.println("The total number of words in the file are: " + counter);
	}
}
