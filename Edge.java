package geneticAlgorithms;

public class Edge {

    private double distance;
    private Vertex firstVertex;
    private Vertex secondVertex;

    public Edge(double distance,Vertex firstVertex,Vertex secondVertex){
        this.distance = distance;
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
    }

    public boolean isConnectingVertices(Vertex vertex1, Vertex vertex2) {
        return (firstVertex == vertex1 && secondVertex == vertex2) || (firstVertex == vertex2 && secondVertex == vertex1);
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Vertex getFirstVertex() {
        return firstVertex;
    }

    public void setFirstVertex(Vertex firstVertex) {
        this.firstVertex = firstVertex;
    }

    public Vertex getSecondVertex() {
        return secondVertex;
    }

    public void setSecondVertex(Vertex secondVertex) {
        this.secondVertex = secondVertex;
    }
}
