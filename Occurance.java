import java.util.List;
import java.util.ArrayList;
class Occurrence
{
	public static List findOccurence(int[] a,int times)
	{
		int count=1;
		List<Integer> list = new ArrayList<>();
		for(int k=0;k<a.length-1;k++)
		{
			if(a[k]==a[k+1])
			{
				count++;
				if(k+1==a.length-1&&count>=times) list.add(a[k]); 
			}
			else
			{
				if(count>=times) list.add(a[k]);
				count=1;
			}
		}
		return list;
	}

	public static void threeWayPartition(int[] a,int low,int high)
	{
		if(low>=high) return;
		int i=low,lt=low,gt=high,v=a[low];
		while(i<=gt)
		{
			if(a[i]<v) exchange(a,lt++,i++);
			else if(a[i]>v) exchange(a,gt--,i);
			else i++;
		}
		threeWayPartition(a,low,lt-1);
		threeWayPartition(a,gt+1,high);
	}

		
	public static void exchange(int[] arr,int a,int b)
	{
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}

	public static void main(String[] args)
	{
		int[] a = {1,1,2,3,4,4,4,5,5,6,6};
		threeWayPartition(a,0,a.length-1);
		System.out.println(findOccurence(a,2).toString());
	}
}
