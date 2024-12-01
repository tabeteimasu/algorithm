public class Palindrome
{
	public static boolean isPalindrome(int num)
	{
		if(num<0) return false;
		int digit = 0;
		int temp = num;
		while(temp>=1)
		{
			digit+=1;
			temp/=10;
		}
		System.out.println(digit);

		for(int k=0;k<=digit/2-1;k++)
		{
			int first=num,last;
			for(int i=k;i<digit-1-k;i++)
			{
				 first = first / 10;
			}
			first = first % 10;
			last = num % 10;
			System.out.println("first is "+first+", last is "+last);
			if(first!=last) return false;
			num /= 10;
		}
		return true;
	}

	public static void main(String[] args)
	{
		System.out.println(isPalindrome(-122233221));
	}
}
