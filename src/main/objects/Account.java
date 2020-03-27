package main.objects;

import java.util.Date;

public class Account {
    private String name;
    private int stock;
    private int drankTotal;
    private Date created;
    private double drankPerDay;
    private double drankPerMonth;


    public Account(String name){
        this.name = name;
        this.stock = 0;
        this.drankTotal = 0;
        this.drankPerDay = 0;
        this.drankPerMonth = 0;
        this.created = new Date();
    }
}
