package net.codejava.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ORMTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nifnie="40316811X";
		String start="La";
		String numCompte = "ES2898987899";
		Set<Employee> empleats = new HashSet<Employee>();
		Employee empleat1 = new Employee();
		Employee empleat2 = new Employee();
		Employee empleat3 = new Employee();
		Employee e = new Employee();
		e.setEmail("a2006299@institutmontilivi.cat");
		e.setFirst_name("Ferrán");
		e.setLast_name("Martínez");
		e.setNifnie("45677850F");
		Account c = new Account();
		Account account1 = new Account();
		Account account2 = new Account();
		Account account3 = new Account();
		Department d = new Department();
		d.setName("Administració");
		d.setId(30);
		double saldo = 10000.27;
		e.setDepartment(d);
		empleat1.setDepartment(d);
		empleat1.setEmail("a2006271@institutmontilivi.cat");
		empleat1.setFirst_name("Roberto");
		empleat1.setLast_name("Betancourt");
		empleat1.setNifnie("43313573F");
		empleat2.setDepartment(d);
		empleat2.setEmail("a2006279@institutmontilivi.cat");
		empleat2.setFirst_name("Martin");
		empleat2.setLast_name("Clayton");
		empleat2.setNifnie("43313575X");
		empleat3.setDepartment(d);
		empleat3.setEmail("a2106279@institutmontilivi.cat");
		empleat3.setFirst_name("Marina");
		empleat3.setLast_name("Fernandez");
		empleat3.setNifnie("43973575Z");
		//System.out.println(manager.CreateAccount(numCompte, saldo));
		ORMManager manager = new ORMManager();
		manager.setup();
		//account1 = manager.CreateAccount("ES1001", 4500);
		//account2 = manager.CreateAccount("ES1020",3000 );
		//account3 = manager.CreateAccount("ES203496", 2000);
		//empleat1.setAcount(account1);
		//empleat2.setAcount(account2);
		//empleat3.setAcount(account3);
		//empleats.add(empleat1);
		//empleats.add(empleat2);
		//empleats.add(empleat3);
		//empleat3.setAcount(account3);
		//if(manager.CreateEmployeesAndDepartment(d, empleats)) System.out.println("Departament i empleats creats amb èxit");
		//else System.out.println("Hi ha hagut problemes al crear el departament i els empleats");
		//c = manager.CreateAccount("1028", 5000);
		//if(manager.CreateEmployee(e, c)) System.out.println("Compte i empleat creats amb èxit");
		//else System.out.println("Problemes al crear el compte i l'empleat");
		//manager.ReadEmployeesFromDepartment("Administració");
		//System.out.println(manager.ReadDepartment(30));
		System.out.println(manager.GetAccountNumberFromEmployee(nifnie));
		//if(manager.UpdateEmployeeDepartment(20, d)) System.out.println("Actualitzat amb èxit");
		//else System.out.println("No s'ha pogut actualitzar");
		ShowEmpleats((ArrayList<Employee>) manager.GetEmployeesFromLastName(start));
		ShowStringResults((ArrayList<String>) manager.GetAllDataFromEmployees());
		manager.exit();
	}
	public static void ShowEmpleats(ArrayList<Employee> llistaObjectes) {
        Iterator<Employee> it = llistaObjectes.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
            System.out.println();
        }
    }
	public static void ShowResults(ArrayList<Object> llistaObjectes) {
        Iterator<Object> it = llistaObjectes.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
            System.out.println();
        }
	}
    public static void ShowStringResults(ArrayList<String> llistaStrings) {
        Iterator<String> it = llistaStrings.iterator();
        while(it.hasNext()) {
                System.out.println(it.next());
                System.out.println();
            }
    }

}
