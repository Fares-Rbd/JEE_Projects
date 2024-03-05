package couplageFaible.services;

import couplageFaible.interfaces.Service;

public class ServiceB implements Service {
	 
    @Override
    public String getInfo() {
        return "ServiceB's Info";
    }
}