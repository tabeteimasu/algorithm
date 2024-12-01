import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
public class Taxicab
{
	public static void TaxicabLoops(int n)
	{
		for(int i=1;i<n;i++)
			for(int j=1;j<n;j++)
				for(int k=1;k<n;k++)
					for(int l=1;l<n;l++)
						if(i == j || i == k || i == l || j == k || j == l || k == l) break;
						else if(i*i*i+j*j*j==k*k*k+l*l*l)
							System.out.println(i+" "+j+" "+k+" "+l+
							" "+(i*i*i+j*j*j)+" "+(k*k*k+l*l*l));
	}

	public static void TaxicabMaps(int n)
	{
		Map<Integer,String> map = new HashMap<>();
		for(int i = 0;i <n;i++)
			for(int j=i+1;j<n;j++)
			{
				int sumOfCubes = i*i*i + j*j*j;
				if(map.containsKey(sumOfCubes))
					System.out.println(map.get(sumOfCubes)+"="+i+"^3 + "+j+"^3");
				else
					map.put(sumOfCubes,i+"^3 + "+j+"^3");
			}
	}

	public static void TaxicabList(int n)
	{
		List<CubeSum> cubeSums = new ArrayList<>();
		for(int i=1;i<n;i++)
			for(int j=i+1;j<n;j++)
				cubeSums.add(new CubeSum(i,j));
		Collections.sort(cubeSums);
		for(int k=1;k<cubeSums.size();k++)
			if(cubeSums.get(k).sum==cubeSums.get(k-1).sum)
			{
				CubeSum a = cubeSums.get(k-1);
				CubeSum b = cubeSums.get(k);
				System.out.println(a.i+"^3 + "+a.j+"^3="+b.i+"^3 + "+b.j+"^3="+a.sum);
			}
	}

	public static void TaxicabPQ(int n)
	{
		PriorityQueue<CubeSum> pq = new PriorityQueue<>();
		for(int i=1;i<n;i++)
			pq.add(new CubeSum(i,i+1));
		CubeSum previous = null;
		while(!pq.isEmpty())
		{
			CubeSum current = pq.poll();
			if(previous!=null&&current.sum==previous.sum)
				System.out.println(previous.i+"^3+"+previous.j+"^3="+current.i+"^3 + "+current.j+"^3="+previous.sum);
			previous = current;
			if(current.j+1<n)
				pq.add(new CubeSum(current.i,current.j+1));
		}
	}

	public static void main(String[] args)
	{
		TaxicabLoops(20);
		TaxicabMaps(20);
		TaxicabList(20);
		TaxicabPQ(20);
	}

	static class CubeSum implements Comparable<CubeSum>
	{
		int sum,i,j;
		CubeSum(int i,int j)
		{
			this.i = i;
			this.j = j;
			this.sum = i*i*i + j*j*j;
		}

		@Override
		public int compareTo(CubeSum other)
		{
			return Integer.compare(this.sum,other.sum);
		}
	}


}
