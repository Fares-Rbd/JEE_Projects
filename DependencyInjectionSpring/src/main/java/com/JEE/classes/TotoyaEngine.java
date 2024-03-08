package com.JEE.classes;
import com.JEE.interfaces.IEngine;
public class TotoyaEngine implements IEngine {
    String company;
    double cost;
    public double getCost()
    {
        return cost;
    }

    public void setCost(double cost)
    {
        cost = this.cost;
    }

    public String getCompany()
    {
        return company;
    }

    public void setCompany(String company)
    {
        this.company = company;
    }

    @Override
    public String importOrigin()
    {
        return "Japan";
    }

    @Override
    public double cost()
    {
        return cost;
    }
    @Override
    public String toString()
    {
        return "This is Engine object from: "
                + company;
    }
}
