/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Entity.Customer;
import java.util.Collection;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/**
 *
 * @author KRISH
 */
@RegisterRestClient(baseUri = "http://localhost:8080/Practical_Server/rest/example")
public interface I_Client {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)    
    public Collection<Customer> getCustomers();
        
    @GET
    @Path("/{condition}/{rat}")
    @Produces(MediaType.APPLICATION_JSON)    
    public Collection<Customer> getCustomersWithRating(@PathParam("condition")String condition,@PathParam("rat")int rat) ;
}
