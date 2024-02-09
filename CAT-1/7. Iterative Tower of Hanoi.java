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
