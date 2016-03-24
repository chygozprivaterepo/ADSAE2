import java.util.Iterator;
import java.util.TreeSet;

public class Testing {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		//set1.addAll(set2);
		//set1.print();
		
		//TreeSet<Integer> seta = new TreeSet<Integer>();
		//TreeSet<Integer> setb = new TreeSet<Integer>();
		LinkedSet1<Integer> seta = new LinkedSet1<Integer>();
		LinkedSet1<Integer> setb = new LinkedSet1<Integer>();
		seta.add(1);seta.add(2);seta.add(3);seta.add(5);seta.add(6);seta.add(9);seta.add(11);
		setb.add(1); setb.add(5);setb.add(2);setb.add(7);setb.add(9);setb.add(11);
		
		System.out.println(seta.containsAll(setb));
		//seta.removeAll(setb);
		//print(seta);
		seta.print();
		System.out.println("size of a is "+seta.size());
		setb.print();
		seta.removeAll(setb);
		seta.print();
		System.out.println("size of a is "+seta.size());
	}
	
	public static <E> void print(TreeSet<E> s){
		System.out.print("S contains {");
		for (E e: s){
			System.out.print(e + ", ");
		}
		System.out.print("}");
		System.out.println();
	}

}
