package inst.an.algorithms.trees;

public class CharCount implements Comparable<Character>{
	private Character character;
	private int count;
	
	public CharCount(char ch, int i) {
		this.character = new Character(ch);
		this.count = i;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int compareTo(Character o) {
		return this.character.compareTo(o);
	}
}
