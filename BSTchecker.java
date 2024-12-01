public class BSTchecker
{
	public static Node insert(Node node,int key)
	{
		if(node==null) return new Node(key);
		if(key>node.key) node.right = insert(node.right,key);
		else if(key<node.key) node.left = insert(node.left,key);
		return node;
	}

	public static boolean isBST(Node node)
	{
		if(node==null) return true;
		if(node.right!=null&&node.right.key<node.key) return false;
		if(node.left!=null&&node.left.key>node.key) return false;
		return isBST(node.left)&&isBST(node.right);		
	}

	public static void main(String[] args)
	{
		Node root = new Node(4);
		insert(root,2);
		insert(root,6);
		insert(root,1);
		insert(root,3);
		insert(root,5);
		insert(root,7);
		System.out.println(isBST(root));
		Node notree = new Node(4);
		notree.left=new Node(2);
		notree.right=new Node(6);
		notree.left.left=new Node(10);
		System.out.println(isBST(notree));
	}
}

class Node
{
	int key;
	Node next;
	Node right,left;
	Node(int key)
	{
		this.key = key;
	}
}
