import java.util.*;
import java.io.*;

public class ReportGenerator {
	public static void main (String [] args){
		String [] hashcodes = {"RSHashCode", "JSHashCode", "PJWHashCode",
		"ELFHashCode", "BKDRHashCode", "SDBMHashCode", "DJBHashCode",
		"DEKHashCode", "BPHashCode", "FNVHashCode", "APHashCode",
		"JavaHashCode"};

		Combinations combos = new Combinations();
		combos.add(new Combination(49891,51,91));
		combos.add(new Combination(49891,25,91));
		combos.add(new Combination(49891,51,50));
		combos.add(new Combination(49891,25,25));

		List<String> dictWords = new ArrayList<String>();
		try{
			Scanner scan = new Scanner(new FileReader("dictionary.txt"));
			while(scan.hasNext()){
				dictWords.add(scan.next());
			}
			scan.close();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}

		String output = "";
		int i = 1;
		for(Combination c: combos.combinations){
			output+=String.format("%d. N = %d\tscalingFactor = %d\tshift = %d%n%n", i,c.N,c.scalingFactor,c.shift);
			output+=String.format("%-15s%-15s%-15s%-15s%n","hashCode","collisions",
								"maxBuckSize","avgOccBuckSize");
			for(String s: hashcodes){
				HashTable ht = new HashTable(c.N,c.scalingFactor,c.shift);
				ht.hc = s;

				for (String w: dictWords){
					ht.put(w);
				}
				
				output+=String.format("%-15s%-15s%-15s%-15s%n",s,ht.getCollisions(),
								ht.getMaxBucketSize(),ht.getAverageOccupiedBucketSize());
			}
			i++;
			output+=String.format("%n%n%n");
		}

		try{
			FileWriter fw = new FileWriter("report.txt");
			fw.write(output);
			fw.close();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static class Combination{

		public int N, scalingFactor, shift;

		public Combination(int n, int sf, int s){
			this.N = n;
			this.scalingFactor = sf;
			this.shift = s;
		}
	}

	public static class Combinations{
		public List<Combination> combinations;

		public Combinations(){
			combinations = new ArrayList<Combination>();
		}

		public void add(Combination c){
			combinations.add(c);
		}
	}


}