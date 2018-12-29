package hw3;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IDLListTest {

	@Test
	void test() {
		IDLList<String> test = new IDLList<String>(); 
		//Adding elements with add(index, elem), add(), and append()
		assertEquals(true, test.add(0, "Hello"));
		assertEquals(true, test.add(1, "I'm"));
		assertEquals(true, test.append("Christina"));
		assertEquals("[Hello, I'm, Christina]", test.toString());
		
		//Retrieving elements using get(), getHead(), and getLast()
		assertEquals("I'm", test.get(1));
		assertEquals("Hello", test.getHead());
		assertEquals("Christina", test.getLast());
		
		//Finding size using size()
		assertEquals(3, test.size());
		assertEquals(true, test.append("."));
		assertEquals(4,  test.size());
		assertEquals("[Hello, I'm, Christina, .]", test.toString());
		
		//Removing elements using remove(), removeLast(), remove(index), remove(elem)
		assertEquals("Hello", test.remove());
		assertEquals(".", test.removeLast());
		assertEquals("Hello", test.removeAt(0));
		assertEquals(false, test.remove("Jing"));
		assertEquals(true, test.remove("Christina"));
		assertEquals("[my]", test.toString());
		
		//Other cases
		IDLList<String> test2 = new IDLList<String>();
		assertEquals(false, test2.add(3, "Word"));
		assertEquals(false, test2.add(5, "AnotherWord"));
		assertEquals(false, test2.add(0,NoSuchElementException()));
		assertEquals(false, test2.add(NoSuchElementException()));
		assertEquals(false, test2.append(NoSuchElementException()));
		assertEquals(true, test2.append("some"));
		assertEquals("[some]", test2.toString());
		
		IDLList<String> test3 = new IDLList<String>();
		assertEquals(NoSuchElementException(), test3.getHead());
		assertEquals(NoSuchElementException(), test3.getLast());
		assertEquals(0, test3.size());
		assertEquals(true, test3.add("some"));
		assertEquals("some", test3.getHead());
		assertEquals("some", test3.getLast());
		assertEquals(1, test3.size());
		assertEquals("[some]", test3.toString());
		
		IDLList<String> test4 = new IDLList<String>();
		assertEquals(NoSuchElementException(), test4.remove());
		assertEquals(true, test4.add("funny"));
		assertEquals("funny", test4.remove());
		assertEquals(null, test4.getHead());
		assertEquals(null, test4.getLast());
		assertEquals("[]", test4.toString());
		
		IDLList<String> test5 = new IDLList<String>();
		assertEquals(NoSuchElementException(), test5.removeLast());
		assertEquals(true, test5.add("LMAO"));
		assertEquals("LMAO", test5.removeLast());
		assertEquals(null, test5.getHead());
		assertEquals(null, test5.getLast());
		assertEquals("[]", test5.toString());
		
		IDLList<String> test6 = new IDLList<String>();
		assertEquals(false, test6.remove("randomWord"));
		assertEquals(false, test6.remove(NoSuchElementException()));
		assertEquals("[]", test6.toString());
	}
	
	private String NoSuchElementException() {
		return null;
	}


}
