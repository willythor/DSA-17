import java.util.NoSuchElementException;

public class MyLinkedList {

	private Node head;
	private Node tail;
	private int size;

	private class Node {
		Chicken val;
		Node prev;
		Node next;

		private Node(Chicken d, Node prev, Node next) {
			this.val = d;
			this.prev = prev;
			this.next = next;
		}
	}

	public MyLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void add(Chicken c) {
		addLast(c);
	}

	public Chicken pop() {
		return removeLast();
	}

	public void addLast(Chicken c) {
		if (size == 0) {
			head = new Node(c,null,null);
			tail = head;
			size++;
		}
		else if (size != 0) {
			tail.next = new Node(c, tail, null);
			tail = tail.next;
			size++;
		}
	}

	public void addFirst(Chicken c) {
		if (size == 0) {
			head = new Node(c,null,null);
			tail = head;
			size++;
		}
		else if (size != 0) {
			head.prev = new Node(c,null,head);
			head = head.prev;
			size++;
		}
	}

	public Chicken get(int index) {
		return getNode(index).val;
	}

	public Node getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		Node elem = head;
		for (int i = 0; i < index; i++)
		{
			elem = elem.next;
		}
		return elem;

	}

	public Chicken remove(int index) {
		if (size == 0) {throw new NoSuchElementException();}
		Node elem = getNode(index);
		if (index == 0){
			return removeFirst();
		}
		if (index == size -1){
			return removeLast();
		}
		if (size == 1) {head = null; tail = null; size--;}
		if (size > 1) {
			elem.prev.next = elem.next;
			elem.next.prev = elem.prev;
			size--;
		}

		return elem.val;
	}

	public Chicken removeFirst() {
		if (size == 0) {throw new NoSuchElementException();}
		Node elem = head;
		if (size == 1) {head = null; tail = null; size--;}
		if (size > 1) {head = head.next; size--; head.prev = null;}
		return elem.val;

	}

	public Chicken removeLast() {
		if (size == 0) {throw new NoSuchElementException();}
		Node elem = tail;
		if (size == 1) {head = null;
		tail = null;
		size--;}

		if (size > 1) {tail=tail.prev;
		tail.next=null;
		size--;}
		return elem.val;
	}
}