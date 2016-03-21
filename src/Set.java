import java.util.Iterator;

public interface Set<E> extends Iterable<E> {
	// Each Set<E> object is a homogeneous set whose 	// members are of type E.
	//////////// Accessors ////////////
	public boolean isEmpty ();	// Return true if and only if this set is empty.
	
	public int size ();	// Return the size of this set.
	
	public boolean contains (E it);
	// Return true if and only if it is a member of this set.
	
	public boolean equals (Set<E> that);
	// Return true if and only if this set is equal to that.
	
	public boolean containsAll (Set<E> that);
	// Return true if and only if this set subsumes that.
	
    ////////////Transformers ////////////
	
    public void clear();	// Make this set empty.

    public void add (E it);	// Add it as a member of this set. 
    // Do nothing if it is already a member of this set.

    public void remove (E it);	// Remove it from this set. 
    // Do nothing if it is not a member of this set.

    public void addAll (Set<E> that);	// Make this set the union of itself and that.

     ////////////Transformers ////////////
    
    public void removeAll (Set<E> that);	// Make this set the difference of itself and that.
	
    public void retainAll (Set<E> that);    // Make this set the intersection of itself and that.
	
    //////////// Iterator ////////////
	
    public Iterator<E> iterator();	// Return an iterator that will visit all members of this 
    // set, in no particular order

}
