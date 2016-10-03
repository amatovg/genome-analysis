

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SequenceReader {

	
	private SequenceReader(){}
	
	
	public static String[] readSequence (String fileName){
			
		Scanner scan = null;
		try {
			scan = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.err.println(fileName+ " doest not exist.");
			System.exit(1);
		}
		ArrayList<String> list = new ArrayList<String>();
		while(scan.hasNext()){
			list.add(scan.next());
		}
		
		String[] returnArray = new String[list.size()];
		list.toArray(returnArray);
		return returnArray;
		
	}
	
}
