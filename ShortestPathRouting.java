import java.util.*;

class ShortestPathRouting {

    static class Graph {
        private final int vertices;
        private final int[][] adjacencyMatrix;

        Graph(int vertices) {
            this.vertices = vertices;
            adjacencyMatrix = new int[vertices][vertices];
        }

        void addEdge(int source, int destination, int weight) {
            adjacencyMatrix[source][destination] = weight;
            adjacencyMatrix[destination][source] = weight; // Assuming undirected graph
        }

        void dijkstra(int startVertex) {
            int[] shortestDistances = new int[vertices];
            boolean[] added = new boolean[vertices];

            Arrays.fill(shortestDistances, Integer.MAX_VALUE);
            Arrays.fill(added, false);

            shortestDistances[startVertex] = 0;

            int[] parents = new int[vertices];
            parents[startVertex] = -1;

            for (int i = 1; i < vertices; i++) {
                int nearestVertex = -1;
                int shortestDistance = Integer.MAX_VALUE;

                for (int vertexIndex = 0; vertexIndex < vertices; vertexIndex++) {
                    if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) {
                        nearestVertex = vertexIndex;
                        shortestDistance = shortestDistances[vertexIndex];
                    }
                }

                added[nearestVertex] = true;

                for (int vertexIndex = 0; vertexIndex < vertices; vertexIndex++) {
                    int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex];

                    if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) {
                        parents[vertexIndex] = nearestVertex;
                        shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
                    }
                }
            }

            printSolution(startVertex, shortestDistances, parents);
        }

        private void printSolution(int startVertex, int[] distances, int[] parents) {
            System.out.println("Vertex\t Distance\tPath");

            for (int vertexIndex = 0; vertexIndex < vertices; vertexIndex++) {
                if (vertexIndex != startVertex) {
                    System.out.print("\n" + startVertex + " -> ");
                    System.out.print(vertexIndex + " \t\t ");
                    System.out.print(distances[vertexIndex] + "\t\t");
                    printPath(vertexIndex, parents);
                }
            }
        }

        private void printPath(int currentVertex, int[] parents) {
            if (currentVertex == -1) {
                return;
            }
            printPath(parents[currentVertex], parents);
            System.out.print(currentVertex + " ");
        }
    }

    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);
        
        graph.dijkstra(0);
    }
}