class ThreeSumTwoPointers{
	static int countTriplet(int[] a){
		int count=0;
		int left,right;
		for(int k=0;k<=a.length-3;k++)
		{
			left=k+1;
			right=a.length-1;
			while(left<right)
			{
				int sum = a[k]+a[left]+a[right];
				if(sum==0)
				{
					count++;
					System.out.println(a[k]+":"+a[left]+":"+a[right]);
					left++;
					right--;
				}
				if(sum<0)	left++;
				if(sum>0)	right--;
			}
		}
		return count;
	}
	public static void main(String args[])
	{
		int[] sortedArray = new int[args.length];
		for(int k=0;k<args.length;k++)
		{
			sortedArray[k]=Integer.parseInt(args[k]);
		}
		System.out.println(countTriplet(sortedArray));
	}
}
