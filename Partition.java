import java.util.Arrays;
public class partition
{
	public static int[] partition(int a[],int low,int high)
	{
		int i = low+1, j = high;
		while(true)
		{
			while(a[i]<a[low])
			{
				i++;
				if(i>high) break;
			}
			while(a[j]>a[low])
			{
				j--;
				if(j<low) break;
			}
			if(i>=j) break;
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
		int temp = a[low];
		a[low] = a[j];
		a[j] = temp;
		return a;
	}

	public static void main(String[] args)
	{
		int[] a = {3,4,5,2,1};
		System.out.println(Arrays.toString(partition(a,0,4)));
	}
}
