/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Client.I_Client;
import Entity.Tblproduct;
import Entity.Tbluser;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;

/**
 *
 * @author KRISH
 */
@Named(value = "newJSFManagedBean")
@SessionScoped
public class NewJSFManagedBean implements Serializable {
        
    @Inject @RestClient I_Client rc;
    String username,password,CategoryName,ProductName,msg="";

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    int CategoryId,ProductId;
    Collection<Tblproduct> pList = new ArrayList<Tblproduct>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int CategoryId) {
        this.CategoryId = CategoryId;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int ProductId) {
        this.ProductId = ProductId;
    }

    public Collection<Tblproduct> getpList() {
        return pList;
    }

    public void setpList(Collection<Tblproduct> pList) {
        this.pList = pList;
    }
    
    public NewJSFManagedBean() {
    }
    public String addUser() throws IOException
    {        
        try
        {
            Tbluser u = new Tbluser();
            u.setUsername(username);
            u.setPassword(password);
            u.setRole("Admin");
            rc.insertUser(u);
            username = "";
            password = "";        
            return "/login.jsf";
        }catch(Exception e)
        {
            msg="Authorization Fail..";
            return "/login.jsf";
        }
        
    }
    
    
    
    public Collection<Tblproduct> allProducts()
    {
        try
        {
            return rc.getAllProduct();
        }catch(Exception e)
        {
            msg="Authorization Fail..";
            return null;
        }
        
    }
}
