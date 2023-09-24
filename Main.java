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

        /*Vertex EWR = new Vertex("EWR");
        Vertex SIN = new Vertex("SIN");
        Vertex TLV = new Vertex("TLV");
        Vertex HND = new Vertex("HND");
        Vertex ICN = new Vertex("ICN");
        Vertex JFK= new Vertex( "JFK");
        Vertex DEL = new Vertex("DEL");
        Vertex LGA = new Vertex("LGA");
        Vertex DOH = new Vertex("DOH");
        Vertex CDG = new Vertex("CDG");
        Vertex SAN = new Vertex("SAN");
        Vertex BUD = new Vertex("BUD");
        Vertex EYW = new Vertex("EYW");
        Vertex LHM = new Vertex("LHM");
        Vertex LHR = new Vertex("LHR");
        Vertex DSM = new Vertex("DSM");
        Vertex SFO = new Vertex("SFO");
        Vertex ORD = new Vertex("ORD");
        Vertex BGI = new Vertex("BGI");

        List<Vertex> newVertexList = new ArrayList<>();
        List<Edge> newEdgeList = new ArrayList<>();
        Graph newGraph = new Graph(newVertexList,newEdgeList);
        newGraph.addVertex(EWR);
        newGraph.addVertex(SIN);
        newGraph.addVertex(TLV);
        newGraph.addVertex(HND);
        newGraph.addVertex(SAN);
        newGraph.addVertex(BUD);
        newGraph.addVertex(EYW);
        newGraph.addVertex(ICN);
        newGraph.addVertex(LHM);
        newGraph.addVertex(BGI);
        newGraph.addVertex(JFK);
        newGraph.addVertex(DEL);
        newGraph.addVertex(LHR);
        newGraph.addVertex(ORD);
        newGraph.addVertex(SFO);
        newGraph.addVertex(DSM);
        newGraph.addVertex(CDG);
        newGraph.addVertex(DOH);
        newGraph.addVertex(LGA);

        newGraph.addDirectedEdge(EWR,HND,1);
        newGraph.addDirectedEdge(HND,ICN,2);
        newGraph.addDirectedEdge(ICN,JFK,2);
        newGraph.addDirectedEdge(HND,JFK,1);
        newGraph.addDirectedEdge(JFK,LGA,1);
        newGraph.addDirectedEdge(DSM,ORD,2);
        newGraph.addDirectedEdge(ORD,BGI,2);
        newGraph.addDirectedEdge(SFO,DSM,1);
        newGraph.addDirectedEdge(LHR,SFO,1);
        newGraph.addDirectedEdge(EYW,LHR,2);
        newGraph.addDirectedEdge(SAN,EYW,2);
        newGraph.addDirectedEdge(SFO,SAN,1);
        newGraph.addDirectedEdge(DEL,DOH,1);
        newGraph.addDirectedEdge(DEL,CDG,2);
        newGraph.addDirectedEdge(CDG,BUD,2);
        newGraph.addDirectedEdge(CDG,SIN,1);
        newGraph.addDirectedEdge(SIN,CDG,1);
        newGraph.addDirectedEdge(DEL,TLV,2);*/



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


        //System.out.println(newGraph.minEdgesForSpanningTree(19));
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