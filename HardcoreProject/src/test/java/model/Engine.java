package model;

public class Engine {

    private String numberOfInstances;

    public Engine (String numberOfInstance){
        this.numberOfInstances = numberOfInstance;
    }
    public String getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(String numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }
}
