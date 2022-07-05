package com.skilldistillery.jets.entities;

public abstract class Jets {
    private String model;
    private double speed;
    private int range;
    private double price;

    public Jets() {
    }

    public Jets(String model, double speed, int range, double price) {
        this.model = model;
        this.speed = speed;
        this.range = range;
        this.price = price;
    }

    public abstract double getMachSpeed();

    public abstract void fly();

    public double flightTime() {
        return (this.getRange() / this.getSpeed()) * 60;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "model=" + model + ", speed=" + speed + ", range=" + range + ", price=" + price;
    }

}
