package playground.search;

public class SetBrotherWorker implements Worker {
	
	public void work(BinaryTreeNode node) {
		if (node.left != null && node.right != null) {
			node.left.brother = node.right;
		}
		if (node.right != null && node.brother != null && node.brother.left != null) {
			node.right.brother = node.brother.left;
		}
	}
}