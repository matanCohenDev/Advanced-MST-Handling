import java.util.*;

public class Graph {
    private final List<Vertex> vertices;
    private final List<Edge> edges;

    public Graph() {
      vertices = new ArrayList<>();
      edges = new ArrayList<>();
    }

    public List<Vertex> getVertices() {
      return vertices;
    }

    public void addVertex(Vertex v) {
      vertices.add(v);
    }

    public List<Edge> getEdges() {
      return edges;
    }

    public void addEdge(Edge edge) {
      edges.add(edge);
    }

    public List<Edge> getEdgesFromVertex(Vertex vertex) {
      List<Edge> result = new ArrayList<>();
      for (Edge edge : edges) {
        if (edge.getSource() == vertex || edge.getDest() == vertex) {
          result.add(edge);
        }
      }
      return result;
    }

    public void remove(Edge edge) {
      edges.remove(edge);
    }
  }