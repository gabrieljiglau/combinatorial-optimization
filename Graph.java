package geneticAlgorithms;

import java.util.*;

public class Graph {

    private List<Vertex> vertexList;
    private List<Edge> edgeList;


    public Graph(List<Vertex> vertexList,List<Edge> edgeList){
        this.vertexList = vertexList;
        this.edgeList = edgeList;
    }


    public void addVertex(Vertex v){
        vertexList.add(v);
    }

    public List<Vertex> twoOpt(List<Vertex> currentTour,int start,int end){
        List<Vertex> bestTour = new ArrayList<>(currentTour);

        if(!currentTour.get(start).equals(currentTour.get(end))){
            return null;
        }

        boolean improvement = true;
        while(improvement){
            improvement = false;
            for(int i = 1; i < currentTour.size() -2; i++){
                for(int j = i+1; j < currentTour.size() -2; j++){
                    List<Vertex> newTour = twoOptSwap(currentTour,i,j);
                    if(newTour == null){
                        break;
                    }

                    if(getDistanceFromOrdering(newTour) < getDistanceFromOrdering(bestTour)){
                        if(getDistanceFromOrdering(newTour) < 0){
                            break;
                        } else {
                            bestTour = newTour;
                            improvement = true;
                        }
                    }
                }
            }
        }

        printOrderingOfVertices(bestTour);
        return bestTour;
    }

    private List<Vertex> twoOptSwap(List<Vertex> currentTour,int start,int end){

        List<Vertex> newTour = new ArrayList<>(currentTour);

        newTour.subList(start,end +1).clear();
        List<Vertex> reversedVertices = reverseVerticesInRange(currentTour,start,end);
        if(reversedVertices == null){
            return null;
        }

        newTour.addAll(start,reversedVertices);

        int lastIndex = newTour.size() -1;
        if(!newTour.get(0).equals(newTour.get(lastIndex))){
            return null;
        }

        return newTour;
    }

    public List<Vertex> reverseVerticesInRange(List<Vertex> vertices,int start,int end){

        List<Vertex> reversedVertices = new ArrayList<>();

        for(int i = end; i >= start; i--){
            reversedVertices.add(vertices.get(i));
        }

        for(int i = 0; i < reversedVertices.size() -1; i++){
            Vertex current = reversedVertices.get(i);
            Vertex next = reversedVertices.get(i +1);

            if(!areVerticesConnected(current,next)){
                return null;
            }
        }

        return reversedVertices;
    }


    public void addEdge(Vertex source,Vertex target,double distance){
        Edge edge = new Edge(distance,source,target);
        edgeList.add(edge);

        source.addNeighbour(target,edge);
        target.addNeighbour(source,edge);
    }

    public List<Vertex> solveTsp(List<Vertex> vertexList) {
        Vertex clonedStartingVertex;
        List<Vertex> vertices = new ArrayList<>();

        int cityNumber = 0;
        Vertex startingVertex = vertexList.get(cityNumber);
        clonedStartingVertex = startingVertex;
        startingVertex.setVisited(true);

        Queue<Vertex> queue = new LinkedList<>();
        queue.add(startingVertex);

        while (!queue.isEmpty()) {
            Vertex current = queue.remove();
            if (!vertices.contains(current)) {
                vertices.add(current);
            }

            double minEdgeDistance = Double.POSITIVE_INFINITY;
            Vertex closestNeighbour = null;

            for (Vertex neighbour : current.getNeighbours()) {
                if (!neighbour.isVisited()) {
                    double edgeDistance = getDistanceBetweenVertices(current, neighbour);
                    if (edgeDistance < minEdgeDistance) {
                        minEdgeDistance = edgeDistance;
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
        printOrderingOfVertices(vertices);

        return vertices;
    }

    public double getDistanceFromOrdering(List<Vertex> vertexList){

        double distance = 0;
        double distanceFromLastToFirst;
        for(int i = 0; i < vertexList.size() -1; i++){
            Vertex current = vertexList.get(i);
            Vertex next = vertexList.get(i +1);

            distanceFromLastToFirst = getDistanceFromLastToFirst(next,current);
            if(distanceFromLastToFirst < 0){
                return -1;
            }
            distance += distanceFromLastToFirst;
        }

        return distance;
    }

    private boolean areVerticesConnected(Vertex first,Vertex second){

        List<Edge> edgesFirstVertex = first.getEdges();

        boolean connected = false;
        for(Edge e : edgesFirstVertex){
            if(e.isConnectingVertices(first,second)){
                connected = true;
                break;
            }
        }

        return connected;
    }

    private double getDistanceFromLastToFirst(Vertex last,Vertex first){

        List<Edge> edges = last.getEdges();

        for(Edge e : edges){
            if(e.isConnectingVertices(last,first)){
                return e.getDistance();
            }
        }

        return -1;
    }

    private void printOrderingOfVertices(List<Vertex> vertices){

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


    public List<Vertex> getVertexList() {
        return vertexList;
    }

    public void setVertexList(List<Vertex> vertexList) {
        this.vertexList = vertexList;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(List<Edge> edgeList) {
        this.edgeList = edgeList;
    }
}
