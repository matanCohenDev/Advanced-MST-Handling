import java.util.*;

public class PrimAlgorithm {

  public Graph prim(Graph graph, Vertex start) {
    Graph mstGraph = new Graph();
    PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

    start.setVisited(true);
    mstGraph.addVertex(start);
    minHeap.addAll(graph.getEdgesFromVertex(start));

    while (!minHeap.isEmpty()) {
      Edge minEdge = minHeap.poll();
      Vertex sourceVertex = minEdge.getSource();
      Vertex destVertex = minEdge.getDest();

      if (!destVertex.isVisited()) {
        destVertex.setVisited(true);
        destVertex.setParent(sourceVertex);
        mstGraph.addVertex(destVertex);
        mstGraph.addEdge(minEdge);
        minHeap.addAll(graph.getEdgesFromVertex(destVertex));
      }
      if (!sourceVertex.isVisited()) {
        sourceVertex.setVisited(true);
        sourceVertex.setParent(destVertex);
        mstGraph.addVertex(sourceVertex);
        mstGraph.addEdge(minEdge);
        minHeap.addAll(graph.getEdgesFromVertex(sourceVertex));
      }
    }
    return mstGraph;
  }

  static boolean isEdgeAffectsMST(Graph mst, Edge newEdge) {
    for (Edge edge : mst.getEdges()) {
      if (newEdge.getWeight() < edge.getWeight()) {
        return true;
      }
    }
    return false;
  }

  public boolean AddEdgeToMst(Graph mst, Edge newEdge) {
    if (isEdgeAffectsMST(mst, newEdge)) {
      mst.addEdge(newEdge);
      Stack<Vertex> CycleVertices = new Stack<>();
      Stack<Edge> CycleEdges = new Stack<>();
      Vertex v;
      boolean flag = false;
      CycleVertices.push(newEdge.getDest());
      do {
        v = CycleVertices.peek().getParent();
        if (v == null) {
          flag = true;
          break;
        }
        for (Edge e : mst.getEdges()) {
          if (e.getDest() == v && e.getSource() == CycleVertices.peek() || e.getSource() == v && e.getDest() == CycleVertices.peek()) {
            CycleEdges.push(e);
            break;
          }
        }
        CycleVertices.push(v);
      }
      while (CycleVertices.peek() != newEdge.getSource().getParent());
      if (flag) {
        while (!CycleVertices.isEmpty())
          CycleVertices.pop();
        while (!CycleEdges.isEmpty())
          CycleEdges.pop();

        CycleVertices.push(newEdge.getSource());
        do {
          v = CycleVertices.peek().getParent();
          if (v == null)
            break;
          for (Edge e : mst.getEdges()) {
            if (e.getDest() == v && e.getSource() == CycleVertices.peek() || e.getSource() == v && e.getDest() == CycleVertices.peek()) {
              CycleEdges.push(e);
              break;
            }
          }
          CycleVertices.push(v);
        }
        while (CycleVertices.peek() != newEdge.getDest().getParent());
      }
      Edge maxEdge = newEdge;
      int max = newEdge.getWeight();
      while (!CycleEdges.isEmpty()) {
        Edge e = CycleEdges.pop();
        if (e.getWeight() > max) {
          max = e.getWeight();
          maxEdge = e;
        }
      }
      mst.remove(maxEdge);
      if(maxEdge == newEdge)
        return false;
      return true;
    }
    return false;
  }
}
