import java.util.Stack;

public class myStack
{
	private Stack<Double> maxStack = new Stack<>();
	private Stack<Double> stack = new Stack<>();	

	public void push(double item){
		stack.push(item);

		if(maxStack.isEmpty()||item>maxStack.peek())	
			maxStack.push(item);

	}

	public void pop(){
		if(stack.pop().equals(maxStack.peek())) 
			maxStack.pop();
	}

	public double getMax(){
		return maxStack.peek();
	}

	public static void main(String args[])
	{
		myStack s = new myStack();
		s.push(1.0);
		s.push(11.0);
		s.push(111.0);
		System.out.println(s.getMax());
		s.pop();
		System.out.println(s.getMax());
	}
}
