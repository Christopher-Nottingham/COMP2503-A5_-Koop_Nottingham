package model;

public class HashElement {

	private String word; //key for the hash table
	private int count;
	
	public HashElement (String word, int count) {
		this.word = word;
		this.count = count;
	}
	public HashElement (String word) {
	  this.word=word;
	  this.count=0;
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		return "Number of times " + word + " occures is: " + count;
	}


}
