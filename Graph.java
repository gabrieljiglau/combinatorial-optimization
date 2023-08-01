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

    public void addEdge(Vertex source,Vertex target,double distance){
        Edge edge = new Edge(distance,source,target);
        edgeList.add(edge);

        source.addNeighbour(target,edge);
        target.addNeighbour(source,edge);
    }

    public double solveTsp(List<Vertex> vertexList){

        Vertex clonedStartingVertex;
        List<Vertex> vertices = new ArrayList<>();

        Vertex lastVertex;
        double distanceFromLastToFirst = 0;

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
                    lastVertex = closestNeighbour;
                    vertices.add(closestNeighbour);
                } else {
                    lastVertex = closestNeighbour;
                }

                System.out.println("lastVertex is " + lastVertex.getName());
            }

        }
        vertices.add(clonedStartingVertex);
        System.out.println("distanceFromLastToFirst is " + distanceFromLastToFirst);
        printOrderingOfVertices(vertices);
        return getDistanceFromOrdering(vertices);
    }

    private double getDistanceFromOrdering(List<Vertex> vertexList){

        double distance = 0;

        for(int i = 0; i < vertexList.size() -1; i++){
            Vertex current = vertexList.get(i);
            Vertex next = vertexList.get(i +1);

            distance += getDistanceFromLastToFirst(next,current);
            System.out.print(getDistanceFromLastToFirst(next,current) + " ");
        }

        return distance;
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
                System.out.print(vertex.getName() + "->");
            } else {
                System.out.println(vertex.getName());
            }
        }
    }


    private double getMinEdge(List<Edge> edges){

        edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge edge1, Edge edge2) {
                return Double.compare(edge1.getDistance(),edge2.getDistance());
            }
        });

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
