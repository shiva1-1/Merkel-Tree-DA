package merkletree;

import java.security.MessageDigest;
import java.util.List;


public class MerkleTree
{	
	private MerkleTree leftTree = null;
	private MerkleTree rightTree = null;
	
	private Leaf leftLeaf = null;
	private Leaf rightLeaf = null;
	private byte[] digest;
	
	
	private final MessageDigest md;
	
	private byte[] digest(Leaf leaf)
	{
		final List<byte[]> dataBlock = leaf.getDataBlock();
		
		final int numBlocks = dataBlock.size();
		for (int index=0; index<numBlocks-1; index++)
		{
			md.update(dataBlock.get(index));
		}
		digest = md.digest(dataBlock.get(numBlocks-1));
		
		return (digest);
	}
	
	public MerkleTree(MessageDigest md)
	{
		this.md = md;
	}
	
	public void add(final MerkleTree leftTree, final MerkleTree rightTree)
	{
		this.leftTree = leftTree;
		this.rightTree = rightTree;

		md.update(leftTree.digest());
		digest = md.digest(rightTree.digest());
	}

	public void add(final Leaf leftLeaf, final Leaf rightLeaf)
	{
		this.leftLeaf = leftLeaf;
		this.rightLeaf = rightLeaf;
		
		md.update(digest(leftLeaf));
		digest = md.digest(digest(rightLeaf));
	}

	public MerkleTree leftTree()
	{
		return (leftTree);
	}

	public MerkleTree rightTree()
	{
		return (rightTree);
	}

	public Leaf leftLeaf()
	{
		return (leftLeaf);
	}

	public Leaf rightLeaf()
	{
		return (rightLeaf);
	}

	public byte[] digest() 
	{
		return (digest);
	}

	private String toHexString(final byte[] array)
	{
		final StringBuilder str = new StringBuilder();
		
		str.append("[");
		
		boolean isFirst = true;
		for(int idx=0; idx<array.length; idx++)
		{
			final byte b = array[idx];
			
			if (isFirst)
			{			
				isFirst = false;
			}
			else
			{
				str.append(",");
			}
			
			final int hiVal = (b & 0xF0) >> 4;
	        final int loVal = b & 0x0F;
	        str.append((char) ('0' + (hiVal + (hiVal / 10 * 7))));
	        str.append((char) ('0' + (loVal + (loVal / 10 * 7))));
		}
		
		str.append("]");
		
		return(str.toString());
	}
	
	private void prettyPrint(final int indent)
	{
		for(int idx=0; idx<indent; idx++)
		{
			System.out.print(" ");
		}
		
		System.out.println("Node digest: " + toHexString(digest()));
			
		if (rightLeaf!=null && leftLeaf!=null)
		{
			for(int idx=0; idx<indent+1; idx++)
			{
				System.out.print(" ");
			}
			
			System.out.println("Left leaf: " + rightLeaf.toString() +
					           " Right leaf: " + leftLeaf.toString());
			
		}
		else if (rightTree!=null && leftTree!=null)
		{
			rightTree.prettyPrint(indent+1);
			leftTree.prettyPrint(indent+1);
		}
		else
		{
			System.out.println("Empty tree");
		}
	}

	public void prettyPrint()
	{
		prettyPrint(0);
	}
}
