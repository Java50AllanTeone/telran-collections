package telran.util;

import java.util.Set;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.HashSet;
import java.util.Random;



class MaxColorPathInfo {
	int length;
	String color;
	Set<ColoredBinaryTree.TreeNode> nodes = new HashSet<>();
}

public class ColoredBinaryTree {
	MaxColorPathInfo result = new MaxColorPathInfo();
	
	public static class TreeNode {
		public int val;
		public String color;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int val, String color) {
			this.val = val;
			this.color = color;
		}

		@Override
		public String toString() {
			return color + " " + val;	
		}
	}
	
	@Override
	public String toString() {
		int length = result.length;
		String color = result.color;
		List<Integer> values = new ArrayList<Integer>();
		
		result.nodes.forEach(node -> values.add(node.val));
		values.sort(Comparator.naturalOrder());
		return String.format("%d, %s, %s",length, color, values.toString());
	}

	
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
		TreeNode root = ct.fillRandom(new TreeNode(0, null), 4, new Random());
		displayRotated(root, 0);

		int resChainLength = ct.longestPath(root);
		String resChainColor = ct.result.color;

		System.out.println("Length: " + resChainLength);
		System.out.println("Color: " + resChainColor);
		System.out.println(ct.result.nodes.toString());
//		System.out.println(ct.toString());
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
			root = new TreeNode(random.nextInt(3000), getRandomColor());
	        root.left = fillRandom(root.left, depth - 1, random);
	        root.right = fillRandom(root.right, depth - 1, random);
            return root;
        }
        return null;
	}

	private String getRandomColor() {
		Random random = new Random();
        String[] colors = {"Red", "Blue", "Green"};
        return colors[random.nextInt(colors.length)];
	}
	
	

}
