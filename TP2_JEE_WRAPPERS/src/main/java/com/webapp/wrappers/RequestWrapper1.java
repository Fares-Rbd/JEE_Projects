package com.webapp.wrappers;

import java.io.*;
import jakarta.servlet.*;


public class RequestWrapper1 extends ServletRequestWrapper
{
    public RequestWrapper1 (ServletRequest req)
    {
        //calls the ServletRequestWrapper superclass's constructor i.e. ServletRequest.
        super (req);
    }

    public String getParameter (String str)
    {
        //Calling the superclass method i.e. ServletRequest's getParameter(String) method.
        String name = super.getParameter (str); //interception du username envoyé dans la requete
        
        //traitement sur le username recupéré
        if (name.equals (""))
        {
         name = "Please, enter your name in the form";
        }
        return name;
    }
}