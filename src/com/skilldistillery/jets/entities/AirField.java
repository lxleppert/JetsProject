package com.skilldistillery.jets.entities;

import java.util.List;

public class AirField {
    private List<Jets> fleet;
        
    public AirField() {
    }

    public AirField(List<Jets> fleet) {
        this.fleet = fleet;
    }
    
    public void displayFleet() {
        for (int i = 0; i < fleet.size(); i++) {
            System.out.println(i + 1 + ". " + fleet.get(i).toString());
        }
    }

    public List<Jets> getFleet() {
        return fleet;
    }

    public void setFleet(List<Jets> fleet) {
        this.fleet = fleet;
    }

    @Override
    public String toString() {
        return "AirField [fleet=" + fleet + "]";
    }
    
}
