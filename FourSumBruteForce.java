import java.util.HashMap;
import java.util.Arrays;
import java.util.HashSet;
public class FourSum
{
	public static void fourSumMap(int[] arr)
	{
		HashMap<Integer,String> map = new HashMap<>();
		for(int i=0;i<arr.length;i++)
			for(int j=i+1;j<arr.length;j++)
			{
				int sum = arr[i]+arr[j];
				if(map.containsKey(sum))
					System.out.println(map.get(sum)+"="+i+"+"+j);
				else
					map.put(sum,i+"+"+j);
			}
	}


	public static void main(String[] args)
	{
		int[] a = {0,1,2,3,4,5,6,7};
		fourSumMap(a);
	}
}
