package Binarytree.src;

public class TreeNode {
	public int item;
	public TreeNode lChild; // ลูกทางซ้าย
	public TreeNode rChild; // ลูกทางขวา

	public TreeNode(int newItem) {
		item = newItem;
		lChild = null;
		rChild = null;
	}
}

class Main {
	public static void main(String[] args) {
		//TreeNode tree = new TreeNode(50);
		BinaryTree t = new BinaryTree();
        
        t.insert(48);
        t.insert(15);
        t.insert(69);
        t.insert(89);
        t.Delete(48);
        t.insert(32);
        t.insert(25);
        t.Delete(15);
        t.insert(48);
        t.insert(35);
        t.Delete(32);

        t.PrintBST();

	}
}
