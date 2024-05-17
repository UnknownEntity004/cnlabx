import java.util.*;

class AODV {
    static class Node {
        int id;
        List<Edge> neighbors;

        Node(int id) {
            this.id = id;
            this.neighbors = new ArrayList<>();
        }

        void addNeighbor(Edge edge) {
            neighbors.add(edge);
        }
    }

    static class Edge {
        Node destination;
        int weight;

        Edge(Node destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class AODVNetwork {
        Map<Integer, Node> nodes;

        AODVNetwork() {
            nodes = new HashMap<>();
        }

        void addNode(int id) {
            nodes.put(id, new Node(id));
        }

        void addEdge(int sourceId, int destinationId, int weight) {
            Node sourceNode = nodes.get(sourceId);
            Node destinationNode = nodes.get(destinationId);
            if (sourceNode != null && destinationNode != null) {
                sourceNode.addNeighbor(new Edge(destinationNode, weight));
                destinationNode.addNeighbor(new Edge(sourceNode, weight)); // Assuming bidirectional edges
            }
        }

        void findRoute(int sourceId, int destinationId) {
            Node source = nodes.get(sourceId);
            Node destination = nodes.get(destinationId);
            if (source == null || destination == null) {
                System.out.println("Source or Destination node not found");
                return;
            }

            Map<Node, Integer> distances = new HashMap<>();
            Map<Node, Node> previousNodes = new HashMap<>();
            PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparing(distances::get));

            distances.put(source, 0);
            queue.add(source);

            for (Node node : nodes.values()) {
                if (node != source) {
                    distances.put(node, Integer.MAX_VALUE);
                }
                queue.add(node);
            }

            while (!queue.isEmpty()) {
                Node current = queue.poll();

                if (current == destination) {
                    break;
                }

                for (Edge edge : current.neighbors) {
                    int newDist = distances.get(current) + edge.weight;
                    if (newDist < distances.get(edge.destination)) {
                        distances.put(edge.destination, newDist);
                        previousNodes.put(edge.destination, current);
                        queue.add(edge.destination);
                    }
                }
            }

            printRoute(destination, previousNodes);
        }

        private void printRoute(Node destination, Map<Node, Node> previousNodes) {
            List<Node> path = new ArrayList<>();
            for (Node at = destination; at != null; at = previousNodes.get(at)) {
                path.add(at);
            }
            Collections.reverse(path);

            System.out.println("Route: ");
            for (Node node : path) {
                System.out.print(node.id + " ");
            }
        }
    }

    public static void main(String[] args) {
        AODVNetwork network = new AODVNetwork();

        // Add nodes
        network.addNode(0);
        network.addNode(1);
        network.addNode(2);
        network.addNode(3);
        network.addNode(4);
        network.addNode(5);

        // Add edges
        network.addEdge(0, 1, 2);
        network.addEdge(0, 2, 4);
        network.addEdge(1, 2, 1);
        network.addEdge(1, 3, 7);
        network.addEdge(2, 4, 3);
        network.addEdge(3, 5, 1);
        network.addEdge(4, 5, 5);

        // Find route from node 0 to node 5
        network.findRoute(0, 5);
    }
}