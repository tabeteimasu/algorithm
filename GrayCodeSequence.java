import java.util.List;
import java.util.ArrayList;
public class GrayCodeSequence
{
	public static List<Integer> grayCodeSequence(int n)
	{
		List<Integer> list = new ArrayList<>();
		int size = (int)Math.pow(2,n);
		for(int k=0;k<size;k++)
			list.add(grayCode(k));
		for(int num:list)
			System.out.println(Integer.toBinaryString(num));
		return list;
	}
	
	public static int grayCode(int num)
	{
		return num ^ num>>1;
	}

	public  static void main(String[] args)
	{
		System.out.println(grayCodeSequence(2));
	}
}
