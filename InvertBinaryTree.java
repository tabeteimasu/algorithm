import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
public class InvertBinaryTree
{
	public static List<Integer> invertBinaryTree(TreeNode root)
	{
		List<Integer> originTree = levelOrder(root);
		TreeNode invertTree = new TreeNode(originTree.get(0));
		for(int node:originTree)
		{
			if(node==originTree.get(0)) continue;
			else invertInsert(invertTree,node);
		}
		return levelOrder(invertTree);		
	}

	public static void main(String[] args)
	{
		int[] arr = {4,2,7,1,3,6,9};
		TreeNode originTree = new TreeNode(4);
		for(int k=1;k<arr.length;k++)
		{
			insert(originTree,arr[k]);
		}
		System.out.println(levelOrder(originTree).toString());
		List<Integer> invertTree = invertBinaryTree(originTree);
		System.out.println(invertTree.toString());
	}

	public static List<Integer> levelOrder(TreeNode root)
	{
		List<Integer> l = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty())
		{
			root = q.remove();
			if(root!=null)
			{
				l.add(root.value);
				System.out.println(root.value);
				q.add(root.left);
				q.add(root.right);
			}
		}
		return l;
	}

	public static TreeNode insert(TreeNode node,int value)
	{
		if(node==null) return new TreeNode(value);
		if(value<node.value) node.left = insert(node.left,value);
		if(value>node.value) node.right = insert(node.right,value);
		return node;
	}

	public static TreeNode invertInsert(TreeNode node,int value)
	{
		if(node==null) return new TreeNode(value);
		if(value<node.value) node.right = invertInsert(node.right,value);
		if(value>node.value) node.left = invertInsert(node.left,value);
		return node;
	}
}

public class TreeNode
{
	int value;
	TreeNode left;
	TreeNode right;
	TreeNode(int value)
	{
		this.value = value;
	}
}
