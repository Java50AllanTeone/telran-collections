package telran.util;

import java.util.Set;
import java.util.HashSet;

class TreeNode {
	int val;
	String color;
	TreeNode left;
	TreeNode right;

	TreeNode(int val, String color) {
		this.val = val;
		this.color = color;
	}

	@Override
	public String toString() {
		return color + " " + val;	
	}
}

class MaxColorPathInfo {
	int length;
	String color;
	Set<TreeNode> nodes = new HashSet<>();
}

public class ColoredBinaryTree {
	MaxColorPathInfo result = new MaxColorPathInfo();

	public int longestPath(TreeNode root) {
		if (root != null) {
			longestPath(root.left);
			getPath(root);
			longestPath(root.right);
		}
		return result.length;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void getPath(TreeNode node) {
		if (node == null) {
			return;
		}

		Set left = getSidePath(node.left, node.color, new HashSet<>());
		Set right = getSidePath(node.right, node.color, new HashSet<>());
		int length = left.size() + right.size() + 1;

		if (length > result.length) {
			result.length = length;
			result.color = node.color;
			left.addAll(right);
			left.add(node);
			result.nodes = left;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Set getSidePath(TreeNode root, String pColor, Set nodes) {
		Set longestPath;

		if (root != null && root.color.equals(pColor)) {
			Set left = getSidePath(root.left, pColor, new HashSet<>());
			Set right = getSidePath(root.right, pColor, new HashSet<>());
			longestPath = left.size() > right.size() ? left : right;
			nodes.addAll(longestPath);
			nodes.add(root);
		}
		return nodes;
	}


	public static void main(String[] args) {
        TreeNode root = new TreeNode(1, "Blue");
        root.left = new TreeNode(2, "Red");
        root.right = new TreeNode(3, "Blue");
        root.left.left = new TreeNode(4, "Red");
        root.left.right = new TreeNode(5, "Red");
        root.right.right = new TreeNode(6, "Blue");
        root.left.right.left = new TreeNode(7, "Red");
        root.left.right.left.right = new TreeNode(8, "Red");
        root.right.right.right = new TreeNode(10, "Blue");
        root.right.right.right.right = new TreeNode(12, "Blue");

//		TreeNode root = new TreeNode(20, "Green");
//		root.left = new TreeNode(15, "Yellow");
//		root.right = new TreeNode(35, "Blue");
//		root.right.right = new TreeNode(79, "Blue");
//		root.left.left = new TreeNode(6, "Yellow");
//		root.left.right = new TreeNode(18, "Blue");
//		root.left.left.right = new TreeNode(10, "Yellow");
//		root.left.left.left = new TreeNode(5, "Blue");

		displayRotated(root, 1);

		ColoredBinaryTree ct = new ColoredBinaryTree();
		int resChainLength = ct.longestPath(root);
		String resChainColor = ct.result.color;

		System.out.println("Length: " + resChainLength);
		System.out.println("Color: " + resChainColor);
		System.out.println(ct.result.nodes.toString());
	}

	
	
	
	
	private static void displayRotated(TreeNode root, int level) {
		if (root != null) {
			displayRotated(root.right, level + 1);
			displayRoot(root, level);
			displayRotated(root.left, level + 1);
		}
	}

	private static void displayRoot(TreeNode root, int level) {
		System.out.print(" ".repeat(level * 4));
		System.out.println(root);
	}
	
	
//  private void getChain(TreeNode node) {
//  if (node == null) {
//      return;
//  }
//  
//  int left = getChainSide(node.left, node.color);
//  int right = getChainSide(node.right, node.color);
//  int length = left + right + 1;
//
//  if (length > res.length) {
//  	res.length = length;
//      res.color = node.color;
//  }
//}

//private int getChainSide(TreeNode root, String parentColor) {
//	int res = 0;
//	
//	if (root != null && root.color.equals(parentColor)) {
//		int leftHeight = getChainSide(root.left, parentColor);
//		int rightHeight = getChainSide(root.right, parentColor);
//		res = Math.max(leftHeight, rightHeight) + 1;
//	}
//	return res;
//}

}
