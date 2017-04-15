package inst.an.algorithms.linkedlist;

public class LInkedListApp {

	public static void main(String[] args) {
		LinkedList<String> strList = new LinkedList<>();
		strList.insert("Ananth");
		strList.insert("Shravani");
		strList.insert("Meenal");
		strList.display();
		System.out.println("Size of the linked list - "+strList.size());
	}
}
