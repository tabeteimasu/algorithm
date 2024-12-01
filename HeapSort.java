public class Heap
{
	public static void sort(String[] arr)
	{
		int N = arr.length-1;
		for(int k=N/2;k>=1;k--)//build the heap bottom up
			sink(arr,k,N);
		while(N>1)//sort down
		{
			exchange(arr,1,N--);
			sink(arr,1,N);
		}
	}

	public static void sink(String[] arr,int k,int N)
	{
		while(2*k<=N)
		{
			int j = 2*k;
			if(j<N&&arr[j].compareTo(arr[j+1])<0) j++;
			if(arr[k].compareTo(arr[j])>0) break;
			exchange(arr,k,j);
			k=j;
		}
	}

	public static void exchange(String[] arr,int a,int b)
	{
		String temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	public static void main(String[] args)
	{
		String[] s = {"-","s","o","r","t","e","x","a","m","p","l","e"};
		sort(s);
		System.out.println(java.util.Arrays.toString(s));
	}
}
