import java.util.*;

public class HashTable {

    private int capacity;                   // hash table capacity, should be prime
    private Object[] S;                     // bucket array
    private int scalingFactor;
    private int shift;
    private int size;
	
    public HashTable(int capacity,int scalingFactor,int shift){
	this.capacity = capacity;
	this.scalingFactor = scalingFactor;
	this.shift = shift;
	S = new Object[capacity];
	for (int i=0;i<capacity;i++) S[i] = new ArrayList<String>();
	size = 0;
    }

    public HashTable(){
	capacity = 1;
	scalingFactor = 1;
	shift = 0;
	S = new Object[capacity];
	for (int i=0;i<capacity;i++) S[i] = new ArrayList<String>();
	size = 0;
    }
	
    public int size(){return size;}
	 
    public boolean isEmpty(){return size > 0;}

    private long hashCode(String s){
	// return s.hashCode();
	/*long h = 7;
	int  a = 33;
	for (int i=0;i<s.length();i++) h = a*h + s.charAt(i);
	return h;*/
    	//return s.hashCode(); //java hashcode
    	//return new GeneralHashFunctionLibrary().RSHashCode(s); //RSHashCode
    	//return new GeneralHashFunctionLibrary().JSHashCode(s); //JSHashCode
    	//return new GeneralHashFunctionLibrary().PJWHashCode(s); //PJWHashCode
    	//return new GeneralHashFunctionLibrary().ELFHashCode(s); //ELFHashCode
    	//return new GeneralHashFunctionLibrary().BKDRHashCode(s); //BKDRHashCode
    	//return new GeneralHashFunctionLibrary().SDBMHashCode(s); //SDBMHashCode
    	//return new GeneralHashFunctionLibrary().DJBHashCode(s); //DJBHashCode
    	//return new GeneralHashFunctionLibrary().DEKHashCode(s); //DEKHashCode
    	return new GeneralHashFunctionLibrary().BPHashCode(s); //BPHashCode
    	//return new GeneralHashFunctionLibrary().FNVHashCode(s); //FNVHashCode
    	//return new GeneralHashFunctionLibrary().APHashCode(s); //APHashCode
    }
    //
    // cut and paste replacement of the above from Arash Partow's 
    // "General Purpose Hash Function Algorithms Library"
    // Also try the java s.hashCode() 
    //

    private int hash(String s){
	return (int)(Math.abs(scalingFactor*hashCode(s) + shift) % capacity);
    }

    public void put(String s){
	int i = hash(s);
	ArrayList<String> bucket = (ArrayList<String>)S[i];
	if (!bucket.contains(s)){bucket.add(s); size++;}
    }

    public boolean get(String s){
	int i = hash(s);
	ArrayList<String> bucket = (ArrayList<String>)S[i];
	return bucket.contains(s);
    }

    public String stats(){
	String s = "capacity: " + capacity +" ";
	int count = size;
	s = s + "size: " + count +" ";
	// collisions = number of buckets containing at least 2 elements
	int collisions = this.getCollisions();
	s = s + "collisions: " + collisions +" ";
	int max = this.getMaxBucketSize();
	s = s + "maxBucketSize: " + max +" ";
	int tot = size;
	int n = this.getNoOfOccupiedBuckets();
	s = s + "averageOccupiedBucketSize: " + String.format("%.2f", ((double)tot)/n) +" ";
	return s;
    }
    
    private int getCollisions(){
    	int no = 0;
    	for(Object o: S){
    		ArrayList<String> bucket = (ArrayList<String>)o;
    		if (bucket.size() > 1)
    			no++;
    	}
    	return no;
    }
    
    private int getMaxBucketSize(){
    	int max = 0;
    	for(Object o: S){
    		ArrayList<String> bucket = (ArrayList<String>)o;
    		if(bucket.size() > max)
    			max = bucket.size();
    	}
    	return max;
    }
    
    private int getNoOfOccupiedBuckets(){
    	int no = 0;
    	for(Object o: S){
    		ArrayList<String> bucket = (ArrayList<String>)o;
    		if(bucket.size() > 0)
    			no++;
    	}
    	return no;
    }
    
    /*
    public String stats(){
	String s = "capacity: " + capacity +" ";
	int count = 0;
	s = s + "size: " + count +" ";
	// collisions = number of buckets containing at least 2 elements
	int collisions = 0;
	s = s + "collisions: " + collisions +" ";
	int max = 0;
	s = s + "maxBucketSize: " + max +" ";
	int tot = 0;
	int n = 0;
	s = s + "averageOccupiedBucketSize: " + tot/n +" ";
	return s;
    }*/

}
