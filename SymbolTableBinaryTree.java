import java.util.Queue;
import java.util.LinkedList;
public class BST<Key extends Comparable<Key>, Value>
{
	private Node root;
	
	public void put(Key key,Value val)
	{ root = put(root,key,val);}

	private Node put(Node x,Key key,Value val)
	{
		if(x==null) return new Node(key,val);
		int cmp = key.compareTo(x.key);
		if(cmp<0)
			x.left = put(x.left,key,val);
		else if(cmp>0)
			x.right = put(x.right,key,val);
		else
			x.val = val;
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}

	public Value get(Key key)
	{
		Node x = root;
		while(x!=null)
		{
			int cmp = key.compareTo(x.key);
			if	(cmp<0) x=x.left;
			else if (cmp>0) x=x.right;
			else 		return x.val;
		}
		return null;
	}
	

	public Iterable<Key> iterator()
	{
		Queue<Key> q = new LinkedList<Key>();
		inorder(root,q);
		return q;
	}
	private void inorder(Node x,Queue<Key> q)
	{
		if(x==null) return;
		inorder(x.left,q);
		q.add(x.key);
		inorder(x.right,q);
	}

	public static void main(String[] args)
	{
		BST<String,Integer> bst = new BST<String,Integer>();
		bst.put("S",0);
		bst.put("A",1);
		bst.put("E",2);
		bst.put("R",3);
		bst.put("H",4);
		bst.put("M",5);
		bst.put("C",7);
		bst.put("X",8);
		System.out.println("Rank of T"+bst.rank("T"));
		System.out.println(bst.iterator());
		for(String key:bst.iterator())
			System.out.println("Rank of "+key+":"+bst.rank(key));
	}

	public void deleteMin()
	{
		root = deleteMin(root);
	}
	private Node deleteMin(Node x)
	{
		if(x.left==null) return x.right;	
		x.left = deleteMin(x.left);
		x.count = 1 + size(x.left) + size(x.right);
		return x;
	}
	
	public void delete(Key key)
	{
		root = delete(root,key);
	}
	private Node delete(Node x,Key key)
	{
		if(x==null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp<0) x.left = delete(x.left,key);
		else if(cmp>0) x.right = delete(x.right,key);
		else
		{	
			//for case that the node has no child and one child
			if(x.right==null) return x.left;
			if(x.left==null) return x.right;
			//the node to be delete has two children and sub children
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		x.count = size(x.left)+size(x.right)+1;
		return x;
	}
	private Node min(Node x)
	{
		while(x.left!=null) x=x.left;
		return x;
	}

	public Key floor(Key key)
	{
		Node x = floor(root,key);
		if(x==null) return null;
		return x.key;
	}

	private Node floor(Node x,Key key)
	{
		if(x==null) return null;
		int cmp = key.compareTo(x.key);
		if(cmp==0) return x;
		if(cmp<0) return floor(x.left,key);
		Node t = floor(x.right,key);
		if(t!=null) return t;
		else return x;
	}

	public int size()
	{
		return size(root);
	}

	private int size(Node x)
	{
		if(x==null) return 0;
		return x.count;
	}

	public int rank(Key key)
	{	return rank(key,root);	}
	private int rank(Key key,Node x)
	{
		if(x==null) return 0;
		int cmp = key.compareTo(x.key);
		if(cmp<0) return rank(key,x.left);
		else if(cmp>0) return 1 + size(x.left)+rank(key,x.right);
		else return size(x.left);
	}

private class Node
{
	private Key key;
	private Value val;
	private Node left,right;
	private int count;
	public Node(Key key, Value val)
	{
		this.key = key;
		this.val = val;
		this.count=1;
	}
}
}
