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

        graph.addEdge(A, B, 3);
        graph.addEdge(A, C, 5);
        graph.addEdge(A, D, 7);
        graph.addEdge(A, E, 4);
        graph.addEdge(A, F, 6);
        graph.addEdge(A, G, 2);

        graph.addEdge(B, C, 8);
        graph.addEdge(B, D, 5);
        graph.addEdge(B, E, 6);
        graph.addEdge(B, F, 8);
        graph.addEdge(B, G, 9);

        graph.addEdge(C, D, 3);
        graph.addEdge(C, E, 7);
        graph.addEdge(C, F, 4);
        graph.addEdge(C, G, 5);

        graph.addEdge(D, E, 9);
        graph.addEdge(D, F, 2);
        graph.addEdge(D, G, 7);

        graph.addEdge(E, F, 3);
        graph.addEdge(E, G, 4);

        graph.addEdge(F, G, 6);

        System.out.println("NN heuristic result :");
        List<Vertex> initialSolution = graph.solveTsp(vertexList);
        double minimumDistance = graph.getDistanceFromOrdering(initialSolution);
        System.out.println("minimum distance is " + minimumDistance);

        int start = 0;
        System.out.println();
        int end = initialSolution.size() -1;

        System.out.println("2-opt applied on generated solution by NN :");
        List<Vertex> optimisedTour = graph.twoOpt(initialSolution,start,end);
        System.out.println("minimumDistance from 2opt is " + graph.getDistanceFromOrdering(optimisedTour));

    }
}