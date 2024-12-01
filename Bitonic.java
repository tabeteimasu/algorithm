class Bitonic{
	static int findBitonicPoint(int array[])
	{
		int low=0,high=array.length-1;
		while(low<high)
		{
			int mid=low+(high-low)/2;
			if(array[mid]>array[mid+1]) high=mid;
			else low=mid+1;
		}
		return low;
	}
	static int BinarySearch(int array[],int target,int low,int high,boolean increasing)
	{
		while(low<=high)
		{	
			int mid=low+(high-low)/2;
			if(array[mid]>target)
			{ 
				if(increasing)
					high=mid-1;
				else
					low=mid+1;
			}
			else if(array[mid]<target)
			{
				if(increasing)
					low=mid+1;
				else
					high=mid-1;
			}
			else
			{
				System.out.println("target found at:"+mid);
				return 0;
			}
		}
		System.out.println("target not found");
		return -1;
	}
	public static void main(String args[]){
		int[] array = {-2,-1,2,5,6,3,2,1};
		int target = 1;
		int bitonicIndex = findBitonicPoint(array);
		if(BinarySearch(array,target,0,bitonicIndex,true)==-1)
			BinarySearch(array,target,bitonicIndex,array.length-1,false);
		
	}
}
