package com.johnny.pack.age;

public class Movie {
    String title;
    int year;
    double price;

    public Movie(String title, int year, double price) {
        this.title = title;
        this.year = year;
        this.price = price;
    }

    @Override
    public String toString() {
        return title + "\t" + year + "\t" + price;
    }
}
