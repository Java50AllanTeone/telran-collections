package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.ColoredBinaryTree;
import telran.util.ColoredBinaryTree.TreeNode;

class ColoredBinaryTreeTests {

	@Test
	void test1() {
		ColoredBinaryTree ct = new ColoredBinaryTree();
		TreeNode node = new TreeNode(22, "Green");
		node.left = new TreeNode(15, "Yellow");
		node.right = new TreeNode(35, "Blue");
		node.right.right = new TreeNode(79, "Blue");
		node.left.left = new TreeNode(6, "Yellow");
		node.left.right = new TreeNode(18, "Blue");
		node.left.left.left = new TreeNode(5, "Blue");
		node.left.left.right = new TreeNode(10, "Yellow");	
		ct.longestPath(node);

		assertStr("3, Yellow, [6, 10, 15]", ct.toString());
	}
	
	@Test
	void test2() {
		ColoredBinaryTree ct = new ColoredBinaryTree();
		TreeNode node = new TreeNode(22, "Red");
		node.left = new TreeNode(15, "Green");
		node.right = new TreeNode(35, "Blue");
		node.right.right = new TreeNode(79, "Blue");
		node.right.right.right = new TreeNode(35, "Blue");
		node.right.right.right.right = new TreeNode(99, "Blue");
		node.right.right.right.left = new TreeNode(2, "Green");
		node.left.left = new TreeNode(6, "Green");
		node.left.right = new TreeNode(18, "Green");
		node.left.right.right = new TreeNode(19, "Purple");
		node.left.left.left = new TreeNode(5, "Green");
		node.left.left.right = new TreeNode(10, "Yellow");
		node.left.left.left.left = new TreeNode(2, "Green");				
		ct.longestPath(node);

		assertStr("5, Green, [2, 5, 6, 15, 18]", ct.toString());
	}
	
	@Test
	void test3() {
		ColoredBinaryTree ct = new ColoredBinaryTree();
		TreeNode node = new TreeNode(35, "Green");
		node.left = new TreeNode(22, "Green");
		node.right = new TreeNode(79, "Blue");
		node.right.right = new TreeNode(35, "Blue");
		node.right.right.right = new TreeNode(99, "Blue");
		node.right.right.left = new TreeNode(2, "Green");
		node.left.left = new TreeNode(15, "Green");
		node.left.left.right = new TreeNode(18, "Green");
		node.left.left.left = new TreeNode(6, "Green");
		node.left.left.right.right = new TreeNode(19, "Purple");
		node.left.left.left.right = new TreeNode(10, "Yellow");
		node.left.left.left.left = new TreeNode(5, "Green");
		node.left.left.left.left.left = new TreeNode(2, "Green");
		node.left.left.left.left.left.left = new TreeNode(1, "Yellow");
		ct.longestPath(node);

		assertStr("6, Green, [2, 5, 6, 15, 22, 35]", ct.toString());
	}
	
	
	void assertStr(String exp, String cur) {
		assertEquals(exp, cur);
		System.out.println(cur);
		System.out.println();
	}

}
