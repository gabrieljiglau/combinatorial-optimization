package geneticAlgorithms;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

    private String name;
    private List<Vertex> neighbours;
    private List<Edge> edges;
    private boolean isVisited;

    public Vertex(String name){
        this.name = name;
        this.neighbours = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public void addNeighbour(Vertex newVertex,Edge edge){
        neighbours.add(newVertex);
        edges.add(edge);
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Vertex> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Vertex> neighbours) {
        this.neighbours = neighbours;
    }
}
