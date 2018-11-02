package com.example.daryl.error404;

public class Service {
    String name;
    double price;

    public Service (String n, double p){
        name=n;
        price=p;
    }

    public void setName(String n){name=n;}
    public void setPrice(double price) { this.price = price; }
    public String getName(){return name;}
    public String getPrice() { return Double.toString(price); }
}
