public class ReverseString
{
	public static void reverseString(char[] c)
	{
		for(int k=c.length-1;k>c.length/2;k--)
			for(int j=c.length-1-k;j<c.length;j++)
			{
				char temp = c[k];
				c[k] = c[j];
				c[j] = temp;
				break;
			}
	}

	public static void reverse(char[] c)
	{
		for(int k=0;k<c.length/2;k++)
		{
			char temp = c[k];
			c[k] = c[c.length-1-k];
			c[c.length-1-k] = temp;
		}
	}


	public static void main(String[]  args)
	{
		char[] hello = {'h','e','l','l','o'};
		reverseString(hello);
		System.out.println(java.util.Arrays.toString(hello));
		reverse(hello);
		System.out.println(java.util.Arrays.toString(hello));
	}
}
