public class Palindrome
{
	public static boolean isPalindrome(int num)
	{
		int temp = num, digit = 0;
		
		while(temp>=1)
		{
			temp/=10;
			digit+=1;
		}
		
		temp = num;
		int[] arr = new int[digit];
		for(int k=0;k<digit;k++)
		{
			arr[k] = temp % 10;
			temp /= 10;
		}
		System.out.println(java.util.Arrays.toString(arr));	
		int reversed = 0;
		/*
		for(int k=0;k<arr.length;k++)
		{
			reversed += arr[k] * (int)Math.pow(10,(digit-1-k));	
		}
		*/
		int multiplication = 1;
		for(int k=arr.length-1;k>=0;k--)
		{
			reversed += arr[k] * multiplication;
			multiplication *= 10;
		}

		System.out.println(reversed);
		System.out.println(num);
		if(reversed==num) return true;
		return false;
	}

	public static void main(String[] args)
	{
		System.out.println(isPalindrome(-122321));
	}
}
