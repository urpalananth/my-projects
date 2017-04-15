package com.intuit.wordprocessor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class StatementProcessor {

	private char [] statement;
	
	public StatementProcessor(String statement) {
		if(statement != null)
			this.statement = statement.toCharArray();
	}
	
	public long getWordCount(){
		if(statement == null)
			return 0;
		int wordCount = 0, charCount=0;
		for (char c : statement) {
			if(c != ' '){
				charCount++;
			}else{
				if(charCount !=0){
					wordCount++;
				}
				charCount = 0;
			}
		}
		if(charCount > 0)
			wordCount = wordCount + 1;//for the last word there's no delimiter.
		return wordCount; 
	}
	
	public long getAllCharacterCount(){
		if(statement == null)
			return 0;
		int count = 0;
		for (char c : statement) {
			if(c != ' ')
				count++;
		}
		return count;
	}
	
	public void getCharacterCount(){
		BinaryTree bt = new BinaryTree();
		for (char c : statement) {
			bt.add(c);
		}
		
		for (char c : statement) {
			if(bt.get(c).getCount() != 0 ) {
				System.out.println(c+": "+bt.get(c).getCount());
				bt.get(c).setCount(0);
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println("Please enter the input: ");
		Scanner input = new Scanner(System.in);
		StatementProcessor sp = new StatementProcessor(input.nextLine());

		System.out.println("Word count = "+sp.getWordCount());
		System.out.println("Non-whitespace character count = "+sp.getAllCharacterCount());
		System.out.println("Unique breakdown of all characters in order they appeared...");
		sp.getCharacterCount();
		input.close();
	}
}
