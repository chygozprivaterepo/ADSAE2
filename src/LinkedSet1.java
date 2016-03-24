import java.util.Iterator;
import java.util.NoSuchElementException;

//note that this implementation assumes that any set "that"
//has an iterator that visits the elements in ascending order
//wouldn't work if, say, "that" was implemented by a BST
//whose iterator output elements in pre-order, or post-order 

//use a sorted SLL

public class LinkedSet1<E extends Comparable<E>> implements Set<E> {

	private int size;
	private LinkedSet1.Node<E> first;
	
	public LinkedSet1(){
		size = 0;
		first = null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size == 0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	//SLL linear search
	//should be O(n)
	public boolean contains(E it) {
		// TODO Auto-generated method stub			
		Node<E> curr = first;
		while(curr != null){
			if(curr.elem.equals(it))
				return true;
			curr = curr.next;
		}
		return false;
	}

	// Return true if and only if this set is equal to that.
	//pairwise comparison
	//should be O(n)
	public boolean equals(Set<E> that) {
		// TODO Auto-generated method stub
		if(that.size() != this.size())
			return false;
		Node<E> curr = first;
		Iterator<E> it = that.iterator();
		while(it.hasNext() && curr != null){
			if(!curr.elem.equals(it.next()))
				return false;
			curr = curr.next;
		}
		return true;
	}

	@Override
	// Return true if and only if this set subsumes that.
	// variant of pairwise comparison
	//should be O(n)
	public boolean containsAll(Set<E> that) {
		// TODO Auto-generated method stub
		Iterator<E> it = that.iterator(); //get the iterator for that set
		Node<E> curr = first;
		E thatCurr = null;
		while(curr != null && it.hasNext()){ // while both this set and that set have elements
			thatCurr = it.next(); //get the next element in that set
			int comp = thatCurr.compareTo(curr.elem); //check if that's element comes before or after the element in curr
			System.out.printf("Checking %d in that and %d in this.%n", thatCurr, curr.elem);

			//check if thatCurr is found contained in this set
			while (comp > 0 && curr.next != null){ //while thatCurr comes after curr. Keep checking subsequent elements in this for a match
				curr = curr.next;
				comp = thatCurr.compareTo(curr.elem);
				System.out.printf("Checking %d in that and %d in this.%n", thatCurr, curr.elem);
			}
			if (comp == 0){ // if a match is found, get the node in this. the loop to check if both sets still have elements will be repeated
				System.out.printf("%d found. Checking next element in that.%n", thatCurr);
				curr = curr.next;
			}
			else { // if a match was not found. Then this set does not contain all of that. Terminate
				System.out.printf("%d not found. Returning false ", thatCurr);
				return false;
			}
		}
		if(!it.hasNext())
			System.out.println("Terminating because that does not have any more elements");
		else if (curr == null)
			System.out.println("Terminating because this does not have any more elements");
		if(it.hasNext() && curr == null){
			System.out.printf("This exhausted elements. %d of that not found. Returning false ", it.next());
			return false;
		}
		return true;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		size = 0;
		first = null;
	}

	@Override
	// Add it as a member of this set.
	// Use SLL linear search + insertion
	//should be O(n)
	public void add(E it) {
		// TODO Auto-generated method stub
		Node<E> prev = null, curr = first;
		
		while(curr != null){
			int comp = it.compareTo(curr.elem);
			if(comp == 0)
				return; //it is already in set. Return.
			else if (comp < 0)
				break; //it is smaller than curr. Stop going through rest of set
			prev = curr;
			curr = curr.next;
		}
		Node<E> ins = new Node<E>(it, curr); //create new node containing it and pointing to curr
		if(prev == null) //if set is empty, point first to the newly created node
			first = ins;
		else //else point curr's predecessor to the newly created node
			prev.next = ins;
		size++; //increment size of set
	}

	@Override
	// Remove it from this set. 
    // Do nothing if it is not a member of this set.
	// SLL search + deletion
	//should be O(n)
	public void remove(E it) {
		// TODO Auto-generated method stub
		//if the set is empty, nothing to remove. Return
		if (first == null){
			return;
		}
		//set is not empty, look for node containing it
		Node<E> curr = first, pred = null;
		while(curr != null){
			if(curr.elem.equals(it)){ //if element is found remove it
				pred = curr.next;
				size--;
				return;
			}
			pred = curr;
			curr = curr.next;
		}
		//it not found. Throw exception
		throw new NoSuchElementException();
	}

	@Override
	// Make this set the union of itself and that.
	// SLL merge
	// should be O(n+n')
	// where n' is size of merged SLL
	public void addAll(Set<E> that) {
		// TODO Auto-generated method stub
		Iterator<E> it = that.iterator();
		
			
	}

	@Override
	// Make this set the difference of itself and that.
	// variant of SLL merge
	// should be O(n+n')
	// where n' is size of merged SLL
	public void removeAll(Set<E> that) {
		// TODO Auto-generated method stub
		
	}

	@Override
	// Make this set the intersection of itself and that.
	// variant of SLL merge
	// should be O(n+n')
	// where n' is size of merged SLL
	public void retainAll(Set<E> that) {
		// TODO Auto-generated method stub
		
	}

	@Override
	// Return an iterator that will visit all members of this 
    // set, in no particular order
	// will be the same as that for LinkedList
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new LSIterator();
	}

	//inner Node class
	private static class Node<E extends Comparable<E>> {
		protected E elem;
		protected Node<E> next;
		
		private Node(E elem, Node<E> next){
			this.elem = elem;
			this.next = next;
		}
	}
	
	private class LSIterator implements Iterator<E>{

		private Node<E> position;
		
		private LSIterator(){
			this.position = first;
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return (position != null);
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			if (position == null)
				throw new NoSuchElementException();
			E nextElem = position.elem;
			position = position.next;
			return nextElem;
		}
	}
	
	//method to print out elements of a set. To be removed
	public void print(){
		Node<E> curr = first;
		while(curr != null){
			System.out.print(curr.elem + ", ");
			curr = curr.next;
		}
	}
}
