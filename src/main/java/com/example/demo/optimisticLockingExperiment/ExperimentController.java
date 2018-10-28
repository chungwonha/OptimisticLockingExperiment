/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.optimisticLockingExperiment;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Chung2
 */
@Controller
@RequestMapping("expcontroller")
public class ExperimentController {

    @Autowired
    CustomerRepository repository;

    private Customer c0;
    
    
    @RequestMapping("init")
    public String init() {
        System.out.println("**********************************init");
        repository.save(new Customer("Jack", "Bauer"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        List<Customer> customers = (List<Customer>) this.repository.findAll();

        for (Customer customer : customers) {
            System.out.println(customer.toString());
        }
        
        c0=customers.get(0);
        System.out.println("now c0 is referenced as customers.get(0)");
        
        System.out.println("****************************************");
        
                return "index.html";
    }

    @RequestMapping("updatecustomer")
    public String updateCustomer() {
        System.out.println("****************************updateCustomer");
//        List<Customer> customers = (List<Customer>) this.repository.findAll();
//
//            Customer cust1 = customers.get(0);
//            System.out.println("cust1.getId(): "+cust1.getId()+" cust1.getVersion() : "+cust1.getVersion());
            
     
             Timestamp ts = new Timestamp(new Date().getTime());
 
            c0.setFirstName("DIFFERENTFIRSTNAME_"+ts);
            
//            if(cust1.getVersion()==0){
//                c0 = cust1;
//            }
            
        System.out.println("first name updated!!");
        System.out.println("*******************************************");
        return "index.html";
    }
    
    @RequestMapping("printcustomer")
    public String printCustomer() {
        System.out.println("*******************************printCustomer");
        List<Customer> customers = (List<Customer>) this.repository.findAll();

        Customer cust1 = customers.get(0);
        System.out.println("from DB");
        System.out.println("cust1.getId(): "+cust1.getId()+" cust1.getVersion() : "+cust1.getVersion());
        System.out.println("cust1.getFirstName(): "+cust1.getFirstName());
        System.out.println("");
        System.out.println("Ones locally referenced");
         if(c0 != null)
           System.out.println("c0.getFirstName(): "+c0.getFirstName()+" c0.getVersion() : "+c0.getVersion());
           
        System.out.println("****************************************");
        return "index.html";
    }
    
    @RequestMapping("savecustomer0")
    public String saveCustomer0(){
        System.out.println("******************************savecustomer");
        System.out.println("c0.getId(): "+c0.getId()+" c0.getVersion() : "+c0.getVersion());
        this.repository.save(c0);
        System.out.println("****************************************");
        return "index.html";
    }
    
    @RequestMapping("savecustomerwithoutlockingexception")
    public String saveCustomerWithoutLockingException(){
        System.out.println("******************************savecustomer WITHOUT LOCKING Exception");
        System.out.println("c0.getId(): "+c0.getId()+" c0.getVersion() : "+c0.getVersion());
        List<Customer> customers = (List<Customer>) this.repository.findAll();
        Customer customer = customers.get(0);
        customer.setFirstName(c0.getFirstName());
        customer.setLastName(c0.getLastName());
        
        this.repository.save(customer);
        System.out.println("****************************************");
        return "index.html";
    }
    @RequestMapping("readme")
    public String readme(){
        return "readme";
    }
}
