import java.util.*;

public class Main {
  public static void main(String[] args) {
    Graph graph = generateRandomGraph(20, 50);
    if(graph == null){
      return;
    }
    System.out.println("Original Graph:");
    printGraph(graph);

    PrimAlgorithm primAlgorithm = new PrimAlgorithm();
    Vertex startVertex = graph.getVertices().getFirst();
    Graph mst = primAlgorithm.prim(graph, startVertex);

    System.out.println("\nMinimum Spanning Tree (MST):");
    for (Edge edge : mst.getEdges()) {
      System.out.println("V" + edge.getSource().getId() + " <----" + edge.getWeight() + "----> V" + edge.getDest().getId());
    }

    Edge newEdge = generateRandomEdge(graph);

    System.out.println("\nNew Edge: V" + newEdge.getSource().getId() + " <----" + newEdge.getWeight() + "----> V" + newEdge.getDest().getId() + "\n");

    primAlgorithm.AddEdgeToMst(mst, newEdge);
    for (Edge edge : mst.getEdges())
      System.out.println("V" + edge.getSource().getId() + " <----" + edge.getWeight() + "----> V" + edge.getDest().getId());
    if (primAlgorithm.AddEdgeToMst(mst, newEdge))
      System.out.println("\nUpdated Minimum Spanning Tree (MST):");
    else
      System.out.println("\nFailed to add the new edge to MST.");
    System.out.println("done");
  }

  private static Graph generateRandomGraph(int numVertices, int numEdges) {
    if(numVertices <= numEdges + 1) {
      Graph graph = new Graph();
      Random random = new Random();

      for (int i = 1; i <= numVertices; i++) {
        Vertex v = new Vertex(i);
        graph.addVertex(v);
      }

      Set<String> createdEdges = new HashSet<>();
      while (graph.getEdges().size() < numEdges) {
        int sourceIndex = random.nextInt(numVertices);
        int destIndex = random.nextInt(numVertices);
        if (sourceIndex != destIndex) {
          Vertex sourceVertex = graph.getVertices().get(sourceIndex);
          Vertex destVertex = graph.getVertices().get(destIndex);
          String edgeKey = sourceVertex.getId() + "-" + destVertex.getId();
          String reverseEdgeKey = destVertex.getId() + "-" + sourceVertex.getId();
          if (!createdEdges.contains(edgeKey) && !createdEdges.contains(reverseEdgeKey)) {
            int weight = random.nextInt(20) + 1;
            Edge edge = new Edge(sourceVertex, destVertex, weight);
            graph.addEdge(edge);
            createdEdges.add(edgeKey);
            createdEdges.add(reverseEdgeKey);
          }
        }
      }
      return graph;
    }
    else{
      System.out.println("The number of edges must be greater than the number of vertices - 1");
      return null;
    }
  }

  private static Edge generateRandomEdge(Graph graph) {
    Random random = new Random();
    Vertex source;
    Vertex dest;
    int weight;

    do {
      int sourceIndex = random.nextInt(graph.getVertices().size());
      int destIndex = random.nextInt(graph.getVertices().size());
      source = graph.getVertices().get(sourceIndex);
      dest = graph.getVertices().get(destIndex);
      weight = random.nextInt(20) + 1;
    } while (source == dest || edgeExists(graph, source, dest));

    return new Edge(source, dest, weight);
  }

  private static boolean edgeExists(Graph graph, Vertex source, Vertex dest) {
    for (Edge edge : graph.getEdges()) {
      if ((edge.getSource() == source && edge.getDest() == dest) ||
        (edge.getSource() == dest && edge.getDest() == source)) {
        return true;
      }
    }
    return false;
  }

  private static void printGraph(Graph graph) {
    for (Edge edge : graph.getEdges()) {
      System.out.println("V" + edge.getSource().getId() + " <----" + edge.getWeight() + "----> V" + edge.getDest().getId());
    }
  }
}

