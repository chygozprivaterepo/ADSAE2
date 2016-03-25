import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class LinkedSet1Test {
	

	@Test
	public void testIsEmpty() {
		//fail("Not yet implemented");
		LinkedSet1<Integer> set = new LinkedSet1<Integer>();
		set.add(10);
		assertFalse(set.isEmpty());
	}
	
	@Test
	public void testSize() {
		//fail("Not yet implemented");
		LinkedSet1<Integer> set = new LinkedSet1<Integer>();
		set.add(20);set.add(10);set.add(20);
		assertEquals("Test size",2,set.size());
	}

	@Test
	public void testContains() {
		//fail("Not yet implemented");
		LinkedSet1<Integer> set = new LinkedSet1<Integer>();
		set.add(20);set.add(10);set.add(30);
		assertTrue("Test contains", set.contains(20));
		assertFalse("Test contains", set.contains(40));
	}

	@Test
	public void testEqualsSetOfE() {
		//fail("Not yet implemented");
		LinkedSet1<Integer> setz = new LinkedSet1<Integer>();
		LinkedSet1<Integer> that = new LinkedSet1<Integer>();
		setz.add(10); setz.add(12);setz.add(20);setz.add(30);
		that.add(10);
		that.add(20);
		assertFalse("Test if two sets are equal", setz.equals(that));
		setz.remove(12);setz.remove(30);
		//System.out.println(setz);
		assertTrue("Test if two sets are equal after", setz.equals(that));
	}

	@Test
	public void testContainsAll() {
		//fail("Not yet implemented");
		LinkedSet1<Integer> set11 = new LinkedSet1<Integer>();
		LinkedSet1<Integer> set21 = new LinkedSet1<Integer>();
		set11.add(2);
		set11.add(3); set11.add(4);
		set11.add(5); set11.add(6);
		
		set21.add(3); set21.add(5); set21.add(6); 
		
		assertTrue("Test if set1 contains set2", set11.containsAll(set21));
	}

	@Test
	public void testClear() {
		//fail("Not yet implemented");
		LinkedSet1<Integer> set = new LinkedSet1<Integer>();
		set.add(10); set.add(20);
		set.clear();
		assertEquals("Test size after clearing", 0, set.size());
		assertEquals("Content after clearing", "{}", set.toString());
	}

	@Test
	public void testAdd() {
		//fail("Not yet implemented");
		LinkedSet1<Integer> set = new LinkedSet1<Integer>();
		set.add(20);
		assertEquals("Test size after adding one element",1,set.size());
		assertEquals("Content after adding one element", "{20, }", set.toString());
		set.add(5);
		assertEquals("Content after adding two elements", "{5, 20, }", set.toString());
	}

	@Test
	public void testRemove() {
		//fail("Not yet implemented");
		LinkedSet1<Integer> set = new LinkedSet1<Integer>();
		set.add(10); set.add(20);
		assertEquals("Test size before removal of one element",2,set.size());
		set.remove(10); 
		//System.out.println(set);
		assertEquals("Test size after removal of one element",1,set.size());
		assertFalse("test if set still contains removed element", set.contains(10));
	}

	@Test
	public void testAddAll() {
		LinkedSet1<Integer> set1 = new LinkedSet1<Integer>();
		LinkedSet1<Integer> set2 = new LinkedSet1<Integer>();
		set1.add(3);
		set1.add(11);
		set1.add(17); set1.add(13);
		set2.add(10);
		set2.add(13);
		set2.add(14);
		set2.add(16);
		//fail("Not yet implemented");
		set1.addAll(set2);
		//set1.print();
		assertEquals("Test size of set 1 after union", 7, set1.size());
		assertEquals("Test contents of set 1 after union", "{3, 10, 11, 13, 14, 16, 17, }", set1.toString());
	}

	@Test
	public void testRemoveAll() {
		//fail("Not yet implemented");
		LinkedSet1<Integer> seta = new LinkedSet1<Integer>();
		LinkedSet1<Integer> setb = new LinkedSet1<Integer>();
		seta.add(1);seta.add(2);seta.add(3);seta.add(5);seta.add(6);seta.add(9);seta.add(11);
		setb.add(1); setb.add(5);setb.add(2);setb.add(7);setb.add(9);setb.add(11);
		seta.removeAll(setb);
		assertEquals("Testing remove all before", "{3, 6, }", seta.toString());
		setb.add(3); seta.add(13);
		seta.removeAll(setb);
		assertEquals("Testing remove all after", "{6, 13, }", seta.toString());
		assertEquals("Size of a ", 2, seta.size());
	}

	@Test
	public void testRetainAll() {
		//fail("Not yet implemented");
		LinkedSet1<Integer> seta = new LinkedSet1<Integer>();
		LinkedSet1<Integer> setb = new LinkedSet1<Integer>();
		seta.add(1);seta.add(2);seta.add(3);seta.add(5);seta.add(6);seta.add(9);seta.add(11);seta.add(12);seta.add(13);seta.add(14);
		setb.add(1); setb.add(5);setb.add(2);setb.add(7);setb.add(9);setb.add(11);
		seta.retainAll(setb);
		//System.out.println(seta);
		assertEquals("Testing retain all", "{1, 2, 5, 9, 11, }", seta.toString());
		assertEquals("Size of a ", 5, seta.size());
	}

}
