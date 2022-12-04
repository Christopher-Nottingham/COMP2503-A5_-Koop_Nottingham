package model;

import java.util.Arrays;
import model.HashInterface;

public class WordCounter implements HashInterface<HashElement> {


  public WordCounter(int size) {
    this.size = size;
  }

  private int size;
  HashElement[] hashtable = new HashElement[size];



  @Override
  public int gethashCode(HashElement key) {
    int asciiSum = 0;
    char charKey[] = key.getWord().toString().toCharArray();
    int s = charKey.length;
    int a = 1;
    System.out.println("The charKey[] lenght " + s);
    for (int counter = 0; counter < charKey.length; counter++) {

      int letter = charKey[counter];
      asciiSum = asciiSum + (letter * (a ^ (charKey.length - counter)));


    }
    return asciiSum % size;
  }

  @Override
  public void put(HashElement key) {
    // TODO Auto-generated method stub
    int hashCode = gethashCode(key);
    if (hashtable==null) {
      hashtable[hashCode] = key;
    } else {
      if (hashtable[hashCode] != null) {
        putQuad(hashCode, key);
      } else {
        System.out.println("Table is full");
      }
    }
  }



  @Override
  public HashElement remove(HashElement key) {
    // TODO Auto-generated method stub
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
        System.out.println(" ");
      } else {
        System.out.println(hashtable[index].getWord().toString());
      }

    }
  }

  public int quadraticProb(int index) {
    /*
     * Prob Index = hashcode + j2
     */

    int hashcode = index;
    for (int j = 0; j < size; j++) {
      int k = j + 1;
      index = hashcode * (k * k) % size;
      if (hashtable[index] == null) {
        return index;
      }
    }
    return -1;
  }

  public void putQuad(int index, HashElement theArg) {
    if (hashtable[index] == null) {
      hashtable[index] = theArg;
    } else {
      int j = quadraticProb(index);
      if (j == -1) {
        System.out.println("Error the table is full!!!!!!!");
      } else {
        System.out.println("Quad put" + j);
        hashtable[j] = theArg;
      }

    }

  }

}


