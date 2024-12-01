public class DynamicMedian
{
	PriorityQueue pqMax;
	PriorityQueue pqMin;
	public DynamicMedian(int capacity)
	{
		pqMax = new PriorityQueue(capacity);
		pqMin = new PriorityQueue(capacity);
	}

	public void insert(int x)
	{
		if(pqMax.Size==0|| x<=pqMax.getFirst())
		{
			pqMax.Size++;
			pqMax.addLast(x);
			pqMax.swimMax(pqMax.Size);
		}
		else
		{
			pqMin.Size++;
			pqMin.addLast(x);
			pqMin.swimMin(pqMin.Size);
		}
		if(pqMax.Size-pqMin.Size>1)
		{
			pqMin.Size++;
			pqMin.addLast(pqMax.getFirst());
			pqMax.delMax();
			pqMin.swimMin(pqMin.Size);
		}
		if(pqMin.Size>pqMax.Size)
		{
			pqMax.Size++;
			pqMax.addLast(pqMin.delMin());
			pqMax.swimMax(pqMax.Size);
		}
	}

	public int findTheMedian()
	{	pqMax.print();pqMin.print();return pqMax.getFirst();	}
	
	public void removeTheMedian()
	{
		pqMax.delMax();
		
		if(pqMin.Size>pqMax.Size)
		{
			pqMax.Size++;
			pqMax.addLast(pqMin.delMin());
			pqMax.swimMax(pqMax.Size);
		}
	}

	public static void main(String[] args)
	{
		DynamicMedian dm = new DynamicMedian(10);
		dm.insert(11);
		dm.insert(22);
		dm.insert(3);
		System.out.println(dm.findTheMedian());
		dm.insert(4);
		System.out.println(dm.findTheMedian());
		dm.removeTheMedian();
		System.out.println(dm.findTheMedian());
	}
}

public class PriorityQueue
{
	int capacity;
	int[] pq;
	int Size;
	public PriorityQueue(int capacity)
	{
		pq = new int[capacity];
	}

	public int getFirst()
	{
		return pq[1];
	}

	public void addLast(int k)
	{
		 pq[Size] = k;
	}
	
	public void swimMax(int k)
	{
		while(k>1&&pq[k/2]<pq[k])
		{
			this.exchange(k/2,k);
			k=k/2;
		}
	}

	public void swimMin(int k)
	{
		while(k>1&&pq[k/2]>pq[k])
		{
			this.exchange(k/2,k);
			k=k/2;
		}
	}

	public void sinkMax(int k)
	{
		while(2*k<=Size)
		{
			int j = 2*k;
			if(j<Size&&pq[j]<pq[j+1]) j++;
			if(pq[k]>pq[j]) break;
			this.exchange(k,j);
			k=j;
		}
	}

	public void sinkMin(int k)
	{
		while(2*k<=Size)
		{
			int j = 2*k;
			if(j<Size&&pq[j]>pq[j+1]) j++;
			if(pq[k]<pq[j]) break;
			this.exchange(k,j);
			k=j;
		}
	}

	public int delMax()
	{
		int max = this.getFirst();
		this.exchange(1,Size--);
		sinkMax(1);
		return max;
	}

	public int delMin()
	{
		int min = pq[1];
		this.exchange(1,Size--);
		sinkMin(1);
		return min;
	}

	public void exchange(int a,int b)
	{
		int temp = pq[a];
		pq[a] = pq[b];
		pq[b] = temp;
	}

	public void print()
	{
		System.out.println(java.util.Arrays.toString(pq));
	}
}
