import java.util.Random;
public class ElementarySort
{
	public static int[] bubbleSort(int[] a)
	{
		for(int i=0;i<a.length-1;i++)
			for(int j=i+1;j<a.length;j++)
				if(a[j]<a[i]) 
					swap(a,j,i);
		return a;
	}

	public static int[] selectionSort(int[] a)
	{
		for(int i=0;i<a.length-1;i++)
		{
			int smallestIndex = i;
			for(int j=i+1;j<a.length;j++)
					if(a[j]<a[smallestIndex])
						smallestIndex = j;
			swap(a,i,smallestIndex);
		}
		return a;
	}

	public static int[] insertionSort(int[] a)
	{
		for(int i=0;i<a.length-1;i++)
			for(int j=i;j>=0;j--)
				if(a[j+1]<a[j])
					swap(a,j+1,j);
				else break;//the previous part is sorted
		return a;
	}

	public static void insertionSort(int[] a,int low,int high)
	{
		for(int i=low+1;i<=high;i++)
			for(int j=i;j>0;j--)
				if(a[j]<a[j-1])
					swap(a,j,j-1);
				else break;
	}

	public static int[] shellSort(int[] a,int h)
	{
		for(int i=h;i<a.length;i++)
			for(int j=i;j>=h;j-=h)
				if(a[j]<a[j-h])
					swap(a,j-h,j);
		return a;
				
	}
	
	//for 3x+1 sequence, the complexity n**3/2	
	public static int[] shellSort3Xplus1(int[] a)
	{
		int N = a.length;
		int h = 1;
		while(h<N/3) h=3*h+1;
		while(h>=1)
		{
			for(int i=h;i<N;i++)
				for(int j=i;j>=h;j-=h)
					if(a[j]<a[j-h])
						swap(a,j,j-h);
			h=h/3;
		}
		return a;
	}

	public static int[] knuthShuffle(int[] a)
	{
		Random random = new Random();
		for(int k=0;k<a.length;k++)
		{
			int randomIndex = random.nextInt(k+1);
			swap(a,k,randomIndex);
		}
		return a;
	}

	
	public static int[] bubbleSortCosty(int[] a)
	{
		for(int k=0;k<a.length;k++)
			for(int i=0;i<a.length-1;i++)
				if(a[i]>a[i+1])
					swap(a,i,i+1);
		return a;
	}


	private static void swap(int a,int b)
	{
		int temp = a;
		a = b;
		b = temp;
	}

	private static void swap(int[] arr,int a,int b)
	{
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;
	}
	
	public static void main(String args[])
	{
		int[] arr = {2,4,1,3,0};
		shellSort(arr,1);
		for(int k=0;k<arr.length;k++)
			System.out.println(arr[k]);
		int[] a = {13,40,121,4,1};
		shellSort3Xplus1(a);
		for(int k=0;k<a.length;k++)
			System.out.println(a[k]);
		System.out.println("_____________");
		a = new int[]{1,2,3,4,5,6,7};
		knuthShuffle(a);
		for(int k=0;k<a.length;k++)
			System.out.println(a[k]);
	}
}
