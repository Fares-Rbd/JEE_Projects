package implementationCouplageFort;

import couplageFaible.interfaces.Service;
import couplageFaible.services.ServiceB;
import couplageFaible.services.ServiceC;
import couplageFaible.services.ServiceD;
import couplageFaible.clients.ClientA;
import couplageFaible.interfaces.Client;

public class ExempleCouplageFort {

	public static void main(String[] args) {
		Service service1 = new ServiceB();
		Client client1 = new ClientA(service1);
		client1.doSomething();

		Service service2 = new ServiceC();
		Client client2 = new ClientA(service2);
		client2.doSomething();

		Service service3 = new ServiceD();
		Client client3 = new ClientA(service3);
		client3.doSomething();

		((ClientA) client1).setService(new ServiceC());
		client1.doSomething();
	}
}

//COMMENT:
//In this example implementation we created 3 clients, 
//all of them are of type Client(our client interface)
//then we set a different service for each client using 
//constructor injection.
//and lastly we showcased the use of setter injection by casting 
//our client1 variable to the ClientA class (in order to use the setService method)
//in order to change client1's service from serviceB to serviceC



