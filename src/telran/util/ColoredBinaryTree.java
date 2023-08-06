package telran.util;

import java.util.Set;
import java.util.HashSet;
import java.util.Random;

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
		ColoredBinaryTree ct = new ColoredBinaryTree();
		TreeNode root = ct.fillRandom(new TreeNode(0, null), 6, new Random());
		displayRotated(root, 1);

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
	
	
	
	private TreeNode fillRandom(TreeNode root, int depth, Random random) {
		if (depth != 0) {
			root = new TreeNode(random.nextInt(3000), getColor());
	        root.left = fillRandom(root.left, depth - 1, random);
	        root.right = fillRandom(root.right, depth - 1, random);
            return root;
        }
        return null;
	}

	private String getColor() {
		Random random = new Random();
        String[] colors = {"Red", "Blue", "Green"};
        return colors[random.nextInt(colors.length)];
	}

}
