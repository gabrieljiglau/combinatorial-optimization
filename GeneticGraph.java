package geneticAlgorithms;

import java.util.*;

public class GeneticGraph {

    private Graph baseGraph; //composition
    private int numberOfNodes;
    private static final int STARTING_INDEX = 0;
    private TSPSolution currentSolution;


    public GeneticGraph(Graph baseGraph){
        this.numberOfNodes = baseGraph.getVertexList().size();
        this.currentSolution = new TSPSolution(baseGraph.getVertexList(),baseGraph.getEdgeList());
        this.baseGraph = baseGraph;
    }

    public double evaluateForNewSolution(TSPSolution solution) {
        List<Vertex> vertexList = solution.getTourList();
        double fitness = 0;
        double distanceBetweenVertices;

        List<Vertex> copyOfVertexList = new ArrayList<>(vertexList);

        for (int i = 0; i < copyOfVertexList.size() - 1; i++) {
            Vertex current = copyOfVertexList.get(i);
            Vertex next = copyOfVertexList.get(i + 1);

            distanceBetweenVertices = getDistanceBetweenVertices(next, current);
            if (distanceBetweenVertices < 0) {
                repairVertex(current, next);
            }
            fitness += distanceBetweenVertices;
        }

        return fitness;
    }


    private double getDistanceBetweenVertices(Vertex last, Vertex first){

        List<Edge> edges = last.getEdges();

        for(Edge e : edges){
            if(e.isConnectingVertices(last,first)){
                return e.getDistance();
            }
        }

        return -1;
    }

    public TSPSolution generateNewSolution(int populationSize){

        return initializeGeneration(populationSize).get(Randomizer.intLessThan(numberOfNodes -1));
    }

    public List<TSPSolution> initializeGeneration(int populationSize){

        List<TSPSolution> generation = new ArrayList<>(populationSize);
        List<Vertex> vertexList = currentSolution.getTourList();
        List<Vertex> verticesToShuffle = vertexList.subList(1,vertexList.size() -1);

        for(int i = 0; i < populationSize; i++) {

            List<Vertex> newVertexList;
            if(Randomizer.getDoubleFromZeroToOne() < 0.5){
                newVertexList = solveTsp(baseGraph.getVertexList());
            } else {
                List<Vertex> shuffledVertices = new ArrayList<>(verticesToShuffle);
                Collections.shuffle(shuffledVertices,new Random());

                newVertexList = new ArrayList<>();
                newVertexList.add(vertexList.get(0));
                newVertexList.addAll(shuffledVertices);
                newVertexList.add(vertexList.get(vertexList.size() -1));
                newVertexList.add(vertexList.get(0));
            }


            TSPSolution newTour = new TSPSolution(baseGraph.getVertexList(),baseGraph.getEdgeList());
            newTour.setTourList(newVertexList);

            generation.add(i, newTour);
        }

        for(int i = 0; i < vertexList.size() -1; i++) {
            Vertex current = vertexList.get(i);
            Vertex next = vertexList.get(i +1);

            if(!areVerticesConnected(current,next)) {
                repairVertex(current,next);
            }
        }

        currentSolution.setTourList(vertexList);
        return generation;
    }

    public List<Vertex> solveTsp(List<Vertex> vertexList){

        Vertex clonedStartingVertex;
        List<Vertex> vertices = new ArrayList<>();

        int cityNumber = 0;
        Vertex startingVertex = vertexList.get(cityNumber);
        clonedStartingVertex = startingVertex;
        startingVertex.setVisited(true);

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(startingVertex);

        while(!queue.isEmpty()){
            Vertex current = queue.remove();
            if(!vertices.contains(current)) {
                vertices.add(current);
            }

            double minEdgeDistance = Double.POSITIVE_INFINITY;
            Vertex closestNeighbour = null;

            for(Vertex neighbour : current.getNeighbours()) {
                if (!neighbour.isVisited()) {
                    double minEdgeDistanceForV = getMinEdge(neighbour.getEdges());
                    if (minEdgeDistanceForV < minEdgeDistance) {
                        minEdgeDistance = minEdgeDistanceForV;
                        closestNeighbour = neighbour;
                    }
                }
            }

            if (closestNeighbour != null) {
                queue.add(closestNeighbour);
                closestNeighbour.setVisited(true);

                if (closestNeighbour == startingVertex) {
                    vertices.add(closestNeighbour);
                }
            }

        }
        vertices.add(clonedStartingVertex);
        //printOrderingOfVertices(vertices);

        return vertices;
    }


    public void printOrderingOfVertices(List<Vertex> vertices){

        for(int i = 0; i < vertices.size(); i++){
            Vertex vertex = vertices.get(i);

            if(i < vertices.size() -1) {
                System.out.print(vertex.getName() + " -> ");
            } else {
                System.out.println(vertex.getName());
            }
        }
    }


    private double getMinEdge(List<Edge> edges){

        edges.sort(Comparator.comparingDouble(Edge::getDistance));

        return edges.get(0).getDistance();
    }

    private void repairVertex(Vertex first, Vertex second){

        for(Vertex neighbour : first.getNeighbours()){
            if(!neighbour.isVisited() && areVerticesConnected(neighbour,second)){
                return;
            }
        }

    }

    private boolean areVerticesConnected(Vertex first,Vertex second){

        for(Edge e : first.getEdges()){
            if(e.isConnectingVertices(first,second)){
                return true;
            }
        }

        return false;
    }

    public List<Vertex> getVertexListGraph() {
        return baseGraph.getVertexList();
    }

    public List<Edge> getEdgeListGraph() {
        return baseGraph.getEdgeList();
    }

    public static int getStartingIndex() {
        return STARTING_INDEX;
    }

    public TSPSolution getCurrentSolution() {
        return currentSolution;
    }

    public void setCurrentSolution(TSPSolution currentSolution) {
        this.currentSolution = currentSolution;
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public void setNumberOfNodes(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
    }
}
