import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class calculator
{
	public static void main(String[] args)
	{
	while(true)
	{
		StdOut.print("What is the probability of the event?");
		String s = StdIn.readString();
		double event;
		if(s.contains("/"))
			 event = Double.parseDouble(s.split("/")[0])/Double.parseDouble(s.split("/")[1]);
		else event = Double.parseDouble(s);
		StdOut.print("we are 95% confident that the event will occur once in ");
		System.out.println(Math.log(1-0.95)/Math.log(1-event));
		StdOut.print("we are 99% percent sure that the event will happen once in ");
		StdOut.println(Math.log(1-0.99)/Math.log(1-event));
			
		StdOut.print("How many times you will try?");
		double t = StdIn.readDouble();
		StdOut.print("The probability that the event will occur once is ");
		System.out.println(1-Math.pow(1-event,t));
	}
	}
}
