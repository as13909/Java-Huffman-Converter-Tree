package Aaron_Stein_Homework_Four;
import java.util.ArrayList;

public class HuffmanTree {

	HuffmanNode root;
	
	public HuffmanTree(HuffmanNode huff)
	{
		this.root = huff; 
	}
	public void printLegend()
	{
		printLegend(root, "");
	}
	private void printLegend(HuffmanNode t, String s)
	{
		if(t.letter.length() > 1)
		{
			printLegend(t.left, s + "0");
			printLegend(t.right, s + "1");
		}
		else
		{
			System.out.println(t.letter+"="+s);
		}
	}
	public static BinaryHeap legendToHeap(String legend)
	{
		char temp; //allows us to iterate through string and pull out one element at a time
		String number = ""; //makes sure numbers stay together 
		ArrayList<String> ar = new ArrayList<String>(); //arraylist to hold items
		HuffmanNode[] node_ar; //array of HuffmanNodes
		
		for(int x = 0; x < legend.length(); x++)
		{
			
			temp = legend.charAt(x); //gets first element
			if(Character.isLetter(temp)) 
			{
				ar.add(Character.toString(temp)); //if its a letter it adds it to the array
				
			}
			else if(Character.isDigit(temp)) //if its a number it creates a new string to make sure the number isn't messed up 
			{
				number = number + Character.toString(temp); 
			}
			else if(temp == ' ') //ends the number string and adds all the contents to the arrya before resetting the variable 
			{
				if(number != "")
				{
					ar.add(number);
					number = "";
				}
			}
		
		}
		if(number != "") //makes sure the last number string is added into the array 
		{
			ar.add(number);
		}
		
		HuffmanNode node;
		BinaryHeap<HuffmanNode> heap = new BinaryHeap<HuffmanNode>();
		node_ar = new HuffmanNode[(ar.size()/2)+1];
		for(int i = 0; i < ar.size()-1; i += 2) //iterates through the arraylist
		{
			
			node = new HuffmanNode(ar.get(i), Double.parseDouble(ar.get(i+1))); //creates a new huffman node for each element in arraylist
			node.letter = ar.get(i);
			node.frequency = Double.parseDouble(ar.get(i+1));
		
			heap.insert(node); //inserts elements into binaryheap 
		}
		return heap; 
		
	}
	
	public static HuffmanTree createFromHeap(BinaryHeap b)
	{
		HuffmanNode node1, node2, new_node, fin;
		while(b.getSize() > 1)
		{
			node1 = (HuffmanNode) b.deleteMin(); //takes out two elements from binaryheap
			node2 = (HuffmanNode) b.deleteMin();
			
			new_node = new HuffmanNode(node1, node2); //adds those nodes as children of other node
			new_node.left = node1;
			new_node.right = node2;
			
			b.insert(new_node); //inserts node back into the array;
		}
		fin = (HuffmanNode) b.deleteMin(); //takes out and returns root node; 
		
		return new HuffmanTree(fin);
		
	}
	
}
