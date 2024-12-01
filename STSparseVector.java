import java.util.Set;
import java.util.HashSet;
public class SparseVector
{
	private HashST<Integer,Double> v;
	
	public SparseVector()
	{v=new HashST<Integer,Double>();}

	public void put(int i,double x)
	{v.put(i,x);}

	public double get(int i)
	{
		if(!v.contains(i)) return 0.0;
		else return v.get(i);
	}

	public Iterable<Integer> indices()
	{return v.keys();}
	
	public double dot(double[] that)
	{
		double sum = 0.0;
		for(int i:indices())
			sum+=that[i]*this.get(i);
		return sum;
	}

	public static void main(String[] args)
	{
		int N=5;
		SparseVector[] a = new SparseVector[N];
		double[] x = {0.50,0.40,0.36,0.37,0.19};
		double[] b = new double[N];
		
		for(int i=0;i<=4;i++)
			a[i] = new SparseVector();
		a[0].put(1,0.90);
		a[1].put(2,0.36);
		a[1].put(3,0.36);
		a[1].put(4,0.18);
		a[2].put(3,0.90);
		a[3].put(0,0.90);
		a[4].put(0,0.47);
		a[4].put(2,0.47);

		for(int i=0;i<N;i++)
			b[i]=a[i].dot(x);
	
		for(int i=0;i<N;i++)
			System.out.println(b[i]);
		 
	}
}

public class HashST<Key,Value>
{
	private int M = 30001;
	private Value[] vals = (Value[]) new Object[M];
	private Key[]	keys = (Key[]) 	 new Object[M];

	private int hash(Key key)
	{
		return (key.hashCode()&0x7fffffff)%M;
	}

	public void put(Key key,Value val)
	{
		int i;
		for(i=hash(key);keys[i]!=null;i=(i+1)%M)
			if(keys[i].equals(key)) break;
		keys[i] = key;
		vals[i] = val;
	}

	public Value get(Key key)
	{
		for(int i = hash(key);keys[i]!=null;i=(i+1)%M)
			if(key.equals(keys[i])) 
				return vals[i];
		return null;
	}

	
	public boolean contains(Key key)
	{
		for(int i = hash(key);keys[i]!=null;i=(i+1)%M)
			if(key.equals(keys[i])) 
				return true;
		return false;
	}
	
	public Set<Key> keys()
	{
		Set<Key> k = new HashSet<Key>();
		for(int i=0;i<M;i++)
			if(keys[i]!=null)
				k.add(keys[i]);
		return k;	
	}
}
