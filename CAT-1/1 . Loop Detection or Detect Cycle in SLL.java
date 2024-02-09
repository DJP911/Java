import java.util.*;
class Node{
    int num;
    Node next;
    Node(int val){
        num=val;
        next=null;
    }
}
public class Main
{
    static Node insertNode(Node head,int val){
        Node newNode = new Node(val);
        if(head == null){
            head = newNode;
            return head;
        }
        
        Node temp = head;
        while(temp.next != null) temp=temp.next;
        
        temp.next=newNode;
        return head;   
    }
    static void display(Node head){
        Node temp = head;
        while(temp.next != null){
            System.out.print(temp.num+"->");
            temp=temp.next;
        }
        System.out.println(temp.num+"->"+"NULL");
    }
    static void createCycle(Node head,int a,int b){
        int cnta = 0;
        int cntb = 0;
        Node p1 = head;
        Node p2 = head;
        while(cnta != a || cntb != b){
            if(cnta != a) p1 = p1.next;++cnta;
            if(cntb != b) p2 = p2.next;++cntb;
        }
        if(p2 != null) p2.next = p1;
    }
    static boolean cycleDetect(Node head){
        if(head == null) return false;
        Node fast = head;
        Node slow = head;
        
        while(fast.next != null && fast.next.next != null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow) return true;
        }
        return false;
    }
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int n = sc.nextInt();
	    Node head = null;
	    for(int i=0;i<n;i++){
	        int m = sc.nextInt();
	        head = insertNode(head,m);
	    }
	    display(head);
	    int a = sc.nextInt();
	    createCycle(head,1,a); // creating cycle in the list
	    if(cycleDetect(head)==true) System.out.println("Cycle Detected");
	    else System.out.println("Cycle not Detected");
	    
	}
}
