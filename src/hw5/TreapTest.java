package hw5;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TreapTest {

	@Test
	public void add() {
		Treap<Integer> testTree = new Treap<Integer>();
		Treap<Integer> testTreeTwo = new Treap<Integer>();
		testTree.add(4,19); 
		testTree.add(2,31);
		testTree.add(6,70); 
		testTree.add(1,84);
		testTree.add(3,12); 
		testTree.add(5,83);
		testTree.add(7,26);
		testTreeTwo.add(4,19); 
		testTreeTwo.add(2,31);
		testTreeTwo.add(6,70); 
		testTreeTwo.add(1,84);
		testTreeTwo.add(3,12); 
		testTreeTwo.add(5,83);
		testTreeTwo.add(7,26);
		assertEquals(testTree.toString(), testTreeTwo.toString());
	}
	
	@Test

	public void delete(){
		Treap<Integer> testTree = new Treap<Integer>();
		Treap<Integer> testTreeTwo = new Treap<Integer>();
		testTree.add(4,19); 
		testTree.add(2,31);
		testTree.add(6,70); 
		testTree.add(1,84);
		testTree.add(3,12); 
		testTree.add(5,83);
		testTree.add(7,26);
		testTreeTwo.add(4,19); 
		testTreeTwo.add(2,31);
		testTreeTwo.add(6,70); 
		testTreeTwo.add(1,84);
		testTreeTwo.add(3,12); 
		testTreeTwo.add(5,83);
		testTreeTwo.add(7,26);
		testTree.delete(1);
		testTreeTwo.delete(1);
		assertEquals(testTree.toString(), testTreeTwo.toString());
	}

	@Test
	public void find(){
		Treap<Integer> testTree = new Treap<Integer>();
		testTree.add(4,19); 
		testTree.add(2,31);
		testTree.add(6,70); 
		testTree.add(1,84);
		testTree.add(3,12); 
		testTree.add(5,83);
		testTree.add(7,26);
		assertEquals(testTree.find(7), true);
	}


}
