package Binarytree.src;

public class BinaryTree {

	public TreeNode root;

	public boolean isEmpty() {
		return root == null;
	}

	public void insert(int newItem) {
		if (isEmpty()) {
			TreeNode newNode = new TreeNode(newItem);
			root = newNode;
		} else {
			insert(root, newItem);
		}
	}

	void insert(TreeNode bst, int newItem) { // recursive method
		if (newItem < bst.item) {
			if (bst.lChild == null) {
				TreeNode newNode = new TreeNode(newItem);
				bst.lChild = newNode;
			} else
				insert(bst.lChild, newItem);
		} else {
			if (bst.rChild == null) {
				TreeNode newNode = new TreeNode(newItem);
				bst.rChild = newNode;
			} else
				insert(bst.rChild, newItem);
		}
	}

	public void postOrder(TreeNode rootNode) {
		if (rootNode != null) {
			postOrder(rootNode.lChild);
			postOrder(rootNode.rChild);
			System.out.print(rootNode.item + " ");
		}
	}

	public void inOrder(TreeNode rootNode) {
		if (rootNode != null) {
			inOrder(rootNode.lChild);
			System.out.print(rootNode.item + " ");
			inOrder(rootNode.rChild);
		}
	}

	public void preOrder(TreeNode rootNode) {
		if (rootNode != null) {
			System.out.print(rootNode.item + " ");
			preOrder(rootNode.lChild);
			preOrder(rootNode.rChild);
		}
	}

	public void PrintBST() {
		PrintSubBST(root, " ", true);
	}

	private void PrintSubBST(TreeNode parent, String indent, boolean last) {
		System.out.println(indent + "+- " + parent.item);
		indent += last ? "  " : "| ";
		if (parent.lChild != null && parent.rChild != null) {
			PrintSubBST(parent.rChild, indent, false);
			PrintSubBST(parent.lChild, indent, true);
		} else if (parent.lChild != null)
			PrintSubBST(parent.lChild, indent, true);
		else if (parent.rChild != null)
			PrintSubBST(parent.rChild, indent, true);
	}

	public void Delete(int deleteItem) {
		root = Delete(root, deleteItem);
	}

	public TreeNode Delete(TreeNode root, int obj) {
		if (root == null) {
			return null;
		}

		if (obj < root.item) {// หาตัวที่จะลบว่ามีมั้ย
			root.lChild = Delete(root.lChild, obj);
		} else if (obj > root.item) {
			root.rChild = Delete(root.rChild, obj);
		} else {
			if (root.lChild == null && root.rChild == null) {//เป็นใบ
				return null;
			} else if (root.lChild == null || root.rChild == null) { //ลูก1
				if (root.lChild != null) {
					return root.lChild;
				} else {
					return root.rChild;
				}
			} else { //ลูก2
				TreeNode temp = SearchLeaf(root.rChild);
				root.item = temp.item;
				root.rChild = Delete(root.rChild, temp.item);
			}
		}
		return root;
	}

	private TreeNode SearchLeaf(TreeNode node) {
		while (node.lChild != null) {
			node = node.lChild;
		}
		return node;
	}

}
