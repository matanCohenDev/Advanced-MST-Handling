
public class Edge {
    private final Vertex source;
    private final Vertex dest;
    private final int weight;

    public Edge(Vertex source, Vertex dest, int weight) {
      this.source = source;
      this.dest = dest;
      this.weight = weight;
    }

    public Vertex getSource() {
      return source;
    }

    public Vertex getDest() {
      return dest;
    }

    public int getWeight() {
      return weight;
    }
  }