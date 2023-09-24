package geneticAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TSPSolution {

    private List<Vertex> tourList;
    private List<Edge> edgeList;

    public TSPSolution(){
        this.tourList = new ArrayList<>();
        this.edgeList = new ArrayList<>();
    }

    public TSPSolution(List<Vertex> vertexList,List<Edge> edgeList){
        this.tourList = new ArrayList<>(vertexList);
        this.edgeList = new ArrayList<>(edgeList);
    }

    public List<Vertex> addPossibleMutation(double mutationRate,GeneticGraph graph,int populationSize) {
        List<Vertex> mutatedVertices = new ArrayList<>(tourList);
        if(tourList.size() == 0){
            return mutatedVertices;
        }
        double generatedNumber = Randomizer.getDoubleFromZeroToOne();

        if (generatedNumber < mutationRate) {
            if (tourList.size() <= 0) {
                while (tourList.size() == 0) {
                    TSPSolution newSolution = graph.generateNewSolution(populationSize);
                    tourList = newSolution.getTourList();
                }
            }
            int position1 = Randomizer.intBetween(1, Math.max(1, tourList.size() - 1));
            int position2 = Randomizer.intBetween(1, Math.max(1, tourList.size() - 1));

            while (position1 == position2) {
                position1 = Randomizer.intBetween(1, tourList.size() - 1);
            }

            if (position1 > position2) {
                int temp = position1;
                position1 = position2;
                position2 = temp;
            }

            List<Vertex> reversedVertices = reverseVerticesInRange(tourList, position1, position2);
            if (reversedVertices != null) {
                mutatedVertices.subList(position1, position2 + 1).clear();
                mutatedVertices.addAll(position1, reversedVertices);
            }
        }

        return mutatedVertices;
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

    public List<Vertex> getTourList() {
        return tourList;
    }

    public void setTourList(List<Vertex> tourList) {
        this.tourList = tourList;
    }

    public List<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(List<Edge> edgeList) {
        this.edgeList = edgeList;
    }
}
