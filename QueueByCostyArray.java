public class Queue{
	String[] s;
	int size;
	Queue(){
		s = new String[0];
		size = 0;
	}
	void enqueue(String item){
		String[] oldArray = s;
		s = new String[size+1];
		for(int k=0;k<oldArray.length;k++) s[k]=oldArray[k];
		s[size] = item;
		size++;
	}
	String dequeue(){
		String[] oldArray = s;
		s = new String[size-1];	
		for(int k=1;k<oldArray.length;k++) s[k-1]=oldArray[k];
		size--;
		return oldArray[0];
	}
	void print(){
		if(size==0) System.out.println("Empty!");
		else
			for(int k=0;k<s.length;k++) 
				System.out.println(s[k]);
	}
	public static void main(String args[]){
		Queue queue = new Queue();
		queue.enqueue("to");
		queue.enqueue("be");
		queue.enqueue("or");
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		queue.print();
	}
}
