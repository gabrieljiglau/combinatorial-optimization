package geneticAlgorithms;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Vertex A = new Vertex("A");
        Vertex B = new Vertex("B");
        Vertex C = new Vertex("C");
        Vertex D = new Vertex("D");
        Vertex E = new Vertex("E");
        Vertex F = new Vertex("F");
        Vertex G = new Vertex("G");

        List<Vertex> vertexList = new ArrayList<>();
        List<Edge> edgesList = new ArrayList<>();
        Graph graph = new Graph(vertexList, edgesList);

        graph.addVertex(A);
        graph.addVertex(B);
        graph.addVertex(C);
        graph.addVertex(D);
        graph.addVertex(E);
        graph.addVertex(F);
        graph.addVertex(G);

        graph.addEdge(A, C, 3);
        graph.addEdge(B, C, 1);
        graph.addEdge(B, D, 1);
        graph.addEdge(A, B, 5);
        graph.addEdge(C, D, 2);
        graph.addEdge(A, E, 7);
        graph.addEdge(C, E, 4);
        graph.addEdge(D, E, 3);
        graph.addEdge(D, F, 2);
        graph.addEdge(F, G, 7);
        graph.addEdge(G, E, 6);
        graph.addEdge(B, G, 7);
        graph.addEdge(B, F, 3);
        graph.addEdge(E, F, 5);
        graph.addEdge(A, G, 8);
        graph.addEdge(D, G, 4);
        graph.addEdge(E, F, 5);
        graph.addEdge(A, G, 8);
        graph.addEdge(C, G, 6);//undirected edges */

        double minimumDistance = graph.solveTsp(vertexList);
        System.out.println("minimum distance is " + minimumDistance);
    }
}