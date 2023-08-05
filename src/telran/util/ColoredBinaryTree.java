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
		return color;
	}
    
    
}

public class ColoredBinaryTree {
    MaxColorPathInfo res = new MaxColorPathInfo();
    
    
    public int longestChain(TreeNode root) {
        if (root != null) {
        	longestChain(root.left);
        	getChain(root);
        	longestChain(root.right);
        }       
        return res.length;
    }
    
    @SuppressWarnings("rawtypes")
	private void getChain(TreeNode node) {
        if (node == null) {
            return;
        }
        
        Set left = getChainSide(node.left, node.color, res.set);
        Set right = getChainSide(node.right, node.color, res.set);
        
        int length = left.size() + right.size() + 1;

        if (length > res.length) {
        	res.length = length;
            res.color = node.color;
        }
    }
    
    
//    private void getChain(TreeNode node) {
//        if (node == null) {
//            return;
//        }
//        
//        int left = getChainSide(node.left, node.color);
//        int right = getChainSide(node.right, node.color);
//        int length = left + right + 1;
//
//        if (length > res.length) {
//        	res.length = length;
//            res.color = node.color;
//        }
//    }
    
//	private int getChainSide(TreeNode root, String parentColor) {
//		int res = 0;
//		
//		if (root != null && root.color.equals(parentColor)) {
//			int leftHeight = getChainSide(root.left, parentColor);
//			int rightHeight = getChainSide(root.right, parentColor);
//			res = Math.max(leftHeight, rightHeight) + 1;
//		}
//		return res;
//	}
    
    
	@SuppressWarnings("unchecked")
	private Set getChainSide(TreeNode root, String parentColor, Set set) {
		Set res = new HashSet<>();
		
		if (root != null && root.color.equals(parentColor)) {
			Set left = getChainSide(root.left, parentColor, new HashSet<>());
			Set right = getChainSide(root.right, parentColor, new HashSet<>());
		
			res = left.size() > right.size() ? left : right;
			set.addAll(res);
			set.add(root);
			
		}
		return set;
	}

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1, "Blue");
//        root.left = new TreeNode(2, "Red");
//        root.right = new TreeNode(3, "Blue");
//        root.left.left = new TreeNode(4, "Red");
//        root.left.right = new TreeNode(5, "Red");
//        root.right.right = new TreeNode(6, "Blue");
//        root.left.right.left = new TreeNode(7, "Red");
//        root.left.right.left.right = new TreeNode(7, "Red");
//        root.right.right.right = new TreeNode(10, "Blue");
//        root.right.right.right.right = new TreeNode(12, "Blue");
    	
    	
    	TreeNode root = new TreeNode(20, "Green");
    	root.left = new TreeNode(15, "Yellow");
    	root.right = new TreeNode(35, "Blue");
    	root.right.right = new TreeNode(79, "Blue");
    	root.left.left = new TreeNode(6, "Yellow");
    	root.left.right = new TreeNode(18, "Blue");
    	root.left.left.right = new TreeNode(10, "Yellow");
    	root.left.left.left = new TreeNode(5, "Blue");
        
        displayRotated(root, 1);

        ColoredBinaryTree tree = new ColoredBinaryTree();
        int longestChainLength = tree.longestChain(root);
        String longestChainColor = tree.res.color;

        System.out.println("Longest chain length: " + longestChainLength);
        System.out.println("Longest chain color: " + longestChainColor);
        System.out.println(tree.res.set.toString());
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
		System.out.println(root.color);
	}
	
	
	
	
	
	
	class MaxColorPathInfo {
		int length;
		String color;
		Set<TreeNode> set = new HashSet<>();
	}
}
