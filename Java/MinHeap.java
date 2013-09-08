/**
 * Min heap structure.
 *
 * @author      Bogdan Constantinescu <bog_con@yahoo.com>
 * @since       2013.08.04
 * @version     1.0
 * @link        GitHub    https://github.com/z3ppelin/DijkstraShortestPath
 * @licence     The MIT License (http://opensource.org/licenses/MIT); see LICENCE.txt
 */

public class MinHeap {

    public int lastFreePos; // last free position in heap
    public HeapNode[] heapNodes; // heap 's elements
    public int[] positions; // key is the vertex, value is position in heapNodes

    /**
     * Constructor.
     *
     * @param heapDimension Heap 's dimension.
     */
    public MinHeap(int heapDimension) {
        this.lastFreePos = 0;
        this.heapNodes = new HeapNode[heapDimension];
        this.positions = new int[heapDimension];
        for (int i = 0; i < heapDimension; i++) {
            this.heapNodes[i] = null;
            this.positions[i] = -1;
        }
    }

    /**
     * Inserts a new element into heap.
     *
     * @param hn The element to insert.
     * @throws Exception If heap is full.
     */
    public void insert(HeapNode hn) throws Exception {
        if (this.lastFreePos == heapNodes.length) {
            throw new Exception("Heap overflow");
        }
        this.heapNodes[this.lastFreePos] = hn;
        this.positions[hn.vertex] = this.lastFreePos;
        int childPos = this.lastFreePos, parentPos = (childPos - 1) / 2;
        HeapNode aux;
        while (parentPos >= 0 && this.heapNodes[parentPos].compareTo(this.heapNodes[childPos]) > 0) {
            aux = this.heapNodes[parentPos];
            this.heapNodes[parentPos] = this.heapNodes[childPos];
            this.heapNodes[childPos] = aux;
            this.positions[this.heapNodes[childPos].vertex] = childPos;
            this.positions[this.heapNodes[parentPos].vertex] = parentPos;
            childPos = parentPos;
            parentPos = (childPos - 1) / 2;
        }
        this.lastFreePos++;
    }

    /**
     * Extracts min value (situated on first position in vector).
     * 
     * @return The heap node with minimal shortest path.
     * @throws Exception If heap is empty.
     */
    public HeapNode extractMin() throws Exception {
        if (this.lastFreePos == 0) {
            throw new Exception("Empty heap");
        }
        // It 's basicly a deletion from the first position without needing to bubble up
        HeapNode hn = this.heapNodes[0], aux;
        this.heapNodes[0] = this.heapNodes[this.lastFreePos - 1];
        this.positions[this.heapNodes[0].vertex] = 0;
        this.heapNodes[this.lastFreePos - 1] = null;
        this.positions[hn.vertex] = -1;
        this.lastFreePos--;
        
        /* bubble down */
        int parentPos = 0, leftChildPos = (parentPos + 1) * 2 - 1, rightChildPos = leftChildPos + 1, minChildPos;
        while ((leftChildPos < this.lastFreePos && this.heapNodes[parentPos].compareTo(this.heapNodes[leftChildPos]) > 0)
                || (rightChildPos < this.lastFreePos && this.heapNodes[parentPos].compareTo(this.heapNodes[rightChildPos]) > 0)) {
            minChildPos = leftChildPos;
            if (rightChildPos < this.lastFreePos && this.heapNodes[leftChildPos].compareTo(this.heapNodes[rightChildPos]) > 0) {
                minChildPos = rightChildPos;
            }
            aux = this.heapNodes[parentPos];
            this.heapNodes[parentPos] = this.heapNodes[minChildPos];
            this.heapNodes[minChildPos] = aux;
            this.positions[this.heapNodes[minChildPos].vertex] = minChildPos;
            this.positions[this.heapNodes[parentPos].vertex] = parentPos;
            parentPos = minChildPos;
            leftChildPos = (parentPos + 1) * 2 - 1;
            rightChildPos = leftChildPos + 1;
        }
        return hn;
    }

    /**
     * Deletes an element from heap.
     *
     * @param pos The element 's position in heap.
     * @return  The deleted element.
     * @throws Exception If position is out of permitted bounds.
     */
    public HeapNode delete(int pos) throws Exception {
        if (pos < 0 || pos >= this.lastFreePos) {
            throw new Exception("Invalid position");
        }
        HeapNode hn = this.heapNodes[pos], aux;
        this.heapNodes[pos] = this.heapNodes[this.lastFreePos - 1];
        this.positions[this.heapNodes[pos].vertex] = pos;
        this.heapNodes[this.lastFreePos - 1] = null;
        this.positions[hn.vertex] = -1;
        this.lastFreePos--;
        
        int parentPos = (pos - 1) / 2, childPos = pos;
        if (parentPos >= 0 && null != this.heapNodes[childPos] && this.heapNodes[parentPos].compareTo(this.heapNodes[childPos]) > 0) { // bubble up
            while (parentPos >= 0 && this.heapNodes[parentPos].compareTo(this.heapNodes[childPos]) > 0) {
                aux = this.heapNodes[parentPos];
                this.heapNodes[parentPos] = this.heapNodes[childPos];
                this.heapNodes[childPos] = aux;
                this.positions[this.heapNodes[childPos].vertex] = childPos;
                this.positions[this.heapNodes[parentPos].vertex] = parentPos;
                childPos = parentPos;
                parentPos = (childPos - 1) / 2;
            }
        } else { // bubble down
            parentPos = pos;
            int leftChildPos = (parentPos + 1) * 2 - 1, rightChildPos = leftChildPos + 1, minChildPos;
            while ((leftChildPos < this.lastFreePos && this.heapNodes[parentPos].compareTo(this.heapNodes[leftChildPos]) > 0)
                    || (rightChildPos < this.lastFreePos && this.heapNodes[parentPos].compareTo(this.heapNodes[rightChildPos]) > 0)) {
                minChildPos = leftChildPos;
                if (rightChildPos < this.lastFreePos && this.heapNodes[leftChildPos].compareTo(this.heapNodes[rightChildPos]) > 0) {
                    minChildPos = rightChildPos;
                }
                aux = this.heapNodes[parentPos];
                this.heapNodes[parentPos] = this.heapNodes[minChildPos];
                this.heapNodes[minChildPos] = aux;
                this.positions[this.heapNodes[minChildPos].vertex] = minChildPos;
                this.positions[this.heapNodes[parentPos].vertex] = parentPos;
                parentPos = minChildPos;
                leftChildPos = (parentPos + 1) * 2 - 1;
                rightChildPos = leftChildPos + 1;
            }
        }
        return hn;
    }
}
