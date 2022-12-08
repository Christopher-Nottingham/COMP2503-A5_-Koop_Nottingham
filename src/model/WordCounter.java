package model;



import model.HashElement;
import model.HashInterface;

public class WordCounter implements HashInterface<HashElement> {

	private int size;
	private HashElement[] hashtable;

	public WordCounter(int size) {
		this.size = size;
		hashtable = new HashElement[size];
	}

	/**
	 * @author Christopher Nottingham
	 * Solves the hashcode from the assignment by getting the ASCII value of each character and adding it for each word, 
	 * then takes the total ASCII value of the word and returns the modular of the total by the size of the table. 
	 * @param key to retrieve hashcode from
	 */
	@Override
	public int gethashCode(HashElement key) {
		int asciiSum = 0;
		char charKey[] = key.getWord().toString().toLowerCase().toCharArray();
		int s = charKey.length;
		int a = 2;
		System.out.println("The charKey[] lenght " + s);
		for (int counter = 0; counter < charKey.length; counter++) {
			int letter = charKey[counter];
			asciiSum = asciiSum + (letter * (a ^ (charKey.length - (counter+1))));
			System.out.println("The ascii number of " + charKey[counter] + " is " + letter);
			System.out.println("The power of a is " + (charKey.length-(counter+1)));
		}
		return asciiSum % size;
	}

	/**
	 * @author Christopher Nottingham
	 * Puts the word into the hashtable using quadratic probing
	 * @param key to be put into the table
	 */
	@Override
	public void put(HashElement key) {
		int hashCode = gethashCode(key);
		System.out.println(hashCode);
		putQuad(hashCode, key);
		
		

	}

	/**
	 * @author Ethan Koop
	 * Finds the given key, removes the key and replaces it's value in the table as "null"
	 * This leaves room for another item to be put. 
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
	 * @param index to be probed
	 * @return the probed index
	 */
	public int quadraticProb(int index) {
		/*
		 * Prob Index = hashcode + j2
		 */

		//int hashcode = index;
//		String word = hashtable[index].getWord();
//		int lengthOfWord= hashtable[index].getWord().length();
//		for (int j = 0; j < lengthOfWord; j++) {
//			int k = j + 1;
//			System.out.println(k);
//			index = index * (k * k) % size;
//			if (hashtable[index] == null) {
//				return index;
//			}
//		}
//		return -1;
		String word = hashtable[index].getWord();
		
		int lengthOfWord= hashtable[index].getWord().length();
		int probeIndex = 0;
		
		int hashcode = index;
		
		for (int j = 0; j < size; j++) {
		
			int k = j + 1;
			
		
			probeIndex = (hashcode + (k * k))% size;
			
			if (hashtable[probeIndex] == null) {
			
				return probeIndex;
			
			}
		
		}
		
		return -1;
	}

	/**
	 * Puts the item using quadratic probing
	 * @param index to be put
	 * @param theArg to be put
	 */
	public void putQuad(int index, HashElement theArg) {
		if (hashtable[index] == null) {
			
			hashtable[index] = theArg;
			
		} else {
			HashElement keyToRemove = hashtable[index];
			
			
			
			int j = quadraticProb(index);
			remove(keyToRemove);
			if (j == -1) {
				System.out.println("Error the table is full!");
			} else {
				
				hashtable[j] = theArg;
//				System.out.println("The current count is: " + hashtable[j].getCount());
//				System.out.println("The current count for the Arg is: " + theArg.getCount());
				//int currentCount = hashtable[j].getCount();
				
				//hashtable[j].setCount(currentCount + 1);
//				System.out.println("The new count is: " + hashtable[j].getCount());
				theArg.setCount(theArg.getCount()+1);
//				System.out.println("The new count for the Arg is: " + theArg.getCount());
				
			}

		}

	}

}
