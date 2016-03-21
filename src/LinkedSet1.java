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
		return false;
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
		//element not found. Add it to the set
		return;
	}

	@Override
	// Make this set the union of itself and that.
	// SLL merge
	// should be O(n+n')
	// where n' is size of merged SLL
	public void addAll(Set<E> that) {
		// TODO Auto-generated method stub
		Iterator<E> it = that.iterator();
		if(first == null){ //if this set was empty, just add the elements from that set
			Node<E> curr = null;
			while(it.hasNext()){
				E thatCurr = it.next();
				if(curr == null){
					first = new Node<E>(thatCurr, null);
					curr = first;
				}
				else{
					curr.next = new Node<E>(thatCurr, null);
					curr = curr.next;
				}
				size++;
			}
		}
		else{ //this set already has some elements. We have to look for where to insert each element from that set
			Node<E> curr = first, pred = null;
			while(it.hasNext()){
				
				E thatCurr = it.next();
				System.err.println("Adding "+thatCurr);
				if(curr != null){ //this still has elements
					int comp = thatCurr.compareTo(curr.elem);
					if(comp < 0){//thatCurr is smaller than element in curr. Put its node before curr
						if(pred == null){
							first = new Node<E>(thatCurr, first);
							pred = first;
						}
						else{
							System.err.println("that is "+thatCurr+", curr is "+curr.elem);
							Node<E> ins = new Node<E>(thatCurr, curr);
							System.err.println(thatCurr+ "added");
							pred.next = ins;
							pred = ins;
						}
						//curr = curr.next;
					}else if(comp > 0){
						while(comp >= 0){ //thatCurr is bigger than element in curr. Get next node in this
							System.err.println("that is "+thatCurr+", curr is "+curr.elem);
							pred = curr;
							curr = curr.next;
							comp = thatCurr.compareTo(curr.elem);
						}
						System.err.println("that is "+thatCurr+", curr is "+curr.elem);
						Node<E> ins = new Node<E>(thatCurr, curr);
						System.err.println(thatCurr+ "added");
						pred.next = ins;
					}
					curr = curr.next;
				}
				else{ //this has no more elements. Add all of that to this
					Node<E> ins = new Node<E>(thatCurr, null);
					pred.next = ins;
					pred = ins;
				}
				size++;
			}
		}
			
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
