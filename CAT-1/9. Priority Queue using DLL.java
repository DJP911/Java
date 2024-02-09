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
