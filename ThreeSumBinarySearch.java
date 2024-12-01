public class ThreeSum
{
	public static int count(int[] a)
	{
		int N = a.length;
		int count = 0;
		for(int i=0;i<N;i++)
			for(int j=i+1;j<N;j++)
				if(find(a,-(a[i]+a[j])))
					if(a[i]<=a[j]&&a[j]<=-(a[i]+a[j])) //ensure the input is a sorted array
						count++;
		return count;
	}

	public static boolean find(int[] a,int key)
	{
		int low=0, high=a.length-1;
		while(low<=high)
		{
			int mid = low + (high-low)/2;
			if(key<a[mid]) high=mid-1;
			else if(key>a[mid]) low=mid+1;
			else return true;
		}
		return false;
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
