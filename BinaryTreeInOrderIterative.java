import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class InorderIterative
{
	public static List<Integer> inorderTraverse(TreeNode current)
	{
		List<Integer> list = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		while(current!=null||!stack.isEmpty())
		{	
			while(current!=null)
			{
				stack.push(current);
				current=current.left;
			}
			TreeNode popped = stack.pop();
			System.out.println(popped.value);
			list.add(popped.value);
			current = popped.right;
		}
		return list;
	}

	public static void main(String[] args)
	{
		TreeNode root = new TreeNode(2);
		TreeNode.insert(root,1);
		TreeNode.insert(root,3);
		TreeNode.insert(root,0);
		TreeNode.insert(root,4);
		inorderTraverse(root);
	}
}

public class TreeNode
{
	TreeNode left;
	TreeNode right;
	int value;
	TreeNode(int value)
	{
		this.value = value;
	}

	public static TreeNode insert(TreeNode node,int value)
	{
		if(node==null) return new TreeNode(value);
		if(value<node.value) node.left = insert(node.left,value);
		if(value>node.value) node.right = insert(node.right,value);
		return node;
	}
}	
