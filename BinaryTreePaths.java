import java.util.ArrayList;
public class BinaryTreePath
{
	public static TreeNode insert(int value,TreeNode node)
	{
		if(node==null) return new TreeNode(value);
		if(value<node.value) node.left = insert(value,node.left);
		if(value>node.value) node.right = insert(value,node.right);
		return node;
	}

	public static void search(int value,TreeNode node,ArrayList<Integer> list)
	{
		list.add(node.value);
		if(value<node.value) search(value,node.left,list);
		if(value>node.value) search(value,node.right,list);
	}


	public static void preOrder(TreeNode node,ArrayList<TreeNode> list)
	{
		if(node!=null)
		{
			if(node.left==null&&node.right==null) list.add(node);
			preOrder(node.left,list);
			preOrder(node.right,list);
		}
	}

	public static String listToPath(ArrayList<Integer> list)
	{
		String path = "";
		int count=0;
		for(int value:list)
		{	
			count++;
			if(count!=list.size())	path += value +"->";
			else path += value;
		}
		return path;
	}

	public static ArrayList<String> binaryTreePaths(TreeNode root)
	{
		ArrayList<String> paths = new ArrayList<>();
		ArrayList<TreeNode> nodes = new ArrayList<>();
		preOrder(root,nodes); // get all the leaves in the binaryTree
		for(TreeNode node:nodes)
		{
			ArrayList<Integer> path = new ArrayList<>();
			search(node.value,root,path);//get the path to the leaf
			paths.add(listToPath(path));
		}
		return paths;
	}

	public static void main(String[] args)
	{
		ArrayList<Integer> list = new ArrayList<>();
		TreeNode root = new TreeNode(2);
		insert(1,root);
		insert(3,root);
		insert(0,root);
		search(0,root,list);
		System.out.println(listToPath(list));
		
		insert(4,root);

		root = new TreeNode(2);
		insert(8,root);
		ArrayList<String> paths = binaryTreePaths(root);
		for(String path:paths) System.out.println(path);
	}
	
}

public class TreeNode
{
	TreeNode left;
	TreeNode right;
	int value;
	TreeNode(int value){this.value=value;}
}
