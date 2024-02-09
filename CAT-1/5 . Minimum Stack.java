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
