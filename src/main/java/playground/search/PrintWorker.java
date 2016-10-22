package playground.search;

public class PrintWorker implements Worker {
	
	public void work(BinaryTreeNode node) {
		if (node != null) {
			System.out.print("me:" + node.content + ".");
			if (node.left != null) {
				System.out.print("left:" + node.left.content + ".");
			}else{
				System.out.print("left is null.");
			}
			if (node.right != null) {
				System.out.print("right:" + node.right.content + ".");
			}else{
				System.out.print("right is null.");
			}
			if (node.brother != null) {
				System.out.print("brother:" + node.brother.content + ".");
			}else{
				System.out.print("brother is null.");
			}
			System.out.println();
		}else{
			System.out.println("I am null.");
		}
	}
}