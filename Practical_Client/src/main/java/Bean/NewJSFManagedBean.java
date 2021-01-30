/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Client.I_Client;
import Entity.Customer;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author KRISH
 */
@Named(value = "newJSFManagedBean")
@RequestScoped
public class NewJSFManagedBean {

    /**
     * Creates a new instance of NewJSFManagedBean
     */
    
    @Inject @RestClient I_Client rc;
    
    String Name,Email,Address,PhoneNo,Condition;
    Collection<Customer> cust = new ArrayList<Customer>();

    public Collection<Customer> getCust() {
        return cust;
    }

    public void setCust(Collection<Customer> cust) {
        this.cust = cust;
    }

    

    public String getCondition() {
        return Condition;
    }

    public void setCondition(String Condition) {
        this.Condition = Condition;
    }
    int Cid,Rating;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String PhoneNo) {
        this.PhoneNo = PhoneNo;
    }

    public int getCid() {
        return Cid;
    }

    public void setCid(int Cid) {
        this.Cid = Cid;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int Rating) {
        this.Rating = Rating;
    }
    
    
    public NewJSFManagedBean() {
    }
    
    public String getCustomers(){
        
        cust=rc.getCustomersWithRating(Condition, Rating);
        //System.out.print(Condition + " " + Rating);
        return "/index.jsf" ;
    }
    
    public Collection<Customer> getAllCustomer()
    {
        return rc.getCustomers();
    }
    
}
