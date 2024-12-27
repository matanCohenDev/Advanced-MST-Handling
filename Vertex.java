public class Vertex {
    private final int id;
    private Vertex parent;
    private boolean visited;

    public Vertex(int id) {
      this.id = id;
      this.visited = false;
    }

    public int getId() {
      return id;
    }

    public Vertex getParent() {
      return parent;
    }

    public void setParent(Vertex parent) {
      this.parent = parent;
    }

    public boolean isVisited() {
      return visited;
    }

    public void setVisited(boolean visited) {
      this.visited = visited;
    }
  }