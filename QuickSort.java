public class QuickSort
{
	public static void quickSort(int[] a)
	{
		sort(a,0,a.length-1);
	}

	public static void sort(int[] a,int low,int high)
	{
		if(low>=high) return;
		int partitioned = partition(a,low,high);
		sort(a,low,partitioned-1);
		sort(a,partitioned+1,high);
	}

	public static int select(int[] a,int k)
	{
		int low = 0,high = a.length-1;
		while(high>low)
		{
			int j = partition(a,low,high);
			if(j<k) low=j+1;
			else if(j>k) high=j-1;
			else return a[k];
		}
		return a[k];
	}

	public static int partition(int[] a,int low,int high)
	{
		int i = low, j = high+1;
		do{
			while(a[++i]<a[low])
				if(i==high) break;
			while(a[--j]>a[low])
				if(j==low) break;
			if(i>=j) break;
			exchange(a,i,j);
		}while(true);
		exchange(a,low,j);
		return j;
	}

	public static void exchange(int[] a,int one, int two)
	{
		int temp = a[one];
		a[one] = a[two];
		a[two] = temp;
	}

	public static void main(String[] args)
	{
		int[] a = {3,4,5,2,1};
		quickSort(a);
		System.out.println(java.util.Arrays.toString(a));
		System.out.println(select(a,2)); // find the median
	}
}
