public class PowerOfTwo
{
	public static boolean isPowerOfTwoLoop(int n)
	{
		if(n==1) return true;
		if(n%2!=0) return false;
		if(n/2==1) return true;
		else  return isPowerOfTwo(n/2);
	}

	public static boolean isPowerOfTwo(int n)
	{
		double exponent = Math.log(n)/Math.log(2);
		if(exponent%1 == 0) return true;
		else return false;
	}

	public static boolean isPowerOfTwoBit(int n)
	{
		return (n&n-1) == 0;
	}

	public static void main(String[] args)
	{
		System.out.println(isPowerOfTwoLoop(162));
		System.out.println(isPowerOfTwo(162));
		System.out.println(isPowerOfTwoBit(162));
	}
}

