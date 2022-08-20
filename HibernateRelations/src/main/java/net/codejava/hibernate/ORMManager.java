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
    	Boolean created;
    	if(d==null || emps==null) created=false;
    	else {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            d.setEmpleats(emps);
            session.save(d);
            session.getTransaction().commit();
            session.close();
            created = true;
    	}
        return created;
    }
    /*
     * Creates an employee with an Account
     */
    public Boolean CreateEmployee(Employee e, Account c) {
    	Boolean created;
    	if(e==null || c==null) created=false;
    	else {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
    		try {
        	e.setAcount(c);
            session.save(e);
            session.getTransaction().commit();
            created = true;
    		}
    		catch(Exception ex) {
    			ex.printStackTrace();
    			created = false;
    		}
    		finally {
                session.close();
    		}
    	}
    	return created;
    }
    /*
     * Return employees from a Department
     */
    public Set<Employee> ReadEmployeesFromDepartment(String depName)
    {
	    Set<Employee> employees = new HashSet<Employee>();
	    Department d = new Department();
	    Session session = sessionFactory.openSession();
	    session.beginTransaction();
	    String hql = "from Department where name like '%"+depName+"%'";
	    Query query = session.createQuery(hql);
	    d = (Department)query.getSingleResult();
	    if(d!=null) {
	        employees = d.getEmpleats();
	        }
	    else employees = null;
	    session.close();
	    return employees;
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
        String accNum;
    	Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hqlEmployee = "from Employee where NIFNIE like '%"+nifnie+"%'";
        Query query = session.createQuery(hqlEmployee);
        Employee employee = (Employee)query.getSingleResult();
        if(employee !=null) {
        accNum = employee.getAcount().getAcc_number();
        }
        else accNum = null;
        session.close();
        return accNum;
    }
    /*
     * Updates Employee Department
     */
    public Boolean UpdateEmployeeDepartment(int idEmployee,Department department) {
    	Boolean updated;
    	if(department == null) updated = false;
    	else {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
    		try {
                Employee e = session.get(Employee.class, idEmployee);
                if(e==null) updated=false;
                else {
                    e.setDepartment(department);
                    session.update(e);
                    session.getTransaction().commit();
                    updated = true;
                }
    		}
    		catch(Exception exc) {
    			exc.printStackTrace();
    			updated = false;
    		}
            finally {
                session.close();
            }
    	}
    	return updated;
    }
    /*
     * Gets Employees by a substring
     */
    public List<Employee> GetEmployeesFromLastName(String start){
    	List<Employee> employeeList = new ArrayList<Employee>();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Employee where last_name like '"+start+"%'";
        Query query = session.createQuery(hql);
        employeeList = query.getResultList();
        session.close();  
    	return employeeList;
    }
    /*
     * Gets all data from alll Employees
     */
    public List<String> GetAllDataFromEmployees(){
    	List<String> employeeInfoList = new ArrayList<String>();
    	Account a = null; Employee e = null;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "from Account a, Employee e where e.acount.id = a.id";
        Query query = session.createQuery(hql);
        List<Object[]> list = query.getResultList();
        for(Object[] row: list) {
            a = (Account)row[0];
            e = (Employee)row[1];
            employeeInfoList.add(e.getNifnie() + " - "+ e.getFirst_name()+","+e.getLast_name()+ " - "+ a.getAcc_number()+" - "+ e.getDepartment().getName());
        }
        session.close();
    	return employeeInfoList;
    }
}
