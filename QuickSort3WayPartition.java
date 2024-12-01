public class threeWayPartition
{
	public static int[] threeWayPartition(int[] a, int low, int high)
	{
		int[] b = new int[2];
		int v = a[low],i=low,lt=low,gt=high;
		while(i<=gt)
		{
			if(a[i]<v)
			{
				exchange(a,i,lt);
				lt++;
				i++;
			}
			else if(a[i]>v)
			{
				exchange(a,i,gt);
				gt--;
			}
			else i++;
		}
		b[0] = lt-1;b[1] = gt+1;
		return b;
	}
	
	public static void sort(int[] a,int low,int high)
	{
		//if(low>=high) return;
		int[] index = threeWayPartition(a,low,high);
		int lt = index[0];
		int gt = index[1];
		if(lt<0) return;
		sort(a,low,lt);
		if(gt>high) return;
		sort(a,gt,high);
	}
	

	public static void exchange(int[] a, int one,int two)
	{
		int temp = a[one];
		a[one] = a[two];
		a[two] = temp;
	}

	public static void main(String[] args)
	{
		int[] a = {4,3,2,2,2,1,0};
		sort(a,0,a.length-1);
		System.out.println(java.util.Arrays.toString(a));
	}
}
