/**
 * Implementation of Dijkstra 's Shortest Path Algorithm in O(n + log m), n =
 * number of vertices, m = number of edges.
 *
 * @author      Bogdan Constantinescu <bog_con@yahoo.com>
 * @since       2013.08.04
 * @version     1.0
 * @link        GitHub    https://github.com/z3ppelin/DijkstraShortestPath
 * @licence     The MIT License (http://opensource.org/licenses/MIT); see LICENCE.txt
 */

import java.io.FileInputStream;
import java.util.*;

public class DijkstraShortestPath {

    public static int startVertex;
    public static final int DEFAULT_MIN_PATH = 10000000;

    /**
     * Main function. Reads graph, calculates shortest path from a starting
     * vertex to all other vertices and prints the result.
     *
     * @param String[] argv Command line arguments.
     * @return void
     */
    public static void main(String[] args) throws Exception {
        System.out.println("------ Begin DijkstraShortestPath ------");
        long start, end;
        Graph graph = null;
        MinHeap heap = null;
        HeapNode hn, hn2;
        Node n;
        boolean[] visited = null;
        int[] shortestPaths = null;
        double readTime = 0.00, algoTime = 0.00;

        /* read directed graph, initialize variables */
        start = System.currentTimeMillis();
        try {
            if (args.length == 0) {
                throw new Exception("The input file must be given as an argument.");
            }
            graph = readGraphFromFile(args[0]);
            heap = new MinHeap(graph.n);
            visited = new boolean[graph.n];
            shortestPaths = new int[graph.n];
            for (int i = 0; i < graph.n; i++) {
                visited[i] = false;
                shortestPaths[i] = DijkstraShortestPath.DEFAULT_MIN_PATH;

                if (startVertex == i) {
                    hn = new HeapNode(i, startVertex, 0);
                } else {
                    hn = new HeapNode(i, startVertex, DijkstraShortestPath.DEFAULT_MIN_PATH);
                }
                try {
                    heap.insert(hn);
                } catch (Exception e) {
                    System.out.println("ERR. " + e.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println("ERR. " + ex.getMessage());
            System.out.println("------- End DijkstraShortestPath -------");
            System.exit(-1);
        }
        end = System.currentTimeMillis();
        readTime = (double) (end - start) / 100;

        /* print read graph */
        //System.out.println("The read graph:");
        //printGraph(graph);
        //System.out.println();

        /* start Dijkstra 's algorithm */
        start = System.currentTimeMillis();
        for (int i = 0; i < graph.n; i++) {
            hn = heap.extractMin();
            visited[hn.vertex] = true;
            shortestPaths[hn.vertex] = hn.shortestPath;

            n = graph.edges[hn.vertex];
            while (null != n) {
                if (!visited[n.vertex]) {
                    if (shortestPaths[hn.vertex] + n.weight < heap.heapNodes[heap.positions[n.vertex]].shortestPath) {
                        hn2 = heap.delete(heap.positions[n.vertex]);
                        hn2.shortestPath = shortestPaths[hn.vertex] + n.weight;
                        heap.insert(hn2);
                    }
                }
                n = n.next;
            }
        }
        end = System.currentTimeMillis();
        algoTime = (double) (end - start) / 100;

        /* print result */
        for (int i = 0; i < graph.n; i++) { 
            System.out.println("Shortest path from " + (startVertex + 1) + " to " + (i + 1) + " is: " + shortestPaths[i]);
        }
        System.out.println();
        System.out.println("Elapsed: " + readTime + " seconds with initializations, reading graph.");
        System.out.println("Elapsed: " + algoTime + " seconds to calculate shortest paths.");
        System.out.println("------- End DijkstraShortestPath -------\n");
    }

    /**
     * Prints graph.
     *
     * @param graph The graph to print.
     */
    public static void printGraph(Graph graph) {
        System.out.println("Graph has " + graph.n + " vertices and " + graph.m + " edge(s).");
        Node node;
        for (int i = 0; i < graph.n; i++) {
            System.out.print("Vertex " + (i + 1) + " has edge(s) with: ");
            node = graph.edges[i];
            if (null == node) {
                System.out.print("nobody");
            } else {
                while (null != node) {
                    System.out.print((node.vertex + 1) + "(" + node.weight + ") ");
                    node = node.next;
                }
            }
            System.out.println();
        }
    }

    /**
     * Reads graph from file.
     *
     * @param file The file where to read the graph from.
     * @return The read graph.
     * @throws Exception
     */
    public static Graph readGraphFromFile(String file) throws Exception {
        Scanner sc;
        StringTokenizer st, st2;
        FileInputStream fis = null;
        int n, m = 0, vertex, neighbor, weight;
        Node[] edges = null;
        String line;

        try {
            fis = new FileInputStream(file);
            sc = new Scanner(fis);
            if (!sc.hasNextInt()) {
                throw new Exception("Could not read number of vertices the graph has.");
            }
            n = sc.nextInt();
            sc.nextLine();

            if (!sc.hasNextInt()) {
                throw new Exception("Could not read the start vertex.");
            }
            startVertex = sc.nextInt() - 1;
            sc.nextLine();

            edges = new Node[n];
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                st = new StringTokenizer(line);
                if (st.hasMoreElements()) {
                    vertex = Integer.parseInt((String) st.nextElement()) - 1;
                } else {
                    throw new Exception("Could not read tail vertex.");
                }
                while (st.hasMoreElements()) {
                    st2 = new StringTokenizer((String) st.nextElement(), ",");
                    if (st2.hasMoreElements()) {
                        neighbor = Integer.parseInt((String) st2.nextElement()) - 1;
                    } else {
                        throw new Exception("Could not read head vertex.");
                    }
                    if (st2.hasMoreElements()) {
                        weight = Integer.parseInt((String) st2.nextElement());
                    } else {
                        throw new Exception("Could not read weight between " + vertex + " and " + neighbor);
                    }
                    if (null == edges[vertex]) {
                        edges[vertex] = new Node(neighbor, weight);
                    } else {
                        edges[vertex] = new Node(neighbor, weight, edges[vertex]);
                    }
                    m++;
                }
            }
            fis.close();
        } catch (Exception ex) {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                }
            }
            throw ex;
        }
        return new Graph(n, m, edges);
    }
}
