import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class LinkedSet1Test {
	
	private static LinkedSet1<Integer> set;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		set = new LinkedSet1<Integer>();
		set.add(10);
	}

	@Test
	public void testIsEmpty() {
		//fail("Not yet implemented");
		assertFalse(set.isEmpty());
	}
	
	@Test
	public void testSize() {
		//fail("Not yet implemented");
		assertEquals("Test size",2,set.size());
	}

	@Test
	public void testContains() {
		fail("Not yet implemented");
	}

	@Test
	public void testEqualsSetOfE() {
		//fail("Not yet implemented");
		LinkedSet1<Integer> that = new LinkedSet1<Integer>();
		that.add(10);
		that.add(20);
		assertTrue("Test if two sets are equal", set.equals(that));
	}

	@Test
	public void testContainsAll() {
		//fail("Not yet implemented");
		LinkedSet1<Integer> set11 = new LinkedSet1<Integer>();
		LinkedSet1<Integer> set21 = new LinkedSet1<Integer>();
		set11.add(2);
		set11.add(3); set11.add(4);
		set11.add(5); set11.add(6);
		
		set21.add(3); set21.add(5); set21.add(6); set21.add(1);
		
		assertTrue("Test if set1 contains set2", set11.containsAll(set21));
	}

	@Test
	public void testClear() {
		fail("Not yet implemented");
		//set.clear();
		//assertEquals("Test size after clearing", 0, set.size());
	}

	@Test
	public void testAdd() {
		//fail("Not yet implemented");
		set.add(20);
		assertEquals("Test size after adding one element",2,set.size());
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
		//set.remove(10);
		//assertEquals("Test size after removal of one element",1,set.size());
	}

	@Test
	public void testAddAll() {
		LinkedSet1<Integer> set1 = new LinkedSet1<Integer>();
		LinkedSet1<Integer> set2 = new LinkedSet1<Integer>();
		set1.add(3);
		set1.add(11);
		set1.add(17);
		set2.add(10);
		set2.add(13);
		set2.add(14);
		set2.add(16);
		//fail("Not yet implemented");
		set1.addAll(set2);
		//set1.print();
		assertEquals("Test size of set 1 after union", 7, set1.size());
	}

	@Test
	public void testRemoveAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testRetainAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

}
