
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import Client.RegisterClient;
import Entity.Tbluser;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStoreHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.glassfish.soteria.identitystores.hash.Pbkdf2PasswordHashImpl;
import token.MyCredentials;

/**
 *
 * @author KRISH
 */
@Named(value = "loginRegisterBean")
@SessionScoped
public class loginRegisterBean implements Serializable {

    @Inject
    private IdentityStoreHandler identityStoreHandler;
    @Inject
    private MyCredentials mycredentials;
    @Inject
    @RestClient
    RegisterClient rc;

    String username, password;
    Pbkdf2PasswordHashImpl pbkd;

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

    private <T> List<T> convertSetToList(Set<T> set) {
        List<T> list = new ArrayList<>();
        for (T t : set) {
            list.add(t);
        }
        return list;
    }

    public loginRegisterBean() {
    }

    public String login() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession h = request.getSession();

        Credential credential = new UsernamePasswordCredential(username, new Password(password));
        CredentialValidationResult result = identityStoreHandler.validate(credential);

        System.out.println(result.getStatus());
        if (result.getStatus() == CredentialValidationResult.Status.VALID) {
            System.out.println("JWTAuthenticationMechanism - CreatingCredential credential1 = new UserNamePasswordCredential token");

            mycredentials.setSubject(username);
            mycredentials.setGroups(convertSetToList(result.getCallerGroups()));
            mycredentials.setLoginStatus("Login_Success");
            mycredentials.setStausMessage("Hello " + result.getCallerGroups().toString() + " Login Success !!");

//            h.setAttribute("user", "admin");
//            h.setAttribute("groups", convertSetToList(result.getCallerGroups()));
//            h.setAttribute("statusmessage", "Hello " + result.getCallerGroups().toString()+" Login Success !!");
//            h.setAttribute("status",  "Login_Success");
            System.out.println(result.getCallerGroups().toString());
            context.responseComplete();
            if (result.getCallerGroups().contains("Admin")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
//                h.setAttribute("logged-group", "Admin");
                return "/index.jsf?faces-redirect=true";
            } else if (result.getCallerGroups().contains("User")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
//                h.setAttribute("logged-group", "User");
                username = "";
                password = "";
                return "/index.jsf?faces-redirect=true";
            }

            return "/index.jsf?faces-redirect=true";
        } else {
            System.out.println("Login failed!");
            return "/login.jsf";
        }
    }

    public String logout() throws ServletException {
        System.out.println("In Log out");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.getSession().removeAttribute("logged-group");
        request.getSession().removeAttribute("businessid");

        request.logout();
        request.getSession().invalidate();
        //return "/user/Home.jsf";
        username = "";
        password = "";
        return "/login.jsf";
    }

    public String register() throws IOException {
        pbkd = new Pbkdf2PasswordHashImpl();
        System.out.println(pbkd.generate(password.toCharArray()));
        
        Tbluser u = new Tbluser();
        u.setUsername(username);
        u.setPassword(pbkd.generate(password.toCharArray()));
        u.setRole("User");
        rc.insertUser(u);
        username = "";
        password = "";
        FacesContext.getCurrentInstance().getExternalContext().redirect("login.jsf");
        return "/login.jsf";
    }
}
