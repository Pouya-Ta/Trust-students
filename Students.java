import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Students {
    public static void main(String[] args) {

        ArrayList<Integer> test = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int counter = scanner.nextInt();

        for (int j = 0; j < counter; j++) {
            // Read input for each test case
            int v = scanner.nextInt();
            ArrayList<ArrayList<Integer>> adj =
                    new ArrayList<>(v);

            // Initialize adjacency list for graph
            for (int i = 0; i < v; i++)
                adj.add(new ArrayList<Integer>());

            int m = scanner.nextInt();
            int destination = scanner.nextInt();
            int start = scanner.nextInt();

            // Decrement destination and start by 1 to match 0-based indexing
            destination--;
            start--;

            // Add edges to graph
            for (int i = 0; i < m; ++i) {
                int p = scanner.nextInt();
                int q = scanner.nextInt();
                p--;
                q--;
                addEdge(adj, p, q);
            }

            // Calculate shortest path using BFS
            test.add(lengthOfPath(adj, start, destination, v));
        }

        // Print results for each test case
        for (int l = 0; l < test.size(); l++)
            System.out.println(test.get(l));
    }

    // Function to add an edge to the adjacency list
    private static void addEdge(ArrayList<ArrayList<Integer>> adj, int i, int j) {
        adj.get(i).add(j);
    }

    // Function to calculate the shortest path using BFS
    private static int lengthOfPath(ArrayList<ArrayList<Integer>> adj,
                                    int s, int dest, int v) {
        int[] A = new int[v];
        int[] B = new int[v];

        if (!BFS(adj, s, dest, v, A, B))
            return -1;

        LinkedList<Integer> path = new LinkedList<>();
        int crawl = dest;
        path.add(crawl);
        while (A[crawl] != -1) {
            path.add(A[crawl]);
            crawl = A[crawl];
        }

        return B[dest];
    }

    // Function to perform BFS on the given graph
    private static boolean BFS(ArrayList<ArrayList<Integer>> adj, int src,
                               int dest, int v, int[] A, int[] B) {
        LinkedList<Integer> queue = new LinkedList<>();

        boolean[] visited = new boolean[v];
        for (int i = 0; i < v; i++) {
            visited[i] = false;
            B[i] = Integer.MAX_VALUE;
            A[i] = -1;
        }

        visited[src] = true;
        B[src] = 0;
        queue.add(src);

        // bfs Algorithm
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adj.get(u).size(); i++) {
                if (!visited[adj.get(u).get(i)]) {
                    visited[adj.get(u).get(i)] = true;
                    B[adj.get(u).get(i)] = B[u] + 1;
                    A[adj.get(u).get(i)] = u;
                    queue.add(adj.get(u).get(i));
                    if (adj.get(u).get(i) == dest)
                        return true;
                }
            }
        }
        return false;
    }
}
