public class InorderTraverse
{
	public static void inorder(Node node)
	{
		Node current = node;
		while(current!=null)
		{
			if(current.left==null)
			{
				System.out.println(current.key);
				current = current.right;
			}
			else
			{
				Node t = current.left;
				while(t.right!=null&&t.right!=current)
				{
					t = t.right;
				}
				if(t.right==null)
				{
					t.right=current;
					current=current.left;
				}
				else
				{
					t.right=null;
					System.out.println(current.key);
					current=current.right;
				}
			}
		}
	}

	public static void main(String[] args)
	{
		Node root = new Node(4);
		root.left = new Node(2);
		root.left.left = new Node(1);
		root.left.right = new Node(3);
		inorder(root);
	}
}


class Node
{
	Node left,right;
	int key;
	Node(int key)
	{ this.key = key;}
}
