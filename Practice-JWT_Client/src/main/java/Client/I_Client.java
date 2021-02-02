/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Entity.Tblproduct;
import Entity.Tbluser;
import java.util.Collection;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import token.GenerateToken;

/**
 *
 * @author KRISH
 */
@RegisterRestClient(baseUri = "http://localhost:8080/Practice_JWT_Server/rest/example")
public interface I_Client {
    default String generatetoken()
    {
        return "Bearer "+ GenerateToken.generateJWT();
    }
    
    @GET
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Tblproduct> getAllProduct();
    
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @ClientHeaderParam(name = "authorization" ,value = "{generatetoken}")
    public void insertUser(Tbluser u);
}
