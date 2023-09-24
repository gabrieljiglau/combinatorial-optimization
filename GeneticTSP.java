package geneticAlgorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GeneticTSP {

    private int populationSize;
    private double crossoverRate;
    private double mutationRate;
    private GeneticGraph graph;
    private List<TSPSolution> currentGeneration;
    private static final int STOP = 9;
    private final int numberOfElite;


    public GeneticTSP(int populationSize,double crossoverRate,double mutationRate,GeneticGraph graph){
        this.populationSize = populationSize;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.graph = graph;
        this.currentGeneration = graph.initializeGeneration(populationSize);
        this.numberOfElite = (int) (populationSize * 0.05);
    }


    public double optimizeTour(GeneticGraph geneticGraph){

        TSPSolution best = new TSPSolution();
        TSPSolution bestSolutionThisGeneration = new TSPSolution();
        currentGeneration = getCurrentGeneration();

        int generationNumber = 1;
        double bestFitnessScoreAllTime = Double.MAX_VALUE;
        double bestSolutionGenerationNumber = 0;

        while(true){
            double bestFitnessScoreThisGeneration = Double.MAX_VALUE;
            double totalFitnessThisGeneration = 0;

            for(TSPSolution tour : currentGeneration){
                double fitness = geneticGraph.evaluateForNewSolution(tour);
                totalFitnessThisGeneration += fitness;

                if(fitness < bestFitnessScoreThisGeneration && fitness != -1){
                    bestFitnessScoreThisGeneration = fitness;
                    bestSolutionThisGeneration = tour;
                }
            }

            if(bestFitnessScoreThisGeneration < bestFitnessScoreAllTime){
                bestFitnessScoreAllTime = bestFitnessScoreThisGeneration;
                bestSolutionGenerationNumber = generationNumber;
                best = bestSolutionThisGeneration;
            } else if( (generationNumber - bestSolutionGenerationNumber) > STOP){
                break;
            }


            List<TSPSolution> eliteIndividuals = selectElite(currentGeneration, numberOfElite);
            List<TSPSolution> nextGeneration = new ArrayList<>(eliteIndividuals);

            int index = 0;
            while(nextGeneration.size() < populationSize){

                TSPSolution parent1 = selectTour(totalFitnessThisGeneration);
                TSPSolution parent2 = selectTour(totalFitnessThisGeneration);

                List<TSPSolution> children = crossoverParents(parent1,parent2,graph.getNumberOfNodes());

                TSPSolution child1 = children.get(0);
                TSPSolution child2 = children.get(1);

                child1.addPossibleMutation(mutationRate,graph,populationSize);
                child2.addPossibleMutation(mutationRate,graph,populationSize);

                nextGeneration.add(index,child1);
                index++;
                nextGeneration.add(index,child2);
                index++;
            }

            currentGeneration = nextGeneration;
            generationNumber++;
        }
        
        geneticGraph.printOrderingOfVertices(best.getTourList());
        return geneticGraph.evaluateForNewSolution(best);
    }

    private List<TSPSolution> crossoverParents(TSPSolution parent1, TSPSolution parent2,int numberOfNodes){

        List<TSPSolution> children = new ArrayList<>();
        TSPSolution child1 = parent1;
        TSPSolution child2 = parent2;

        if(Randomizer.getDoubleFromZeroToOne() < crossoverRate) {

            int tourSize = graph.getNumberOfNodes();
            int crossoverPoint1 = Randomizer.intLessThan(tourSize);
            int crossoverPoint2 = Randomizer.intLessThan(tourSize);

            if (crossoverPoint1 > crossoverPoint2) {
                int temp = crossoverPoint1;
                crossoverPoint1 = crossoverPoint2;
                crossoverPoint2 = temp;
            }

            for (int i = crossoverPoint1; i < crossoverPoint2; i++) {
                while(i > parent1.getTourList().size() -1){
                    parent1 = graph.generateNewSolution(populationSize);
                    child1 = parent1;
                }

                while(i > parent2.getTourList().size() -1){
                    parent2 = graph.generateNewSolution(populationSize);
                    child2 = parent2;
                }
                Vertex vertexFromParent1 = parent1.getTourList().get(i);
                Vertex vertexFromParent2 = parent2.getTourList().get(i);

                child1.getTourList().set(i, vertexFromParent1);
                child2.getTourList().set(i, vertexFromParent2);
            }
        }

        children.add(child1);
        children.add(child2);

        return children;
    }

    private List<TSPSolution> selectElite(List<TSPSolution> generation, int numberOfElite) {
        generation.sort(Comparator.comparingDouble(graph::evaluateForNewSolution).reversed());
        return generation.subList(0, numberOfElite);
    }

    private TSPSolution selectTour(double totalFitnessThisGeneration){

        List<Double> cumulativeProbabilities = new ArrayList<>();

        for(TSPSolution currentTour : currentGeneration){
            double fitness = graph.evaluateForNewSolution(currentTour);
            double probability = fitness / totalFitnessThisGeneration;
            cumulativeProbabilities.add(probability);
        }

        double randomValue = Randomizer.getDoubleFromZeroToOne();
        double cumulativeProbability = 0;
        int selectedTourIndex = 0;

        for(int i = 0; i < cumulativeProbabilities.size(); i++){
            cumulativeProbability += cumulativeProbabilities.get(i);

            if(cumulativeProbability >= randomValue){
                selectedTourIndex = i;
                break;
            }
        }
        if(selectedTourIndex == 0){
            selectedTourIndex = Randomizer.intLessThan(cumulativeProbabilities.size() -1);
        }
        return currentGeneration.get(selectedTourIndex);
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public double getCrossoverRate() {
        return crossoverRate;
    }

    public void setCrossoverRate(double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public List<TSPSolution> getCurrentGeneration() {
        return currentGeneration;
    }

    public void setCurrentGeneration(List<TSPSolution> currentGeneration) {
        this.currentGeneration = currentGeneration;
    }

    public GeneticGraph getGraph() {
        return graph;
    }

    public void setGraph(GeneticGraph graph) {
        this.graph = graph;
    }

    public static int getSTOP() {
        return STOP;
    }
}
