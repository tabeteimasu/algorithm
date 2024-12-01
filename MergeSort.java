import java.util.Arrays;
public class MergeSort
{	
	public static final int CUTOFF = 7;
	public static int[] sort(int[] a,int[] aux,int low,int high)
	{	
		if(low==high) return new int[]{a[high]};
		int mid = low + (high-low)/2;
		sort(a,aux,low,mid);
		sort(a,aux,mid+1,high);
		return merge(a,aux,low,mid,high);
	//	return merge(sort(a,aux,low,mid),sort(a,aux,mid+1,high));
		
	}

	public static void sortCut(int[] a,int[] aux,int low,int high)
	{	
		if(high <= low + CUTOFF - 1)
		{
			ElementarySort.insertionSort(a,low,high);
			return;
		}
		int mid = low + (high - low) / 2;
		sortCut(a,aux,low,mid);
		sortCut(a,aux,mid+1,high);
		if(a[mid]<=a[mid+1]) return; //already sorted
		merge(a,aux,low,mid,high);
	}
	
	public static int[] merge(int[] a,int[] b)
	{
		int aLow = 0, bLow = 0;
		int[] c = new int[a.length+b.length];
		for(int k=0;k<c.length;k++)
		{
			if(a[aLow]<b[bLow])
			{
				c[k]=a[aLow];
				aLow++;
				if(aLow==a.length)
				{
					aLow--;
					a[aLow]=Integer.MAX_VALUE;
				}
			}
			else
			{
				c[k]=b[bLow];
				bLow++;
				if(bLow==b.length)
				{
					bLow--;
					b[bLow]=Integer.MAX_VALUE;					
				}
			
			}
		}
		return c;
	}

	public static int[] merge(int[] a,int[] aux,int low,int mid,int high)
	{
		for(int k=low;k<=high;k++)
			aux[k] = a[k];
		int i = low, j = mid + 1;
		for(int k=low;k<=high;k++)
			if(i>mid)		a[k]=aux[j++];//left side is exhausted
			else if(j>high)		a[k]=aux[i++];//right side is exhausted 
			else if(aux[j]<aux[i])	a[k]=aux[j++];
			else			a[k]=aux[i++];
		return a;
	}

	public static int countInversionByMerge(int[] a,int low,int mid,int high)
	{
		int i = low, j = mid + 1,count = 0;
		for(int k=low;k<=high;k++)
			if(i>mid)		{j++;count+=mid+1-i;}//left side is exhausted
			else if(j>high)		i++;//right side is exhausted 
			else if(a[j]<a[i])	{j++;count+=mid+1-i;}//left elements form inversions
			else			i++;
		return count;
	}

	public static void mergeSort(int[] a)
	{
		int[] aux = new int[a.length];
		sortCut(a,aux,0,a.length-1);
	}

	public static int[] bottomUp(int[] a)
	{
		boolean odd = false;
		if(a.length%2!=0)
		{	
			odd = true;
			int[] temp = Arrays.copyOf(a,a.length+1);
			temp[temp.length-1] = Integer.MAX_VALUE;
			a = temp;
		}
		int[] aux = new int[a.length];
		int low,mid,high;
		for(int step=1;step<=a.length/2;step*=2)
			for(int k=0;k<=a.length-2*step;k=k+2*step)
			{
				low=k;
				high=k+2*step-1;
				mid=low+(high-low)/2;
			//	System.out.println("merge from index "+low+" to "+high);
				//System.out.println(Arrays.toString(merge(a,aux,low,mid,high)));
				a = merge(a,aux,low,mid,high);
			}
		if(odd)
			a = Arrays.copyOf(a,a.length-1);
		return a;
	}
	
	public static void mergeBU(int[] a)
	{
		int N = a.length;
		int aux[] = new int[N];
		int low,mid,high;
		for(int sz=1;sz<N;sz=sz+sz)
			for(int lo=0;lo<N-sz;lo+=sz+sz)
			{
				low=lo;
				high=Math.min(lo+sz+sz-1,N-1);
				mid=low+(high-low)/2;	
				merge(a,aux,low,mid,high);
			}
	}

	public static void sortTwoSortedArray(int[] a,int[] b)
	{
		for(int i=0;i<a.length;i++)
			for(int j=b.length-1;j>=0;j--)
			{
				if(a[i]>b[j])
				{
					int temp = a[i];
					a[i] = b[j];
					b[j] = temp;
				}
			}
	}
	
	public static void sortTwoSortedArray(int[] a)
	{	
		int N = a.length/2; //assume that two sub arrays has the same length
		for(int i=0;i<N;i++)
			for(int j=a.length-1;j>=N;j--)	
			{
				if(a[i]>a[j])
				{
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
	}

	public static int countInversion(int[] a)
	{	
		int count = 0;
		for(int k=0;k<a.length;k++)
			for(int j=0;j<a.length;j++)
				if(a[k]>a[j]&&k<j)
					count++;
		return count;
	}
	
	public static void main(String args[])
	{
		int[] a = {10000,4,2,3,1,0,-1,-10};
		int[] aux = new int[a.length]; //do not create array in recursive loop,it is costy
		int[] b = sort(a,aux,0,a.length-1);
		for(int item:b)
			System.out.println(item);
		int[] c = {2,5,6,7,-1,8,9,1,100,200,300};
		mergeSort(c);
		for(int item:c)
			System.out.println(item);

		//bottom-up
		int[] d = {-2,4,5,6,2,-1,0};
		a = bottomUp(d);
		for(int n:a)
			System.out.println(n);
		for(int n:d)
			System.out.println(n);

		//mergeBU
		mergeBU(d);
		for(int n:d)
			System.out.println(n);

		int[] e = {1,3,5,7,9};
		int[] f = {2,4,6};
		sortTwoSortedArray(e,f);
		System.out.println(Arrays.toString(e)+Arrays.toString(f));

		int[] g = {1, 3, 5, 7, 9, 2, 4, 6, 8, 10};
		sortTwoSortedArray(g);
		System.out.println(Arrays.toString(g));

		int[] h = {2,3,5};
		System.out.println(countInversion(h));
		int[] i = {3,4,1,2};
		System.out.println(countInversion(i));
		System.out.println(countInversionByMerge(h,0,1,2));
		System.out.println(countInversionByMerge(i,0,1,3));
	}
	
}
