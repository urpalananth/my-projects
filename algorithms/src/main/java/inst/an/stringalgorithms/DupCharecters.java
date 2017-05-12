package inst.an.stringalgorithms;

import java.util.HashMap;
import java.util.Map;

public class DupCharecters {
	
	private String input;
	
	public DupCharecters(String input) {
		this.input = input;
	}
	/**
	 * This is a very naive implementation, where time complexity is n^2
	 * @return
	 */
	public boolean hasDuplicate(){
		char [] arr = input.toCharArray();
		for (int i = 0; i < arr.length -1; i++) {
			char c = arr[i];
			for(int j = i +1; j < arr.length; j++){
				if(c == arr[j])
					return true;
			}
			
		}
		return false;
	}
	/**
	 * This improvises on the above implementation, by using map the time comes down to n
	 * @return
	 */
	public boolean hasDuplicate1(){
		char [] arr = input.toCharArray();
		Map<Character, Boolean> charMap = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			if(charMap.get(c) == null){
				charMap.put(c, true);
			}else{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * If we reduce the character set to ASCII only, then we know that we only have to deal with 127 characters.
	 */
	public boolean hasDuplicate2(){
		char [] arr = input.toCharArray();
		Map<Character, Boolean> charMap = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			char c = arr[i];
			if(charMap.get(c) == null){
				charMap.put(c, true);
			}else{
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		DupCharecters dc = new DupCharecters("abcc");
		System.out.println("--> "+dc.hasDuplicate1());
	}
}
