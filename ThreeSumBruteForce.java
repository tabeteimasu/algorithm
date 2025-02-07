public class ThreeSum
{
	public static int count(int[] a)
	{
		int N = a.length;
		int count = 0;
		for(int i=0;i<N;i++)
			for(int j=i+1;j<N;j++)
				for(int k=j+1;k<N;k++)
					if(a[i]+a[j]+a[k]==0)
						count++;
		return count;
	}

	public static void main(String[] args)
	{
		int[] numbers = new int[args.length];
		for(int i=0;i<args.length;i++)
			numbers[i] = Integer.parseInt(args[i]);
		int[] a = numbers;
		double startTime = System.nanoTime();
		System.out.println(count(a));
		double endTime = System.nanoTime();
		double elapsedTime = endTime - startTime;
		System.out.println(elapsedTime/1000000);
	}
}
