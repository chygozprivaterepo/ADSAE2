import java.util.*;
import java.io.* ;

public class Test {
         
 public static void main(String[] args) throws Exception, FileNotFoundException {

    String commands = "new, read, stats, get, quit (q)";
    String s = "";

    System.out.println(commands);
    Scanner sc = new Scanner(System.in);
    int N             = 49891;
    int scalingFactor = 51;
    int shift         = 91;
    HashTable HT = new HashTable(N,scalingFactor,shift);
    System.out.print("> ");
    String command = sc.next();

    while (!command.equals("quit") && !command.equals("q")){
	
	if (command.equals("help")) System.out.println(commands);

	if (command.equals("stats")) System.out.println(HT.stats());

	if (command.equals("read")){
	    System.out.print(">> ");
	    String fname = sc.next();
	    Scanner scf = new Scanner(new File(fname));
	    while (scf.hasNext()){
		s = scf.next();
		HT.put(s);
	    }
	}

	if (command.equals("new")){
	    System.out.print("N >> ");
	    N = sc.nextInt();
	    System.out.print("scaling factor >> ");
	    scalingFactor = sc.nextInt();
	    System.out.print("shift >> ");
	    shift = sc.nextInt();
	    HT = new HashTable(N,scalingFactor,shift);
	}

	if (command.equals("get")){
	    System.out.print(">> ");
	    s = sc.next();
	    System.out.println(HT.get(s));
	}

	System.out.print("> ");
	command = sc.next();
      }
    System.exit(0);
  }
}
