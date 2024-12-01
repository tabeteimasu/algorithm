public class DutchNationalFlag
{
	public static String[] sort(String[] a){
		int low=0;
		int mid=0;
		int high=a.length-1;
		while(mid<=high)
		{
		if(a[mid]=="red")
		{
			swap(a,mid,low);
			low++;
			mid++;
		}
		else if(a[mid]=="white")
		{
			mid++;
		}
		else
		{
			swap(a,mid,high);
			high--;
		}
		}
		return a;
		
	}

	public static void swap(String[] a,int x,int y)
	{
		String temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}

	public static void main(String args[])
	{
		String a[] = {"white","red","white","blue","red","white","blue"};
		String[] sorted = sort(a);
		for(String bucket:sorted)
			System.out.println(bucket);
	}
}	
