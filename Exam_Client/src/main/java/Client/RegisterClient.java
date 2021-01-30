/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Entity.Tbluser;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import token.GenerateToken;

/**
 *
 * @author KRISH
 */
@RegisterRestClient(baseUri = "http://localhost:8080/Exam_Server/rest/login")
public interface RegisterClient {

    default String generateJWTToken() {
        String token = "Bearer " + GenerateToken.generateJWT();
        System.out.println("Product Token = " + token);
        return token;
    }

    @POST
    //@ClientHeaderParam(name="authorization", value="{generateJWTToken}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertUser(Tbluser u);
}
