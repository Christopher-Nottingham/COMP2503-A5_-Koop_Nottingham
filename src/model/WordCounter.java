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

	@Override
	public void put(HashElement key) {
		int hashCode = gethashCode(key);
		putQuad(hashCode, key);
	}

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

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		for (int i = 0; i < size; i++) {
			hashtable[i] = null;

		}
	}

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

	public void putQuad(int index, HashElement theArg) {

		if (theArg == null) {
			counter--;
		}
		String theArgToString = theArg.getWord();
		if (hashtable[index] != null) {

			hashIndexToString = hashtable[index].getWord().toString();
		}

		if (hashtable[index] == null) {
			counter++;
			hashtable[index] = theArg;
		}

		else if (hashIndexToString.compareTo(theArgToString) == 0) {
			counter++;
			hashtable[index].setCount(hashtable[index].getCount());
		} else {

		}

	}

	public void printDistinctWords() {
		int counter = 0;
		for (int i = 0; i < hashtable.length; i++) {
			if (hashtable[i] != null) {
				counter++;
			}

		}
		System.out.println("\nTotal number of distinct words are: " + counter);
	}

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

	public void printTotalNumberOfWords() {
		System.out.println("The total number of words in the file are: " + counter);
	}
}
