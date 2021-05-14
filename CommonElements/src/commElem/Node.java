package commElem;

public class Node{
	
	String bandName;
	Node nextBandNode;
	Node nextList;
	
	//constructor (only 1 constructor)
	public Node(String artist){
		this.bandName = "" + artist;
		this.nextBandNode = null;
		this.nextList = null;
	}
}