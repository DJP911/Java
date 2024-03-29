  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

1. Recover BST 
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
import java.util.*;
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  TreeNode() {}
  TreeNode(int val) {
    this.val = val;
  }
  TreeNode(int val, TreeNode left, TreeNode right) {
    this.val = val;
    this.left = left;
    this.right = right;
  }
}
class Solution {
   void recoverTree(TreeNode root) {
    Stack < TreeNode > stack = new Stack < > ();
    TreeNode current = root;
    TreeNode lastProcessed = null;
    TreeNode[] swapped = new TreeNode[2];
    while (!stack.isEmpty() || current != null) {
      while (current != null) {
        stack.push(current);
        current = current.left;
      }
      current = stack.pop();
      if (lastProcessed != null && lastProcessed.val > current.val) {
        if (swapped[0] == null) {
          swapped[0] = lastProcessed;
          swapped[1] = current;
        } else {
          swapped[1] = current;
          break;
        }
      }
      lastProcessed = current;
      current = current.right;
    }
    int temp = swapped[0].val;
    swapped[0].val = swapped[1].val;
    swapped[1].val = temp;
  }
  public static void printInorder(TreeNode node) {
    if (node == null)
      return;
    printInorder(node.left);
    System.out.print(" " + node.val);
    printInorder(node.right);
  }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter values for nodes in level order where 'null' represents missing nodes:");
        String input = scanner.nextLine();
        String[] values = input.split("\\s+");
        TreeNode root = buildTree(values);
        scanner.close();
        Solution solution = new Solution();
        solution.recoverTree(root);
        System.out.println("Recovered tree:");
        solution.printInorder(root);
    }
    private static TreeNode buildTree(String[] values) {
        if (values[0].equals("null")) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode current = queue.poll();
            if (!values[i].equals("null")) {
                current.left = new TreeNode(Integer.parseInt(values[i]));
                queue.add(current.left);
            }
            i++;
            if (i < values.length && !values[i].equals("null")) {
                current.right = new TreeNode(Integer.parseInt(values[i]));
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }
}

  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

2. Views of tree
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.*;
import java.util.Map.Entry;

class Node {
    int data, hd;
    Node left, right;

    public Node(int data) {
        this.data = data;
        left = right = null;
        this.hd = Integer.MAX_VALUE;
    }
}

class Main {
    static Node root;
    private List<Integer> path1 = new ArrayList<>();
    private List<Integer> path2 = new ArrayList<>();

    static Node build(String s[]) {
        if (s.length == 0)
            return null;
        Queue<Node> q = new LinkedList<>();
        Node root = new Node(Integer.parseInt(s[0]));
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < s.length) {
            Node curr = q.poll();
            if (!s[i].equals("null")) {
                curr.left = new Node(Integer.parseInt(s[i]));
                q.add(curr.left);
            }
            i++;
            if (i < s.length && !s[i].equals("null")) {
                curr.right = new Node(Integer.parseInt(s[i]));
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }

    // Right View
    void rightview(Node root) {
        if (root == null)
            return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                Node curr = q.peek();
                q.remove();
                if (i == n - 1) {
                    System.out.print(curr.data + " ");
                }
                if (curr.left != null)
                    q.add(curr.left);
                if (curr.right != null)
                    q.add(curr.right);
            }
        }
    }

    // Left View
    void leftview(Node root) {
        if (root == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int n = queue.size();
            for (int i = 1; i <= n; i++) {
                Node temp = queue.poll();
                if (i == 1)
                    System.out.print(temp.data + " ");
                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
        }
    }

    // Top View
    static class QueueObj {
        Node node;
        int hd;

        QueueObj(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    static void topview(Node root) {
        if (root == null)
            return;
        Queue<QueueObj> q = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int min = 0;
        int max = 0;
        q.add(new QueueObj(root, 0));
        while (!q.isEmpty()) {
            QueueObj curr = q.poll();
            if (!map.containsKey(curr.hd))
                map.put(curr.hd, curr.node.data);

            if (curr.node.left != null) {
                min = Math.min(min, curr.hd - 1);
                q.add(new QueueObj(curr.node.left, curr.hd - 1));
            }
            if (curr.node.right != null) {
                max = Math.max(max, curr.hd + 1);
                q.add(new QueueObj(curr.node.right, curr.hd + 1));
            }
        }
        for (; min <= max; min++)
            System.out.print(map.get(min) + " ");
    }

    // Bottom View
    static void bottomview(Node root) {
        if (root == null)
            return;
        int hd = 0;
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<Node> queue = new LinkedList<Node>();
        root.hd = hd;
        queue.add(root);
        while (!queue.isEmpty()) {
            Node temp = queue.remove();
            hd = temp.hd;
            map.put(hd, temp.data);
            if (temp.left != null) {
                temp.left.hd = hd - 1;
                queue.add(temp.left);
            }
            if (temp.right != null) {
                temp.right.hd = hd + 1;
                queue.add(temp.right);
            }
        }
        for (Integer value : map.values()) {
            System.out.print(value + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i;
        Main ob = new Main();
        String s[] = sc.nextLine().split(" ");
        root = build(s);
        ob.rightview(root);
        System.out.println();
        ob.leftview(root);
        System.out.println();
        ob.topview(root);
        System.out.println();
        ob.bottomview(root);
    }
}
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// 3. vertical order traversal
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.*;
import java.util.Map.Entry;

class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
        left = right = null;
    }
}

public class Main {
    static Node root;
    private List<Integer> path1 = new ArrayList<>();
    private List<Integer> path2 = new ArrayList<>();

    static Node build(String s[]) {
        if (s[0].equals("null") || s.length == 0)
            return null;
        Node root = new Node(Integer.parseInt(s[0]));
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < s.length) {
            Node curr = q.poll();
            String cval = s[i];
            if (!cval.equals("null")) {
                int h = Integer.parseInt(cval);
                curr.left = new Node(h);
                q.add(curr.left);
            }
            i++;
            if (i >= s.length)
                break;
            cval = s[i];
            if (!cval.equals("null")) {
                int h = Integer.parseInt(cval);
                curr.right = new Node(h);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }

    static void preOrderTraversal(Node root, long hd, long vd, TreeMap<Long, Vector<Integer>> m) {
        if (root == null)
            return;
        long val = hd << 30 | vd;
        if (m.get(val) != null)
            m.get(val).add(root.data);
        else {
            Vector<Integer> v = new Vector<Integer>();
            v.add(root.data);
            m.put(val, v);
        }
        preOrderTraversal(root.left, hd - 1, vd + 1, m);
        preOrderTraversal(root.right, hd + 1, vd + 1, m);
    }

    void verticalOrder(Node root) {
        TreeMap<Long, Vector<Integer>> mp = new TreeMap<>();
        preOrderTraversal(root, 0, 1, mp);
        int prekey = Integer.MAX_VALUE;
        for (Entry<Long, Vector<Integer>> entry : mp.entrySet()) {
            if ((entry.getKey() >> 30) != prekey && prekey != Integer.MAX_VALUE)
                System.out.println();
            prekey = (int) (entry.getKey() >> 30);
            for (int x : entry.getValue())
                System.out.print(x + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main ob = new Main();
        String s[] = sc.nextLine().split(" ");
        root = build(s);
        ob.verticalOrder(root);
    }
}
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

4. Boundary traversal
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
        left = right = null;
    }
}

class Main {
    static Node root;
    private List<Integer> path1 = new ArrayList<>();
    private List<Integer> path2 = new ArrayList<>();

    static Node build(String s[]) {
        if (s[0].equals("N") || s.length == 0)
            return null;
        Node root = new Node(Integer.parseInt(s[0]));
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);

        int i = 1;
        while (!q.isEmpty() && i < s.length) {
            Node curr = q.poll();
            String cval = s[i];
            if (!cval.equals("N")) {
                int h = Integer.parseInt(cval);
                curr.left = new Node(h);
                q.add(curr.left);
            }
            i++;
            if (i >= s.length)
                break;
            cval = s[i];
            if (!cval.equals("N")) {
                int h = Integer.parseInt(cval);
                curr.right = new Node(h);
                q.add(curr.right);
            }
            i++;
        }
        return root;
    }

    // print the leaves
    void printLeaves(Node node) {
        if (node == null)
            return;
        printLeaves(node.left);
        if (node.left == null && node.right == null)
            System.out.print(node.data + " ");
        printLeaves(node.right);
    }

    // left boundary
    void printBoundaryLeft(Node node) {
        if (node == null)
            return;
        if (node.left != null) {
            System.out.print(node.data + " ");
            printBoundaryLeft(node.left);
        } else if (node.right != null) {
            System.out.print(node.data + " ");
            printBoundaryLeft(node.right);
        }
    }

    // right boundary
    void printBoundaryRight(Node node) {
        if (node == null)
            return;
        if (node.right != null) {
            printBoundaryRight(node.right);
            System.out.print(node.data + " ");
        } else if (node.left != null) {
            printBoundaryRight(node.left);
            System.out.print(node.data + " ");
        }
    }

    void printBoundary(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        printBoundaryLeft(node.left);
        printLeaves(node.left);
        printLeaves(node.right);
        printBoundaryRight(node.right);
    }

    // main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main ob = new Main();
        String s[] = sc.nextLine().split(" ");
        root = build(s);
        ob.printBoundary(root);
    }
}

\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

5. BFS, DFS
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

BFS

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Class to represent a graph using adjacency list
class Graph {
    int vertices;
    LinkedList<Integer>[] adjList;

    @SuppressWarnings("unchecked")
    Graph(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; ++i)
            adjList[i] = new LinkedList<>();
    }

    // Function to add an edge to the graph
    void addEdge(int u, int v) {
        adjList[u].add(v);
    }

    // Function to perform Breadth First Search on a graph
    void bfs(int startNode) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertices];

        visited[startNode] = true;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            System.out.print(currentNode + " ");

            for (int neighbor : adjList[currentNode]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read number of vertices and edges
        int vertices = scanner.nextInt();
        int edges = scanner.nextInt();

        Graph graph = new Graph(vertices);

        // Read and add edges to the graph
        for (int i = 0; i < edges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            graph.addEdge(source, destination);
        }

        // Read the starting vertex for BFS
        int startNode = scanner.nextInt();
        graph.bfs(startNode);
    }
}
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

6... 
dials algo
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.*;
class Graph {
    private int V;
    private List < List < Node >> adj;

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList < > (V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList < > ());
        }
    }
    public void addEdge(int source, int destination, int weight) {
        Node node = new Node(destination, weight);
        adj.get(source).add(node);
    }
    public void dijkstra(int startVertex) {
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[startVertex] = 0;

        PriorityQueue < Node > pq = new PriorityQueue < > (V, Comparator.comparingInt(node -> node.weight));
        pq.add(new Node(startVertex, 0));
        while (!pq.isEmpty()) {
            int currentVertex = pq.poll().vertex;
            for (Node neighbor: adj.get(currentVertex)) {
                int newDist = distance[currentVertex] + neighbor.weight;
                if (newDist < distance[neighbor.vertex]) {
                    distance[neighbor.vertex] = newDist;
                    pq.add(new Node(neighbor.vertex, newDist));
                }
            }
        }
        // Print the distances
        System.out.println("Vertex\tDistance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + "\t" + distance[i]);
        }
    }
    static class Node {
        int vertex;
        int weight;
        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
}
public class Main {
    public static void main(String[] args) {
        // int V = 5; // Number of vertices
        // int source = 0; // Source vertex
        // Graph graph = new Graph(V);
        // graph.addEdge(0, 1, 2);
        // graph.addEdge(0, 3, 1);
        // graph.addEdge(1, 2, 3);
        // graph.addEdge(1, 3, 2);
        // graph.addEdge(3, 4, 4);
        // graph.addEdge(4, 2, 1);
        
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();
        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        Graph graph = new Graph(V);

        System.out.println("Enter edges in format 'source destination weight':");
        for (int i = 0; i < E; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(source, destination, weight);
        }

        System.out.print("Enter the source vertex: ");
        int source = scanner.nextInt();

        graph.dijkstra(source);
    }
}
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

7. bell man ford
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

public class Main {
    class Edge {
        int src, dest, weight;
        Edge() {
            src = dest = weight = 0;
        }
    }

    int V, E;
    Edge[] edge; // Use array declaration style that's more common in Java.

    Main(int v, int e) {
        V = v;
        E = e;
        edge = new Edge[e];
        for (int i = 0; i < e; ++i) {
            edge[i] = new Edge();
        }
    }

    void BellmanFord(Main graph, int src) {
        int V = graph.V, E = graph.E;
        int[] dist = new int[V]; // Use array declaration style that's more common in Java.

        // Initialize distances to MAX_VALUE.
        for (int i = 0; i < V; ++i)
            dist[i] = Integer.MAX_VALUE;
        dist[src] = 0;

        // Relax all edges |V| - 1 times.
        for (int i = 1; i < V; ++i) {
            for (int j = 0; j < E; ++j) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
                    dist[v] = dist[u] + weight;
            }
        }

        // Check for negative-weight cycles.
        for (int j = 0; j < E; ++j) {
            int u = graph.edge[j].src;
            int v = graph.edge[j].dest;
            int weight = graph.edge[j].weight;
            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("Graph contains a negative-weight cycle");
                return;
            }
        }

        // Print distances.
        for (int i = 0; i < V; ++i)
            if (dist[i] != Integer.MAX_VALUE)
                System.out.print(dist[i] + " ");
            else
                System.out.print(-1 + " ");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        Main graph = new Main(V, E);
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            graph.edge[i].src = u;
            graph.edge[i].dest = v;
            graph.edge[i].weight = w;
        }
        graph.BellmanFord(graph, 0); // Starting from vertex 0.
    }
}

  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

8. Heap Sort
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

public class Main {
    // Function to perform the heap sort
    public static void sort(int[] arr) {
        int N = arr.length;

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--) {
            heapify(arr, N, i);
        }

        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // Function to heapify a subtree rooted with node i which is an index in arr[]
    static void heapify(int[] arr, int N, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < N && arr[l] > arr[largest]) {
            largest = l;
        }

        // If right child is larger than largest so far
        if (r < N && arr[r] > arr[largest]) {
            largest = r;
        }

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, N, largest);
        }
    }

    // Main method to test the code
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        sort(arr);
        System.out.println("Sorted array is:");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        scanner.close();
    }
}


  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

9. binomial heap
    // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import java.util.*;
class BinomialHeapNode {
    int key, degree;
    BinomialHeapNode parent;
    BinomialHeapNode sibling;
    BinomialHeapNode child;
    public BinomialHeapNode(int k){
        key = k;
        degree = 0;
        parent = null;
        sibling = null;
        child = null;
    }
    public BinomialHeapNode reverse(BinomialHeapNode sibl){
        BinomialHeapNode ret;
        if (sibling != null)
            ret = sibling.reverse(this);
        else
            ret = this;
        sibling = sibl;
        return ret;
    }
public BinomialHeapNode findMinNode(){
        BinomialHeapNode x = this, y = this;
        int min = x.key;
        while (x != null) {
            if (x.key < min) {
                y = x;
                min = x.key;
            }
            x = x.sibling;
        }
        return y;
    }
    public BinomialHeapNode findANodeWithKey(int value){
        BinomialHeapNode temp = this, node = null;
        while (temp != null) {
            if (temp.key == value) {
                node = temp;
                break;
            }
            if (temp.child == null)
                temp = temp.sibling;
                else {
                node = temp.child.findANodeWithKey(value);
                if (node == null)
                    temp = temp.sibling;
                else
                    break;
            }
        }
        return node;
    }
    public int getSize(){
        return (1 + ((child == null) ? 0 : child.getSize())+ ((sibling == null) ? 0 : sibling.getSize()));
    }
}
class BinomialHeap {
    private BinomialHeapNode Nodes;
    private int size;
    public BinomialHeap(){
        Nodes = null;
        size = 0;
    }

      public boolean isEmpty() { 
        return Nodes == null; 
    }
    public int getSize() { 
        return size; 
    }
    public void makeEmpty(){
        Nodes = null;
        size = 0;
    }
    public void insert(int value){
        if (value > 0) {
            BinomialHeapNode temp = new BinomialHeapNode(value);
            if (Nodes == null) {
                Nodes = temp;
                size = 1;
            }
            else {
                unionNodes(temp);size++;
            }
        }
    }
      private void merge(BinomialHeapNode binHeap){
        BinomialHeapNode temp1 = Nodes, temp2 = binHeap;
        while ((temp1 != null) && (temp2 != null)) {
            if (temp1.degree == temp2.degree) {
                BinomialHeapNode tmp = temp2;
                temp2 = temp2.sibling;
                tmp.sibling = temp1.sibling;
                temp1.sibling = tmp;
                temp1 = tmp.sibling;
            }
            else {
                if (temp1.degree < temp2.degree) {
                    if ((temp1.sibling == null) ||                                  (temp1.sibling.degree > temp2.degree)){
                        BinomialHeapNode tmp = temp2;
                        temp2 = temp2.sibling;
                        tmp.sibling = temp1.sibling;
                        temp1.sibling = tmp;
                        temp1 = tmp.sibling;
                    }
                    
                        else
                        temp1 = temp1.sibling;
                }
                else {
                    BinomialHeapNode tmp = temp1;
                    temp1 = temp2;
                    temp2 = temp2.sibling;
                    temp1.sibling = tmp;
                    if (tmp == Nodes)
                        Nodes = temp1;
                }
            }
        }
        if (temp1 == null) {
            temp1 = Nodes;
            while (temp1.sibling != null)
                temp1 = temp1.sibling;
            temp1.sibling = temp2;
        }
    }
      private void unionNodes(BinomialHeapNode binHeap){
        merge(binHeap);
        BinomialHeapNode prevTemp = null, temp = Nodes, nextTemp =                                      Nodes.sibling;
        while (nextTemp != null) {
            if ((temp.degree != nextTemp.degree) ||                 ((nextTemp.sibling != null) && (nextTemp.sibling.degree ==  temp.degree))){
                prevTemp = temp;
                temp = nextTemp;
            }
            else {
                if (temp.key <= nextTemp.key) {
                    temp.sibling = nextTemp.sibling;
                    nextTemp.parent = temp;
                    nextTemp.sibling = temp.child;
                    temp.child = nextTemp;
                    temp.degree++;
                }
                else {
                    if (prevTemp == null)
                        Nodes = nextTemp;               
                              else
                        prevTemp.sibling = nextTemp;
                    temp.parent = nextTemp;
                    temp.sibling = nextTemp.child;
                    nextTemp.child = temp;
                    nextTemp.degree++;
                    temp = nextTemp;
                }
            }
            nextTemp = temp.sibling;
        }
    }
    public int findMinimum(){
        return Nodes.findMinNode().key;
    }
    public void delete(int value){
        if ((Nodes!=null) && (Nodes.findANodeWithKey(value)!=null)){
            decreaseKeyValue(value, findMinimum() - 1);
            extractMin();
        }
    }
      public void decreaseKeyValue(int old_value,int new_value){
        BinomialHeapNode temp = Nodes.findANodeWithKey(old_value);
        if (temp == null)
            return;
        temp.key = new_value;
        BinomialHeapNode tempParent = temp.parent;
        while ((tempParent != null) && (temp.key < tempParent.key)) {
            int z = temp.key;
            temp.key = tempParent.key;
            tempParent.key = z;
            temp = tempParent;
            tempParent = tempParent.parent;
        }
    }
    public int extractMin(){
        if (Nodes == null)
            return -1;
        BinomialHeapNode temp = Nodes, prevTemp = null;
        BinomialHeapNode minNode = Nodes.findMinNode();
                while (temp.key != minNode.key) {
            prevTemp = temp;
            temp = temp.sibling;
        }
        if (prevTemp == null)
            Nodes = temp.sibling;
        else
            prevTemp.sibling = temp.sibling;
        temp = temp.child;
        BinomialHeapNode fakeNode = temp;
        while (temp != null) {
            temp.parent = null;
            temp = temp.sibling;
        }
        if ((Nodes == null) && (fakeNode == null))
            size = 0;
        else {
            if ((Nodes == null) && (fakeNode != null)) {
                Nodes = fakeNode.reverse(null);
                size = Nodes.getSize();
            }
                  else {
                if ((Nodes != null) && (fakeNode == null))
                    size = Nodes.getSize();
                else {
                    unionNodes(fakeNode.reverse(null));
                    size = Nodes.getSize();
                }
            }
        }
        return minNode.key;
    }
    public void displayHeap(){
        System.out.print("\nHeap : ");displayHeap(Nodes);
        System.out.println("\n");
    }
    private void displayHeap(BinomialHeapNode r){
        if (r != null) {
            displayHeap(r.child);System.out.print(r.key + " ");
            displayHeap(r.sibling);
        }
    }
 }
 public class Main {
    public static void main(String[] args){
        BinomialHeap binHeap = new BinomialHeap();
        Scanner s=new Scanner(System.in);
        int n=s.nextInt();
        for(int i=0; i<n; i++)
            binHeap.insert(s.nextInt());
        System.out.println("Size:" + binHeap.getSize());
        binHeap.displayHeap();
        binHeap.delete(s.nextInt());
        System.out.println("Size:" + binHeap.getSize());
        binHeap.displayHeap();
        System.out.println(binHeap.isEmpty());
        binHeap.makeEmpty();
        System.out.println(binHeap.isEmpty());
    }
 }



  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

10. K-ary heap
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the capacity of the heap: ");
        final int capacity = scanner.nextInt();
        int[] arr = new int[capacity];

        System.out.print("Enter the number of elements in the heap: ");
        int n = scanner.nextInt();

        System.out.println("Enter " + n + " heap elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.print("Enter the value of k for k-ary heap: ");
        int k = scanner.nextInt();

        buildHeap(arr, n, k);
        System.out.println("Built Heap: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        System.out.print("Enter the element to insert: ");
        int element = scanner.nextInt();
        insert(arr, n, k, element);
        n++; // Increment n after insertion

        System.out.println("Heap after insertion of " + element + ": ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int extractedMax = extractMax(arr, n, k);
        n--; // Decrement n after extracting the max

        System.out.println("Extracted max is " + extractedMax);
        System.out.println("Heap after extract max: ");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        scanner.close();
    }

    public static void buildHeap(int[] arr, int n, int k) {
        for (int i = (n - 1) / k; i >= 0; i--) {
            restoreDown(arr, n, i, k);
        }
    }

    public static void insert(int[] arr, int n, int k, int elem) {
        arr[n] = elem; // Insert element at the end of the heap
        restoreUp(arr, n, k); // Restore heap property
    }

    public static int extractMax(int[] arr, int n, int k) {
        int max = arr[0];
        arr[0] = arr[n - 1]; // Replace the root with the last element
        restoreDown(arr, n - 1, 0, k); // Restore heap property
        return max;
    }
 public static void restoreDown(int[] arr, int len, int index, int k){
        int[] child = new int[k + 1];
        while (true) {
            for (int i = 1; i <= k; i++)
                child[i]=(k*index+i) < len ? (k*index+i) : -1;
            int maxChild = -1, maxChildIndex = 0;
            for (int i = 1; i <= k; i++) {
                if (child[i] != -1 && arr[child[i]] > maxChild) {
                    maxChildIndex = child[i];
                    maxChild = arr[child[i]];
                }
            }
            if (maxChild == -1)
                break;
            if (arr[index] < arr[maxChildIndex])
                swap(arr, index, maxChildIndex);
            index = maxChildIndex;
        }
    }
      public static void restoreUp(int[] arr, int index, int k) {
        int parent = (index - 1) / k;
        while (parent >= 0) {
            if (arr[index] > arr[parent]) {
                swap(arr, index, parent);
                index = parent;
                parent = (index - 1) / k;
            } else
                break;
        }
    }
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}

  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

11. Winner tree
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.*;

class Node {
    int idx;
    Node left, right;
}

public class Main {
    static Node createNode(int idx) {
        Node t = new Node();
        t.left = t.right = null;
        t.idx = idx;
        return t;
    }

    static void traverseHeight(Node root, int[] arr, int[] res) {
        if (root == null) {
            return;
        }
        if (root.left != null && arr[root.idx] == arr[root.left.idx]) {
            if (root.right != null && arr[root.right.idx] < res[0]) {
                res[0] = arr[root.right.idx];
            }
            traverseHeight(root.left, arr, res);
        } else if (root.right != null && arr[root.idx] == arr[root.right.idx]) {
            if (root.left != null && arr[root.left.idx] < res[0]) {
                res[0] = arr[root.left.idx];
            }
            traverseHeight(root.right, arr, res);
        }
    }

    static void findSecondMin(int[] arr, int n) {
        List<Node> li = new LinkedList<>();
        Node root = null;
        for (int i = 0; i < n; i += 2) {
            Node t1 = createNode(i);
            if (i + 1 < n) {
                Node t2 = createNode(i + 1);
                root = (arr[i] < arr[i + 1]) ? createNode(i) : createNode(i + 1);
                root.left = t1;
                root.right = t2;
                li.add(root);
            } else {
                li.add(t1);
            }
        }

        while (li.size() > 1) {
            List<Node> newLi = new LinkedList<>();
            for (int i = 0; i < li.size(); i += 2) {
                Node f1 = li.get(i);
                Node f2 = (i + 1 < li.size()) ? li.get(i + 1) : null;
                root = (f2 == null || arr[f1.idx] < arr[f2.idx]) ? createNode(f1.idx) : createNode(f2.idx);
                root.left = f1;
                root.right = f2;
                newLi.add(root);
            }
            li = newLi;
        }

        int[] res = {Integer.MAX_VALUE};
        traverseHeight(li.get(0), arr, res);
        System.out.println("Minimum: " + arr[li.get(0).idx] + ", Second minimum: " + res[0]);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = s.nextInt();
        findSecondMin(arr, n);
    }
}

  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

12.  Topological Sort
  // ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    static void topologicalSortUtil(int v, List<List<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
        visited[v] = true;
        for (int i : adj.get(v)) {
            if (!visited[i])
                topologicalSortUtil(i, adj, visited, stack);
        }
        // Push the current vertex to the stack which stores the result
        stack.push(v);
    }

    static void topologicalSort(List<List<Integer>> adj, int V) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i])
                topologicalSortUtil(i, adj, visited, stack);
        }
        while (!stack.empty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();
        
        List<List<Integer>> adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj.get(u).add(v);
        }

        topologicalSort(adj, V);
    }
}
