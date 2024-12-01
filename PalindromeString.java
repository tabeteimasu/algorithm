public class Palindrome
{
	public static boolean isPalindrome(int num)
	{
		String temp = Integer.toString(num);
		System.out.println(temp);

		/*
		int[] arr = new int[temp.length()];
	  	
		for(int k=arr.length-1;k>=0;k--)
		{
			arr[k] = num%10;
			num/=10;
		}
		System.out.println(java.util.Arrays.toString(arr));
		for(int k=0;k<=arr.length/2-1;k++)
		{
			if(arr[k]!=arr[arr.length-1-k])	return false;
		}
		*/
		
		StringBuilder sb = new StringBuilder(temp);
		sb.reverse();
		String reversed = sb.toString();
		if(!reversed.equals(temp)) return false;
		

		return true;
	}

	public static void main(String[] args)
	{
		System.out.println(isPalindrome(-1221));
	}
}
