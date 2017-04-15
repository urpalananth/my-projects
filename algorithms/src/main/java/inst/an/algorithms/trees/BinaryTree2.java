package inst.an.algorithms.trees;

public class BinaryTree2 {
	private Node root;
	
	public void add(char ch){
		if(root == null){
			Node newNode = new Node(ch, 1);
			root = newNode;
		
		}else{
			addChar(root, ch);
		}
	}

	private void addChar(Node node, char ch) {
		if (ch < node.getValue()){
			if(node.getLeft() != null){
				addChar(node.getLeft(), ch);
			}else{
				Node newNode = new Node(ch, 1);
				node.setLeft(newNode);
			}
		} else if (ch > node.getValue()) {
			if(node.getRight()!= null)
				addChar(node.getRight(), ch);
			else{
				Node newNode = new Node(ch, 1);
				node.setRight(newNode);
			}
		} else if(ch == node.getValue()){
			node.setCount(node.getCount() + 1);
		}
	}
	
	public Node get(char ch){
		if(root == null)
			return null;
		
		return getCharNode(root, ch);
	}

	private Node getCharNode(Node node, char ch) {

		if (ch < node.getValue()){
			if(node.getLeft() != null)
				return getCharNode(node.getLeft(), ch);
		} else if (ch > node.getValue()) {
			if(node.getRight()!= null)
				return getCharNode(node.getRight(), ch);
		} else if(ch == node.getValue()){
			if (node.getLeft() == null)
			return node;
		}
		return null;
	}

	static class Node {
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
	
	public static void main(String[] args) {
		String str = "ananth is a good boy";
		BinaryTree2 bt = new BinaryTree2();
		for (char c : str.toCharArray()) {
			bt.add(c);
		}
		
		for (char c : str.toCharArray()) {
			System.out.println(c+": "+bt.get(c).getCount());
		}
	}
}
