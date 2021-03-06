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
		if(that.size() != this.size()) //if the two sets have different sizes, then they are not equal. return false
			return false;
		Node<E> curr = first;
		Iterator<E> it = that.iterator(); //get iterator for second set
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

		while(curr != null && it.hasNext()){ // while both this set and that set have elements
			E thatCurr = it.next(); //get the next element in that set

			while (curr != null && thatCurr.compareTo(curr.elem) > 0){ //while thatCurr comes after curr. Keep checking subsequent 
																			//elements in this set for a match
				curr = curr.next;
			}
			if (thatCurr.equals(curr.elem)){ // if a match is found, get the node in this. the loop to check if both sets still have elements will be repeated
				curr = curr.next;
			}
			else { // if a match was not found. Then this set does not contain all of that. Terminate
				return false;
			}
		}
		if(it.hasNext() && curr == null){ // this set ran out of elements while that set still has elements to check. 
			return false; //return false since not all elements in that were found in this
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
		Node<E> curr = first, pred = first;
		while(curr != null){
			if(curr.elem.equals(it)){ //if element is found remove it
				if(curr.equals(pred)){ //element found at first position
					first = curr.next;
				}else{
					pred.next = curr.next;
				}
				size--;
				return;
			}
			pred = curr;
			curr = curr.next;
		}
		//if not found. Do nothing
	}

	@Override
	// Make this set the union of itself and that.
	// SLL merge
	// should be O(n+n')
	// where n' is size of merged SLL
	public void addAll(Set<E> that) {
		// TODO Auto-generated method stub

		Iterator<E> it = that.iterator(); //get iterator on second set
		Node<E> curr = first, prev = first;
		
		while (it.hasNext()){ //while second set has elements
			E thatCurr = it.next(); //get next element of second set
			if(size == 0){ //first set is empty. Add element of second set in first position of first set
				Node<E> ins = new Node<E>(thatCurr, null);
				first = ins;
				prev = curr = first;
				size++;
			}else{
				while(curr != null && thatCurr.compareTo(curr.elem) > 0){ //if current element of second set is greater than current element of first set,
																		//get next element of first set
					prev = curr;
					curr = curr.next;
				}
				if(curr != null && thatCurr.compareTo(curr.elem) < 0){
					Node<E> ins = new Node<E>(thatCurr, curr);
					if(prev.equals(curr)){ //if first element of first set is smaller than the current element of second set, insert at first position in first set
						first = ins;
						prev = first;
					}else{ // else insert at correct position
						prev.next = ins;
						prev = ins;
					}
					size++;
				}else if (curr != null && thatCurr.equals(curr.elem)){ //current element of both sets are equal. Skip both of them
					prev = curr;
					curr = curr.next;
				}
				else if (curr == null){ //end of first set reached while second set still has elements to add.
					Node<E> ins = new Node<E>(thatCurr, null);
					prev.next = ins;
					prev = ins;
					size++;
				}
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
		if(first == null) //if this is empty, don't bother checking. return
			return;
		Node<E> curr = first, prev = first;
		Iterator<E> it = that.iterator();
		while(it.hasNext() && curr != null){ //while both sets have elements
			E thatCurr = it.next();
			while(curr != null && thatCurr.compareTo(curr.elem) > 0){ // if it comes after, get next element in this and check again
				prev = curr;
				curr = curr.next;
			}
			if(thatCurr.equals(curr.elem)){ // if that's element is found in this set, 
				if(prev.equals(curr)){ //if it was found at the first position, remove the first element
					first = curr.next;
					curr = first;
					prev = curr;
				}else{ //it is not the first element to be removed. Remove from its position
					prev.next = curr.next;
					curr = curr.next;
				}
				size--;
			}
		}
	}

	@Override
	// Make this set the intersection of itself and that.
	// variant of SLL merge
	// should be O(n+n')
	// where n' is size of merged SLL
	public void retainAll(Set<E> that) {
		// TODO Auto-generated method stub
		
		if(first == null){ //if this is empty, don't bother checking. return
			return;
		}
		int tempSize = 0;
		Node<E> curr = first, prev = first;
		Iterator<E> it = that.iterator(); //iterator of second set
		if(!it.hasNext()){ //if second set has no elements, clear first set and return
			clear();
			return;
		}
		while(it.hasNext() && curr != null){ //while both sets have elements
			E thatCurr = it.next(); //get next element in second set
			while(curr != null && thatCurr.compareTo(curr.elem) > 0){ // current element of second set not found yet while first set still has elements
				//remove element from first set that is not in second set
				if(prev.equals(curr)){ //if the first element of first set is not in the second set, remove it from the set
					first = curr.next;
					prev = curr = first;
				}else{ //else if any other element of the first set is not in the second set, remove it from the set
					prev.next = curr.next;
					curr = curr.next;
				}
			}
			if(thatCurr.equals(curr.elem)){ // if second set's element is found in first set, keep it 
				prev = curr;
				curr = curr.next;
				tempSize++;
			}
		}
		if(curr != null){ //if second set runs out of elements while first set still has elements, remove the remaining elements of first set
			prev.next = null;
		}
		size = tempSize;
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

	
	//tostring method. to be removed
	public String toString(){
		Node<E> curr = first;
		String s = "{";
		while(curr != null){
			s+=(curr.elem + ", ");
			curr = curr.next;
		}
		s+="}";
		return s;
	}
}
