public class RedBlack<Key extends Comparable<Key>, Value>
{
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private Node root;

	public static void main(String[] args)
	{
		RedBlack<Integer,Integer> rb = new RedBlack<>();
		rb.put(1,-1);
		rb.put(2,-2);
		rb.put(3,-3);
		System.out.println(rb.get(3));
	}

	private class Node
	{
		Key key;
		Value val;
		Node left,right;
		boolean color;
		Node(Key key,Value val,boolean color)
		{
			this.key = key;
			this.val = val;
			this.color = RED;
		}
	}

	private boolean isRed(Node x)
	{
		if(x==null) return false;
		return x.color == RED;
	}

	private Node rotateLeft(Node h)
	{
		//assert isRed(h.right);
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}

	private Node rotateRight(Node h)
	{
		//assert isRed(h.left);
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		return x;
	}

	private void flipColors(Node h)
	{
		assert !isRed(h);
		assert isRed(h.left);
		assert isRed(h.right);
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}

	private Node put(Node h,Key key,Value val)
	{
		//insert at bottom
		if(h==null) return new Node(key,val,RED);
		int cmp = key.compareTo(h.key);
		if	(cmp<0) h.left = put(h.left,key,val);
		else if	(cmp>0) h.right = put(h.right,key,val);
		else 		h.val = val;
		
		//lean left
		//balance 4-node
		//split 4-node
		if(isRed(h.right)&& !isRed(h.left))	 h = rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left))	 h = rotateRight(h);
		if(isRed(h.left) && isRed(h.right))	 flipColors(h);
		
		return h;	
	}

	public void put(Key key,Value val)
	{
		root = put(root,key,val);
	}	

	public Value get(Key key)
	{
		Node x = root;
		while(x!=null)
		{
			int cmp = key.compareTo(x.key);
			if(cmp<0) x=x.left;
			else if(cmp>0) x=x.right;
			else return x.val;
		}
		return null;
	}
}
