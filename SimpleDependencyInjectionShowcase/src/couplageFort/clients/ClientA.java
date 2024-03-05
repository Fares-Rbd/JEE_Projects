package couplageFort.clients;

import couplageFort.services.ServiceB;

public class ClientA {
	ServiceB service;

	public void doSomething() {
		String info = service.getInfo();
		System.out.println(info);
	}
}
//COMMENTS:
//This method of writing the code
//is not recommended when the project
// becomes complex because client class
//  is hardly dependent on Service class, 
//  which means any changes in Service class 
//  induce the need for refactoring in Client 
//  class which becomes very bothersome when
//  the scope of the project becomes bigger so 
//  we must use Dependency injection in order to
//  loosen the coupling 
