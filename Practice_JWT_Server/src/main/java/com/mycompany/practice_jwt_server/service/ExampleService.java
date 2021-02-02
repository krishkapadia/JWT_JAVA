package com.mycompany.practice_jwt_server.service;

import Entity.Tblcategory;
import Entity.Tblproduct;
import Entity.Tbluser;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/example")
public class ExampleService {

    @PersistenceContext(unitName = "myPu")
    EntityManager em;
    
    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public Collection<Tblproduct> getAllProduct() {
        return em.createNamedQuery("Tblproduct.findAll").getResultList();
    }
    
    @GET
    @Transactional
    @Path("/{categoryId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("User")
    public Collection<Tblproduct> getProductByCid(@PathParam("categoryId") int Cid) {
        Tblcategory c = em.find(Tblcategory.class, Cid);
        return em.createNamedQuery("Tblproduct.findByCategoryId").setParameter("categoryId", c).getResultList();
    }
    
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("Admin")
    public void insertUser(Tbluser u)
    {
        em.persist(u);
    }

}
