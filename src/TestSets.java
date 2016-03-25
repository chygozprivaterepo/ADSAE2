
public class TestSets {
	
	public static <E> void displaySet(Set<E> mySet){
		String st = "[";
		for(E elem:mySet) st += elem + ",";
		st+="]";
		System.out.println(st);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Set<String> set1 = new LinkedSet1<String>();
		set1.add("horse");
		
		set1.add("elephant");
		
		set1.add("yak");
		
		set1.add("hyena");
		
		set1.add("fox");
		System.out.print("Original set set1 is:\n");
		displaySet(set1);
		System.out.println("Removing element \"horse\" gives set:");
		set1.remove("horse");
		displaySet(set1);
		System.out.println("Removing element \"tiger\" gives set:");
		set1.remove("tiger");
		displaySet(set1);
		Set<String> set2 = new LinkedSet1<String>();
		set2.add("mary");
		set2.add("ann");
		set2.add("horse");
		set2.add("jane");
		set2.add("yak");
		set2.add("zebedee");
		set2.add("zowie");
		System.out.print("Original set set2 is:\n");
		displaySet(set2);
		System.out.println("The union of the sets is:");
		set1.addAll(set2);
		displaySet(set1);
		System.out.println("Removing elements of set2 gives set s1:");
		set1.removeAll(set2);
		displaySet(set1);
		Set<String> set3 = new LinkedSet1<String>();
		set3.add("elephant");
		set3.add("hyena");
		set3.add("yak");
		System.out.println("Set s3 is:");
		displaySet(set3);
		System.out.print("\nset1 does ");
		if(!set1.containsAll(set3)) System.out.print("NOT ");
		System.out.println("sumbsume set3");
		set3.add("antelope");
		System.out.println("Set s3 is:");
		displaySet(set3);
		System.out.print("\nset1 does ");
		if(!set1.containsAll(set3)) System.out.print("NOT ");
		System.out.println("sumbsume set3");
		Set<String> set4 = new LinkedSet1<String>();
		set4.add("elephant");
		set4.add("goat");
		set4.add("yak");
		System.out.println("Set s4 is:");
		displaySet(set4);
		System.out.println("The intersection of sets s3 and s4 is:");
		set3.retainAll(set4);
		displaySet(set3);
		
		
		

	}

}
