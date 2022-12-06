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

	@Override
	public void put(HashElement key) {
		int hashCode = gethashCode(key);
		System.out.println(hashCode);
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
