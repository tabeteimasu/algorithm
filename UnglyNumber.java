import java.util.Collections;
import java.util.ArrayList;
import java.util.LinkedHashSet;
public class UglyNumber
{
	//potential bug
	public static int uglyNumber(int n)
	{
		if(n==1) return 1;
		ArrayList<Integer> list = new ArrayList<>();
			for(int k=0;k<=n/2;k++)
			{
				list.add(2*k);
				list.add(3*k);
				list.add(5*k);
			}
		Collections.sort(list);
		LinkedHashSet<Integer> set = new LinkedHashSet<>();
		for(int item:list) set.add(item);
		System.out.println(set.toString());
		int iteration = 0;
		int output=-1;
		for(int item:set)
		{
			iteration++;
			if(iteration==n)
			{
				output = item;
			}
		}
		return output;
	}

	public static int uglyNum(int n)
	{
		int iteration = 0;
		int count = 0;
		while(count<n)
		{
			iteration++;
			if(isUgly(iteration)) count++;
		}
		return iteration;
	}
	
	public static boolean isUgly(int n)
	{
		if(n==1) return true;
		int[] arr = {2,3,5};
		for(int factor:arr)
		{
			while(n%factor==0)
			{
				n/=factor;
				if(n==1) return true;
			}
		}
		return false;
	}

	public static void main(String[] args)
	{
		System.out.println(uglyNumber(11));
		System.out.println(isUgly(8));
		System.out.println(isUgly(14));
		System.out.println(uglyNum(11));
		System.out.println(uglyNum(10));
	}
}
