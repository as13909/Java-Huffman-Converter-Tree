package Aaron_Stein_Homework_Four;
//Aaron Stein. Homework 5. 4/6/22
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class HuffmanConverter {
	//declaring variables
	public static final int NUMBER_OF_CHARACTERS = 256;
	private String contents;
	private HuffmanTree huffmanTree;
	private int count[];
	private String code[];
	private int uniqueChars = 0;
	
	public HuffmanConverter(String input)
	{
		this.contents = input; //constructor class
		this.count = new int[NUMBER_OF_CHARACTERS];
		this.code = new String[NUMBER_OF_CHARACTERS];
		
	}
	public void recordFrequencies() //records how often each letter appears in the poem
	{
		char item;
		for(int i = 0; i < contents.length(); i++)
		{
			item = contents.charAt(i);
			count[item]++;
		}
	}
	public void frequenciesToTree() //puts the frequencies in a binary Heap 
	{
		HuffmanNode temp; 
		String letter;
		BinaryHeap heap = new BinaryHeap();
		ArrayList<HuffmanNode> ar = new ArrayList<HuffmanNode>();
		HuffmanNode[] ar2;
	
		for(int x = 0; x < count.length; x++) //creates huffman Nodes and puts them in an ArrayList
		{
			
			if(count[x] != 0)
			{
				
				letter = Character.toString((char)x);
			
				temp = new HuffmanNode(letter, (double)count[x]);
				temp.frequency = (double) count[x];
				temp.letter = letter;
				
				
				ar.add(temp);

			}
		}
		ar2 = new HuffmanNode[ar.size()]; //transfers arrayList to array
		for(int i = 0; i < ar.size(); i++)
		{
			ar2[i] = ar.get(i);
		}
		heap = new BinaryHeap(ar2); //sends array to binaryHeap
		
		heap.printHeap(); //prints out heap 
		System.out.println();
		huffmanTree = HuffmanTree.createFromHeap(heap);	
		
	}
	public void treeToCode() //calls recursive treeToCode class after setting code[i] = "" so there is not an error
	{
		for(int i = 0; i < code.length; i++)
		{
			code[i] = "";
		} 
		treeToCode(huffmanTree.root, "");
	}
	private void treeToCode(HuffmanNode t, String s)
	{
		if(t.letter.length() > 1 && t.letter != "\n") //deals with the problem of \n char
		{
			treeToCode(t.right, s + "0");
			treeToCode(t.left, s + "1");
		}
		else
		{
				if(t.letter.equals("\n")) // \n must be printed out with double slashes so it doesn't auto add enter
				{
					System.out.println("'" + "\\n" + "'" + " = " + s);
					code[t.letter.charAt(0)] = s;
				}
				else
				{
					System.out.println("'" + t.letter.charAt(0) + "'" + " = " + s);
					code[t.letter.charAt(0)] = s; //adds the code for each letter to the code array
				}
			
		}
	}
	public String encodeMessage()
	{
		String encoded = "";
		for(int i = 0; i < contents.length(); i++)
		{
			encoded = encoded + code[contents.charAt(i)]; //creates the encoded version of the text
		}
		return encoded;
	}
	public static String readContents(String filename) //tries to open + read the file
	{
		File file; 
		BufferedReader read;
		String contents = ""; 
		try
		{
			
			file = new File(filename); 
			read = new BufferedReader(new FileReader(file));
			int i = 0;
			while((i = read.read())!=-1)
			{
				contents = contents + (char)i;
			}
		}
		catch(Exception e)
		{
			System.out.println("Invalid Filename"); //returns to the user that it is a bad filename 
		}
		return contents;
	}
	public String decodeMessage(String encodedStr) //decodes the message
	{
		String decoded = "";
		HuffmanNode walk = huffmanTree.root;
		
		for(int i = 0; i < encodedStr.length(); i++)
		{
			if(encodedStr.charAt(i) == '0')
			{
				walk = walk.right;
			}
			else
			{
				walk = walk.left;
			}
			if(walk.right == null && walk.left == null)
			{

				decoded += walk.letter;
				walk = huffmanTree.root;
			}
		}
		return decoded;
	}
	public static void main(String args[])
	{
		HuffmanConverter conv; //the convertor used to change the text to code and back
		String file_name = "/Users/aaronstein/Desktop/Aaron_Stein_Java_Workspace/DataStructures/src/Aaron_Stein_Homework_Four/love_poem_80.txt";
		//calculations
		conv = new HuffmanConverter(readContents(file_name));
		conv.recordFrequencies();
		conv.frequenciesToTree();
		conv.treeToCode();
		//output
		System.out.println("\n" + "Encoded Message:");
		System.out.println(conv.encodeMessage());
		System.out.println("\nMessage size in ASCII encoding: " + conv.readContents(file_name).length() * 8);
		System.out.println("Message size in Huffman encoding: " + conv.encodeMessage().length() + "\n");
		System.out.println(conv.decodeMessage(conv.encodeMessage()));
	}
	

	
	
	
}
