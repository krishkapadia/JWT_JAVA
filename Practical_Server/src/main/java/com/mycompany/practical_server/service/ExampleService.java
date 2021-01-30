package com.mycompany.practical_server.service;

import Entity.Customer;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/example")
public class ExampleService {

    @PersistenceContext(unitName = "MyPu")
    EntityManager em;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)    
    public Collection<Customer> getCustomers() {
        return em.createNamedQuery("Customer.findAll").getResultList();
    }
    
    @GET
    @Path("/{condition}/{rat}")
    @Produces(MediaType.APPLICATION_JSON)    
    public Collection<Customer> getCustomersWithRating(@PathParam("condition")String condition,@PathParam("rat")int rat) {
        if(condition.equals("gt"))
        {
            return em.createNamedQuery("Customer.findByRatingGt").setParameter("rating", rat).getResultList();
        }
        else if(condition.equals("lt"))
        {
            return em.createNamedQuery("Customer.findByRatingLt").setParameter("rating", rat).getResultList();
        }
        else if(condition.equals( "gteq"))
        {
            return em.createNamedQuery("Customer.findByRatingGtEq").setParameter("rating", rat).getResultList();
        }
        else if(condition.equals ("lteq"))
        {
            return em.createNamedQuery("Customer.findByRatingLtEq").setParameter("rating", rat).getResultList();
        }
        else 
        {
            return em.createNamedQuery("Customer.findByRating").setParameter("rating", rat).getResultList();
        }
        //return em.createNamedQuery("Customer.findByRatingGt").setParameter("rating", 1).getResultList();
    }
    

}
