package commElem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class ceTester{
	
	//constructs 2d linked list from file input
	public static Node assembleUserBandLists(String filename) throws IOException{
		
		File bandListFile = new File(filename);
		
		if( !bandListFile.exists() ){
			
			System.out.println("File does not exist");
			return null;
		}
		
		Scanner sc = new Scanner(bandListFile);
		Node firstUserList = null;
		Node outerListCursor = null;
		Node innerCursor = null;
		
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			
			//System.out.println(line);
			
			//turn line to lowercase, get rid of all whitespace
			line = line.replaceAll(" ", "");
			line = line.toLowerCase();
			
			if( !line.equals("")  &&  firstUserList == null) { //if line not empty and series of lists is empty
				
				//System.out.println(line + "case 1");
				
				firstUserList = new Node(line);
				outerListCursor = firstUserList;
				outerListCursor.nextBandNode = innerCursor;
				
			}else if( !line.equals("") && outerListCursor.nextBandNode == null){
				
				//System.out.println(line + "case 2");
				
				Node newNode = new Node(line);
				outerListCursor.nextBandNode = newNode;
				innerCursor = newNode;
				
				
			}else if( !line.equals("") ){
				
				//System.out.println(line + "case 3");
				
				//create new node for list, connect to list, then move it over
				Node newNode = new Node(line);
				innerCursor.nextBandNode = newNode;
				innerCursor = innerCursor.nextBandNode;
				
			}else if( line.equals("") ){ //empty line, start next row/list
				
				//System.out.println(line + "case 4");
				
				line = sc.nextLine();
				line = line.replaceAll(" ", "");
				line = line.toLowerCase();
				
				Node newOuterNode = new Node(line);
				outerListCursor.nextList = newOuterNode;
				outerListCursor = outerListCursor.nextList;
				
				innerCursor = null;
			}
		}
		
		sc.close();
		
		return firstUserList;
	}
	
	//prints 2D linked list
	public static void printList(Node list){
		
		Node outerCursor = list;
		Node innerCursor = list;
		
		while(outerCursor != null){
			
			while(innerCursor != null){
				
				if(innerCursor == outerCursor){
					
					System.out.print(innerCursor.bandName);
					
				}else{
					
					System.out.print(", " + innerCursor.bandName);
					
				}
				
				innerCursor = innerCursor.nextBandNode;
				
			}
			
			System.out.println("");
			
			outerCursor = outerCursor.nextList;
			innerCursor = outerCursor;
		}
		
		
	}
	
	
	
	public static void main(String[] args) throws IOException{
		
		System.out.print("type the file name: ");
		Scanner getInput = new Scanner(System.in);
		
		String fileName = getInput.nextLine();
		
		System.out.println("");
		getInput.close();
		
		Node bandLists = assembleUserBandLists(fileName);
		
		ArrayList<String> results = commElements.findCommElementsDriver(bandLists);
		
		System.out.println("User band lists: ");
		System.out.println("");
		ceTester.printList(bandLists);
		System.out.println("");
		System.out.print("Results List: ");
		System.out.println(results);
		
		//System.out.println("We do this for 40 years, and then we die!");
		
	}
	
}