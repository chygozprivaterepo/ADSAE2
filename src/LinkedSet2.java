import java.util.Iterator;
import java.util.NoSuchElementException;

//note that this implementation assumes that any set "that"
//has an iterator that visits the elements in ascending order
//wouldn't work if, say, "that" was implemented by a BST
//whose iterator output elements in pre-order, or post-order 

//use a sorted SLL

public class LinkedSet2<E extends Comparable<E>> implements Set<E> {

	private int size;
	private LinkedSet2.Node<E> first;
	
	public LinkedSet2(){
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
	//The time complexity is O(nn') where n and n' are the sizes of the first and second sets respectively.
	//This is because for each element of the second set, we have to linearly traverse the entire first set to check if it is contained in it. 
	// Traversing the first set will give a complexity of O(n) and we traverse it n' times, resulting in an overall complexity of O(nn')
	public boolean equals(Set<E> that) {
		// TODO Auto-generated method stub
		if(that.size() != this.size()) //if the sizes of the two sets differ, then return false
			return false;
		Iterator<E> it = that.iterator(); //get the iterator for the second set
		while(it.hasNext()){ //while second set has elements
			E thatCurr = it.next(); //get the next element
			if(!this.contains(thatCurr)) //search the first set for that element. Return false if not found
				return false;
		}
		return true;
	}

	@Override
	// Return true if and only if this set subsumes that.
	// The time complexity is O(nn') where n and n' are the sizes of the first and second sets respectively.
	// This is because for each element of the second set, we have to linearly traverse the entire first set to check if it is contained in it. 
	// Traversing the first set will give a complexity of O(n) and we traverse it n' times, resulting in an overall complexity of O(nn')
	public boolean containsAll(Set<E> that) {
		// TODO Auto-generated method stub
		Iterator<E> it = that.iterator(); //get the iterator for the second set
		while(it.hasNext()){ // while second set has elements
			E thatCurr = it.next(); //get the next element in that set
			if(!this.contains(thatCurr)){ //search the first set for the element. return false if not found
				return false;
			}
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
	// The time complexity is O(nn') where n and n' are the sizes of the first and second sets respectively.
	// This is because for each element of the second set, we have to linearly traverse the entire first set to add it to the correct position.
	// Traversing the first set will give a complexity of O(n) and we traverse it n' times, resulting in an overall complexity of O(nn')
	public void addAll(Set<E> that) {
		// TODO Auto-generated method stub

		Iterator<E> it = that.iterator(); //get iterator on second set
		
		while (it.hasNext()){ //while second set has elements
			E thatCurr = it.next(); //get next element of second set
			this.add(thatCurr); //add it to this set
		}	
	}

	@Override
	// Make this set the difference of itself and that.
	// variant of SLL merge
	// The time complexity is O(nn') where n and n' are the sizes of the first and second sets respectively.
	// This is because for each element of the second set, we have to linearly traverse the entire first set to remove it if it is found.
	// Traversing the first set will give a complexity of O(n) and we traverse it n' times, resulting in an overall complexity of O(nn')
	public void removeAll(Set<E> that) {
		// TODO Auto-generated method stub
		if(first == null) //if this is empty, don't bother checking. return
			return;
		Iterator<E> it = that.iterator();
		while(it.hasNext()){ //while second set has elements
			E thatCurr = it.next();
			this.remove(thatCurr);
		}
	}

	@Override
	// Make this set the intersection of itself and that.
	// variant of SLL merge
	// The time complexity is O(nn') where n and n' are the sizes of the first and second sets respectively.
	// This is because for each element of the first set, we have to linearly traverse the entire second set to check if it is found.
	// If found, then it is retained, else it is removed from the set.
	// Traversing the second set will give a complexity of O(n') and we traverse it n times, resulting in an overall complexity of O(nn')
	public void retainAll(Set<E> that) {
		// TODO Auto-generated method stub
		
		if(first == null){ //if this is empty, don't bother checking. return
			return;
		}
		Node<E> curr = first, prev = first;
		int tempSize = 0;
		while(curr != null){ //while the first set still has elements
			Iterator<E> it = that.iterator(); //get iterator of second set
			boolean found = false; //variable to keep record of whether element of first set is found in second set
			while(it.hasNext()){ //while the second set still has elements, traverse it
				if(curr.elem.equals(it.next())){ 
					found = true; //if element was found in second set, set variable to true
					break;
				}
			}
			if(!found){ //if element was not found,
				if(prev.equals(curr)){ //first element was checked. remove the first element
					first = curr.next;
					prev = curr = first;
				}else{ //element at another position was found, remove it
					prev.next = curr.next;
					curr = curr.next;
				}
			}else{ //element was found. Get the next element of first set
				prev = curr;
				curr = curr.next;
				tempSize++;
			}
		}
		size = tempSize++;
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
