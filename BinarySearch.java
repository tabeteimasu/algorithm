class BinarySearch{
	public static int binarySearch(int[] a,int key)
	{
		int low = 0, high = a.length-1;
		while(low<=high)
		{
			int mid = low+(high-low)/2;
			if	(key<a[mid]) high = mid-1;
			else if (key>a[mid]) low = mid+1;
			else return mid;
		}
		return -1;
	}
	public static void main(String args[])
	{
		int[] a = {1,2,3,4,5,6,7};
		int key = Integer.parseInt(args[0]); 
		int index = binarySearch(a,key);
		System.out.println(index);
	}
}
