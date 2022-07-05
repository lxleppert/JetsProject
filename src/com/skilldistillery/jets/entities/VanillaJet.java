package com.skilldistillery.jets.entities;

public class VanillaJet extends Jets {

    public VanillaJet() {
    }

    public VanillaJet(String model, double speed, int range, double price) {
        super(model, speed, range, price);
    }

    @Override
    public void fly() {
        System.out.printf("%s is Flying for up to %.1f minutes while at Mach %.1f\n", this.getModel(),
                super.flightTime(), this.getMachSpeed());
        System.out.println("    (" + this.toString() + ")");
    }

    @Override
    public double getMachSpeed() {
        return super.getSpeed() * 0.001303;
    }

    @Override
    public String toString() {
        return "VanillaJet: " + super.toString();
    }

}
