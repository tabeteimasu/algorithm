import java.util.Stack;
public class Evalue
{
	public static void main(String[] args)
	{
		Stack<String> ops = new Stack<String>();
		Stack<Double> vals = new Stack<Double>();
		String expression = args[0];
		char[] chars = expression.toCharArray();
		for(char c:chars)
		{
			String s = String.valueOf(c);
			if	(s.equals("("))		   ;
			else if	(s.equals("+")) ops.push(s);
			else if	(s.equals("*")) ops.push(s);
			else if (s.equals(")"))
			{
				String op = ops.pop();
				if	(op.equals("+")) vals.push(vals.pop()+vals.pop());
				else if	(op.equals("*")) vals.push(vals.pop()*vals.pop());
			}
			else vals.push(Double.parseDouble(s));
		}
		System.out.println(vals.pop());
	}
}
