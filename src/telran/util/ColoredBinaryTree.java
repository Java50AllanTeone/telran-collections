package telran.util;


class TreeNode {
    int val;
    String color;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int val, String color) {
        this.val = val;
        this.color = color;
    }
}

public class ColoredBinaryTree {
    int maxLength = 0;
    String maxColor = "";

    public int longestChain(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
//        dfs(root, root.color, 0);
        
        return maxLength;
    }

//    private void dfs(TreeNode node, String parentColor, int length) {
//        if (node == null) {
//            return;
//        }
//        
//        if (node.color.equals(parentColor)) {
//            length++;
//        } else {
//            length = 1;
//        }
//
//        if (length > maxLength) {
//            maxLength = length;
//            maxColor = node.color;
//        }
//
//        dfs(node.left, node.color, length);
//        dfs(node.right, node.color, length);
//    }
    
    
    
    private void find(TreeNode node) {
        if (node == null) {
            return;
        }
        
        int left = height(node.left, node.color);
        int right = height(node.right, node.color);
        int length = left + right + 1;

        if (length > maxLength) {
            maxLength = length;
            maxColor = node.color;
        }
    }
    
	private int height(TreeNode root, String parentColor) {
		int res = 0;
		
		if (root != null && root.color.equals(parentColor)) {
			int leftHeight = height(root.left, parentColor);
			int rightHeight = height(root.right, parentColor);
			res = Math.max(leftHeight, rightHeight) + 1;
		}
		return res;
	}

    public static void main(String[] args) {
        // Create the colored binary tree
        TreeNode root = new TreeNode(1, "Blue");
        root.left = new TreeNode(2, "Red");
        root.right = new TreeNode(3, "Blue");
        root.left.left = new TreeNode(4, "Red");
        root.left.right = new TreeNode(5, "Red");
        root.right.right = new TreeNode(6, "Blue");
        root.left.right.left = new TreeNode(7, "Red");

        ColoredBinaryTree tree = new ColoredBinaryTree();
        int longestChainLength = tree.longestChain(root);
        String longestChainColor = tree.maxColor;

        System.out.println("Longest chain length: " + longestChainLength);
        System.out.println("Longest chain color: " + longestChainColor);
    }
    
    
    
    
    
    
    
    
    
//    public int width() {	
//		return width(root);
//	}
//
//	private int width(Node<T> root) {
//		int res = 0;
//		
//		if (root != null) {
//			if (root.left == null && root.right == null) {
//				res = 1;
//			} else {
//				res = width(root.left) + width(root.right);
//			}
//		}
//		
//		return res;
//	}
//
//	public int height() {
//		return height(root);
//	}
//
//	private int height(Node<T> root) {
//		int res = 0;
//		
//		if (root != null) {
//			int leftHeight = height(root.left);
//			int rightHeight = height(root.right);
//			res = Math.max(leftHeight, rightHeight) + 1;
//		}
//		return res;
//	}
}
