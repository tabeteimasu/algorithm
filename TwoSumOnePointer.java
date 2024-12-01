public class TwoSum
{
	public static int[] twoSum(int[] nums,int target)
	{
		int[] result = new int[2];
		for(int k=0;k<nums.length-1;k++)
		{
			boolean breakfor = false;
			int pointer = k+1;
			while(k<pointer&&pointer<nums.length)
			{
				int sum = nums[k] + nums[pointer];
				if(sum<target)	pointer++;
				else if(sum>target) {breakfor=true;break;}//no such numbers
				else
				{
					result[0] = nums[k];
					result[1] = nums[pointer];
					breakfor = true;
					break;
				}
			}
			if(breakfor) break;
		}
		return result;
	}

	public static void main(String args[]){
		int[] nums = {2,3,5};
		System.out.println(java.util.Arrays.toString(twoSum(nums,8)));
	}
}
