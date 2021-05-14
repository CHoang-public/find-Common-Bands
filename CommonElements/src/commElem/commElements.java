package commElem;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;


public class commElements{

	static int highestValue = 0;
	
	
	/*driver for findCommElements
	 Input is the first node in a series of lists (a 2d linked list)
	 It iterates through each list (a row in the 2D linked list) and calls findCommonElements on it
	 With each call, it adds to the HashMap resultSet
	 After iterating through the list, it constructs an ArrayList "resultList" of 5 band names
	 
	 returns resultList
	 */
	public static ArrayList<String> findCommElementsDriver(Node firstUserList){
		
		HashMap<String, Integer> resultSet = null;
		
		//build resultSet (find common bands and assign values)
		Node listCursor = firstUserList;
		while(listCursor != null){
			
			resultSet = findCommonElements(listCursor, resultSet);
			
			listCursor = listCursor.nextList;
		}
		

		//extracting 5 of the top bands that are common to every list
		ArrayList<String> resultList = new ArrayList<String>();
		int counter = highestValue;
		while (counter >=  1 && resultList.size() < 5){
			
			for( Map.Entry<String, Integer> entry : resultSet.entrySet()){
				
				if(entry.getValue() == counter && resultList.size() < 5){
					
					resultList.add(entry.getKey() );
					//
					
				}else if(resultList.size() == 5){
					break;
				}
				
				//resultSet.remove(entry.getKey() );
			}
			
			counter--;
		}
		
		
		return resultList;
	}
	
	
	/*findCommonElements
	 * given a list and the resultSet, it iterates through each node in the list
	 * if the band in the node is already in the resultSet, increase the value of the corresponding entry in
	 * the resultSet by 1. If not, add a new entry with the band name as the key and assign a value of 1.
	 * returns resultSet 
	 */
	public static HashMap<String, Integer> findCommonElements(Node list, HashMap<String, Integer> resultSet){
		
		if(resultSet == null){
			resultSet = new HashMap<String, Integer>();
		}
		
		Node bandCursor = list;
		
		while(bandCursor != null){
			
			if(resultSet.containsKey(bandCursor.bandName)){
				resultSet.put( bandCursor.bandName, resultSet.get(bandCursor.bandName) + 1 );
				
				if(resultSet.get(bandCursor.bandName) > highestValue){
					highestValue = resultSet.get(bandCursor.bandName);
				}
				//update value for that band, increase it by 1
			}else{
				resultSet.put( bandCursor.bandName, 1); 
			}
			
				
			bandCursor = bandCursor.nextBandNode;
		}
		
		return resultSet;
	}

}

 