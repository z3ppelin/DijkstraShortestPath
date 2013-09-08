/**
 * Implementation of Dijkstra 's Shortest Path Algorithm
 * in O(n + log m), n = number of vertices, m = number of edges.
 *
 * @author      Bogdan Constantinescu <bog_con@yahoo.com>
 * @since       2013.08.04
 * @version     1.0
 * @link        GitHub    https://github.com/z3ppelin/DijkstraShortestPath
 * @licence     The MIT License (http://opensource.org/licenses/MIT); see LICENCE.txt
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>
#include "DijkstraShortestPath.h"

/**
 * Main function. Reads graph, calculates shortest path from a starting
 * vertex to all other vertices and prints the result.
 * @param     argc       Command line arguments no.
 * @param     argv       Command line arguments.
 * @return               Success/error code. (0 is a success, anything else is error).
 */
int main(int argc, char** argv) {
	printf("------ Begin Dijkstra 's Shortes Path ------\n");

	int i, startNode, countVisisted = 0;
	int *visited = NULL, *shortestPaths = NULL;
	Node *node = NULL;
	Graph graph;
	MinHeap heap;
	HeapNode *heapNode = NULL, *heapNode2;
	clock_t start, end;
	char error[128];
	double readTime = 0.00, algoTime = 0.00;

	/* read directed graph, initialize variables */
	start = clock();
	if (argc == 1) {
		err("Err. The input file must be given as an argument.\n");
	}
	if (!readGraphFromFile(&graph, &startNode, argv[1], error)) {
		err(error);
	}
	visited = (int*) malloc(sizeof(int) * graph.n);
	shortestPaths = (int*) malloc(sizeof(int) * graph.n);
	heap.lastFreePos = 0;
	heap.heapDimension = graph.n;
	heap.heapNodes = (HeapNode**) malloc(sizeof(HeapNode*) * graph.n);
	heap.positions = (int*) malloc(sizeof(int) * graph.n);
	for (i = 0; i < graph.n; i++) {
		visited[i] = 0;
		shortestPaths[i] = DEFAULT_MIN_PATH;

		heapNode = (HeapNode*) malloc(sizeof(HeapNode));
		heapNode->vertex = i;
		heapNode->startVertex = startNode;
		if (startNode == i) {
			heapNode->shortestPath = 0;
		} else {
			heapNode->shortestPath = DEFAULT_MIN_PATH;
		}
		insertHeap(&heap, heapNode);
	}
	end = clock();
	readTime = (double) (end - start) / CLOCKS_PER_SEC;

	/* print read graphs */
	//printf("The read graph:\n");
	//printGraph(graph);
	//printf("\n");
	
	/* start Dijkstra 's algorithm */
	start = clock();
	for (i = 0; i < graph.n; i++) {
		heapNode = extractMin(&heap);
		visited[heapNode->vertex] = 1;
		shortestPaths[heapNode->vertex] = heapNode->shortestPath;

		node = graph.edges[heapNode->vertex];
		while (NULL != node) {
			if (!visited[node->vertex]) {
				if (heap.heapNodes[heap.positions[node->vertex]]->shortestPath
						> shortestPaths[heapNode->vertex] + node->weight) {
					heapNode2 = deleteHeap(&heap, heap.positions[node->vertex]);
					heapNode2->shortestPath = shortestPaths[heapNode->vertex] + node->weight;
					insertHeap(&heap, heapNode2);
				}
			}
			node = node->next;
		}
	}
	end = clock();
	algoTime = (double) (end - start) / CLOCKS_PER_SEC;

	/* print results */
	for (i = 0; i < graph.n; i++) {
		printf("Shortest path from %d to %d is: %d\n", startNode + 1, i + 1, shortestPaths[i]);
	}
	printf("\n");
	printf("Elapsed: %f seconds with initializations, reading graph.\n", readTime);
	printf("Elapsed: %f seconds to calculate shortest paths.\n", algoTime);

	/* free left allocated memory */
	free(visited);
	free(shortestPaths);
	freeGraph(&graph);
	freeMinHeap(&heap);

	printf("------- End Dijkstra 's Shortest Path -------\n");
	return EXIT_SUCCESS;
}

/**
 * Prints graph.
 * @param   graph   The graph to print.
 */
void printGraph(const Graph graph) {
	printf("Graph has %d vertices and %d arc(s).\n", graph.n, graph.m);
	Node* node;
	int i;
	for (i = 0; i < graph.n; i++) {
		printf("Vertex %d has arc(s) with: ", i + 1);
		node = graph.edges[i];
		if (NULL == node) {
			printf("nobody");
		} else {
			while (NULL != node) {
				printf("%d(%d) ", node->vertex + 1, node->weight);
				node = node->next;
			}
		}
		printf("\n");
	}
}

/**
 * Prints error and exits program.
 * @param    msg    The error to print.
 */
void err(const char* msg) {
	printf(msg);
	printf("------- End Dijkstra 's Shortest Path -------\n");
	exit(EXIT_FAILURE);
}

/**
 * Inserts a new element in a heap maintaining the min-heap property.
 * @param		heap			The min heap.
 * @param		hn  			The new element to insert.
 */
void insertHeap(MinHeap* heap, HeapNode* hn) {
	if (heap->lastFreePos == heap->heapDimension) {
		err("Heap overflow");
	}
	heap->heapNodes[heap->lastFreePos] = hn;
	heap->positions[hn->vertex] = heap->lastFreePos;
	int childPos = heap->lastFreePos, parentPos = (childPos - 1) / 2;
	HeapNode* aux;
	while (parentPos >= 0
           && heap->heapNodes[parentPos]->shortestPath > heap->heapNodes[childPos]->shortestPath) {
		aux = heap->heapNodes[parentPos];
		heap->heapNodes[parentPos] = heap->heapNodes[childPos];
		heap->heapNodes[childPos] = aux;
		heap->positions[heap->heapNodes[childPos]->vertex] = childPos;
		heap->positions[heap->heapNodes[parentPos]->vertex] = parentPos;
		childPos = parentPos;
		parentPos = (childPos - 1) / 2;
	}
	heap->lastFreePos++;
}

/**
 * Extract the element with the smalles key in a heap and restores min-heap property.
 * @param	heap		The min heap.
 * @return	            The element with the smallest key in the heap.
 */
HeapNode* extractMin(MinHeap* heap) {
	if (heap->lastFreePos == 0) {
		err("Empty heap");
	}

	// It 's basicly a deletion from the first position without needing to bubble up
	HeapNode* hn = heap->heapNodes[0], *aux;
	heap->heapNodes[0] = heap->heapNodes[heap->lastFreePos - 1];
	heap->positions[heap->heapNodes[0]->vertex] = 0;
	heap->heapNodes[heap->lastFreePos - 1] = NULL;
	heap->positions[hn->vertex] = -1;
	heap->lastFreePos--;

	/* bubble down */
	int parentPos = 0, leftChildPos = (parentPos + 1) * 2 - 1, rightChildPos = leftChildPos + 1, minChildPos;
	while ((leftChildPos < heap->lastFreePos 
            && heap->heapNodes[parentPos]->shortestPath > heap->heapNodes[leftChildPos]->shortestPath)
	       || (rightChildPos < heap->lastFreePos
               && heap->heapNodes[parentPos]->shortestPath > heap->heapNodes[rightChildPos]->shortestPath)) {
		minChildPos = leftChildPos;
		if (rightChildPos < heap->lastFreePos
            && heap->heapNodes[leftChildPos]->shortestPath > heap->heapNodes[rightChildPos]->shortestPath) {
			minChildPos = rightChildPos;
		}
		aux = heap->heapNodes[parentPos];
		heap->heapNodes[parentPos] = heap->heapNodes[minChildPos];
		heap->heapNodes[minChildPos] = aux;
		heap->positions[heap->heapNodes[minChildPos]->vertex] = minChildPos;
		heap->positions[heap->heapNodes[parentPos]->vertex] = parentPos;
		parentPos = minChildPos;
		leftChildPos = (parentPos + 1) * 2 - 1;
		rightChildPos = leftChildPos + 1;
	}
	return hn;
}

/**
 * Deletes an element in a heap and restores min-heap property.
 * @param	heap	The min heap.
 * @param	pos		The element 's position in the heap.
 * @return          The deleted element.
 */
HeapNode* deleteHeap(MinHeap* heap, const int pos) {
	if (pos < 0 || pos >= heap->lastFreePos) {
		err("Invalid position");
	}
	HeapNode* hn = heap->heapNodes[pos], *aux;
	heap->heapNodes[pos] = heap->heapNodes[heap->lastFreePos - 1];
	heap->positions[heap->heapNodes[pos]->vertex] = pos;
	heap->heapNodes[heap->lastFreePos - 1] = NULL;
	heap->positions[hn->vertex] = -1;
	heap->lastFreePos--;

	int parentPos = (pos - 1) / 2, childPos = pos;
	if (parentPos >= 0
        && NULL != heap->heapNodes[childPos]
        && heap->heapNodes[parentPos]->shortestPath > heap->heapNodes[childPos]->shortestPath) { // bubble up
		while (parentPos >= 0
               && heap->heapNodes[parentPos]->shortestPath > heap->heapNodes[childPos]->shortestPath) {
			aux = heap->heapNodes[parentPos];
			heap->heapNodes[parentPos] = heap->heapNodes[childPos];
			heap->heapNodes[childPos] = aux;
			heap->positions[heap->heapNodes[childPos]->vertex] = childPos;
			heap->positions[heap->heapNodes[parentPos]->vertex] = parentPos;
			childPos = parentPos;
			parentPos = (childPos - 1) / 2;
		}
	} else { // bubble down
		parentPos = pos;
		int leftChildPos = (parentPos + 1) * 2 - 1, rightChildPos = leftChildPos + 1, minChildPos;
		while ((leftChildPos < heap->lastFreePos
                && heap->heapNodes[parentPos]->shortestPath > heap->heapNodes[leftChildPos]->shortestPath)
		        || (rightChildPos < heap->lastFreePos
                    && heap->heapNodes[parentPos]->shortestPath > heap->heapNodes[rightChildPos]->shortestPath)) {
			minChildPos = leftChildPos;
			if (rightChildPos < heap->lastFreePos
                && heap->heapNodes[leftChildPos]->shortestPath > heap->heapNodes[rightChildPos]->shortestPath) {
				minChildPos = rightChildPos;
			}
			aux = heap->heapNodes[parentPos];
			heap->heapNodes[parentPos] = heap->heapNodes[minChildPos];
			heap->heapNodes[minChildPos] = aux;
			heap->positions[heap->heapNodes[minChildPos]->vertex] = minChildPos;
			heap->positions[heap->heapNodes[parentPos]->vertex] = parentPos;
			parentPos = minChildPos;
			leftChildPos = (parentPos + 1) * 2 - 1;
			rightChildPos = leftChildPos + 1;
		}
	}
	return hn;
}

/**
 * Reads graph from file.
 * @param      file      The file where to read graph from.
 * @param      err       An error message, if any occcurred during reading.
 * @return               1 if everything went fine, 0 otherwise.
 */
int readGraphFromFile(Graph* graph, int* startVertex, char* file, char* err) {
	FILE *inputFile = fopen(file, "rt");
	int n, m = 0, i, tailVertex, headVertex, weight;
	Node** edges;
	char buffer[10000];
	char *pbuff = NULL;
	Node *node;
	if (NULL == inputFile) {
		strcpy(err, "Err. Could not open file.\n");
		return 0;
	}
	if (fscanf(inputFile, "%d", &n) != 1) {
		fclose(inputFile);
		strcpy(err, "Err. Could not read number of vertices the graph has.\n");
		return 0;
	}
	if (fscanf(inputFile, "%d", startVertex) != 1) {
		fclose(inputFile);
		strcpy(err, "Err. Could not read the start vertex.\n");
		return 0;
	}
	*startVertex -= 1;
	edges = (Node**) malloc(sizeof(Node*) * n);
	for (i = 0; i < n; i++) {
		edges[i] = NULL;
	}

	while (NULL != fgets(buffer, sizeof buffer, inputFile)) {
		pbuff = strtok(buffer, " \t"); //split line after spaces
		i = 0;
		while (NULL != pbuff) {
			if (!i) { //first element on the line is the tail vertex
				tailVertex = atoi(pbuff);
				if (tailVertex < 1) {
					break;
				}
				tailVertex--;
				i = 1;
			} else {
				sscanf(pbuff, "%d,%d", &headVertex, &weight); //other elements on the line are head vertices and their weight
				if (headVertex < 1 && weight < 1) {
					break;
				}
				headVertex--;
				node = (Node*) malloc(sizeof(Node));
				node->vertex = headVertex;
				node->weight = weight;
				if (NULL == edges[tailVertex]) {
					node->next = NULL;
				} else {
					node->next = edges[tailVertex];
				}
				edges[tailVertex] = node;
				m++;
			}
			pbuff = strtok(NULL, " \t");
		}
	}
	fclose(inputFile);
	free(pbuff);

	graph->n = n;
	graph->m = m;
	graph->edges = edges;
	return 1;
}

/**
 * Frees memory occupied by pointer members of a graph.
 * @param   graph    The graph to free pointer members.
 */
void freeGraph(Graph* graph) {
	Node *node1, *node2;
	int i;
	for (i = 0; i < graph->n; i++) {
		node1 = graph->edges[i];
		while (NULL != node1) {
			node2 = node1;
			node1 = node1->next;
			free(node2);
		}
	}
	free(graph->edges);
}

/**
 * Frees memory occupied by pointer members of a min-heap.
 * @param   graph    The min-heap to free pointer members.
 */
void freeMinHeap(MinHeap* heap) {
	int i;
	for (i = 0; i < heap->heapDimension; i++) {
		free(heap->heapNodes[i]);
	}
	free(heap->heapNodes);
	free(heap->positions);
}
