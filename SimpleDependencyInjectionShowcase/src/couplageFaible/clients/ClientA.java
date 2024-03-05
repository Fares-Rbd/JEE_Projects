package couplageFaible.clients;

import couplageFaible.interfaces.Client;
import couplageFaible.interfaces.Service;

public class ClientA implements Client {
    
    Service service;
     
    public ClientA(Service service) {//injecting dependency in the constructor 
    	  								//=> Constructor Injection
        this.service = service;
    }
 
    @Override
    public void doSomething() {
         
        String info = service.getInfo();
        System.out.println(info);
         
    }
    public void setService(Service service) { //setter injection
        this.service = service;
    }
    
}
//COMMENTS:
//this client now implements the Client class and has a variable of type Service (the interface)
//all we have to do to loosen the dependency (compared to the previous implementation of ClientA and ServiceB)
//is to inject what service we want this client to use inside of our client constructor, doing this means that
//our client is not bound to a specific type of service and is not affected when said service's code is changed 