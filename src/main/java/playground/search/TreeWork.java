package playground.search;

import java.util.*;

import playground.search.Worker;

public class TreeWork {
	
	static ArrayList<BinaryTreeNode> widthFirstQueue;
	static int widthFirstIndex; //point to the next item to process

	public static void DFWork(BinaryTreeNode root, Worker worker) {
		worker.work(root);
		if (root.left != null) {
			DFWork(root.left, worker);
		}
		if (root.right != null) {
			DFWork(root.right, worker);
		}			
	}
	
	public static void WFWorkInit(BinaryTreeNode root) {
		widthFirstQueue = new ArrayList<BinaryTreeNode>();
		//root.bFlag = false;
		widthFirstQueue.add(root);
		widthFirstIndex = 0;
	}
	
	public static void WFWork(Worker worker) {
		if (widthFirstIndex < widthFirstQueue.size()) {
			BinaryTreeNode tr = widthFirstQueue.get(widthFirstIndex);
			//tr.bFlag = true;
			worker.work(tr);
			//if (tr.left != null && !tr.left.bFlag) {
			if (tr.left != null) {
				widthFirstQueue.add(tr.left);
			}
			//if (tr.right != null && !tr.right.bFlag) {
			if (tr.right != null) {
				widthFirstQueue.add(tr.right);
			}
			widthFirstIndex++;
			WFWork(worker);
		}
	}
}