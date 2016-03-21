//generic SLL
public class SLL2<E extends Comparable<E>> {

	// Each SLL object is the header of a 	// singly-linked-list. 
			private SLL2.Node<E> first;
			public SLL2 () {
				// Construct an empty SLL.
				this.first = null;
				}
			
			
	////////// Inner class //////////
			private static class Node<E extends Comparable<E>> {
				// Each SLL.Node object is a node of a 
				// singly-linked-list. 
				protected E element;
				protected Node<E> succ;
				public Node(E elem, Node<E> succ) {
					this.element = elem;
					this.succ = succ;
				}
			}
			
			public void printFirstToLast () {
				// Print all elements in this SLL, in first-to-last order.
				SLL2.Node<E> curr = this.first;
				while (curr != null) {
					System.out.println(curr.element);
					curr = curr.succ;
					}
				}
			
			public void deleteFirst () {
				// Delete this SLL’s first node (assuming length > 0).
				this.first = this.first.succ;
				}
			
			public void insert (E elem,
					SLL2.Node<E> pred) {
				// Insert elem at a given point in this SLL, either after the 
				// node pred, or before the first node if pred is null.
				SLL2.Node<E> ins = new SLL2.Node<E>(elem, null);
				if (pred == null) {
					ins.succ = this.first;
					this.first = ins;
					} else {
						ins.succ = pred.succ;
						pred.succ = ins;
						}
				}
			
			public void insert(E elem){
				//Insert elem at head of list
				insert(elem,null);
			}
			
			public SLL2.Node<E> search (Object target) {
				// Find which (if any) node of this SLL contains an 
				// element equal to target. Return a link to the 
				// matching node (or null if there is none).
				SLL2.Node<E> curr = this.first;
				while (curr != null) {
					if (target.equals(curr.element))
						return curr;
					curr = curr.succ;
					}
				return null;
					
			}
	
	
}
