public class TwoSum
{
	public static int[] twoSum(int[] nums,int target)
	{
		int[] result = new int[2];
		int left = 0;
		int right = nums.length-1;
		while(left<right)
		{
			int sum = nums[left] + nums[right];
			if(sum>target) right--;
			else if(sum<target) left++;
			else
			{
				result[0] = nums[left];
				result[1] = nums[right];
				break;
			}
		}
		return result;
	}

	public static void main(String[] args)
	{
		int nums[] = {2,3,5};
		System.out.println(java.util.Arrays.toString(twoSum(nums,8)));
	}
}
