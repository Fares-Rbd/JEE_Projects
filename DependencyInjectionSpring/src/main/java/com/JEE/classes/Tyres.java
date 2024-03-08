package com.JEE.classes;

public class Tyres {

    String name;
    String place;
    String message;

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getPlace()
    {
        return place;
    }
    public void setPlace(String place)
    {
        this.place = place;
    }
    public String getMessage()
    {
        return message;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }

    @Override
    public String toString()
    {
        return "This is Tyre object: "
                + name + " " + place
                + " " + message;
    }
}

