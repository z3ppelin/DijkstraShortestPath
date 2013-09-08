/**
 * Header file for Dijkstra 's Shortest Path Algorithm.
 * 
 * @author      Bogdan Constantinescu <bog_con@yahoo.com>
 * @since       2013.07.05
 * @version     1.0
 * @link        GitHub  https://github.com/z3ppelin/DijkstraShortestPath
 * @licence     The MIT License (http://opensource.org/licenses/MIT); see LICENCE.txt
 */
#ifndef _DIJKSTRA_SHORTEST_PATH_H_
#define _DIJKSTRA_SHORTEST_PATH_H_

#define	DEFAULT_MIN_PATH 1000000;

struct Node {
	int vertex;
	int weight;
	struct Node* next;
};
typedef struct Node Node;

struct Graph {
	int n;
	int m;
	struct Node** edges;
};
typedef struct Graph Graph;

struct HeapNode {
	int vertex;
	int startVertex;
	int shortestPath;
};
typedef struct HeapNode HeapNode;

struct MinHeap {
	int lastFreePos; // last free position in heap
	HeapNode** heapNodes; // heap 's elements
	int* positions; // key is the vertex, value is position in heapNodes
	int heapDimension; // number of elements
};
typedef struct MinHeap MinHeap;

/* function prototypes */
void insertHeap(MinHeap*, HeapNode*);
HeapNode* deleteHeap(MinHeap*, const int);
HeapNode* extractMin(MinHeap*);
void err(const char*);
int readGraphFromFile(Graph*, int*, char*, char*);
void printGraph(const Graph);
void freeGraph(Graph*);
void freeMinHeap(MinHeap* heap);

#endif /* _DIJKSTRA_SHORTEST_PATH_H_ */
