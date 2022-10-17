package Aaron_Stein_Homework_Four;

public class HuffmanNode implements Comparable{

	public String letter;
	public Double frequency;
	public HuffmanNode left, right;
	
	public HuffmanNode(String letter, Double frequency)
	{
		letter = this.letter;
		frequency = this.frequency;
		left = null;
		right = null;
		
	}
	public HuffmanNode(HuffmanNode left, HuffmanNode right)
	{
		letter = left.letter + right.letter;
		frequency = (left.frequency + right.frequency);
	}
	public String toString()
	{
		if(letter.equals("\n"))
		{
			return "<"+"\\n"+", "+frequency+">";
		}
		else
		{
			return "<"+letter+", "+frequency+">";
		}
	}
	
	
	@Override
	public int compareTo(Object o) 
	{
		HuffmanNode huff = (HuffmanNode) o;
		return this.frequency.compareTo(huff.frequency);
	}

}
