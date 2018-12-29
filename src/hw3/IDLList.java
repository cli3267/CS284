package hw3;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * I pledge my Honor that I have abided by the Stevens Honor System. 
 * @author christinali
 *
 * @param <E>
 */
public class IDLList<E> {
	private static class Node<E>{
		//data fields
		private E data;
		private Node<E> next;
		private Node<E> prev;
		
		//Constructors
		/**
		 * constructs new node, given data
		 * @param elem data thats stored in the node
		 */
		Node(E elem){
			data = elem;
		}
		/**
		 * constructs new node, give data with references to the previous and next nodes
		 * @param elem data thats stored in the node
		 * @param prev previous node in the list
		 * @param next next node in the list
		 */
		Node(E elem, Node<E> prev, Node<E> next){
			data = elem;
			this.prev = prev;
			this.next = next;
		}
	}
	//data fields
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	//Constructor
	/**
	 * creates an empty double linked list
	 */
	public IDLList() {
		head = null;
		tail = null;
		size = 0;
		indices = new ArrayList<Node<E>>();
	}
	/**
	 * adds elem at position index
	 * @param index position that the node will be added
	 * @param element data stored in the node
	 * @return true if the elem is successfully added, else returns false
	 */
	public boolean add(int index, E element) {
		if(index == 0) {
			this.add(element);
			return true;
		}else if(index == size) {
			this.append(element);
			return true;
		}else if(index > 0 && index < size - 1) {
			Node<E> first = indices.get(index);
			Node<E> last = first.prev;
			Node<E> newNode = new Node<E>(element,last,first);
			last.next = newNode;
			first.prev = newNode;
			indices.add(index,newNode);
			size++;
			return true;
		}else {
			return false;
		}
	}
	/**
	 * adds elem at the head of the list
	 * @param elem data stored in the new node
	 * @return true when the node is successfully added to the front of the list
	 */
	public boolean add(E elem) {
		Node<E> node = new Node<E>(elem, null, head);
		if(head == null) {
			head = tail = node;
			size++;
			indices.add(node);
			return true;
		}else {
			head.prev = node;
			head = node;
			size++;
			indices.add(0,node);
			return true;
		}
	}
	/**
	 * adds elem as the new last element of the list
	 * @param elem data stored in the new node
	 * @return true when the node is successfully added to the end of the list
	 */
	public boolean append(E elem) {
		if(size >= 1) {
			Node<E> node = new Node<E>(elem, tail, null);
			tail.next = node;
			tail = node;
			size++;
			indices.add(node);
			return true;
		}else {
			Node<E> node = new Node<E>(elem, null, null);
			head = node;
			tail = node;
			size++;
			indices.add(node);
			return true;
		}
	}
	/**
	 * returns the object at position index from the head
	 * @param index position of the node to be received
	 * @return data of the node at the given index
	 */
	public E get(int index) {
		if(index >= size) {
			System.out.println("There is no node at index " + Integer.toString(index));
			return null;
		}else {
			return indices.get(index).data;
		}
	}
	
	/**
	 * gets the data in the node at the head
	 * @return object at the head
	 */
	public E getHead() {
		if (head == null) {
			System.out.println("List is empty");
			throw new NoSuchElementException();
		} else {
			return head.data;
		}
	}
	
	/**
	 * gets the data in the node at the tail
	 * @return object at the tail
	 */
	public E getLast() {
		if (tail == null) {
			System.out.println("List is empty");
			throw new NoSuchElementException();
		}else {
			return tail.data;
		}
	}
	
	/**
	 * gets the list size
	 * @return the list size
	 */
	public int size() {
		return indices.size();
	}
	
	/**
	 * removes and return element at the head
	 * @return element in the removed node
	 */
	public E remove() {
		if (size == 0) {
			throw new NoSuchElementException();
		}
		if (size == 1) {
			Node<E> node = head;
			head = null;
			tail = null;
			size--;
			indices.remove(0);
			return node.data;
		} else {
			Node<E> node = head;
			head = head.next;
			head.prev = null;
			size--;
			indices.remove(0);
			return node.data;
		}
	}
	/**
	 * removes and return element at the tail
	 * @return element in the removed node
	 */
	public E removeLast() {
		if (size == 0) {
			throw new NoSuchElementException();
		} else {
			E node = tail.data;
			tail = tail.prev;
			tail.next = null;
			size--;
			indices.remove(size - 1);
			return node;
		}
	}
	/**
	 * removes and returns the element at the index
	 * @param index position of the node that needed to be removed
	 * @return element at the index
	 */
	public E removeAt(int index) {
		if (index == 0) {
			Node<E> node = indices.get(index);
			this.remove();
			return node.data;
		}else if((0< index) && (index < (size - 1))) {
			Node<E> node = indices.get(index);
			node.prev.next = node.next;
			node.next.prev = node.prev;
			indices.remove(index);
			size--;
			return node.data;
		}else if(index == (this.size() - 1)) {
			Node<E> node = indices.get(index);
			this.removeLast();
			return node.data;
		}
		else {
			return null;
		}
	}
	/**
	 * removes the first occurrence of elem in the list and returns true
	 * returns false if elem was not in the list
	 * @param elem data inside the node that needs to be removed
	 * @return true if elem is removed from the list, else returns false
	 */
	public boolean remove(E elem) {
		for(int i = 0; i <= size - 1; i++) {
			if (elem == indices.get(i).data) {
				removeAt(i);
				i--;
				return true;
			}
		}
		return false;
	}
	/**
	 * presents a string representation of the list
	 */
	public String toString() {
		ArrayList<String> str = new ArrayList<String>();
		for(Node<E> x = head; x != null; x = x.next) {
			str.add(x.data.toString());
		}
		return str.toString();
	}
}
