package application;

import model.*;


public class WordCounterApp {



	

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println("hi");
    WordCounter wc = new WordCounter(70);
    wc.put(new HashElement ("Chris" , 0));
    wc.printTable();
    
    

  }


}
