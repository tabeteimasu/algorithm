class SymbolTable
{
	private String[] keyArr;
	private int[] valueArr;
	private int size;
	SymbolTable(int capacity)
	{
		keyArr = new String[capacity];
		valueArr = new int[capacity];
	}
	void insert(String key,int value)
	{
		if(size==0)
		{
			keyArr[0] = key;
			valueArr[0] = value;
			size++;
		}
		if(keyArr[insertionPoint(key)].equals(key))
		{
			valueArr[insertionPoint(key)] = value; 
		}
		else
		{
			for(int i=size-1;i>=insertionPoint(key);i--)
			{
				keyArr[i+1] = keyArr[i];
				valueArr[i+1] = valueArr[i];
			}
			keyArr[insertionPoint(key)] = key;
			valueArr[insertionPoint(key)] = value;
			size++;
		}
	}

	int get(String key)
	{
		int index = insertionPoint(key);
		if(keyArr[index].equals(key)) return valueArr[index];
		else return -1;
	}

	int insertionPoint(String key)
	{
		int low = 0,high = size-1;
		while(low<=high)
		{
			int mid = low+(high-low)/2;
			if(keyArr[mid].equals(key)) return mid;
			else if(keyArr[mid].compareTo(key)<0) low = mid+1;
			else high = mid-1; 
		}
		return low;
	}
	
	public void print()
	{
		System.out.println(java.util.Arrays.toString(keyArr));
		System.out.println(java.util.Arrays.toString(valueArr));
	}

	public static void main(String[] args)
	{
		SymbolTable st = new SymbolTable(10);
		st.insert("S",0);
		st.insert("E",1);
		st.insert("A",2);
		st.insert("R",3);
		st.insert("E",4);
		System.out.println(st.get("R"));
		st.print();
	}
}
