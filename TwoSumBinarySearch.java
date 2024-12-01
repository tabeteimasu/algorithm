public class TwoSum
{
	public static int[] twoSum(int[] nums,int target)
	{
		int[] result = new int[2];
		for(int k=0;k<nums.length;k++)
		{
			int complement = target - nums[k];
			int index = binarySearch(nums,complement);
			if(index!=-1)
			{
				result[0] = nums[k];
				result[1] = nums[index];
				break;
			}
		}
		return result;
	}

	public static int binarySearch(int[] a,int target)
	{
		int low=0;
		int high = a.length-1;
		while(low<=high)
		{
			int middle = low + (high-low)/2;
			if(a[middle]<target)	low = middle + 1;
			else if(a[middle]>target)  high = middle - 1;
			else	return middle;
		}
		return -1;
	}

	public static void main(String[] args)
	{
		int nums[] = {2,3,5};
		System.out.println(java.util.Arrays.toString(twoSum(nums,7)));
	}
}
