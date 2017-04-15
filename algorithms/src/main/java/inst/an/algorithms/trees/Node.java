package inst.an.algorithms.trees;

public class Node {
	private char value;
	private int count;
	private Node left;
	private Node right;

	public Node(char ch, int i) {
		this.value = ch;
		this.count = i;
	}
	public char getValue() {
		return value;
	}
	public void setValue(char value) {
		this.value = value;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
}	