import java.util.ArrayList;
import java.util.Collections;


public class MergeTwoLinkedList
{
	public ListNode MergeTwoLinkedList(ListNode list1, ListNode list2)
	{
		ArrayList<Integer> intList = new ArrayList<>();
		while(list1!=null)
		{
			intList.add(list1.val);
			list1=list1.next;
		}
		while(list2!=null)
		{
			intList.add(list2.val);
			list2=list2.next;
		}
		Collections.sort(intList);
		System.out.println(intList);		
		ListNode mergedList = new ListNode();
		ListNode first = mergedList;
		int count=0;
		for(int val:intList)
		{
			count+=1;
			mergedList.val = val;
			if(count==intList.size()) break;
			mergedList.next = new ListNode();
			mergedList = mergedList.next;
		}
		return first;
	}

	public static void main(String[] args)
	{
		MergeTwoLinkedList m = new MergeTwoLinkedList();
		ListNode list1 = new ListNode();
		ListNode head = list1;
		list1.val = 1;
		list1.next = new ListNode();
		list1 = list1.next;
		list1.val = 2;
		list1.next = new ListNode();
		list1 = list1.next;
		list1.val = 4;
		list1 = head;
		while(list1!=null)
		{
			System.out.println(list1.val);
			list1 = list1.next;	
		}
		list1 = head;// the position of node is moved, move back to the head
		
		System.out.println("___________");
		ListNode list2 = new ListNode();
		ListNode head2 = list2;
		list2.val = 1;
		list2.next = new ListNode();
		list2 = list2.next;
		list2.val = 3;
		list2.next = new ListNode();
		list2 = list2.next;
		list2.val = 4;
		list2 = head2;
		while(list2!=null)
		{
			System.out.println(list2.val);
			list2 = list2.next;
		}
		list2 = head2;
		
		System.out.println("------------");
		ListNode node = m.MergeTwoLinkedList(list1,list2);
		while(node!=null)
		{
			System.out.println(node.val);
			node = node.next;
		}
	} 
}


class ListNode
{
	int val;
	ListNode next;
}
