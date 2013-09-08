/**
 * Heap element.
 *
 * @author      Bogdan Constantinescu <bog_con@yahoo.com>
 * @since       2013.08.04
 * @version     1.0
 * @link        GitHub    https://github.com/z3ppelin/DijkstraShortestPath
 * @licence     The MIT License (http://opensource.org/licenses/MIT); see LICENCE.txt
 */

public class HeapNode implements Comparable {

    public int vertex;
    public int startVertex;
    public int shortestPath;

    /**
     * Constructor.
     *
     * @param vertex The vertex to store.
     * @param startVertex The start vertex.
     * @param shortestPath The shortest path from start vertex to vertex.
     */
    public HeapNode(int vertex, int startVertex, int shortestPath) {
        this.vertex = vertex;
        this.startVertex = startVertex;
        this.shortestPath = shortestPath;
    }
    /**
     * Override compareTo
     * @param obj   The object to compare to
     * @return
     */
    public int compareTo(Object obj) {
        if (!(obj instanceof HeapNode)) {
            throw new ClassCastException("A HeapNode object expected.");
        }
        return this.shortestPath - ((HeapNode) obj).shortestPath;
    }

    @Override
    public Object clone() {
        return new HeapNode(this.vertex, this.startVertex, this.shortestPath);
    }
}
