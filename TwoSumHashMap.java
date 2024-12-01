import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
public class TwoSum
{
	public static int[] twoSum(int[] a, int target)
	{
		int[] result = new int[2];
		ArrayList list = new ArrayList<>();
		HashMap<Integer,Integer> map = new HashMap<>();
		for(int k=0;k<a.length;k++)
		{
			map.put(a[k],target-a[k]);
			list.add(a[k]);
		}
		for(int k=0;k<a.length;k++)
		{
			if(list.contains(map.get(a[k])))
			{
				result[0] = a[k];
				result[1] = map.get(a[k]);
				break;
			}
		}
		return result;
	}

	public static void main(String args[])
	{
		int[] nums = {2,3,5};
		System.out.println(Arrays.toString(twoSum(nums,8)));
	}

}
