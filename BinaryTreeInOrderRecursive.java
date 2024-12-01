public class InorderTraversalRecursive
{
	public static void inorderTraverse(TreeNode root)
	{
		if(root.left!=null) inorderTraverse(root.left);
		System.out.println(root.value);
		if(root.right!=null) inorderTraverse(root.right);
	}

	public static TreeNode insert(TreeNode root,int value)
	{
		if(root==null) return new TreeNode(value);
		if(value<root.value)  root.left = insert(root.left,value);
		if(value>root.value) root.right = insert(root.right,value);
		return root;
	}

	public static void main(String[] args)
	{
		TreeNode root = new TreeNode(2);
		insert(root,1);
		insert(root,3);
		inorderTraverse(root);	
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
