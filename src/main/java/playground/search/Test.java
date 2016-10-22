package playground.search;

import java.util.*;

public class Test {
	public static void main(String[] args) {
		//construct
		/* See Test.doc Test 1
		*/
		ArrayList<BinaryTreeNode> al = new ArrayList<BinaryTreeNode>();
		
		for (int i=0; i< 15; i++) {
			BinaryTreeNode tr = new BinaryTreeNode();
			tr.content =i + "";
			al.add(tr);
		}
		al.get(0).left = al.get(1);
		al.get(0).right = al.get(2);
		al.get(1).left = al.get(3);
		al.get(1).right = al.get(4);
		al.get(2).left = al.get(5);
		al.get(2).right = al.get(6);
		al.get(3).left = al.get(7);
		al.get(3).right = al.get(8);
		al.get(4).left = al.get(9);
		al.get(4).right = al.get(10);
		al.get(5).left = al.get(11);
		al.get(5).right = al.get(12);
		al.get(6).left = al.get(13);
		al.get(6).right = al.get(14);
		
		BinaryTreeNode root = al.get(0);
		
		TreeWork.DFWork(root, new PrintWorker());
		System.out.println();
		
		TreeWork.WFWorkInit(root);
		TreeWork.WFWork(new PrintWorker());
		System.out.println();
		
		//TreeWork.DFWork(root, new SetBrotherWorker());
		TreeWork.WFWorkInit(root);
		TreeWork.WFWork(new SetBrotherWorker());
		//TreeWork.WFWorkInit(root);
		//TreeWork.WFWork(new PrintWorker());
		TreeWork.DFWork(root, new PrintWorker());
	}
}