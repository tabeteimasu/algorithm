public class QuickSort
{
	static int CUTOFF = 3;
	public static void quickSort(int[] a)
	{
		sort(a,0,a.length-1);
	}

	public static void sort(int[] a,int low,int high)
	{
		//if(low>=high) return;
		if(high<=low+CUTOFF-1) 
		{
			ElementarySort.insertionSort(a,low,high);
			return;
		}

		int median = medianOf3(a,low,low+(high-low)/2,high);
		exchange(a,low,median);	
	
		int partitioned = partition(a,low,high);
		sort(a,low,partitioned-1);
		sort(a,partitioned+1,high);
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

	public static int medianOf3(int[] a,int low,int middle, int high)
	{
		if(a[low]<a[middle]&&a[middle]<a[high]) return middle;
		else if(a[high]<a[middle]&&a[middle]<a[low]) return middle;
		else if((a[middle]<a[low]&&a[low]<a[high])||(a[high]<a[low]&&a[low]<a[middle])) return low;
		else return high;
	}

	public static void main(String[] args)
	{
		int[] a = {3,4,5,2,1};
		quickSort(a);
		System.out.println(java.util.Arrays.toString(a));
	}
}
