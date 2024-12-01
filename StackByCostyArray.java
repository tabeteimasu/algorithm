public class Stack{
	String[] s;
	int size;
	Stack(){
		s = new String[0];
		size = 0;
	}
	void push(String item){
		String[] oldArray = s;
		s = new String[size+1];
		for(int k=0;k<oldArray.length;k++) s[k]=oldArray[k];
		s[size] = item;
		size++;
	}
	String pop(){
		String[] oldArray = s;
		s = new String[size-1];	
		for(int k=0;k<oldArray.length-1;k++) s[k]=oldArray[k];
		size--;
		return oldArray[oldArray.length-1];
	}
	void print(){
		if(size==0) System.out.println("Empty!");
		else
			for(int k=0;k<s.length;k++) 
				System.out.println(s[k]);
	}
	public static void main(String args[]){
		Stack stack = new Stack();
		stack.push("to");
		stack.push("be");
		stack.push("or");
		stack.pop();
		stack.pop();
		System.out.println(stack.pop());
		stack.print();
	}
}
