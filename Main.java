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
        Vertex H = new Vertex("H");
        Vertex I = new Vertex("I");
        Vertex J = new Vertex("J");
        Vertex K = new Vertex("K");

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
        graph.addVertex(H);
        graph.addVertex(I);
        graph.addVertex(J);
        graph.addVertex(K);

        graph.addUndirectedEdge(A, H, 5);
        graph.addUndirectedEdge(A, I, 6);
        graph.addUndirectedEdge(A, J, 7);

        graph.addUndirectedEdge(B, H, 4);
        graph.addUndirectedEdge(B, I, 5);
        graph.addUndirectedEdge(B, J, 6);

        graph.addUndirectedEdge(C, H, 7);
        graph.addUndirectedEdge(C, I, 8);
        graph.addUndirectedEdge(C, J, 9);

        graph.addUndirectedEdge(D, H, 3);
        graph.addUndirectedEdge(D, I, 4);
        graph.addUndirectedEdge(D, J, 5);

        graph.addUndirectedEdge(E, H, 6);
        graph.addUndirectedEdge(E, I, 7);
        graph.addUndirectedEdge(E, J, 8);

        graph.addUndirectedEdge(F, H, 2);
        graph.addUndirectedEdge(F, I, 3);
        graph.addUndirectedEdge(F, J, 4);

        graph.addUndirectedEdge(G, H, 8);
        graph.addUndirectedEdge(G, I, 9);
        graph.addUndirectedEdge(G, J, 10);

        graph.addUndirectedEdge(A, B, 3);
        graph.addUndirectedEdge(A, C, 5);
        graph.addUndirectedEdge(A, D, 7);
        graph.addUndirectedEdge(A, E, 4);
        graph.addUndirectedEdge(A, F, 6);
        graph.addUndirectedEdge(A, G, 2);

        graph.addUndirectedEdge(B, C, 8);
        graph.addUndirectedEdge(B, D, 5);
        graph.addUndirectedEdge(B, E, 6);
        graph.addUndirectedEdge(B, F, 8);
        graph.addUndirectedEdge(B, G, 9);

        graph.addUndirectedEdge(C, D, 3);
        graph.addUndirectedEdge(C, E, 7);
        graph.addUndirectedEdge(C, F, 4);
        graph.addUndirectedEdge(C, G, 5);

        graph.addUndirectedEdge(D, E, 9);
        graph.addUndirectedEdge(D, F, 2);
        graph.addUndirectedEdge(D, G, 7);

        graph.addUndirectedEdge(E, F, 3);
        graph.addUndirectedEdge(E, G, 4);

        graph.addUndirectedEdge(F, G, 6);
        graph.addUndirectedEdge(A, K, 5);
        graph.addUndirectedEdge(B, K, 4);
        graph.addUndirectedEdge(C, K, 6);
        graph.addUndirectedEdge(D, K, 3);
        graph.addUndirectedEdge(E, K, 8);
        graph.addUndirectedEdge(F, K, 7);
        graph.addUndirectedEdge(G, K, 9);
        graph.addUndirectedEdge(H, K, 2);
        graph.addUndirectedEdge(I, K, 5);
        graph.addUndirectedEdge(J, K, 4);

        System.out.println("NN heuristic result :");
        List<Vertex> initialSolution = graph.solveTsp(vertexList);
        double minimumDistance = graph.getDistanceFromOrdering(initialSolution);
        System.out.println("minimum distance is " + minimumDistance);
        System.out.println();

        int start = 0;
        int end = initialSolution.size() -1;

        System.out.println("2-opt applied on generated solution by NN :");
        List<Vertex> optimisedTour = graph.twoOpt(initialSolution,start,end);
        System.out.println("minimumDistance from 2opt is " + graph.getDistanceFromOrdering(optimisedTour));
        System.out.println();

        int populationSize = 100;
        double crossoverRate = 0.13;
        double mutationRate = 0.09;
        GeneticGraph geneticGraph = new GeneticGraph(graph);
        GeneticTSP tsp = new GeneticTSP(populationSize,crossoverRate,mutationRate,geneticGraph);

        double newDistance = tsp.optimizeTour(geneticGraph);
        System.out.println("optimized distance by genetic algorithm : " + newDistance);

    }
}
