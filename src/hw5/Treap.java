package hw5;

import java.util.Random;
import java.util.Stack;
//I pledge my honor that I have abided by the Stevens Honor System.
//cli50
public class Treap<E extends Comparable<E>> {
	private static class Node<E>{
		//data fields
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;
		//constructor
		public Node(E data, int priority) {
			if (data == null) {
				throw new IllegalArgumentException("Data cannot be null.");
			}else {
				this.data = data;
				this.priority = priority;
				this.left = null;
				this.right = null;
			}
		}
		//methods
		/**
		 * right rotation 
		 * @return reference to the root of the result
		 */
		public Node<E> rotateRight(){
			if (this.left == null) {
				return this;
			}else {
				Node<E> update = this.left;
				this.left = this.left.right;
				update.right = this;
				return update;
			}
		}
		/**
		 * left rotation
		 * @return reference to the root of the resut
		 */
		public Node<E> rotateLeft(){
			if(this.right == null) {
				return this;
			}else {
				Node<E> update = this.right;
				this.right = this.right.left;
				update.left = this;
				return update;
			}
		}
		
		public String toString() {
			return "(key=" + data.toString() + ", priority=" + String.valueOf(priority) + ")";
		}
	}
	//data fields
	private Random priorityGenerator;
	private Node<E> root;
	
	//constructors
	public Treap() {
		priorityGenerator = new Random();
		this.root = null;
	}
	public Treap(long seed) {
		priorityGenerator = new Random(seed);
		this.root = null;
	}
	//methods
	/**
	 * generation a priority and calling the add(key, priority) function
	 * @param key data being inserted into the Treap
	 * @return true if the insertion was successful, otherwise false
	 */
	public boolean add(E key) {
		int priority = priorityGenerator.nextInt();
		return add(key, priority);
	}
	/**
	 * inserting the node and a priority into the Treap
	 * @param key data being inserted into the Treap
	 * @param priority random number given to the node
	 * @return true if the insertion was successful, otherwise false
	 */
	public boolean add(E key , int priority) {
		Node<E> temp = new Node<E>(key, priority);
		if(this.root == null){
			this.root = temp;
			return true;
		}
		Stack<Node<E>> stack = new Stack<Node<E>>();
		Node<E> current = this.root;
		int compare = temp.data.compareTo(current.data);
		stack.push(current);
		while(current != null){
			if(compare > 0){
				if(current.right == null){
					current.right = temp;
					reheap(stack, temp);
					return true;
				}
				current = current.right;
				stack.push(current);
			}
			if(compare < 0){
				if(current.left == null){
					current.left = temp;
					reheap(stack, temp);
					return true;
				}
				current = current.left;
				stack.push(current);
			}
		}
		return false;
	}
	/**
	 * balances the Treap
	 * @param stack stack containing all the stacks 
	 * @param temp node being inserted
	 */
	private void reheap(Stack<Node<E>> stack, Node<E> temp){
		while(!stack.isEmpty()){
			Node<E> move = stack.pop();
			int compare = temp.data.compareTo(move.data);
			if(move.priority > temp.priority){
				break;
			} else {
				if(compare > 0){
					temp = move.rotateLeft();
				} else {
					temp = move.rotateRight();
				}
				if(!stack.isEmpty()){
					Node<E> comp = stack.peek();
					if(comp.left == move){
						comp.left = temp;
					} else {
						comp.right = temp;
						}
				} else {
					this.root = temp;
				}
			}
		}
	}
	/**
	 * deletion of the node given key from the Treap and calling delete(temp,key)
	 * @param key data being deleted from the Treap
	 * @return true if the deletion was successful, otherwise false
	 */
	public boolean delete(E key){
		if(this.root == null || this.find(root, key) == false || key == null){
			return false;
		} else {
			this.root = delete(this.root, key);
			return true;
		}
	}
	/**
	 * deletion of the node given the key and rot;
	 * @param root root of the Treap
	 * @param key data being deleted from the Treap
	 * @return
	 */
	public Node<E> delete(Node<E> root, E key){
		int compare = key.compareTo(root.data);
		if(compare > 0){
			root.right = delete(root.right, key);
		}
		else if(compare < 0){
			root.left = delete(root.left, key);
		}
		else if(root.right == null){
			Node<E> change = root.left;
			root = change;
		}
		else if(root.left == null){
			Node<E> change = root.right;
			root = change;
		}
		else if(root.right.priority > root.left.priority){
			root = root.rotateLeft();
			root.left = delete(root.left, key);
		}
		else if(root.right.priority < root.left.priority){
			root = root.rotateRight();
			root.right = delete(root.right, key);
		}else {
			return null;
		}
		return root;
	}
	/**
	 * finding the node given the key and root
	 * @param root root of the Treap
	 * @param key key wanting to be found
	 * @return true if the key is found, otherwise false
	 */
	private boolean find(Node<E> root, E key) {
		int compare = key.compareTo(root.data);
		if (root == null || key == null) {
			return false;
		}else if (compare == 0) {
			return true;
		}else if (compare > 0) {
			return find(root.right, key);
		}else {
			return find(root.left, key);
		}
	}
	/**
	 * calling the find(root,key) function to find the node
	 * @param key key to be found
	 * @return true if the key is found, otherwise false
	 */
	public boolean find(E key) {
		return find(root, key);
	}
	
	/**
	 * method of a string representation of the Treap
	 * @return string representation of the Treap
	 */
	public String toString() {
        StringBuilder string = new StringBuilder();
        preOrderTraverse(root, 1, string);
        return string.toString();
    }

    /**
     * performs a preorder traversal
     * @param node local root
     * @param depth depth
     * @param string string buffer for the output
     */
    private void preOrderTraverse(Node<E> node, int depth, StringBuilder string) {
        for (int i = 1; i < depth; i++) {
            string.append("  ");
        }
        if (node == null) {
            string.append("null\n");
        } else {
            string.append(node.toString());
            string.append("\n");
            preOrderTraverse(node.left, depth + 1, string);
            preOrderTraverse(node.right, depth + 1, string);
        }
    }

}
