package playground.search;

public class BinaryTreeNode {
	public BinaryTreeNode left;
	public BinaryTreeNode right;
	public BinaryTreeNode brother;
	public String content;
	public boolean bFlag;
	
	public BinaryTreeNode() {
		left = null;
		right = null;
		brother = null;
		bFlag = false;
	}

}
