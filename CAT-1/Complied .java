// 1  Loop Detection or Detect Cycle in SLL.java
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

// 2 Sort the bitonic doubly linked list
import java.util.*;

public class Main
{
    static class Node{
        int data;
        Node next;
        Node prev;
    };
    static Node sort(Node head){
        if(head == null || head.next == null) return head;
        
        Node front = head;
        Node last = head;
        Node res = new Node();
        Node resend = res;
        Node next ;
        while(last.next != null){
            last = last.next;
        }
        while(front != last){
            if(last.data <= front.data){
                resend.next=last;
                next = last.prev;
                last.prev.next= null;
                last.prev= resend;
                last= next;
                resend= resend.next;
            }
            else{
                resend.next= front;
                next= front.next;
                front.next=null;
                front.prev=resend;
                front = next;
                resend= resend.next;
            }
        }
        resend.next = front;
        front.prev= resend;
        return res.next;
    }
    static Node push(Node head_ref,int new_data){
        Node new_node = new Node();
        new_node.data=new_data;
        new_node.prev=null;
        new_node.next=head_ref;
        if(head_ref != null) head_ref.prev= new_node;
        head_ref = new_node;
        return head_ref;
    }
    static void printList(Node head){
        if(head == null) System.out.print("Doubly Linked List is Empty");
        while(head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
    }
	public static void main(String[] args) {
	    Scanner sc = new Scanner(System.in);
	    int n = sc.nextInt();
	    Node head = null;
	    for(int i=0;i<n;i++){
	        int m = sc.nextInt();
	        head = push(head,m);
	    }
	    head = sort(head);
	    System.out.println("After Sorting");
	    printList(head);
	}
}

// 3 . Segregate even and odd nodes in a Linked List
import java.util.*;

class Main {
    Node head;

    class Node {
        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    void segregateEvenOdd() {
        Node evenStart = null;
        Node evenEnd = null;
        Node oddStart = null;
        Node oddEnd = null;
        Node currentNode = head;

        while (currentNode != null) {
            int element = currentNode.data;
            if (element % 2 == 0) {
                if (evenStart == null) {
                    evenStart = currentNode;
                    evenEnd = evenStart;
                } else {
                    evenEnd.next = currentNode;
                    evenEnd = evenEnd.next;
                }
            } else {
                if (oddStart == null) {
                    oddStart = currentNode;
                    oddEnd = oddStart;
                } else {
                    oddEnd.next = currentNode;
                    oddEnd = oddEnd.next;
                }
            }
            currentNode = currentNode.next;
        }
        if (oddStart == null || evenStart == null) {
            return;
        }
        evenEnd.next = oddStart;
        oddEnd.next = null;
        head = evenStart;
    }

    void push(int new_data) {
        Node new_node = new Node(new_data);
        new_node.next = head;
        head = new_node;
    }

    void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String args[]) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            main.push(m);
        }
        main.segregateEvenOdd();
        main.printList();
    }
}
// 4. Merge Sort in Doubly Linked List
import java.util.Scanner;

class Node {
    int data;
    Node next, prev;

    Node(int val) {
        data = val;
        next = null;
        prev = null;
    }
}

class Solution {
    public Node split(Node head) {
        Node fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        Node temp = slow.next;
        slow.next = null;
        return temp;
    }

    public Node mergeSort(Node node) {
        if (node == null || node.next == null)
            return node;
        Node second = split(node);
        node = mergeSort(node);
        second = mergeSort(second);
        return merge(node, second);
    }

    public Node merge(Node first, Node second) {
        if (first == null)
            return second;
        if (second == null)
            return first;
        if (first.data < second.data) {
            first.next = merge(first.next, second);
            first.next.prev = first;
            first.prev = null;
            return first;
        } else {
            second.next = merge(first, second.next);
            second.next.prev = second;
            second.prev = null;
            return second;
        }
    }
}

public class Main {
    public static void printList_left_right(Node head) {
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void printList_right_left(Node head) {
        Node tail = head;
        while (tail.next != null)
            tail = tail.next;
        while (tail != null) {
            System.out.print(tail.data + " ");
            tail = tail.prev;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int val = sc.nextInt();
        Node head = new Node(val);
        for (int i = 0; i < n; i++) {
            val = sc.nextInt();
            Node nd = new Node(val);
            nd.next = head;
            head = nd;
        }
        Solution g = new Solution();
        Node res = g.mergeSort(head);
        printList_left_right(res);
        printList_right_left(res);
    }
}
// 5 . Minimum Stack
import java.util.*;

class Mystack {
    Stack<Integer> s;
    Stack<Integer> a;

    Mystack() {
        s = new Stack<Integer>();
        a = new Stack<Integer>();
    }

    void getMin() {
        if (a.isEmpty())
            System.out.println("Stack is Empty");
        else
            System.out.println("Minimum element : " + a.peek());
    }

    void peek() {
        if (s.isEmpty()) {
            System.out.println("Stack is Empty");
            return;
        }

        Integer t = s.peek();
        System.out.print("Top most element: " + t);
    }

    void pop() {
        int t = s.pop();
        if (s.isEmpty()) {
            System.out.println("Stack is Empty");
            return;
        } else
            System.out.println("Removed element : " + t);
        if (t == a.peek())
            a.pop();
    }

    void push(int x) {
        if (s.isEmpty()) {
            s.push(x);
            a.push(x);
            System.out.println(" Number Inserted: " + x);
            return;
        } else {
            s.push(x);
            System.out.println(" Number Inserted: " + x);
        }
        if (x <= a.peek())
            a.push(x);
    }
}

public class Main {
    public static void main(String args[]) {
        Mystack s = new Mystack();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            s.push(m);
        }
        s.getMin();
        s.pop();
        s.getMin();
        s.pop();
        s.peek();
    }
}
// 6. Celebrity Problem
import java.util.*;

public class Main {
    public static int celebritySolution(int[][] mat) {
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < mat.length; i++) {
            stk.push(i);
        }
        while (stk.size() > 1) {
            int col = stk.pop();
            int row = stk.pop();
            if (mat[row][col] == 1) {
                stk.push(col);
            } else {
                stk.push(row);
            }
        }
        int x = stk.pop();
        for (int j = 0; j < mat.length; j++) {
            if (j == x) continue;
            if (mat[x][j] == 1) {
                return -1;
            }
        }
        for (int i = 0; i < mat.length; i++) {
            if (i == x) continue;
            if (mat[i][x] == 0) {
                return -1;
            }
        }
        return x;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = sc.nextInt();
        int res = celebritySolution(matrix);
        if (res == -1)
            System.out.println("There is no celebrity in the party");
        else
            System.out.println(res + " is the celebrity in the party");
    }
}
// 7. Iterative Tower of Hanoi
// Java recursive program to solve tower of hanoi puzzle

class GFG
{
	// Java recursive function to solve tower of hanoi puzzle
	static void towerOfHanoi(int n, char from_rod, char to_rod, char aux_rod)
	{
		if (n == 1)
		{
			System.out.println("Move disk 1 from rod " + from_rod + " to rod " + to_rod);
			return;
		}
		towerOfHanoi(n-1, from_rod, aux_rod, to_rod);
		System.out.println("Move disk " + n + " from rod " + from_rod + " to rod " + to_rod);
		towerOfHanoi(n-1, aux_rod, to_rod, from_rod);
	}
	
	// Driver method
	public static void main(String args[])
	{
		int n = 4; // Number of disks
		towerOfHanoi(n, \'A\', \'C\', \'B\'); // A, B and C are names of rods
	}
}



import java.util.*;

class Main {
    class Stack {
        int capacity;
        int top;
        int array[];

    }

    Stack createStack(int capacity) {
        Stack stack = new Stack();
        stack.capacity = capacity;
        stack.top = -1;
        stack.array = new int[capacity];
        return stack;
    }

    static boolean isFull(Stack stack) {
        return (stack.top == stack.capacity - 1);
    }

    static boolean isEmpty(Stack stack) {
        return (stack.top == -1);
    }

    static void push(Stack stack, int item) {
        if (isFull(stack))
            return;
        stack.top++;
        stack.array[stack.top] = item;
    }

    static int pop(Stack stack) {
        if (isEmpty(stack))
            return Integer.MIN_VALUE;
        return stack.array[stack.top--];
    }

    static void move_disc(Stack source, Stack destination, char s, char d) {
        int p1 = pop(source);
        int p2 = pop(destination);
        if (p1 == Integer.MIN_VALUE) {
            push(source, p2);
            System.out.println("Move the disk " + p2 + " from " + d + " to " + s);
        } else if (p2 == Integer.MIN_VALUE) {
            push(destination, p1);
            System.out.println("Move the disk " + p1 + " from " + s + " to " + d);
        } else if (p1 > p2) {
            push(source, p1);
            push(source, p2);
            System.out.println("Move the disk " + p2 + " from " + d + " to " + s);
        } else {
            push(destination, p2);
            push(destination, p1);
            System.out.println("Move the disk " + p1 + " from " + s + " to " + d);
        }
    }

    public static void main(String[] args) {
        Scanner us = new Scanner(System.in);
        int num_of_disks = us.nextInt();
        Main ob = new Main();
        Stack source, destination, auxillary;

        source = ob.createStack(num_of_disks);
        destination = ob.createStack(num_of_disks);
        auxillary = ob.createStack(num_of_disks);
        int total_num_of_moves;
        char s = 'S', d = 'D', a = 'A';
        if (num_of_disks % 2 == 0) {
            char temp = d;
            d = a;
            a = temp;
        }
        total_num_of_moves = (int) (Math.pow(2, num_of_disks) - 1);
        for (int i = num_of_disks; i >= 1; i--)
            ob.push(source, i);
        for (int i = 1; i <= total_num_of_moves; i++) {
            if (i % 3 == 1)
                ob.move_disc(source, destination, s, d);
            else if (i % 3 == 2)
                ob.move_disc(source, auxillary, s, a);
            else if (i % 3 == 0)
                ob.move_disc(auxillary, destination, a, d);
        }
    }
}
// 8. STOCK SPAN
import java.util.*;

public class Main {
    static void calculate(int arr[], int n, int S[]) {
        Stack<Integer> st = new Stack<>();
        st.push(0);
        S[0] = 1;
        for (int i = 1; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] <= arr[i])
                st.pop();
            S[i] = (st.isEmpty()) ? (i + 1) : (i - st.peek());
            st.push(i);
        }
    }

    static void printArray(int arr[]) {
        System.out.print(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        int S[] = new int[n];
        calculate(arr, n, S);
        printArray(S);
    }
}
// 9. Priority Queue using DLL
import java.util.*;

class Main {
    static class Node {
        int data;
        int priority;
        Node next, prev;

        public Node(int data, int priority) {
            this.data = data;
            this.priority = priority;
        }
    }

    private static Node head = null;

    private static void push(int data, int priority) {
        if (head == null) {
            Node newNode = new Node(data, priority);
            head = newNode;
            return;
        }
        Node node = new Node(data, priority);
        Node temp = head, parent = null;
        while (temp != null && temp.priority >= priority) {
            parent = temp;
            temp = temp.next;
        }
        if (parent == null) {
            node.next = head;
            head.prev = node;
            head = node;
        } else if (temp == null) {
            parent.next = node;
            node.prev = parent;
        } else {
            parent.next = node;
            node.prev = parent;
            node.next = temp;
            temp.prev = node;
        }
    }

    private static int peek() {
        if (head != null) {
            return head.data;
        }
        return -1;
    }

    private static int pop() {
        if (head != null) {
            int curr = head.data;
            head = head.next;
            if (head != null)
                head.prev = null;
            return curr;
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int data = sc.nextInt();
            int pri = sc.nextInt();
            push(data, pri);
        }
        System.out.println(peek());
        System.out.println(pop());
        System.out.println(pop());
        System.out.println(peek());
    }
}
// 10. Sort without extra space
import java.util.*;

class Main {
    public static int minIndex(Queue<Integer> list, int sortIndex) {
        int min_index = -1;
        int min_value = Integer.MAX_VALUE;
        int s = list.size();
        for (int i = 0; i < s; i++) {
            int current = list.peek();
            list.poll();
            if (current <= min_value && i <= sortIndex) {
                min_index = i;
                min_value = current;
            }
            list.add(current);
        }
        return min_index;
    }

    public static void insertMinToRear(Queue<Integer> list, int min_index) {
        int min_value = 0;
        int s = list.size();
        for (int i = 0; i < s; i++) {
            int current = list.peek();
            list.poll();
            if (i != min_index)
                list.add(current);
            else
                min_value = current;
        }
        list.add(min_value);
    }

    public static void sortQueue(Queue<Integer> list) {
        for (int i = 1; i <= list.size(); i++) {
            int min_index = minIndex(list, list.size() - i);
            insertMinToRear(list, min_index);
        }
    }

    public static void main(String[] args) {
        Queue<Integer> list = new LinkedList<Integer>();
        list.add(6);
        list.add(11);
        list.add(15);
        list.add(4);
        sortQueue(list);
        while (list.isEmpty() == false) {
            System.out.print(list.peek() + " ");
            list.poll();
        }
    }
}
// 11  Maximum Sliding Window
import java.util.*;

public class Main {
    public static int[] maxSliding(int[] in, int w) {
        int[] max_left = new int[in.length];
        int[] max_right = new int[in.length];
        max_left[0] = in[0];
        max_right[in.length - 1] = in[in.length - 1];
        for (int i = 1; i < in.length; i++) {
            max_left[i] = (i % w == 0) ? in[i] : Math.max(max_left[i - 1], in[i]);
            final int j = in.length - i - 1;
            max_right[j] = (j % w == 0) ? in[j] : Math.max(max_right[j + 1], in[j]);
        }
        final int[] sliding_max = new int[in.length - w + 1];
        for (int i = 0, j = 0; i + w <= in.length; i++) {
            sliding_max[j++] = Math.max(max_right[i], max_left[i + w - 1]);
        }
        return sliding_max;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int a[] = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int ans[] = maxSliding(a, k);
        for (int i = 0; i < ans.length; i++)
            System.out.print(ans[i] + " ");
    }
}

// 12  Stack Permutation
import java.util.*;

class Main {
    static Boolean check(int ip[], int op[], int n) {
        Stack<Integer> s = new Stack<Integer>();
        int j = 0;
        for (int i = 0; i < n; i++) {
            s.push(ip[i]);
            while (!s.isEmpty() && s.peek() == op[j]) {
                s.pop();
                j++;
            }
        }
        if (s.isEmpty())
            return true;
        return false;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int input[] = new int[n];
        int output[] = new int[n];
        for (int i = 0; i < n; i++)
            input[i] = sc.nextInt();
        for (int i = 0; i < n; i++)
            output[i] = sc.nextInt();
        if (check(input, output, n))
            System.out.println("Yes");
        else
            System.out.println("Not Possible");
    }
}
