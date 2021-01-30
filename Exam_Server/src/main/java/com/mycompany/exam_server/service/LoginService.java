/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.exam_server.service;

import Entity.Tbluser;
import java.util.Collection;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author KRISH
 */
@Path("/login")
public class LoginService {
    
    @PersistenceContext(unitName = "MyPu")
    EntityManager em ;
    
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertUser(Tbluser u)
    {
        em.persist(u);
    }
}
