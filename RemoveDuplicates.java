import java.util.LinkedHashSet;
import java.util.Arrays;

public class RemoveDuplicates
{
	public static int removeDuplicate(int[] nums)
	{
		LinkedHashSet<Integer> s = new LinkedHashSet<>();
		for(int k=0;k<nums.length;k++)
		{
			s.add(nums[k]);
		}
		int index=0;
		for(int num:s)
		{
			nums[index] = num;
			index++;
		}
		System.out.println(Arrays.toString(nums));
		return s.size();
	}

	public static void main(String[] args)
	{
		int[] nums = {0,1,1,2,2,3,3};
		System.out.println(removeDuplicate(nums));
	}
}
