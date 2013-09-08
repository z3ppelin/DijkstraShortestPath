
/**
 * Node class used for a simple linked list to store graph edges.
 *
 * @author      Bogdan Constantinescu <bog_con@yahoo.com>
 * @since       2013.08.04
 * @version     1.0
 * @link        GitHub    https://github.com/z3ppelin/DijkstraShortestPath
 * @licence     The MIT License (http://opensource.org/licenses/MIT); see LICENCE.txt
 */
public class Node {

    public int vertex;
    public Node next;
    public int weight;

    /**
     * Constructor.
     *
     * @param vertex The vertex to store.
     * @param weight The weight of the edge.
     */
    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.next = null;
        this.weight = weight;
    }

    /**
     * Constructor.
     *
     * @param vertex The vertex to store.
     * @param weight The weight of the edge.
     * @param node The next node to link with.
     */
    public Node(int vertex, int weight, Node node) {
        this(vertex, weight);
        this.next = node;
    }
}
