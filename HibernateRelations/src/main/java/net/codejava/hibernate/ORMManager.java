package net.codejava.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class ORMManager {
	protected SessionFactory sessionFactory;

	
    protected void setup() {
    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    	        .configure() // configures settings from hibernate.cfg.xml
    	        .build();
    	try {
    	    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    	} catch (Exception ex) {
    	    StandardServiceRegistryBuilder.destroy(registry);
    	    ex.printStackTrace();
    	}
    }
 
    protected void exit() {
        // code to close Hibernate Session factory
    	sessionFactory.close();
    }
    /*
     * Creates an account setting the balance
     */
    public Account CreateAccount(String accNumber, double balance) {
    	Account account = new Account();
    	if(accNumber==null) account=null;
    	else {
            	account.setAcc_number(accNumber);
            	account.setBalance(balance);
                Session session = sessionFactory.openSession();
                session.beginTransaction();
                //session.save(entityName, object);
                session.save(account);
                session.getTransaction().commit();
                session.close();
    	}
    	return account;
    }
    /*
     * Creates a Department and assignees to the employees
     */
    public Boolean CreateEmployeesAndDepartment(Department d, Set<Employee> emps) {
    	Boolean creats;
    	if(d==null || emps==null) creats=false;
    	else {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            d.setEmpleats(emps);
            session.save(d);
            session.getTransaction().commit();
            session.close();
            creats = true;
    	}
        return creats;
    }
    /*
     * Creates an employee with an Account
     */
    public Boolean CreateEmployee(Employee e, Account c) {
    	Boolean creat;
    	if(e==null || c==null) creat=false;
    	else {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
    		try {
        	e.setAcount(c);
            session.save(e);
            session.getTransaction().commit();
            creat = true;
    		}
    		catch(Exception ex) {
    			ex.printStackTrace();
    			creat = false;
    		}
    		finally {
                session.close();
    		}
    	}
    	return creat;
    }
    /*
     * Return employees from a Department
     */
    public Set<Employee> ReadEmployeesFromDepartment(String depName)
    {
	    Set<Employee> empleats = new HashSet<Employee>();
	    Department d = new Department();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    String hql = "from Department where name like '%"+depName+"%'";
	    Query query = session.createQuery(hql);
	    d = (Department)query.getSingleResult();
	    if(d!=null) {
	        empleats = d.getEmpleats();
	        }
	    else empleats = null;
	    session.close();
	    return empleats;
    }
    /*
     * Gets a Department by it's ID
     */
    public Department ReadDepartment(int id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Department d = session.get(Department.class, id);
        session.close();
        return d;
    }
    /*
     * Get a Account from an Employee by the VAT Number
     */
    public String GetAccountNumberFromEmployee(String nifnie) {
        String numCompte;
    	Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hqlEmpleat = "from Employee where NIFNIE like '%"+nifnie+"%'";
        Query query = session.createQuery(hqlEmpleat);
        Employee employee = (Employee)query.getSingleResult();
        if(employee !=null) {
        numCompte = employee.getAcount().getAcc_number();
        session.close();
        }
        else numCompte = null;
        return numCompte;
    }
    /*
     * Updates Employee Department
     */
    public Boolean UpdateEmployeeDepartment(int idEmployee,Department department) {
    	Boolean actualitzat;
    	if(department == null) actualitzat = false;
    	else {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
    		try {
                Employee e = session.get(Employee.class, idEmployee);
                if(e==null) actualitzat=false;
                else {
                    e.setDepartment(department);
                    session.update(e);
                    session.getTransaction().commit();
                    actualitzat = true;
                }
                session.close();
    		}
    		catch(Exception e) {
    			e.printStackTrace();
    			actualitzat = false;
    			session.close();
    		}
    	}
    	return actualitzat;
    }
    /*
     * Gets Employees by a substring
     */
    public List<Employee> GetEmployeesFromLastName(String start){
    	List<Employee> llistaEmpleats = new ArrayList<Employee>();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Employee where last_name like '"+start+"%'";
        Query query = session.createQuery(hql);
        llistaEmpleats = query.getResultList();
        session.close();  
    	return llistaEmpleats;
    }
    /*
     * Gets all data from alll Employees
     */
    public List<String> GetAllDataFromEmployees(){
    	List<String> llistaInfoEmpleats = new ArrayList<String>();
    	Account a = null; Employee e = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Account a, Employee e where e.acount.id = a.id";
        Query query = session.createQuery(hql);
        List<Object[]> llista = query.getResultList();
        for(Object[] fila: llista) {
            a = (Account)fila[0];
            e = (Employee)fila[1];
            llistaInfoEmpleats.add(e.getNifnie() + " - "+ e.getFirst_name()+","+e.getLast_name()+ " - "+ a.getAcc_number()+" - "+ e.getDepartment().getName());
        }
        session.close();
    	return llistaInfoEmpleats;
    }
}
