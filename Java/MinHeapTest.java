/**
 * JUnit test for MinHeap.
 *
 * @author      Bogdan Constantinescu <bog_con@yahoo.com>
 * @since       2013.07.05
 * @version     1.0
 * @link        GitHub  https://github.com/z3ppelin/DijkstraShortestPath
 * @licence     The MIT License (http://opensource.org/licenses/MIT); see LICENCE.txt
 */

import static org.junit.Assert.*;
import org.junit.*;
import java.util.Random;

public class MinHeapTest {

    protected HeapNode[] v = {
        new HeapNode(0, 0, 6),
        new HeapNode(1, 0, 11),
        new HeapNode(2, 0, 5),
        new HeapNode(3, 0, 7),
        new HeapNode(4, 0, 1),
        new HeapNode(5, 0, 9),
        new HeapNode(6, 0, 100),
        new HeapNode(7, 0, 4),
        new HeapNode(8, 0, 80),
        new HeapNode(9, 0, 45),
        new HeapNode(10, 0, 93),
        new HeapNode(11, 0, 2),
        new HeapNode(12, 0, 32),
        new HeapNode(13, 0, 20),
        new HeapNode(14, 0, 2) //now there are 2 x 2
    };

    /**
     * Test constructor, that all variables are initialized properly.
     */
    @Test
    public void constructorTest() {
        MinHeap mheap = new MinHeap(100);
        assertEquals(mheap.lastFreePos, 0);
        assertEquals(mheap.heapNodes.length, 100);
        assertEquals(mheap.positions.length, 100);
        for (int i = 0; i < 100; i++) {
            assertNull(mheap.heapNodes[i]);
            assertEquals(mheap.positions[i], -1);
        }
    }

    /**
     * Test case for MinHeap.insert();
     */
    @Test
    public void insertTest() {
        int i, j;
        MinHeap heap = new MinHeap(this.v.length);

        i = 0;
        try {
            heap.insert(v[i]); // 6
        } catch (Exception ex) {
            fail("Exception was not expected to be thrown.");
        }
        assertEquals(heap.heapNodes[0], v[0]);
        assertEquals(heap.positions[v[0].vertex], 0);
        assertEquals(heap.lastFreePos, ++i);
        for (j = i; j < heap.heapNodes.length; j++) {
            assertNull(heap.heapNodes[j]);
            assertEquals(heap.positions[j], -1);
        }

        try {
            heap.insert(v[i]); // 6 11
        } catch (Exception ex) {
            fail("Exception was not expected to be thrown.");
        }
        assertEquals(heap.heapNodes[0], v[0]);
        assertEquals(heap.heapNodes[1], v[1]);
        assertEquals(heap.positions[v[0].vertex], 0);
        assertEquals(heap.positions[v[1].vertex], 1);
        assertEquals(heap.lastFreePos, ++i);
        for (j = i; j < heap.heapNodes.length; j++) {
            assertNull(heap.heapNodes[j]);
            assertEquals(heap.positions[j], -1);
        }

        try {
            heap.insert(v[i]); // 5 11 6
        } catch (Exception ex) {
            fail("Exception was not expected to be thrown.");
        }
        assertEquals(heap.heapNodes[0], v[2]);
        assertEquals(heap.heapNodes[1], v[1]);
        assertEquals(heap.heapNodes[2], v[0]);
        assertEquals(heap.positions[v[2].vertex], 0);
        assertEquals(heap.positions[v[1].vertex], 1);
        assertEquals(heap.positions[v[0].vertex], 2);
        assertEquals(heap.lastFreePos, ++i);
        for (j = i; j < heap.heapNodes.length; j++) {
            assertNull(heap.heapNodes[j]);
            assertEquals(heap.positions[j], -1);
        }

        try {
            heap.insert(v[i]); // 5 7 6 11
        } catch (Exception ex) {
            fail("Exception was not expected to be thrown.");
        }
        assertEquals(heap.heapNodes[0], v[2]);
        assertEquals(heap.heapNodes[1], v[3]);
        assertEquals(heap.heapNodes[2], v[0]);
        assertEquals(heap.heapNodes[3], v[1]);
        assertEquals(heap.positions[v[2].vertex], 0);
        assertEquals(heap.positions[v[3].vertex], 1);
        assertEquals(heap.positions[v[0].vertex], 2);
        assertEquals(heap.positions[v[1].vertex], 3);
        assertEquals(heap.lastFreePos, ++i);
        for (j = i; j < heap.heapNodes.length; j++) {
            assertNull(heap.heapNodes[j]);
            assertEquals(heap.positions[j], -1);
        }

        try {
            heap.insert(v[i]);  // 1 5 6 11 7
        } catch (Exception ex) {
            fail("Exception was not expected to be thrown.");
        }
        assertEquals(heap.heapNodes[0], v[4]);
        assertEquals(heap.heapNodes[1], v[2]);
        assertEquals(heap.heapNodes[2], v[0]);
        assertEquals(heap.heapNodes[3], v[1]);
        assertEquals(heap.heapNodes[4], v[3]);
        assertEquals(heap.positions[v[4].vertex], 0);
        assertEquals(heap.positions[v[2].vertex], 1);
        assertEquals(heap.positions[v[0].vertex], 2);
        assertEquals(heap.positions[v[1].vertex], 3);
        assertEquals(heap.positions[v[3].vertex], 4);
        assertEquals(heap.lastFreePos, ++i);
        for (j = i; j < heap.heapNodes.length; j++) {
            assertNull(heap.heapNodes[j]);
            assertEquals(heap.positions[j], -1);
        }

        try {
            heap.insert(v[i]);  // 1 5 6 11 7 9
        } catch (Exception ex) {
            fail("Exception was not expected to be thrown.");
        }
        assertEquals(heap.heapNodes[0], v[4]);
        assertEquals(heap.heapNodes[1], v[2]);
        assertEquals(heap.heapNodes[2], v[0]);
        assertEquals(heap.heapNodes[3], v[1]);
        assertEquals(heap.heapNodes[4], v[3]);
        assertEquals(heap.heapNodes[5], v[5]);
        assertEquals(heap.positions[v[4].vertex], 0);
        assertEquals(heap.positions[v[2].vertex], 1);
        assertEquals(heap.positions[v[0].vertex], 2);
        assertEquals(heap.positions[v[1].vertex], 3);
        assertEquals(heap.positions[v[3].vertex], 4);
        assertEquals(heap.positions[v[5].vertex], 5);
        assertEquals(heap.lastFreePos, ++i);
        for (j = i; j < heap.heapNodes.length; j++) {
            assertNull(heap.heapNodes[j]);
            assertEquals(heap.positions[j], -1);
        }

        try {
            heap.insert(v[i]);  // 1 5 6 11 7 9 100
        } catch (Exception ex) {
            fail("Exception was not expected to be thrown.");
        }
        assertEquals(heap.heapNodes[0], v[4]);
        assertEquals(heap.heapNodes[1], v[2]);
        assertEquals(heap.heapNodes[2], v[0]);
        assertEquals(heap.heapNodes[3], v[1]);
        assertEquals(heap.heapNodes[4], v[3]);
        assertEquals(heap.heapNodes[5], v[5]);
        assertEquals(heap.heapNodes[6], v[6]);
        assertEquals(heap.positions[v[4].vertex], 0);
        assertEquals(heap.positions[v[2].vertex], 1);
        assertEquals(heap.positions[v[0].vertex], 2);
        assertEquals(heap.positions[v[1].vertex], 3);
        assertEquals(heap.positions[v[3].vertex], 4);
        assertEquals(heap.positions[v[5].vertex], 5);
        assertEquals(heap.positions[v[6].vertex], 6);
        assertEquals(heap.lastFreePos, ++i);
        for (j = i; j < heap.heapNodes.length; j++) {
            assertNull(heap.heapNodes[j]);
            assertEquals(heap.positions[j], -1);
        }

        try {
            heap.insert(v[i]);  // 1 4 6 5 7 9 100 11
        } catch (Exception ex) {
            fail("Exception was not expected to be thrown.");
        }
        assertEquals(heap.heapNodes[0], v[4]);
        assertEquals(heap.heapNodes[1], v[7]);
        assertEquals(heap.heapNodes[2], v[0]);
        assertEquals(heap.heapNodes[3], v[2]);
        assertEquals(heap.heapNodes[4], v[3]);
        assertEquals(heap.heapNodes[5], v[5]);
        assertEquals(heap.heapNodes[6], v[6]);
        assertEquals(heap.heapNodes[7], v[1]);
        assertEquals(heap.positions[v[4].vertex], 0);
        assertEquals(heap.positions[v[7].vertex], 1);
        assertEquals(heap.positions[v[0].vertex], 2);
        assertEquals(heap.positions[v[2].vertex], 3);
        assertEquals(heap.positions[v[3].vertex], 4);
        assertEquals(heap.positions[v[5].vertex], 5);
        assertEquals(heap.positions[v[6].vertex], 6);
        assertEquals(heap.positions[v[1].vertex], 7);
        assertEquals(heap.lastFreePos, ++i);
        for (j = i; j < heap.heapNodes.length; j++) {
            assertNull(heap.heapNodes[j]);
            assertEquals(heap.positions[j], -1);
        }

        try {
            heap.insert(v[i]);  // 1 4 6 5 7 9 100 11 80
        } catch (Exception ex) {
            fail("Exception was not expected to be thrown.");
        }
        assertEquals(heap.heapNodes[0], v[4]);
        assertEquals(heap.heapNodes[1], v[7]);
        assertEquals(heap.heapNodes[2], v[0]);
        assertEquals(heap.heapNodes[3], v[2]);
        assertEquals(heap.heapNodes[4], v[3]);
        assertEquals(heap.heapNodes[5], v[5]);
        assertEquals(heap.heapNodes[6], v[6]);
        assertEquals(heap.heapNodes[7], v[1]);
        assertEquals(heap.heapNodes[8], v[8]);
        assertEquals(heap.positions[v[4].vertex], 0);
        assertEquals(heap.positions[v[7].vertex], 1);
        assertEquals(heap.positions[v[0].vertex], 2);
        assertEquals(heap.positions[v[2].vertex], 3);
        assertEquals(heap.positions[v[3].vertex], 4);
        assertEquals(heap.positions[v[5].vertex], 5);
        assertEquals(heap.positions[v[6].vertex], 6);
        assertEquals(heap.positions[v[1].vertex], 7);
        assertEquals(heap.positions[v[8].vertex], 8);
        assertEquals(heap.lastFreePos, ++i);
        for (j = i; j < heap.heapNodes.length; j++) {
            assertNull(heap.heapNodes[j]);
            assertEquals(heap.positions[j], -1);
        }

        try {
            heap.insert(v[i]);  // 1 4 6 5 7 9 100 11 80 45
        } catch (Exception ex) {
            fail("Exception was not expected to be thrown.");
        }
        assertEquals(heap.heapNodes[0], v[4]);
        assertEquals(heap.heapNodes[1], v[7]);
        assertEquals(heap.heapNodes[2], v[0]);
        assertEquals(heap.heapNodes[3], v[2]);
        assertEquals(heap.heapNodes[4], v[3]);
        assertEquals(heap.heapNodes[5], v[5]);
        assertEquals(heap.heapNodes[6], v[6]);
        assertEquals(heap.heapNodes[7], v[1]);
        assertEquals(heap.heapNodes[8], v[8]);
        assertEquals(heap.heapNodes[9], v[9]);
        assertEquals(heap.positions[v[4].vertex], 0);
        assertEquals(heap.positions[v[7].vertex], 1);
        assertEquals(heap.positions[v[0].vertex], 2);
        assertEquals(heap.positions[v[2].vertex], 3);
        assertEquals(heap.positions[v[3].vertex], 4);
        assertEquals(heap.positions[v[5].vertex], 5);
        assertEquals(heap.positions[v[6].vertex], 6);
        assertEquals(heap.positions[v[1].vertex], 7);
        assertEquals(heap.positions[v[8].vertex], 8);
        assertEquals(heap.positions[v[9].vertex], 9);
        assertEquals(heap.lastFreePos, ++i);
        for (j = i; j < heap.heapNodes.length; j++) {
            assertNull(heap.heapNodes[j]);
            assertEquals(heap.positions[j], -1);
        }

        try {
            heap.insert(v[i]);  // 1 4 6 5 7 9 100 11 80 45 93
        } catch (Exception ex) {
            fail("Exception was not expected to be thrown.");
        }
        assertEquals(heap.heapNodes[0], v[4]);
        assertEquals(heap.heapNodes[1], v[7]);
        assertEquals(heap.heapNodes[2], v[0]);
        assertEquals(heap.heapNodes[3], v[2]);
        assertEquals(heap.heapNodes[4], v[3]);
        assertEquals(heap.heapNodes[5], v[5]);
        assertEquals(heap.heapNodes[6], v[6]);
        assertEquals(heap.heapNodes[7], v[1]);
        assertEquals(heap.heapNodes[8], v[8]);
        assertEquals(heap.heapNodes[9], v[9]);
        assertEquals(heap.heapNodes[10], v[10]);
        assertEquals(heap.positions[v[4].vertex], 0);
        assertEquals(heap.positions[v[7].vertex], 1);
        assertEquals(heap.positions[v[0].vertex], 2);
        assertEquals(heap.positions[v[2].vertex], 3);
        assertEquals(heap.positions[v[3].vertex], 4);
        assertEquals(heap.positions[v[5].vertex], 5);
        assertEquals(heap.positions[v[6].vertex], 6);
        assertEquals(heap.positions[v[1].vertex], 7);
        assertEquals(heap.positions[v[8].vertex], 8);
        assertEquals(heap.positions[v[9].vertex], 9);
        assertEquals(heap.positions[v[10].vertex], 10);
        assertEquals(heap.lastFreePos, ++i);
        for (j = i; j < heap.heapNodes.length; j++) {
            assertNull(heap.heapNodes[j]);
            assertEquals(heap.positions[j], -1);
        }

        try {
            heap.insert(v[i]);  // 1 4 2 5 7 6 100 11 80 45 93 9
        } catch (Exception ex) {
            fail("Exception was not expected to be thrown.");
        }
        assertEquals(heap.heapNodes[0], v[4]);
        assertEquals(heap.heapNodes[1], v[7]);
        assertEquals(heap.heapNodes[2], v[11]);
        assertEquals(heap.heapNodes[3], v[2]);
        assertEquals(heap.heapNodes[4], v[3]);
        assertEquals(heap.heapNodes[5], v[0]);
        assertEquals(heap.heapNodes[6], v[6]);
        assertEquals(heap.heapNodes[7], v[1]);
        assertEquals(heap.heapNodes[8], v[8]);
        assertEquals(heap.heapNodes[9], v[9]);
        assertEquals(heap.heapNodes[10], v[10]);
        assertEquals(heap.heapNodes[11], v[5]);
        assertEquals(heap.positions[v[4].vertex], 0);
        assertEquals(heap.positions[v[7].vertex], 1);
        assertEquals(heap.positions[v[11].vertex], 2);
        assertEquals(heap.positions[v[2].vertex], 3);
        assertEquals(heap.positions[v[3].vertex], 4);
        assertEquals(heap.positions[v[0].vertex], 5);
        assertEquals(heap.positions[v[6].vertex], 6);
        assertEquals(heap.positions[v[1].vertex], 7);
        assertEquals(heap.positions[v[8].vertex], 8);
        assertEquals(heap.positions[v[9].vertex], 9);
        assertEquals(heap.positions[v[10].vertex], 10);
        assertEquals(heap.positions[v[5].vertex], 11);
        assertEquals(heap.lastFreePos, ++i);
        for (j = i; j < heap.heapNodes.length; j++) {
            assertNull(heap.heapNodes[j]);
            assertEquals(heap.positions[j], -1);
        }

        try {
            heap.insert(v[i]);  // 1 4 2 5 7 6 100 11 80 45 93 9 32
        } catch (Exception ex) {
            fail("Exception was not expected to be thrown.");
        }
        assertEquals(heap.heapNodes[0], v[4]);
        assertEquals(heap.heapNodes[1], v[7]);
        assertEquals(heap.heapNodes[2], v[11]);
        assertEquals(heap.heapNodes[3], v[2]);
        assertEquals(heap.heapNodes[4], v[3]);
        assertEquals(heap.heapNodes[5], v[0]);
        assertEquals(heap.heapNodes[6], v[6]);
        assertEquals(heap.heapNodes[7], v[1]);
        assertEquals(heap.heapNodes[8], v[8]);
        assertEquals(heap.heapNodes[9], v[9]);
        assertEquals(heap.heapNodes[10], v[10]);
        assertEquals(heap.heapNodes[11], v[5]);
        assertEquals(heap.heapNodes[12], v[12]);
        assertEquals(heap.positions[v[4].vertex], 0);
        assertEquals(heap.positions[v[7].vertex], 1);
        assertEquals(heap.positions[v[11].vertex], 2);
        assertEquals(heap.positions[v[2].vertex], 3);
        assertEquals(heap.positions[v[3].vertex], 4);
        assertEquals(heap.positions[v[0].vertex], 5);
        assertEquals(heap.positions[v[6].vertex], 6);
        assertEquals(heap.positions[v[1].vertex], 7);
        assertEquals(heap.positions[v[8].vertex], 8);
        assertEquals(heap.positions[v[9].vertex], 9);
        assertEquals(heap.positions[v[10].vertex], 10);
        assertEquals(heap.positions[v[5].vertex], 11);
        assertEquals(heap.positions[v[12].vertex], 12);
        assertEquals(heap.lastFreePos, ++i);
        for (j = i; j < heap.heapNodes.length; j++) {
            assertNull(heap.heapNodes[j]);
            assertEquals(heap.positions[j], -1);
        }

        try {
            heap.insert(v[i]);  // 1 4 2 5 7 6 20 11 80 45 93 9 32 100
        } catch (Exception ex) {
            fail("Exception was not expected to be thrown.");
        }
        assertEquals(heap.heapNodes[0], v[4]);
        assertEquals(heap.heapNodes[1], v[7]);
        assertEquals(heap.heapNodes[2], v[11]);
        assertEquals(heap.heapNodes[3], v[2]);
        assertEquals(heap.heapNodes[4], v[3]);
        assertEquals(heap.heapNodes[5], v[0]);
        assertEquals(heap.heapNodes[6], v[13]);
        assertEquals(heap.heapNodes[7], v[1]);
        assertEquals(heap.heapNodes[8], v[8]);
        assertEquals(heap.heapNodes[9], v[9]);
        assertEquals(heap.heapNodes[10], v[10]);
        assertEquals(heap.heapNodes[11], v[5]);
        assertEquals(heap.heapNodes[12], v[12]);
        assertEquals(heap.heapNodes[13], v[6]);
        assertEquals(heap.positions[v[4].vertex], 0);
        assertEquals(heap.positions[v[7].vertex], 1);
        assertEquals(heap.positions[v[11].vertex], 2);
        assertEquals(heap.positions[v[2].vertex], 3);
        assertEquals(heap.positions[v[3].vertex], 4);
        assertEquals(heap.positions[v[0].vertex], 5);
        assertEquals(heap.positions[v[13].vertex], 6);
        assertEquals(heap.positions[v[1].vertex], 7);
        assertEquals(heap.positions[v[8].vertex], 8);
        assertEquals(heap.positions[v[9].vertex], 9);
        assertEquals(heap.positions[v[10].vertex], 10);
        assertEquals(heap.positions[v[5].vertex], 11);
        assertEquals(heap.positions[v[12].vertex], 12);
        assertEquals(heap.positions[v[6].vertex], 13);
        assertEquals(heap.lastFreePos, ++i);
        for (j = i; j < heap.heapNodes.length; j++) {
            assertNull(heap.heapNodes[j]);
            assertEquals(heap.positions[j], -1);
        }

        try {
            heap.insert(v[i]);  // 1 4 2 5 7 6 2 11 80 45 93 9 32 100 20
        } catch (Exception ex) {
            fail("Exception was not expected to be thrown.");
        }
        assertEquals(heap.heapNodes[0], v[4]);
        assertEquals(heap.heapNodes[1], v[7]);
        assertEquals(heap.heapNodes[2], v[11]);
        assertEquals(heap.heapNodes[3], v[2]);
        assertEquals(heap.heapNodes[4], v[3]);
        assertEquals(heap.heapNodes[5], v[0]);
        assertEquals(heap.heapNodes[6], v[14]);
        assertEquals(heap.heapNodes[7], v[1]);
        assertEquals(heap.heapNodes[8], v[8]);
        assertEquals(heap.heapNodes[9], v[9]);
        assertEquals(heap.heapNodes[10], v[10]);
        assertEquals(heap.heapNodes[11], v[5]);
        assertEquals(heap.heapNodes[12], v[12]);
        assertEquals(heap.heapNodes[13], v[6]);
        assertEquals(heap.heapNodes[14], v[13]);
        assertEquals(heap.positions[v[4].vertex], 0);
        assertEquals(heap.positions[v[7].vertex], 1);
        assertEquals(heap.positions[v[11].vertex], 2);
        assertEquals(heap.positions[v[2].vertex], 3);
        assertEquals(heap.positions[v[3].vertex], 4);
        assertEquals(heap.positions[v[0].vertex], 5);
        assertEquals(heap.positions[v[14].vertex], 6);
        assertEquals(heap.positions[v[1].vertex], 7);
        assertEquals(heap.positions[v[8].vertex], 8);
        assertEquals(heap.positions[v[9].vertex], 9);
        assertEquals(heap.positions[v[10].vertex], 10);
        assertEquals(heap.positions[v[5].vertex], 11);
        assertEquals(heap.positions[v[12].vertex], 12);
        assertEquals(heap.positions[v[6].vertex], 13);
        assertEquals(heap.positions[v[13].vertex], 14);
        assertEquals(heap.lastFreePos, ++i);

        try {
            heap.insert(new HeapNode(1, 2, 3));
            fail("Heap should be full.");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Heap overflow");
        }
    }

    /**
     * Test case for MinHeap.insert() with random elements.
     */
    @Test
    public void insertTest2() {
        int i, j, heapDimension, min = 201, x;
        HeapNode hNode = null, minHNode = null;
        Random randomGenerator = new Random();
        heapDimension = 100 + randomGenerator.nextInt(200); //[100, 200)
        MinHeap heap = new MinHeap(heapDimension);
        for (i = 0; i < heapDimension; i++) {
            x = 1 + randomGenerator.nextInt(200);
            hNode = new HeapNode(i, 0, x);
            if (x < min) {
                min = x;
                minHNode = hNode;
            }
            try {
                heap.insert(hNode);
            } catch (Exception ex) {
                fail("Exception was not expected to be thrown.");
            }
            assertEquals(heap.lastFreePos, i + 1);
            assertEquals(heap.heapNodes[0], minHNode);
            assertFalse(heap.positions[hNode.vertex] == -1);
            for (j = 0; j < heap.lastFreePos; j++) {
                assertEquals(j, heap.positions[heap.heapNodes[j].vertex]);

                if ((j + 1) * 2 - 1 < heap.lastFreePos) {
                    assertTrue(heap.heapNodes[j].compareTo(heap.heapNodes[(j + 1) * 2 - 1]) <= 0);
                } else {
                    continue;
                }
                if ((j + 1) * 2 < heap.lastFreePos) {
                    assertTrue(heap.heapNodes[j].compareTo(heap.heapNodes[(j + 1) * 2]) <= 0);
                }
            }
        }

        try {
            heap.insert(new HeapNode(1, 2, 3));
            fail("Heap should be full.");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Heap overflow");
        }
    }

    /**
     * Test case for MinHeap.extractMin();
     */
    @Test
    public void extractMinTest() {
        int i, j, heapDimension;
        HeapNode minHNode = null;
        Random randomGenerator = new Random();
        heapDimension = 100 + randomGenerator.nextInt(200);
        MinHeap heap = new MinHeap(heapDimension);

        try {
            heap.extractMin();
            fail("Heap should be empty.");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Empty heap");
        }

        for (i = 0; i < heapDimension; i++) {
            try {
                heap.insert(new HeapNode(i, 0, 1 + randomGenerator.nextInt(200)));
            } catch (Exception ex) {
                fail("Exception was not expected to be thrown.");
            }
        }

        for (i = 0; i < heapDimension; i++) {
            try {
                minHNode = heap.extractMin();
            } catch (Exception ex) {
                fail("Exception was not expected to be thrown.");
            }

            assertEquals(heap.lastFreePos, heapDimension - i - 1);
            assertTrue(heap.positions[minHNode.vertex] == -1);

            for (j = 0; j < heap.lastFreePos; j++) {
                assertEquals(j, heap.positions[heap.heapNodes[j].vertex]);

                if ((j + 1) * 2 - 1 < heap.lastFreePos) {
                    assertTrue(heap.heapNodes[j].compareTo(heap.heapNodes[(j + 1) * 2 - 1]) <= 0);
                } else {
                    continue;
                }
                if ((j + 1) * 2 < heap.lastFreePos) {
                    assertTrue(heap.heapNodes[j].compareTo(heap.heapNodes[(j + 1) * 2]) <= 0);
                }
            }
        }
    }

    /**
     * Test case for MinHeap.delete();
     */
    @Test
    public void deleteTest() {
        int i, j, heapDimension, pos, lastFreePos;
        HeapNode hNode = null;
        Random randomGenerator = new Random();
        heapDimension = 100 + randomGenerator.nextInt(200);
        MinHeap heap = new MinHeap(heapDimension);

        try {
            heap.delete(0);
            fail("Heap should be empty.");
        } catch (Exception ex) {
            assertEquals(ex.getMessage(), "Invalid position");
        }

        for (i = 0; i < heapDimension; i++) {
            try {
                heap.insert(new HeapNode(i, 0, 1 + randomGenerator.nextInt(200)));
            } catch (Exception ex) {
                fail("Exception was not expected to be thrown.");
            }
        }

        for (i = 0; i < heapDimension; i++) {
            pos = randomGenerator.nextInt(heapDimension);
            lastFreePos = heap.lastFreePos;

            if (pos >= heap.lastFreePos) {
                try {
                    heap.delete(pos);
                    fail("Exception was not expected to be thrown.");
                } catch (Exception ex) {
                    assertEquals(ex.getMessage(), "Invalid position");
                }
            } else {
                try {
                    hNode = heap.delete(pos);
                } catch (Exception ex) {
                    fail("Exception was not expected to be thrown.");
                }
                assertTrue(heap.positions[hNode.vertex] == -1);
                assertEquals(heap.lastFreePos, lastFreePos - 1);

                for (j = pos; j < heap.lastFreePos; j++) {
                    assertEquals(j, heap.positions[heap.heapNodes[j].vertex]);

                    if ((j + 1) * 2 - 1 < heap.lastFreePos) {
                        assertTrue(heap.heapNodes[j].compareTo(heap.heapNodes[(j + 1) * 2 - 1]) <= 0);
                    } else {
                        continue;
                    }
                    if ((j + 1) * 2 < heap.lastFreePos) {
                        assertTrue(heap.heapNodes[j].compareTo(heap.heapNodes[(j + 1) * 2]) <= 0);
                    }
                }
            }
        }
    }

    /**
     * Test min heap property keeps for multiple inserts, extract min, deletes.
     */
    @Test
    public void allTest() {
        int i, j, heapDimension, pos, lastFreePos;
        HeapNode hNode = null;
        Random randomGenerator = new Random();
        heapDimension = 100 + randomGenerator.nextInt(200);
        MinHeap heap = new MinHeap(heapDimension);
        int operation, randPosToDelete;

        for (i = 0; i < heapDimension; i++) { // do randomly inserts or deletes or extracts min
            operation = randomGenerator.nextInt(3);
            switch (operation) {
                case 0: // insert
                    hNode = new HeapNode(i, 0, randomGenerator.nextInt(100));
                    try {
                        heap.insert(hNode);
                    } catch (Exception ex) {
                        fail("Exception was not expected to be thrown.");
                    }
                    for (j = 0; j < (heap.lastFreePos + 1) / 2 - 1; j++) {
                        assertTrue(heap.heapNodes[j].compareTo(heap.heapNodes[(j + 1) * 2 - 1]) <= 0);
                        if ((j + 1) * 2 < heap.lastFreePos) {
                            assertTrue(heap.heapNodes[j].compareTo(heap.heapNodes[(j + 1) * 2]) <= 0);
                        }
                    }
                    break;
                case 1: // extract min
                    if (heap.lastFreePos == 0) {
                        try {
                            heap.extractMin();
                            fail("Heap should be empty.");
                        } catch (Exception ex) {
                            assertEquals(ex.getMessage(), "Empty heap");
                        }
                    } else {
                        try {
                            heap.extractMin();
                        } catch (Exception ex) {
                            fail("Exception was not expected to be thrown.");
                        }
                        for (j = 0; j < (heap.lastFreePos + 1) / 2 - 1; j++) {
                            assertTrue(heap.heapNodes[j].compareTo(heap.heapNodes[(j + 1) * 2 - 1]) <= 0);
                            if ((j + 1) * 2 < heap.lastFreePos) {
                                assertTrue(heap.heapNodes[j].compareTo(heap.heapNodes[(j + 1) * 2]) <= 0);
                            }
                        }
                    }
                    break;
                default: // delete
                    randPosToDelete = randomGenerator.nextInt(heapDimension);
                    lastFreePos = heap.lastFreePos;

                    if (randPosToDelete >= heap.lastFreePos) {
                        try {
                            heap.delete(randPosToDelete);
                            fail("Exception was not expected to be thrown.");
                        } catch (Exception ex) {
                            assertEquals(ex.getMessage(), "Invalid position");
                        }
                    } else {
                        try {
                            heap.delete(randPosToDelete);
                        } catch (Exception ex) {
                            fail("Exception was not expected to be thrown.");
                        }
                        for (j = 0; j < (heap.lastFreePos + 1) / 2 - 1; j++) {
                            assertTrue(heap.heapNodes[j].compareTo(heap.heapNodes[(j + 1) * 2 - 1]) <= 0);
                            if ((j + 1) * 2 < heap.lastFreePos) {
                                assertTrue(heap.heapNodes[j].compareTo(heap.heapNodes[(j + 1) * 2]) <= 0);
                            }
                        }
                    }
            }
        }
    }
}
