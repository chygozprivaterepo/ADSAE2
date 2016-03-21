
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
		set1.addAll(set2);
		set1.print();
	}

}
