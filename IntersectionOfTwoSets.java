public class Intersection
{
	public static int[] insertionSort(int[] a)
	{
		for(int i=1;i<a.length;i++)
			for(int j=i;j>0;j--)
				if(a[j]<a[j-1])
				{
					int temp = a[j];
					a[j] = a[j-1];
					a[j-1] = temp;
				}
		return a;
	}

	public static int binarySearch(int[] a,int target)
	{
		int low = 0;
		int high = a.length-1;
		while(low<=high)
		{
			int mid = low + (high-low)/2;
			if(a[mid]>target) high = mid - 1;
			else if(a[mid]<target) low = mid + 1;
			else return 0;
		} 
		return -1;
	}

	public static void main(String args[])
	{
		int[] a = {2,4,1,5,3};
		int[] b = {1,4,6,7,8};
		int[] sortedA = insertionSort(a);
		int count = 0;
		for(int num:b)
		{
			if(binarySearch(sortedA,num)==0)
				count++;
		}
		System.out.println(count);	
	}
}
